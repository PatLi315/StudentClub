package com.unimelb.swen90007.studentclub.servlet;

import com.unimelb.swen90007.studentclub.dao.EventDAO;
import com.unimelb.swen90007.studentclub.model.Event;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;
import java.sql.Date;

public class AddEventServlet extends HttpServlet {

    private EventDAO eventDAO;

    @Override
    public void init() {
        eventDAO = new EventDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);  // Do not create a session
        if (session == null || session.getAttribute("student") == null) {
            // User not logged in, redirect to login page
            response.sendRedirect("login.jsp");
            return;
        }

        // Retrieve event data from the form
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        LocalDate localDate = LocalDate.parse(request.getParameter("eventDate"));  // Parse the date from the form
        Date eventDate = Date.valueOf(localDate);
        int clubId = Integer.parseInt(request.getParameter("clubId")); // Assuming clubId is passed as a parameter

        // Create a new Event object
        Event newEvent = new Event(title, description, eventDate, clubId);

        // Add the event to the database
        try {
            eventDAO.addEvent(newEvent);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exception, maybe redirect to an error page
        }

        // Redirect to the list of events or a success page
        response.sendRedirect("displayEvents"); // Assuming this servlet shows the list of events
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("addEvent.jsp").forward(request, response);
    }
}
