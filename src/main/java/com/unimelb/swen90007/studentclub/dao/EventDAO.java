package com.unimelb.swen90007.studentclub.dao;

import com.unimelb.swen90007.studentclub.model.Event;
import com.unimelb.swen90007.studentclub.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventDAO {

    private static final String INSERT_EVENT_SQL = "INSERT INTO events (title, description) VALUES (?, ?)";
    private static final String SELECT_ALL_EVENTS_SQL = "SELECT * FROM events";

    public void addEvent(Event event) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EVENT_SQL)) {
            preparedStatement.setString(1, event.getTitle());
            preparedStatement.setString(2, event.getDescription());
            preparedStatement.executeUpdate();
        }
    }

    public List<Event> listAllEvents() throws SQLException {
        List<Event> events = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_EVENTS_SQL)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                events.add(new Event(id, title, description));
            }
        }
        return events;
    }
}
