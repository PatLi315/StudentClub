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
  <title>RSVP Event</title>
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

    input[type="number"] {
      padding: 10px;
      margin-top: 5px;
      border: 1px solid #ddd;
      border-radius: 5px;
    }

    input[type="submit"] {
      margin-top: 20px;
      padding: 10px;
      background-color: #007bff;
      color: white;
      border: none;
      border-radius: 5px;
      cursor: pointer;
    }

    input[type="submit"]:hover {
      background-color: #0056b3;
    }
  </style>
</head>
<body>
<div class="container">
  <h1>RSVP for Event</h1>
  <form action="addRSVP" method="post">
    <label for="numTickets">Number of Tickets</label>
    <input type="number" id="numTickets" name="numTickets" min="1" required>
    <input type="hidden" name="eventId" value="${param.eventId}">
    <input type="submit" value="RSVP">
  </form>
</div>
</body>
</html>
