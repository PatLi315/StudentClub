package com.unimelb.swen90007.studentclub.dao;

import com.unimelb.swen90007.studentclub.model.Event;
import com.unimelb.swen90007.studentclub.util.UnitOfWork;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class EventDAO {

    private static final String INSERT_EVENT_SQL = "INSERT INTO events (title, description, event_date, club_id, capacity, venue) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String GET_EVENTS_FOR_CLUBS_SQL = "SELECT * FROM events WHERE club_id IN (SELECT club_id FROM memberships WHERE student_id = ?)";
    private static final String DELETE_EVENT_SQL = "DELETE FROM events WHERE id = ?";
    private static final String GET_EVENT_BY_ID_SQL = "SELECT * FROM events WHERE id = ?";
    private static final String UPDATE_EVENT_SQL = "UPDATE events SET title = ?, description = ?, event_date = ?, capacity = ?, venue = ? WHERE id = ?";

    // Add an event
    public void addEvent(Event event, UnitOfWork unitOfWork) throws SQLException {
        Connection connection = unitOfWork.getConnection();
        unitOfWork.registerOperation(() -> {
            try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EVENT_SQL)) {
                preparedStatement.setString(1, event.getTitle());
                preparedStatement.setString(2, event.getDescription());
                preparedStatement.setDate(3, event.getEventDate());
                preparedStatement.setInt(4, event.getClubId());
                preparedStatement.setInt(5, event.getCapacity());
                preparedStatement.setString(6, event.getVenue());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    // Get events for student clubs
    public List<Event> getEventsForStudentClubs(int studentId, UnitOfWork unitOfWork) throws SQLException {
        Connection connection = unitOfWork.getConnection();
        List<Event> events = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_EVENTS_FOR_CLUBS_SQL)) {
            preparedStatement.setInt(1, studentId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                Date eventDate = rs.getDate("event_date");
                int clubId = rs.getInt("club_id");
                int capacity = rs.getInt("capacity");
                String venue = rs.getString("venue");

                events.add(new Event(id, title, description, eventDate, clubId, capacity, venue));
            }
        }
        return events;
    }

    // Get event by ID using UnitOfWork
    public Event getEventById(int eventId, UnitOfWork unitOfWork) throws SQLException {
        Connection connection = unitOfWork.getConnection();
        AtomicReference<Event> event = null;

        unitOfWork.registerOperation(() -> {
            try (PreparedStatement preparedStatement = connection.prepareStatement(GET_EVENT_BY_ID_SQL)) {
                preparedStatement.setInt(1, eventId);
                ResultSet rs = preparedStatement.executeQuery();

                if (rs.next()) {
                    String title = rs.getString("title");
                    String description = rs.getString("description");
                    Date eventDate = rs.getDate("event_date");
                    int clubId = rs.getInt("club_id");
                    int capacity = rs.getInt("capacity");
                    String venue = rs.getString("venue");

                    event.set(new Event(eventId, title, description, eventDate, clubId, capacity, venue));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        return event.get();
    }

    // Update an event using UnitOfWork
    public void updateEvent(Event event, UnitOfWork unitOfWork) throws SQLException {
        Connection connection = unitOfWork.getConnection();
        unitOfWork.registerOperation(() -> {
            try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EVENT_SQL)) {
                preparedStatement.setString(1, event.getTitle());
                preparedStatement.setString(2, event.getDescription());
                preparedStatement.setDate(3, event.getEventDate());
                preparedStatement.setInt(4, event.getCapacity());
                preparedStatement.setString(5, event.getVenue());
                preparedStatement.setInt(6, event.getId());

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    // Delete an event using UnitOfWork
    public void deleteEvent(int eventId, UnitOfWork unitOfWork) throws SQLException {
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
}
