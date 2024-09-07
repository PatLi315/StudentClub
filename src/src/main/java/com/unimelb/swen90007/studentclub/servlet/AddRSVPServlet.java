package com.unimelb.swen90007.studentclub.servlet;

import com.unimelb.swen90007.studentclub.dao.RSVPDAO;

import com.unimelb.swen90007.studentclub.model.RSVP;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import jakarta.servlet.http.HttpSession;

public class AddRSVPServlet extends HttpServlet {

    private RSVPDAO rsvpDAO;

    @Override
    public void init() {
        rsvpDAO = new RSVPDAO();
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

        int studentId = Integer.parseInt(request.getParameter("studentId"));
        int eventId = Integer.parseInt(request.getParameter("eventId"));

        RSVP rsvp = new RSVP(studentId, eventId);

        try {
            rsvpDAO.addRSVP(rsvp);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exception, maybe redirect to an error page
        }

        response.sendRedirect("displayEvents"); // Assuming this is a page that lists events
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("rsvpEvent.jsp").forward(request, response);
    }
}
