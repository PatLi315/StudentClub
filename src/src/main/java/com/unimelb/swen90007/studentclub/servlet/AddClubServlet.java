package com.unimelb.swen90007.studentclub.servlet;

import com.unimelb.swen90007.studentclub.dao.ClubDAO;
import com.unimelb.swen90007.studentclub.dao.AdminDAO;
import com.unimelb.swen90007.studentclub.model.Club;
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

public class AddClubServlet extends HttpServlet {

    private ClubDAO clubDAO;
    private AdminDAO adminDAO;

    @Override
    public void init() {
        clubDAO = new ClubDAO();
        adminDAO = new AdminDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);  // Check if session exists
        if (session == null || session.getAttribute("student") == null) {
            // User not logged in, redirect to login page
            response.sendRedirect("login.jsp");
            return;
        }

        // Get the logged-in student
        Student loggedInStudent = (Student) session.getAttribute("student");
        String clubName = request.getParameter("clubName");

        if (clubName == null || clubName.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Club name cannot be empty");
            return;
        }

        try (Connection connection = DatabaseConnection.getConnection()) {
            UnitOfWork unitOfWork = new UnitOfWork(connection);  // Pass the connection to UnitOfWork

            // Create the club
            Club club = new Club(clubName);
            clubDAO.addClub(club, unitOfWork);

            // Retrieve the newly created club ID (Ensure this method works properly in your DAO)
            //int clubId = clubDAO.getLastInsertedClubId(unitOfWork);

            if (club.getId() == 0) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to retrieve club ID.");
                return;
            }
            // Register the logged-in student as an admin of the new club
            adminDAO.addAdmin(loggedInStudent.getId(), club.getId(), unitOfWork);

            // Commit the UnitOfWork to execute all operations
            unitOfWork.commit();

            response.sendRedirect("listClubs.jsp");

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while adding the club");
        }
    }
}

