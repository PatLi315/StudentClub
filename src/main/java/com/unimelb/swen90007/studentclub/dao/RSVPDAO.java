package com.unimelb.swen90007.studentclub.dao;

import com.unimelb.swen90007.studentclub.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RSVPDAO {

    private static final String INSERT_RSVP_SQL = "INSERT INTO rsvps (student_id, event_id) VALUES (?, ?)";
    private static final String DELETE_RSVP_SQL = "DELETE FROM rsvps WHERE student_id = ? AND event_id = ?";

    public void addRSVP(int studentId, int eventId) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_RSVP_SQL)) {
            preparedStatement.setInt(1, studentId);
            preparedStatement.setInt(2, eventId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeRSVP(int studentId, int eventId) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_RSVP_SQL)) {
            preparedStatement.setInt(1, studentId);
            preparedStatement.setInt(2, eventId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
