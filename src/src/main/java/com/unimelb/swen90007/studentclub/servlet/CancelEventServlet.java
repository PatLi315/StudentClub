package com.unimelb.swen90007.studentclub.servlet;

import com.unimelb.swen90007.studentclub.dao.AdminDAO;
import com.unimelb.swen90007.studentclub.dao.EventDAO;
import com.unimelb.swen90007.studentclub.model.Student;
import com.unimelb.swen90007.studentclub.util.DatabaseConnection;
import com.unimelb.swen90007.studentclub.util.UnitOfWork;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


public class CancelEventServlet extends HttpServlet {

    private EventDAO eventDAO;
    private AdminDAO adminDAO;

    @Override
    public void init() {
        eventDAO = new EventDAO();
        adminDAO = new AdminDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("student") == null) {
            // User not logged in, redirect to login page
            response.sendRedirect("login.jsp");
            return;
        }

        // Get the logged-in student
        Student loggedInStudent = (Student) session.getAttribute("student");
        int clubId = Integer.parseInt(request.getParameter("clubId"));
        int eventId = Integer.parseInt(request.getParameter("eventId"));

        // Check if the logged-in student is an admin of the club
        try (Connection connection = DatabaseConnection.getConnection()) {
            UnitOfWork unitOfWork = new UnitOfWork(connection);

            if (!adminDAO.isAdmin(loggedInStudent.getId(), clubId, unitOfWork)) {
                // If the user is not an admin, return a 403 Forbidden error
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "You are not authorized to cancel events for this club.");
                return;
            }

            // Register the operation to cancel the event in the UnitOfWork
            eventDAO.deleteEvent(eventId, unitOfWork);

            // Commit the UnitOfWork to execute the operation
            unitOfWork.commit();

            response.sendRedirect("displayEvents.jsp");

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while canceling the event.");
        }
    }
}
