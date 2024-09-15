<%@ page import="com.unimelb.swen90007.studentclub.model.Student" %>
<%@ page import="com.unimelb.swen90007.studentclub.model.Club" %>
<%@ page import="java.util.List" %>
<%@ page session="true" %>
<%
  String studentName = (String) session.getAttribute("student");
  if (studentName == null) {
    response.sendRedirect("login.jsp");
    return;
  }
%>

<%
  List<Club> clubs = (List<Club>) request.getAttribute("clubs");
%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>All Clubs</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f5f5f5;
    }

    .container {
      max-width: 1000px;
      margin: auto;
      padding: 20px;
    }

    h1 {
      text-align: center;
      color: #333;
    }

    .club-list {
      list-style: none;
      padding: 0;
    }

    .club-item {
      background-color: #007bff;
      color: white;
      padding: 15px;
      margin: 10px 0;
      border-radius: 5px;
    }

    .club-item:hover {
      background-color: #0056b3;
    }

    .club-item a {
      color: white;
      text-decoration: none;
      font-weight: bold;
    }

    .club-item a:hover {
      text-decoration: underline;
    }
  </style>
</head>
<body>
<div class="container">
  <h1>All Clubs</h1>
  <% if (clubs != null && !clubs.isEmpty()) { %>
  <ul>
    <% for (Club club : clubs) { %>
    <li><%= club.getName() %></li>
    <% } %>
  </ul>
  <% } else { %>
  <p>No clubs available.</p>
  <% } %>
</div>
</body>
</html>
