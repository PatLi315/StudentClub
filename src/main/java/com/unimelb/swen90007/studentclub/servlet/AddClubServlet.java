package com.unimelb.swen90007.studentclub.servlet;

import com.unimelb.swen90007.studentclub.dao.ClubDAO;
import com.unimelb.swen90007.studentclub.model.Club;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddClubServlet extends HttpServlet {

    private ClubDAO clubDAO;

    @Override
    public void init() {
        clubDAO = new ClubDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");

        Club newClub = new Club(name);

        try {
            clubDAO.addClub(newClub);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exception, maybe redirect to an error page
        }

        response.sendRedirect("listClubs");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("addClub.jsp").forward(request, response);
    }
}
