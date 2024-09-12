<%@ page import="com.unimelb.swen90007.studentclub.model.Event" %>
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
    <title>Events List</title>
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
<h2>All Events</h2>
<table>
    <thead>
    <tr>
        <th>Title</th>
        <th>Description</th>
        <th>Date</th>
        <th>Club</th>
    </tr>
    </thead>
    <tbody>
    <%
        List<Event> events = (List<Event>) request.getAttribute("events");
        if (events != null) {
            for (Event event : events) {
    %>
    <tr>
        <td><%= event.getTitle() %></td>
        <td><%= event.getDescription() %></td>
        <td><%= event.getEventDate() %></td>
        <td><%= event.getClubId() %></td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="4">No events available</td>
    </tr>
    <% } %>
    </tbody>
</table>
</body>
</html>
