package medium;

import model.Student;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AttendanceService {
    
    public void addStudent(String name, String groupName, boolean isAttended) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            int groupId = getOrCreateGroup(conn, groupName);
            String insertQuery = "INSERT INTO students (name, group_id, is_attended) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {
                pstmt.setString(1, name);
                pstmt.setInt(2, groupId);
                pstmt.setBoolean(3, isAttended);
                pstmt.executeUpdate();
            }
        }
    }
    
    public List<Student> getStudents() throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            List<Student> students = new ArrayList<>();
            String query = "SELECT s.id, s.name, s.is_attended, g.group_name " +
                          "FROM students s JOIN groups g ON s.group_id = g.id " +
                          "ORDER BY s.name";
            try (PreparedStatement pstmt = conn.prepareStatement(query);
                 ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    students.add(new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("group_name"),
                        rs.getBoolean("is_attended")
                    ));
                }
            }
            return students;
        }
    }
    
    private int getOrCreateGroup(Connection conn, String groupName) throws SQLException {
        String selectQuery = "SELECT id FROM groups WHERE group_name = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(selectQuery)) {
            pstmt.setString(1, groupName);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        }
        
        String insertQuery = "INSERT INTO groups (group_name) VALUES (?) RETURNING id";
        try (PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {
            pstmt.setString(1, groupName);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        }
        throw new SQLException("Failed to create or retrieve group");
    }
}

