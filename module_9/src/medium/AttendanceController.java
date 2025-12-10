package medium;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import util.DatabaseConnection;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/attendance")
public class AttendanceController extends HttpServlet {
    
    private AttendanceService attendanceService;
    
    @Override
    public void init() throws ServletException {
        super.init();
        attendanceService = new AttendanceService();
        try {
            DatabaseConnection.initializeNormalizedSchema();
        } catch (SQLException e) {
            throw new ServletException("DB init failed", e);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("/login");
            return;
        }
        
        String role = (String) session.getAttribute("role");
        if (!"ADMIN".equals(role)) {
            // Students cannot add data
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Only admins can add students");
            return;
        }
        
        String name = request.getParameter("name");
        String groupName = request.getParameter("groupName");
        String isAttendedParam = request.getParameter("isAttended");
        boolean isAttended = "on".equals(isAttendedParam) || "true".equals(isAttendedParam);
        
        try {
            attendanceService.addStudent(name, groupName, isAttended);
        } catch (SQLException e) {
            throw new ServletException("Save failed", e);
        }
        
        response.sendRedirect("/attendance");
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("/login");
            return;
        }
        
        String role = (String) session.getAttribute("role");
        String username = (String) session.getAttribute("username");
        
        request.setAttribute("role", role);
        request.setAttribute("username", username);
        request.setAttribute("isAdmin", "ADMIN".equals(role));
        
        try {
            request.setAttribute("students", attendanceService.getStudents());
        } catch (SQLException e) {
            throw new ServletException("Load failed", e);
        }
        request.getRequestDispatcher("/medium/attendance.jsp").forward(request, response);
    }
}

