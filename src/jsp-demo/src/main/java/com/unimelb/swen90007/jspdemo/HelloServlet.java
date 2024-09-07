package com.unimelb.swen90007.jspdemo;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import java.io.*;

    @WebServlet(name = "helloServlet", value = "/hello-servlet")
    public class HelloServlet extends HttpServlet {
        private String message;

        public void init() {
            message = "Creat Successfully";
        }

        public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
            response.setContentType("text/html");
            request.setCharacterEncoding("utf-8");
            String u = request.getParameter("name");
            String p = request.getParameter("date");
            try {
                String url = "jdbc:postgresql://dpg-cqvciotsvqrc73c0c5d0-a.singapore-postgres.render.com:5432/swen90007_react_example_xxzg";
                String user = "swen90007_react_example_owner";
                String pw = "hM8S2usBWZOnQBtiIM7gdZNC1k5CaUBc";
                Class.forName("org.postgresql.Driver");
                Connection conn = DriverManager.getConnection(url, user, pw);
//            Statement stmt = conn.createStatement();

                // execute query
                String sql = "INSERT INTO event VALUES (?,?);";
                PreparedStatement stmt = conn.prepareStatement(sql);
//            ResultSet rs = stmt.executeQuery(sql, USING u,p);
                stmt.setString(1, u);
                stmt.setString(2, p);
                stmt.executeUpdate();
                // handle result
                PrintWriter out = response.getWriter();
                out.println("<html><body>");
                out.println("<h1>" + message + "</h1>");
                out.println("</body></html>");

                conn.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        public void destroy() {
        }
    }
