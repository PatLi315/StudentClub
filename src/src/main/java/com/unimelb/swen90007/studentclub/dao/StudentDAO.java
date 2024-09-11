package com.unimelb.swen90007.studentclub.dao;

import com.unimelb.swen90007.studentclub.model.Student;
import com.unimelb.swen90007.studentclub.util.UnitOfWork;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    private static final String INSERT_STUDENT_SQL = "INSERT INTO students (name, email, password) VALUES (?, ?, ?)";
    private static final String SELECT_STUDENT_BY_ID_SQL = "SELECT * FROM students WHERE id = ?";
    private static final String SELECT_ALL_STUDENTS_SQL = "SELECT * FROM students";
    private static final String AUTHENTICATE_STUDENT_SQL = "SELECT * FROM students WHERE email = ? AND password = ?";

    // Register the operation to add a student
    public void addStudent(Student student, UnitOfWork unitOfWork) {
        Connection connection = unitOfWork.getConnection();
        unitOfWork.registerOperation(() -> {
            try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENT_SQL)) {
                preparedStatement.setString(1, student.getName());
                preparedStatement.setString(2, student.getEmail());
                preparedStatement.setString(3, student.getPassword());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    // Retrieve a student by ID
    public Student getStudentById(int id, UnitOfWork unitOfWork) throws SQLException {
        Connection connection = unitOfWork.getConnection();
        Student student = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENT_BY_ID_SQL)) {
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

    // Authenticate a student
    public Student authenticateStudent(String email, String password, UnitOfWork unitOfWork) throws SQLException {
        Connection connection = unitOfWork.getConnection();
        Student student = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(AUTHENTICATE_STUDENT_SQL)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                student = new Student(id, name, email, password);
            }
        }
        return student;
    }

    // List all students
    public List<Student> listAllStudents(UnitOfWork unitOfWork) throws SQLException {
        List<Student> students = new ArrayList<>();
        Connection connection = unitOfWork.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STUDENTS_SQL)) {
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
}
