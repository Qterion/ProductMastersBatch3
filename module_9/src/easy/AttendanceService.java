package easy;

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
            String insertQuery = "INSERT INTO students (name, group_name, is_attended) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {
                pstmt.setString(1, name);
                pstmt.setString(2, groupName);
                pstmt.setBoolean(3, isAttended);
                pstmt.executeUpdate();
            }
        }
    }
    
    public List<Student> getStudents() throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            List<Student> students = new ArrayList<>();
            String query = "SELECT id, name, group_name, is_attended FROM students ORDER BY name";
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
}

