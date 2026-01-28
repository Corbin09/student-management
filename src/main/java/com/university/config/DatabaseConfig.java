package com.university.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {

    private static final String URL = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    static {
        try (Connection conn = getConnection()) {
            conn.createStatement().execute("""
                CREATE TABLE IF NOT EXISTS students (
                    id VARCHAR(50) PRIMARY KEY,
                    name VARCHAR(100),
                    email VARCHAR(100),
                    gpa DOUBLE
                )
            """);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
