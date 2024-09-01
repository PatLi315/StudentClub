package com.unimelb.swen90007.studentclub.servlet;

import com.unimelb.swen90007.studentclub.dao.EventDAO;
import com.unimelb.swen90007.studentclub.model.Event;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

public class ModifyEventServlet extends HttpServlet {

    private EventDAO eventDAO;

    @Override
    public void init() {
        eventDAO = new EventDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int eventId = Integer.parseInt(request.getParameter("eventId"));
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        Date eventDate = Date.valueOf(request.getParameter("eventDate"));
        int clubId = (int) request.getSession().getAttribute("clubId"); // Assuming clubId is stored in session
        Event event = new Event(eventId, title, description, eventDate, clubId);
        try {
            eventDAO.updateEvent(event);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("adminDashboard.jsp"); // Redirect to admin dashboard or confirmation page
    }
}
