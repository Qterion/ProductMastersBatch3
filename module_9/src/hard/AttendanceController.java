package hard;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
        
        String action = request.getParameter("action");
        
        if ("delete".equals(action)) {
            String studentId = request.getParameter("studentId");
            if (studentId != null) {
                try {
                    attendanceService.deleteStudent(Integer.parseInt(studentId));
                } catch (SQLException e) {
                    throw new ServletException("Delete failed", e);
                }
            }
            response.sendRedirect("/attendance");
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
        try {
            String selectedGroupId = request.getParameter("groupId");
            request.setAttribute("groups", attendanceService.getAllGroups());
            request.setAttribute("students", attendanceService.getStudents(selectedGroupId));
            request.setAttribute("selectedGroupId", selectedGroupId);
        } catch (SQLException e) {
            throw new ServletException("Load failed", e);
        }
        request.getRequestDispatcher("/hard/attendance.jsp").forward(request, response);
    }
}

