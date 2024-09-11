package com.unimelb.swen90007.studentclub.dao;

import com.unimelb.swen90007.studentclub.model.Event;
import com.unimelb.swen90007.studentclub.util.UnitOfWork;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventDAO {

    private static final String INSERT_EVENT_SQL = "INSERT INTO events (title, description, event_date, club_id) VALUES (?, ?, ?, ?)";
    private static final String SELECT_ALL_EVENTS_SQL = "SELECT * FROM events";
    private static final String DELETE_EVENT_SQL = "DELETE FROM events WHERE id = ?";
    private static final String UPDATE_EVENT_SQL = "UPDATE events SET title = ?, description = ?, event_date = ? WHERE id = ?";

    // Register the operation to add an event in the UnitOfWork
    public void addEvent(Event event, UnitOfWork unitOfWork) {
        Connection connection = unitOfWork.getConnection();
        unitOfWork.registerOperation(() -> {
            try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EVENT_SQL)) {
                preparedStatement.setString(1, event.getTitle());
                preparedStatement.setString(2, event.getDescription());
                preparedStatement.setDate(3, event.getEventDate());
                preparedStatement.setInt(4, event.getClubId());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    // Retrieve all events (this method doesn't modify data so no UnitOfWork needed)
    public List<Event> listAllEvents(UnitOfWork unitOfWork) throws SQLException {
        List<Event> events = new ArrayList<>();
        Connection connection = unitOfWork.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_EVENTS_SQL)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                Date eventDate = rs.getDate("event_date");
                int clubId = rs.getInt("club_id");
                events.add(new Event(id, title, description, eventDate, clubId));
            }
        }
        return events;
    }

    // Register the operation to delete an event in the UnitOfWork
    public void deleteEvent(int eventId, UnitOfWork unitOfWork) {
        Connection connection = unitOfWork.getConnection();
        unitOfWork.registerOperation(() -> {
            try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_EVENT_SQL)) {
                preparedStatement.setInt(1, eventId);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    // Register the operation to update an event in the UnitOfWork
    public void updateEvent(Event event, UnitOfWork unitOfWork) {
        Connection connection = unitOfWork.getConnection();
        unitOfWork.registerOperation(() -> {
            try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EVENT_SQL)) {
                preparedStatement.setString(1, event.getTitle());
                preparedStatement.setString(2, event.getDescription());
                preparedStatement.setDate(3, event.getEventDate());
                preparedStatement.setInt(4, event.getId());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    // Retrieve upcoming events matching a search query (no modification so no UnitOfWork needed)
    public List<Event> searchUpcomingEvents(String searchQuery, UnitOfWork unitOfWork) throws SQLException {
        List<Event> events = new ArrayList<>();
        String query = "SELECT * FROM events WHERE title ILIKE ? OR event_date >= CURRENT_DATE";
        Connection connection = unitOfWork.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, "%" + searchQuery + "%");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                Date eventDate = rs.getDate("event_date");
                int clubId = rs.getInt("club_id");
                events.add(new Event(id, title, description, eventDate, clubId));
            }
        }
        return events;
    }
}
