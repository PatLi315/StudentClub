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
    </style>
</head>
<body>
<h1>Welcome to the Student Club Management System</h1>
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
</body>
</html>
