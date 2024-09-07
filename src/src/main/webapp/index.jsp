<%@ page import="com.unimelb.swen90007.studentclub.model.Student" %>
<!DOCTYPE html>
<html>
<head>
    <title>Student Club Management System</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f4f4f4;
        }
        h1 {
            color: #333;
        }
        ul {
            list-style-type: none;
            padding: 0;
        }
        li {
            margin-bottom: 10px;
        }
        a {
            text-decoration: none;
            color: #007bff;
            font-weight: bold;
        }
        a:hover {
            text-decoration: underline;
        }
        .logged-in {
            color: green;
            font-weight: bold;
        }
    </style>
</head>
<body>
<h1>Welcome to the Student Club Management System</h1>

<%
    Student loggedInStudent = (Student) session.getAttribute("student");
%>

<%
    if (loggedInStudent != null) {
        // User is logged in, display the student's name and the logout option
%>
<p class="logged-in">Hello, <%= loggedInStudent.getName() %>! You are logged in.</p>
<p><a href="logout">Logout</a></p>

<p>Select an option below to manage clubs, events, and RSVPs:</p>
<ul>
    <li><a href="addClub.jsp">Add a New Club</a></li>
    <li><a href="addEvent.jsp">Create a New Event</a></li>
    <li><a href="rsvpEvent.jsp">RSVP to an Event</a></li>
    <li><a href="cancelRSVP.jsp">Cancel an RSVP</a></li>
    <li><a href="displayEvents">View All Events</a></li>
    <li><a href="modifyEvent.jsp">Modify an Existing Event</a></li>
    <li><a href="cancelEvent.jsp">Cancel an Event</a></li>
    <li><a href="addAdmin.jsp">Add an Admin to a Club</a></li>
    <li><a href="removeAdmin.jsp">Remove an Admin from a Club</a></li>
    <li><a href="listClubs.jsp">View All Clubs</a></li>
</ul>
<%
} else {
    // User is not logged in, show login option
%>
<p>You are not logged in.</p>
<p><a href="login.jsp">Login</a> to manage clubs, events, and RSVPs.</p>
<%
    }
%>
</body>
</html>
