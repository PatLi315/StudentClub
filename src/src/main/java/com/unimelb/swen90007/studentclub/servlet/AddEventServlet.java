package com.unimelb.swen90007.studentclub.servlet;

import com.unimelb.swen90007.studentclub.dao.EventDAO;
import com.unimelb.swen90007.studentclub.model.Event;
import com.unimelb.swen90007.studentclub.model.Person;
import com.unimelb.swen90007.studentclub.util.DatabaseConnection;
import com.unimelb.swen90007.studentclub.util.UnitOfWork;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

public class AddEventServlet extends HttpServlet {

    private EventDAO eventDAO;

    @Override
    public void init() {
        eventDAO = new EventDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("student") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        Person loggedInStudent = (Person) session.getAttribute("student");

        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String venue = request.getParameter("venue");
        int capacity = Integer.parseInt(request.getParameter("capacity"));
        int clubId = Integer.parseInt(request.getParameter("clubId"));
        Date eventDate = Date.valueOf(request.getParameter("eventDate"));

        try (Connection connection = DatabaseConnection.getConnection()) {
            UnitOfWork unitOfWork = new UnitOfWork(connection);

            Event event = new Event(title, description, eventDate, clubId, capacity, venue);
            eventDAO.addEvent(event, unitOfWork);

            unitOfWork.commit();

            response.sendRedirect("displayEvents.jsp");

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while adding the event.");
        }
    }
}
