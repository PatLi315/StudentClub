package com.unimelb.swen90007.studentclub.dao;

import com.unimelb.swen90007.studentclub.model.Student;
import com.unimelb.swen90007.studentclub.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    private static final String INSERT_STUDENT_SQL = "INSERT INTO students (name, email, password) VALUES (?, ?, ?)";
    private static final String SELECT_STUDENT_BY_ID = "SELECT * FROM students WHERE id = ?";
    private static final String SELECT_ALL_STUDENTS = "SELECT * FROM students";
    private static final String UPDATE_STUDENT_SQL = "UPDATE students SET name = ?, email = ?, password = ? WHERE id = ?";
    private static final String DELETE_STUDENT_SQL = "DELETE FROM students WHERE id = ?";

    // Add a new student to the database
    public void addStudent(Student student) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENT_SQL)) {
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getEmail());
            preparedStatement.setString(3, student.getPassword());
            preparedStatement.executeUpdate();
        }
    }

    // Retrieve a student by ID
    public Student getStudentById(int id) throws SQLException {
        Student student = null;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENT_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                student = new Student(id, name, email, password);
            }
        }
        return student;
    }

    // Retrieve all students
    public List<Student> listAllStudents() throws SQLException {
        List<Student> students = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STUDENTS)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                students.add(new Student(id, name, email, password));
            }
        }
        return students;
    }

    // Update a student
    public void updateStudent(Student student) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STUDENT_SQL)) {
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getEmail());
            preparedStatement.setString(3, student.getPassword());
            preparedStatement.setInt(4, student.getId());
            preparedStatement.executeUpdate();
        }
    }

    // Delete a student by ID
    public void deleteStudent(int id) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STUDENT_SQL)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }
}
