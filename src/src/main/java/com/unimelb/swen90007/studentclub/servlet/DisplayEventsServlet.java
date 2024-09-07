package com.unimelb.swen90007.studentclub.servlet;

import com.unimelb.swen90007.studentclub.dao.EventDAO;
import com.unimelb.swen90007.studentclub.model.Event;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import jakarta.servlet.http.HttpSession;

public class DisplayEventsServlet extends HttpServlet {

    private EventDAO eventDAO;

    @Override
    public void init() {
        eventDAO = new EventDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);  // Do not create a session
        if (session == null || session.getAttribute("student") == null) {
            // User not logged in, redirect to login page
            response.sendRedirect("login.jsp");
            return;
        }

        // Get search query (for future events)
        String search = request.getParameter("search");
        List<Event> events;

        try {
            if (search != null && !search.isEmpty()) {
                events = eventDAO.searchUpcomingEvents(search); // Implement this method in EventDAO
            } else {
                events = eventDAO.listAllEvents();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            events = null; // Handle properly in JSP
        }

        request.setAttribute("events", events);
        request.getRequestDispatcher("displayEvents.jsp").forward(request, response);
    }
}
