package com.unimelb.swen90007.jspdemo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/eventServlet")
public class AddRemoveEvent extends HttpServlet {

    private static final String JDBC_URL = "postgresql://swen90007_react_example_owner:hM8S2usBWZOnQBtiIM7gdZNC1k5CaUBc@dpg-cqvciotsvqrc73c0c5d0-a.singapore-postgres.render.com/swen90007_react_example_xxzg";
    private static final String JDBC_USER = "swen90007_react_example_owner";
    private static final String JDBC_PASSWORD = "hM8S2usBWZOnQBtiIM7gdZNC1k5CaUBc";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Event> eventList = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            String sql = "SELECT id, name FROM events";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                eventList.add(new Event(id, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("eventList", eventList);
        request.getRequestDispatcher("events.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            if ("add".equals(action)) {
                String eventName = request.getParameter("name");
                String sql = "INSERT INTO events (name) VALUES (?)";
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setString(1, eventName);
                statement.executeUpdate();
            } else if ("remove".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                String sql = "DELETE FROM events WHERE id = ?";
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("eventServlet");
    }
}

class Event {
    private int id;
    private String name;

    public Event(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

