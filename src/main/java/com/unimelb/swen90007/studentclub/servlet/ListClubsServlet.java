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

public class ListClubsServlet extends HttpServlet {

    private ClubDAO clubDAO;

    @Override
    public void init() {
        clubDAO = new ClubDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Club> clubs = null;
        try {
            clubs = clubDAO.listAllClubs();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception (e.g., log it and display an error page)
        }

        // Set the list of clubs as a request attribute
        request.setAttribute("clubs", clubs);

        // Forward the request to the JSP page for display
        request.getRequestDispatcher("listClubs.jsp").forward(request, response);
    }
}
