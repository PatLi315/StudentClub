<%@ page import="com.unimelb.swen90007.studentclub.model.RegularStudent" %>
<%@ page session="true" %>
<%
    String studentName = (String) session.getAttribute("student");
    if (studentName == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Modify Event</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
        }

        .container {
            max-width: 600px;
            margin: auto;
            padding: 20px;
        }

        h1 {
            text-align: center;
            color: #333;
        }

        form {
            display: flex;
            flex-direction: column;
        }

        label {
            margin-top: 10px;
            font-weight: bold;
        }

        input[type="text"], input[type="datetime-local"], input[type="number"], textarea {
            padding: 10px;
            margin-top: 5px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }

        input[type="submit"] {
            margin-top: 20px;
            padding: 10px;
            background-color: #ffc107;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #e0a800;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Modify Event</h1>
    <form action="modifyEvent" method="post">
        <input type="hidden" name="eventId" value="${event.id}">

        <label for="title">Event Title</label>
        <input type="text" id="title" name="title" value="${event.title}" required>

        <label for="description">Event Description</label>
        <textarea id="description" name="description" rows="4">${event.description}</textarea>

        <label for="eventDate">Event Date</label>
        <input type="datetime-local" id="eventDate" name="eventDate" value="${event.eventDate}" required>

        <label for="capacity">Capacity</label>
        <input type="number" id="capacity" name="capacity" value="${event.capacity}" required>

        <label for="venue">Venue</label>
        <input type="text" id="venue" name="venue" value="${event.venue}" required>

        <input type="submit" value="Update Event">
    </form>
</div>
</body>
</html>
