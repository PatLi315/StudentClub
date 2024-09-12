package com.unimelb.swen90007.studentclub.servlet;

import com.unimelb.swen90007.studentclub.dao.AdminDAO;
import com.unimelb.swen90007.studentclub.dao.ClubDAO;
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

public class AddAdminServlet extends HttpServlet {

    private AdminDAO adminDAO;
    private ClubDAO clubDAO;  // To verify if the student is an admin

    @Override
    public void init() {
        adminDAO = new AdminDAO();
        clubDAO = new ClubDAO();
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
        int clubId = (int) session.getAttribute("clubId"); // Assuming clubId is stored in session
        int studentIdToAdd = Integer.parseInt(request.getParameter("studentId"));

        // Check if the logged-in student is an admin of the club
        try (Connection connection = DatabaseConnection.getConnection()) {
            UnitOfWork unitOfWork = new UnitOfWork(connection);

            if (!adminDAO.isAdmin(loggedInStudent.getId(), clubId, unitOfWork)) {
                // If the user is not an admin, return a 403 Forbidden error
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "You are not authorized to add admins for this club.");
                return;
            }

            // Register the operation to add the admin in the UnitOfWork
            adminDAO.addAdmin(studentIdToAdd, clubId, unitOfWork);

            // Commit the UnitOfWork to execute the operation
            unitOfWork.commit();

            // Redirect to the admin dashboard after successful addition
            response.sendRedirect("index.jsp");

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while adding the admin.");
        }
    }
}
