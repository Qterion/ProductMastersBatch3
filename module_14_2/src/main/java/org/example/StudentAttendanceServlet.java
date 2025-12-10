package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.StudentAttendanceDto;
import org.example.util.AttendanceNameUtil;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/attendance")
public class StudentAttendanceServlet extends HttpServlet {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "postgres";

    @Override
    public void init() throws ServletException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<StudentAttendanceDto> list = getStudentsFromDB();
        
        // Get user role from session
        jakarta.servlet.http.HttpSession session = req.getSession(false);
        String role = session != null ? (String) session.getAttribute("role") : null;
        String username = session != null ? (String) session.getAttribute("username") : null;
        boolean isStudent = "student".equalsIgnoreCase(role);
        boolean isAdmin = "admin".equalsIgnoreCase(role) || "teacher".equalsIgnoreCase(role);

        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<style>  table {\n" +
                "            width: 50%;\n" +
                "            border-collapse: collapse;\n" +
                "            margin: 20px 0;\n" +
                "            font-size: 18px;\n" +
                "            text-align: left;\n" +
                "        }\n" +
                "        th, td {\n" +
                "            border: 1px solid black;\n" +
                "            padding: 8px;\n" +
                "        }\n" +
                "        th {\n" +
                "            background-color: #f2f2f2;\n" +
                "        }");
        out.println("</style>");
        out.println("<body>");
        
        // Show user info and logout link
        if (username != null) {
            out.println("<div style='text-align: right; margin-bottom: 20px;'>");
            out.println("Logged in as: <strong>" + username + "</strong> (" + role + ") ");
            out.println("<a href='/ServletPractice/logout' style='margin-left: 15px; color: red;'>Logout</a>");
            out.println("</div>");
        }
        
        out.println("<h2>Посещение лекций</h2>");

        // Only show add form for admins/teachers, not for students
        if (!isStudent && isAdmin) {
            out.println("<form action='/ServletPractice/attendance' method='POST'>");
            out.println("ФИО: <input type='text' name='name' required><br>");
            out.println("Группа: <input type='text' name='groupName' required><br>");
            out.println("Посетил: <select name='isAttended'><option value='true'>Да</option><option value='false'>Нет</option></select><br>");
            out.println("<input type='submit' value='Добавить'>");
            out.println("</form>");
        } else if (isStudent) {
            out.println("<div style='background-color: #fff3cd; padding: 10px; margin-bottom: 20px; border: 1px solid #ffc107;'>");
            out.println("<strong>Student View:</strong> Вы можете просматривать таблицу, но не можете добавлять записи.");
            out.println("</div>");
        }

        out.println("<table>");
        out.println("    <tr>\n" +
                "            <th>ФИО</th>\n" +
                "            <th>Группа</th>\n" +
                "            <th>Посетил</th>\n" +
                "        </tr>");
        if (list.isEmpty()) {
            out.println("</table>");
            out.println("<h1>Нет данных в таблице<h1>");
        }
        for (StudentAttendanceDto studentAttendanceDto : list) {
            out.println("   <tr>\n" +
                    "            <td>" + studentAttendanceDto.getName() + "</td>\n" +
                    "            <td>" + studentAttendanceDto.getGroupName() + "</td>\n" +
                    "            <td>" + AttendanceNameUtil.fromBooleanToString(studentAttendanceDto.isAttended()) + "</td>\n" +
                    "        </tr>");
        }
        out.println("</table>");
    }

    private List<StudentAttendanceDto> getStudentsFromDB() {
        String sql = "Select * from students";
        List<StudentAttendanceDto> result = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                StudentAttendanceDto dto = StudentAttendanceDto.builder()
                        .name(rs.getString("name"))
                        .groupName(rs.getString("group_name"))
                        .isAttended(rs.getBoolean("is_attended"))
                        .build();
                result.add(dto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Check if user is admin/teacher (not student)
        jakarta.servlet.http.HttpSession session = req.getSession(false);
        String role = session != null ? (String) session.getAttribute("role") : null;
        boolean isStudent = "student".equalsIgnoreCase(role);
        
        if (isStudent) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Только администраторы могут добавлять записи");
            return;
        }
        
        String name = req.getParameter("name");
        String groupName = req.getParameter("groupName");
        boolean isAttended = Boolean.parseBoolean(req.getParameter("isAttended"));
        
        String sql = "INSERT INTO students (name, group_name, is_attended) VALUES (?, ?, ?)";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, groupName);
            stmt.setBoolean(3, isAttended);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            resp.getWriter().println("Ошибка при добавлении записи");
            return;
        }
        
        resp.sendRedirect("/ServletPractice/attendance");
    }
}
