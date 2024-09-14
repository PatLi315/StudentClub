package com.unimelb.swen90007.studentclub.servlet;

import com.unimelb.swen90007.studentclub.dao.AdminDAO;
import com.unimelb.swen90007.studentclub.dao.ClubDAO;
import com.unimelb.swen90007.studentclub.model.Student;
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

public class AddAdminServlet extends HttpServlet {

    private AdminDAO adminDAO;
    private ClubDAO clubDAO;

    @Override
    public void init() {
        adminDAO = new AdminDAO();
        clubDAO = new ClubDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("student") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        Student loggedInStudent = (Student) session.getAttribute("student");
        int clubId = Integer.parseInt(request.getParameter("clubId"));
        int studentIdToAdd = Integer.parseInt(request.getParameter("studentId"));

        try (Connection connection = DatabaseConnection.getConnection()) {
            UnitOfWork unitOfWork = new UnitOfWork(connection);

            if (adminDAO.isAdmin(loggedInStudent.getId(), clubId, unitOfWork)) {
                adminDAO.addAdmin(studentIdToAdd, clubId, unitOfWork);
                unitOfWork.commit();
                response.sendRedirect("adminDashboard.jsp");
            } else {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "You are not authorized to add an admin to this club.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while adding the admin.");
        }
    }
}
