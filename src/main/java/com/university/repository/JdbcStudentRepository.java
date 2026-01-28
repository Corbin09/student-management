package com.university.repository;

import com.university.config.DatabaseConfig;
import com.university.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcStudentRepository implements StudentRepository {

    @Override
    public void save(Student student) {
        String sql = "INSERT INTO students (id, name, email, gpa) VALUES (?, ?, ?, ?)";

        try (
                Connection conn = DatabaseConfig.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, student.getId());
            ps.setString(2, student.getName());
            ps.setString(3, student.getEmail());
            ps.setDouble(4, student.getGpa());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean existsById(String id) {
        return false;
    }

    @Override
    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";

        try (
                Connection conn = DatabaseConfig.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                students.add(
                        new Student(
                                rs.getString("id"),
                                rs.getString("name"),
                                rs.getString("email"),
                                rs.getDouble("gpa")
                        )
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return students;
    }

    @Override
    public void deleteById(String id) {
        String sql = "DELETE FROM students WHERE id = ?";

        try (
                Connection conn = DatabaseConfig.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
