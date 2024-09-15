<%@ page import="com.unimelb.swen90007.studentclub.model.RegularStudent" %>
<%@ page import="com.unimelb.swen90007.studentclub.model.Event" %>
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
    List<Event> events = (List<Event>) request.getAttribute("events");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Upcoming Events</title>
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

        .event-list {
            list-style: none;
            padding: 0;
        }

        .event-item {
            background-color: #28a745;
            color: white;
            padding: 15px;
            margin: 10px 0;
            border-radius: 5px;
        }

        .event-item:hover {
            background-color: #218838;
        }

        .event-item a {
            color: white;
            text-decoration: none;
            font-weight: bold;
        }

        .event-item a:hover {
            text-decoration: underline;
        }

        .event-date {
            font-size: 14px;
            margin-top: 5px;
        }

        .rsvp-btn {
            background-color: #ff6347;
            color: white;
            padding: 5px 10px;
            border: none;
            border-radius: 3px;
            cursor: pointer;
            text-decoration: none;
        }

        .rsvp-btn:hover {
            background-color: #e55347;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Upcoming Events</h1>
    <ul class="event-list">
        <% if (events != null && !events.isEmpty()) { %>
        <ul>
            <% for (Event event : events) { %>
            <li><%= event.getTitle() %></li>
            <% } %>
        </ul>
        <% } else { %>
        <p>No events available.</p>
        <% } %>
    </ul>
</div>
</body>
</html>
