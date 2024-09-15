<%@ page import="com.unimelb.swen90007.studentclub.model.Student" %>
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
    <title>Cancel RSVP</title>
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

        select, input[type="submit"] {
            padding: 10px;
            margin-top: 5px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }

        input[type="submit"] {
            margin-top: 20px;
            background-color: #ff6347;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #e55347;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Cancel RSVP</h1>
    <form action="cancelRSVP" method="post">
        <label for="rsvpId">Select RSVP to Cancel</label>
        <select id="rsvpId" name="rsvpId">
            <c:forEach var="rsvp" items="${rsvps}">
                <option value="${rsvp.id}">${rsvp.eventTitle} - ${rsvp.numTickets} tickets</option>
            </c:forEach>
        </select>

        <input type="submit" value="Cancel RSVP">
    </form>
</div>
</body>
</html>
