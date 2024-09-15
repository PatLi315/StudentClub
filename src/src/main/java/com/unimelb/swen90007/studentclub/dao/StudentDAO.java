package com.unimelb.swen90007.studentclub.dao;

import com.unimelb.swen90007.studentclub.model.Student;
import com.unimelb.swen90007.studentclub.util.UnitOfWork;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDAO {

    private static final String GET_STUDENT_BY_ID_SQL = "SELECT * FROM students WHERE id = ?";
    private static final String VALIDATE_LOGIN_SQL = "SELECT * FROM students WHERE username = ? AND password = ?";
    private static final String REGISTER_SQL = "INSERT INTO students (username, password, email, role) VALUES (?, ?, ?, ?)";
    private static final String CHECK_USERNAME_SQL = "SELECT COUNT(*) FROM students WHERE username = ?";
    private static final String GET_STUDENT_ID_SQL = "SELECT id FROM students WHERE username = ?";

    public Student getStudentByName(int studentName, UnitOfWork unitOfWork) throws SQLException {
        Connection connection = unitOfWork.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_STUDENT_BY_ID_SQL)) {
            preparedStatement.setInt(1, studentName);
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

    public int getStudentId(String studentName, UnitOfWork unitOfWork) throws SQLException {
        Connection connection = unitOfWork.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_STUDENT_ID_SQL)) {
            preparedStatement.setString(1, studentName);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");

                return id;
            }
        }
        return -1;
    }

    public void registerStudent(Student student, UnitOfWork unitOfWork) throws SQLException {
        Connection connection = unitOfWork.getConnection();

        unitOfWork.registerOperation(() -> {
            try (PreparedStatement preparedStatement = connection.prepareStatement(REGISTER_SQL)) {
                preparedStatement.setString(1, student.getName());
                preparedStatement.setString(2, student.getEmail());
                preparedStatement.setString(3, student.getPassword());
                preparedStatement.setString(4, student.getRole());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public boolean isUsernameTaken(String name, UnitOfWork unitOfWork) throws SQLException {
        Connection connection = unitOfWork.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CHECK_USERNAME_SQL)) {
            preparedStatement.setString(1, name);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }

    public Student validateLogin(String username, String password, UnitOfWork unitOfWork) throws SQLException {
        Connection connection = unitOfWork.getConnection(); // Get the connection from UnitOfWork
        final Student[] student = {null}; // Create an array to hold the student

        unitOfWork.registerOperation(() -> {
            try (PreparedStatement preparedStatement = connection.prepareStatement(VALIDATE_LOGIN_SQL)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);

                ResultSet rs = preparedStatement.executeQuery();

                if (rs.next()) {
                    // If login is successful, retrieve the student's data from the result set
                    int studentId = rs.getInt("id");
                    String name = rs.getString("username");
                    String email = rs.getString("email");
                    String role = rs.getString("role");

                    // Create and store a Student object
                    student[0] = new Student(studentId, name, email, role);
                }
            } catch (SQLException e) {
                throw new RuntimeException("Error validating login", e);
            }
        });

        unitOfWork.commit(); // Commit the transaction

        return student[0]; // Return the student object (null if invalid login)
    }

}
