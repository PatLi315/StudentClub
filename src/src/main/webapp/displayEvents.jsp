<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.unimelb.swen90007.studentclub.model.Event" %>
<!DOCTYPE html>
<html>
<head>
    <title>Event List</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f4f4f4;
        }
        h2 {
            color: #333;
        }
        ul {
            list-style-type: none;
            padding: 0;
        }
        li {
            margin-bottom: 20px;
            padding: 10px;
            background-color: #fff;
            border: 1px solid #ddd;
        }
        strong {
            display: block;
            margin-bottom: 5px;
        }
    </style>
</head>
<body>
<h2>Event List</h2>
<ul>
    <%
        List<Event> events = (List<Event>) request.getAttribute("events");
        if (events != null) {
            for (Event event : events) {
    %>
    <li>
        <strong>Title:</strong> <%= event.getTitle() %>
        <strong>Description:</strong> <%= event.getDescription() %>
        <strong>Date:</strong> <%= event.getEventDate() %>
        <strong>Club ID:</strong> <%= event.getClubId() %>
    </li>
    <%
        }
    } else {
    %>
    <li>No events found.</li>
    <%
        }
    %>
</ul>
</body>
</html>
