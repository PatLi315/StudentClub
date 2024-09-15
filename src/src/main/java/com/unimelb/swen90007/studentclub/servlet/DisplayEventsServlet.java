package com.unimelb.swen90007.studentclub.servlet;

import com.unimelb.swen90007.studentclub.dao.ClubDAO;
import com.unimelb.swen90007.studentclub.dao.EventDAO;
import com.unimelb.swen90007.studentclub.dao.StudentDAO;
import com.unimelb.swen90007.studentclub.model.Event;
import com.unimelb.swen90007.studentclub.model.Student;
import com.unimelb.swen90007.studentclub.util.DatabaseConnection;
import com.unimelb.swen90007.studentclub.util.UnitOfWork;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.lang.model.element.NestingKind;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DisplayEventsServlet extends HttpServlet {

    private EventDAO eventDAO;
    private ClubDAO clubDAO;
    private StudentDAO studentDAO;

    @Override
    public void init() {
        eventDAO = new EventDAO();
        clubDAO = new ClubDAO();
        studentDAO = new StudentDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("student") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String loggedInStudent = (String) session.getAttribute("student");


        try (Connection connection = DatabaseConnection.getConnection()) {
            UnitOfWork unitOfWork = new UnitOfWork(connection);

            int loggedInStudentId = studentDAO.getStudentId(loggedInStudent, unitOfWork);
            List<Event> eventsForStudent = eventDAO.getEventsForStudentClubs(loggedInStudentId, unitOfWork);
            request.setAttribute("events", eventsForStudent);

            unitOfWork.commit();
            request.getRequestDispatcher("displayEvents.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while retrieving the events.");
        }
    }
}
