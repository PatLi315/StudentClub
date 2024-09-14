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
    <title>Cancel Event</title>
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

        select {
            padding: 10px;
            margin-top: 5px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }

        input[type="submit"] {
            margin-top: 20px;
            padding: 10px;
            background-color: #dc3545;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #c82333;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Cancel Event</h1>
    <form action="cancelEvent" method="post">
        <label for="eventId">Select Event to Cancel</label>
        <select id="eventId" name="eventId">
            <c:forEach var="event" items="${events}">
                <option value="${event.id}">${event.title}</option>
            </c:forEach>
        </select>
        <input type="submit" value="Cancel Event">
    </form>
</div>
</body>
</html>
