<%@ page session="true" %>
<%
    if (session.getAttribute("student") == null) {
        response.sendRedirect("login.jsp");
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Modify Event</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f4f4f4;
        }
        h2 {
            color: #333;
        }
        form {
            margin-top: 20px;
        }
        label, input, textarea {
            display: block;
            margin-bottom: 10px;
        }
        input[type="text"], input[type="date"], textarea {
            padding: 8px;
            width: 300px;
        }
        input[type="submit"] {
            padding: 8px 16px;
            background-color: #007bff;
            color: #fff;
            border: none;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<h2>Modify Event</h2>
<form action="modifyEvent" method="post">
    <label for="eventId">Event ID:</label>
    <input type="number" id="eventId" name="eventId" required>

    <label for="title">New Title:</label>
    <input type="text" id="title" name="title" required>

    <label for="description">New Description:</label>
    <textarea id="description" name="description" required></textarea>

    <label for="eventDate">New Date:</label>
    <input type="date" id="eventDate" name="eventDate" required>

    <input type="submit" value="Modify Event">
</form>
</body>
</html>
