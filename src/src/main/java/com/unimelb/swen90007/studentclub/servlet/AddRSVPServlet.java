package com.unimelb.swen90007.studentclub.servlet;

import com.unimelb.swen90007.studentclub.dao.RSVPDAO;

import com.unimelb.swen90007.studentclub.model.RSVP;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddRSVPServlet extends HttpServlet {

    private RSVPDAO rsvpDAO;

    @Override
    public void init() {
        rsvpDAO = new RSVPDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
