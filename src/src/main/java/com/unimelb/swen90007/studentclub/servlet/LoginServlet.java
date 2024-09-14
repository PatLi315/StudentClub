package com.unimelb.swen90007.studentclub.servlet;

import com.unimelb.swen90007.studentclub.auth.CustomLoginConfig;
import com.unimelb.swen90007.studentclub.auth.StudentCallbackHandler;
import com.unimelb.swen90007.studentclub.dao.StudentDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {

    private StudentDAO studentDAO;

    @Override
    public void init() {
        studentDAO = new StudentDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        CustomLoginConfig.setCustomConfiguration();

        try {
            LoginContext loginContext = new LoginContext("studentClub", new StudentCallbackHandler(username, password));
            loginContext.login();

            HttpSession session = request.getSession();
            session.setAttribute("student", username);
            response.sendRedirect("index.jsp");
        } catch (LoginException e) {
            request.setAttribute("errorMessage", "Login failed: " + e.getMessage());
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
