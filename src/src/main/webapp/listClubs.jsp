<%@ page import="com.unimelb.swen90007.studentclub.model.Club" %>
<%@ page import="java.util.List" %>
<%@ page session="true" %>
<%
  if (session.getAttribute("student") == null) {
    response.sendRedirect("login.jsp");
  }
%>
<!DOCTYPE html>
<html>
<head>
  <title>Club List</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 20px;
      background-color: #f4f4f4;
    }
    h2 {
      color: #333;
    }
    table {
      width: 100%;
      border-collapse: collapse;
      margin-top: 20px;
    }
    table, th, td {
      border: 1px solid #ccc;
    }
    th, td {
      padding: 10px;
      text-align: left;
    }
  </style>
</head>
<body>
<h2>All Clubs</h2>
<table>
  <thead>
  <tr>
    <th>Club ID</th>
    <th>Club Name</th>
  </tr>
  </thead>
  <tbody>
  <%
    List<Club> clubs = (List<Club>) request.getAttribute("clubs");
    if (clubs != null) {
      for (Club club : clubs) {
  %>
  <tr>
    <td><%= club.getId() %></td>
    <td><%= club.getName() %></td>
  </tr>
  <%
    }
  } else {
  %>
  <tr>
    <td colspan="2">No clubs available</td>
  </tr>
  <% } %>
  </tbody>
</table>
</body>
</html>
