package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/studentsdb";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "postgres";
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
    
    /**
     * Initializes database with simple schema (for EASY task)
     * students table with group_name column
     */
    public static void initializeSimpleSchema() throws SQLException {
        try (Connection conn = getConnection()) {
            String createTable = """
                CREATE TABLE IF NOT EXISTS students (
                    id SERIAL PRIMARY KEY,
                    name VARCHAR(100) NOT NULL,
                    group_name VARCHAR(50) NOT NULL,
                    is_attended BOOLEAN NOT NULL
                )
                """;
            conn.createStatement().executeUpdate(createTable);
        }
    }
    
    /**
     * Initializes database with normalized schema (for MEDIUM and HARD tasks)
     * Separate groups table with foreign key relationship
     */
    public static void initializeNormalizedSchema() throws SQLException {
        try (Connection conn = getConnection()) {
            // Создаем таблицу groups
            String createGroupsTable = """
                CREATE TABLE IF NOT EXISTS groups (
                    id SERIAL PRIMARY KEY,
                    group_name VARCHAR(50) NOT NULL UNIQUE
                )
                """;
            conn.createStatement().executeUpdate(createGroupsTable);
            
            // Создаем таблицу students с внешним ключом на groups
            String createStudentsTable = """
                CREATE TABLE IF NOT EXISTS students (
                    id SERIAL PRIMARY KEY,
                    name VARCHAR(100) NOT NULL,
                    group_id INTEGER NOT NULL,
                    is_attended BOOLEAN NOT NULL,
                    FOREIGN KEY (group_id) REFERENCES groups(id) ON DELETE CASCADE
                )
                """;
            conn.createStatement().executeUpdate(createStudentsTable);
        }
    }
}

