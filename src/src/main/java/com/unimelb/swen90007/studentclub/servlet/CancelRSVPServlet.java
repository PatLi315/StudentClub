package com.unimelb.swen90007.studentclub.servlet;

import com.unimelb.swen90007.studentclub.dao.RSVPDAO;
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
import java.sql.SQLException;

public class CancelRSVPServlet extends HttpServlet {

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

        int eventId = Integer.parseInt(request.getParameter("eventId"));
        Person loggedInStudent = (Person) session.getAttribute("student");

        try (Connection connection = DatabaseConnection.getConnection()) {
            UnitOfWork unitOfWork = new UnitOfWork(connection);

            rsvpDAO.cancelRSVP(loggedInStudent.getId(), eventId, unitOfWork);

            unitOfWork.commit();

            response.sendRedirect("displayEvents.jsp");

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while canceling your RSVP.");
        }
    }
}
