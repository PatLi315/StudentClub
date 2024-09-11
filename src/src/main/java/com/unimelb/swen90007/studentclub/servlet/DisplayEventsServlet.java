package com.unimelb.swen90007.studentclub.servlet;

import com.unimelb.swen90007.studentclub.dao.EventDAO;
import com.unimelb.swen90007.studentclub.util.DatabaseConnection;
import com.unimelb.swen90007.studentclub.util.UnitOfWork;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import com.unimelb.swen90007.studentclub.model.Event;


public class DisplayEventsServlet extends HttpServlet {

    private EventDAO eventDAO;

    @Override
    public void init() {
        eventDAO = new EventDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try (Connection connection = DatabaseConnection.getConnection()) {
            UnitOfWork unitOfWork = new UnitOfWork(connection);

            // Get all events
            List<Event> events = eventDAO.listAllEvents(unitOfWork);

            // Set events in request attribute and forward to JSP
            request.setAttribute("events", events);
            request.getRequestDispatcher("displayEvents.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while fetching events.");
        }
    }
}
