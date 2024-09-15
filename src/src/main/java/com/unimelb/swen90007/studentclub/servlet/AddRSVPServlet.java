package com.unimelb.swen90007.studentclub.servlet;

import com.unimelb.swen90007.studentclub.dao.RSVPDAO;
import com.unimelb.swen90007.studentclub.model.Person;
import com.unimelb.swen90007.studentclub.model.RSVP;
import com.unimelb.swen90007.studentclub.util.DatabaseConnection;
import com.unimelb.swen90007.studentclub.util.UnitOfWork;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class AddRSVPServlet extends HttpServlet {

    private RSVPDAO rsvpDAO;

    @Override
    public void init() {
        rsvpDAO = new RSVPDAO();
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
        int tickets = Integer.parseInt(request.getParameter("tickets"));

        try (Connection connection = DatabaseConnection.getConnection()) {
            UnitOfWork unitOfWork = new UnitOfWork(connection);

            RSVP rsvp = new RSVP(loggedInStudent.getId(), eventId, tickets);
            rsvpDAO.addRSVP(rsvp, unitOfWork);

            unitOfWork.commit();

            response.sendRedirect("displayEvents.jsp");

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing your RSVP.");
        }
    }
}
