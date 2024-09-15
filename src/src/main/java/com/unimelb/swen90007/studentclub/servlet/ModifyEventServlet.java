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

public class ModifyEventServlet extends HttpServlet {

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

        int eventId = Integer.parseInt(request.getParameter("eventId"));
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String venue = request.getParameter("venue");
        int capacity = Integer.parseInt(request.getParameter("capacity"));
        Date eventDate = Date.valueOf(request.getParameter("eventDate"));

        try (Connection connection = DatabaseConnection.getConnection()) {
            UnitOfWork unitOfWork = new UnitOfWork(connection);

            Event event = eventDAO.getEventById(eventId, unitOfWork);

            if (event == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Event not found");
                return;
            }

//            if (!event.isAdmin(loggedInStudent.getId())) {
//                response.sendError(HttpServletResponse.SC_FORBIDDEN, "You are not authorized to modify this event");
//                return;
//            }

            event.setTitle(title);
            event.setDescription(description);
            event.setVenue(venue);
            event.setCapacity(capacity);
            event.setEventDate(eventDate);

            eventDAO.updateEvent(event, unitOfWork);

            unitOfWork.commit();

            response.sendRedirect("displayEvents.jsp");

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while updating the event.");
        }
    }
}
