package com.unimelb.swen90007.studentclub.servlet;

import com.unimelb.swen90007.studentclub.dao.RSVPDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RSVPServlet extends HttpServlet {

    private RSVPDAO rsvpDAO;

    @Override
    public void init() {
        rsvpDAO = new RSVPDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int eventId = Integer.parseInt(request.getParameter("eventId"));
        int studentId = (int) request.getSession().getAttribute("studentId"); // Assuming studentId is stored in session
        rsvpDAO.addRSVP(studentId, eventId);
        response.sendRedirect("displayEvents.jsp"); // Redirect back to events page or confirmation page
    }
}
