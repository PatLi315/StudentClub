package com.unimelb.swen90007.studentclub.dao;

import com.unimelb.swen90007.studentclub.model.Event;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventDAO {
    private String jdbcURL = "jdbc:postgresql://dpg-cqvbnfdds78s739ghsrg-a.oregon-postgres.render.com:5432/sda_agw5";
    private String jdbcUsername = "admin";
    private String jdbcPassword = "Cw2dSOz91dJ8TymtnKDgxcVHuj8dP2NJ";

    private static final String INSERT_EVENT_SQL = "INSERT INTO events (title, description) VALUES (?, ?)";
    private static final String SELECT_ALL_EVENTS_SQL = "SELECT * FROM events";

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void addEvent(Event event) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EVENT_SQL)) {
            preparedStatement.setString(1, event.getTitle());
            preparedStatement.setString(2, event.getDescription());
            preparedStatement.executeUpdate();
        }
    }

    public List<Event> listAllEvents() throws SQLException {
        List<Event> events = new ArrayList<>();
        try (Connection connection = getConnection();
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
