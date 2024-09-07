package com.unimelb.swen90007.studentclub.dao;

import com.unimelb.swen90007.studentclub.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdminDAO {
    private Connection connection;

    public AdminDAO() {
        connection = DatabaseConnection.getConnection();
    }

    public void addAdmin(int studentId, int clubId) {
        try {
            String sql = "INSERT INTO admins (student_id, club_id) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, studentId);
            statement.setInt(2, clubId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeAdmin(int studentId, int clubId) {
        try {
            String sql = "DELETE FROM admins WHERE student_id = ? AND club_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, studentId);
            statement.setInt(2, clubId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
