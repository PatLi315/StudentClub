package com.unimelb.swen90007.studentclub.dao;

import com.unimelb.swen90007.studentclub.util.UnitOfWork;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAO {

    private static final String ADD_ADMIN_SQL = "INSERT INTO admins (student_id, club_id) VALUES (?, ?)";
    private static final String REMOVE_ADMIN_SQL = "DELETE FROM admins WHERE student_id = ? AND club_id = ?";
    private static final String CHECK_ADMIN_SQL = "SELECT COUNT(*) FROM admins WHERE student_id = ? AND club_id = ?";

    // Register the operation to add an admin in the UnitOfWork
    public void addAdmin(int studentId, int clubId, UnitOfWork unitOfWork) {
        Connection connection = unitOfWork.getConnection();
        unitOfWork.registerOperation(() -> {
            try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_ADMIN_SQL)) {
                preparedStatement.setInt(1, studentId);
                preparedStatement.setInt(2, clubId);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    // Register the operation to remove an admin in the UnitOfWork
    public void removeAdmin(int studentId, int clubId, UnitOfWork unitOfWork) {
        Connection connection = unitOfWork.getConnection();
        unitOfWork.registerOperation(() -> {
            try (PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_ADMIN_SQL)) {
                preparedStatement.setInt(1, studentId);
                preparedStatement.setInt(2, clubId);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    // Check if a student is an admin for a specific club
    public boolean isAdmin(int studentId, int clubId, UnitOfWork unitOfWork) throws SQLException {
        Connection connection = unitOfWork.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CHECK_ADMIN_SQL)) {
            preparedStatement.setInt(1, studentId);
            preparedStatement.setInt(2, clubId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;  // True if the student is an admin
            }
        }
        return false;
    }
}
