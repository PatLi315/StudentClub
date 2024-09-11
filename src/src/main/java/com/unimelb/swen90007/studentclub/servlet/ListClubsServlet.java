package com.unimelb.swen90007.studentclub.servlet;

import com.unimelb.swen90007.studentclub.dao.ClubDAO;
import com.unimelb.swen90007.studentclub.util.DatabaseConnection;
import com.unimelb.swen90007.studentclub.util.UnitOfWork;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import com.unimelb.swen90007.studentclub.model.Club;

public class ListClubsServlet extends HttpServlet {

    private ClubDAO clubDAO;

    @Override
    public void init() {
        clubDAO = new ClubDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try (Connection connection = DatabaseConnection.getConnection()) {
            UnitOfWork unitOfWork = new UnitOfWork(connection);

            // Get all clubs
            List<Club> clubs = clubDAO.listAllClubs(unitOfWork);

            // Set clubs in request attribute and forward to JSP
            request.setAttribute("clubs", clubs);
            request.getRequestDispatcher("clubs.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while fetching clubs.");
        }
    }
}
