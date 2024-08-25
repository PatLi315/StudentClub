package com.unimelb.swen90007.studentclub.servlet;

import com.unimelb.swen90007.studentclub.dao.EventDAO;
import com.unimelb.swen90007.studentclub.model.Event;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

//@WebServlet("/addEvent")
public class AddEventServlet extends HttpServlet {

    private EventDAO eventDAO;

    public void init() {
        eventDAO = new EventDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String title = request.getParameter("title");
        String description = request.getParameter("description");

        Event newEvent = new Event(0, title, description);
        try {
            eventDAO.addEvent(newEvent);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect("displayEvents");
    }
}
