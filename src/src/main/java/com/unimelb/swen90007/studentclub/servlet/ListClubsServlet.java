package com.unimelb.swen90007.studentclub.servlet;

import com.unimelb.swen90007.studentclub.dao.ClubDAO;
import com.unimelb.swen90007.studentclub.model.Club;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import jakarta.servlet.http.HttpSession;

public class ListClubsServlet extends HttpServlet {

    private ClubDAO clubDAO;

    @Override
    public void init() throws ServletException {
        clubDAO = new ClubDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);  // Do not create a session
        if (session == null || session.getAttribute("student") == null) {
            // User not logged in, redirect to login page
            response.sendRedirect("login.jsp");
            return;
        }

        List<Club> clubs = null;
        try {
            clubs = clubDAO.listAllClubs();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Set the list of clubs as an attribute to the request
        if (clubs != null) {
            request.setAttribute("clubs", clubs);
        } else {
            request.setAttribute("clubs", null); // Handle the empty case
        }

        // Forward the request to the JSP page
        request.getRequestDispatcher("listClubs.jsp").forward(request, response);
    }
}
