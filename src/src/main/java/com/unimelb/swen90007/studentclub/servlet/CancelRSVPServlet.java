package com.unimelb.swen90007.studentclub.servlet;

import com.unimelb.swen90007.studentclub.dao.RSVPDAO;
import com.unimelb.swen90007.studentclub.model.RSVP;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CancelRSVPServlet extends HttpServlet {

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
            rsvpDAO.removeRSVP(rsvp);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exception, maybe redirect to an error page
        }

        // Redirect to a confirmation page or the list of events
        response.sendRedirect("displayEvents"); // Assuming you redirect to a page that lists events
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward to a JSP page for confirmation (optional)
        request.getRequestDispatcher("cancelRSVP.jsp").forward(request, response);
    }
}
