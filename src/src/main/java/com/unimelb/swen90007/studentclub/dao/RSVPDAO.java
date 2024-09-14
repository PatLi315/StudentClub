package com.unimelb.swen90007.studentclub.dao;

import com.unimelb.swen90007.studentclub.model.RSVP;
import com.unimelb.swen90007.studentclub.util.UnitOfWork;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RSVPDAO {

    private static final String INSERT_RSVP_SQL = "INSERT INTO rsvps (student_id, event_id, tickets) VALUES (?, ?, ?)";
    private static final String DELETE_RSVP_SQL = "DELETE FROM rsvps WHERE student_id = ? AND event_id = ?";
    private static final String GET_RSVPS_BY_EVENT_SQL = "SELECT * FROM rsvps WHERE event_id = ?";

    public void addRSVP(RSVP rsvp, UnitOfWork unitOfWork) throws SQLException {
        Connection connection = unitOfWork.getConnection();
        unitOfWork.registerOperation(() -> {
            try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_RSVP_SQL)) {
                preparedStatement.setInt(1, rsvp.getStudentId());
                preparedStatement.setInt(2, rsvp.getEventId());
                preparedStatement.setInt(3, rsvp.getTickets());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void cancelRSVP(int studentId, int eventId, UnitOfWork unitOfWork) throws SQLException {
        Connection connection = unitOfWork.getConnection();
        unitOfWork.registerOperation(() -> {
            try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_RSVP_SQL)) {
                preparedStatement.setInt(1, studentId);
                preparedStatement.setInt(2, eventId);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public List<RSVP> getRSVPsForEvent(int eventId, UnitOfWork unitOfWork) throws SQLException {
        Connection connection = unitOfWork.getConnection();
        List<RSVP> rsvps = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_RSVPS_BY_EVENT_SQL)) {
            preparedStatement.setInt(1, eventId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int studentId = rs.getInt("student_id");
                int tickets = rs.getInt("tickets");

                rsvps.add(new RSVP(id, studentId, eventId, tickets));
            }
        }
        return rsvps;
    }
}
