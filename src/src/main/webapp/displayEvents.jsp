<%@ page import="com.unimelb.swen90007.studentclub.model.Student" %>
<%@ page session="true" %>
<%
    Student student = (Student) session.getAttribute("student");
    if (student == null) {
        response.sendRedirect("login.jsp");
        return;
    }
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
        <c:forEach var="event" items="${events}">
            <li class="event-item">
                <a href="eventDetails.jsp?eventId=${event.id}">${event.title}</a>
                <p class="event-date">${event.eventDate}</p>
                <p>${event.description}</p>
                <a href="rsvpEvent.jsp?eventId=${event.id}" class="rsvp-btn">RSVP</a>
            </li>
        </c:forEach>
    </ul>
</div>
</body>
</html>
