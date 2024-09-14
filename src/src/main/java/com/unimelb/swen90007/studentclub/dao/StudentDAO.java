package com.unimelb.swen90007.studentclub.dao;

import com.unimelb.swen90007.studentclub.model.Student;
import com.unimelb.swen90007.studentclub.util.DatabaseConnection;
import com.unimelb.swen90007.studentclub.util.UnitOfWork;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDAO {

    private static final String GET_STUDENT_BY_ID_SQL = "SELECT * FROM students WHERE id = ?";
    private static final String VALIDATE_LOGIN_SQL = "SELECT * FROM students WHERE username = ? AND password = ?";

    public Student getStudentById(int studentId, UnitOfWork unitOfWork) throws SQLException {
        Connection connection = unitOfWork.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_STUDENT_BY_ID_SQL)) {
            preparedStatement.setInt(1, studentId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String role = rs.getString("role");

                return new Student(id, name, email, password, role);
            }
        }
        return null;
    }

    public boolean validateLogin(String username, String password) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(VALIDATE_LOGIN_SQL)) {

            preparedStatement.setString(1, username); // Assuming login is done with a username
            preparedStatement.setString(2, password);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                return count == 1;  // Login successful if exactly one match is found
            }
        } catch (SQLException e) {
            throw new SQLException("Error validating login", e);
        }
        return false;
    }
}
