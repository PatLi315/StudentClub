package com.unimelb.swen90007.studentclub.servlet;

import com.unimelb.swen90007.studentclub.dao.StudentDAO;
import com.unimelb.swen90007.studentclub.model.Person;
import com.unimelb.swen90007.studentclub.model.RegularStudent;
import com.unimelb.swen90007.studentclub.util.DatabaseConnection;
import com.unimelb.swen90007.studentclub.util.UnitOfWork;
import com.unimelb.swen90007.studentclub.util.PasswordUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class RegisterServlet extends HttpServlet {

    private StudentDAO studentDAO;

    @Override
    public void init() {
        studentDAO = new StudentDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Hash the password
        String hashedPassword = PasswordUtils.hashPassword(password);

        try (Connection connection = DatabaseConnection.getConnection()) {
            UnitOfWork unitOfWork = new UnitOfWork(connection);

            // Check if username is already taken
            if (studentDAO.isUsernameTaken(username, unitOfWork)) {
                request.setAttribute("errorMessage", "Username is already taken.");
                request.getRequestDispatcher("register.jsp").forward(request, response);
                return;
            }

            unitOfWork.commit();

            // Register the student
            Person newStudent = new RegularStudent(username, hashedPassword, email);
            studentDAO.registerStudent(newStudent, unitOfWork);

            // Commit the unit of work
            unitOfWork.commit();
            response.sendRedirect("login.jsp?registerSuccess=true");

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Registration failed: " + e.getMessage());
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}
