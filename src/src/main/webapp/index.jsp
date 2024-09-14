<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student Club Management System</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 1200px;
            margin: auto;
            padding: 20px;
        }

        h1 {
            color: #333;
            text-align: center;
            padding: 20px 0;
        }

        nav {
            text-align: center;
            background-color: #007bff;
            padding: 10px 0;
        }

        nav a {
            margin: 0 15px;
            color: white;
            text-decoration: none;
            font-weight: bold;
        }

        nav a:hover {
            text-decoration: underline;
        }

        .welcome-msg {
            text-align: center;
            margin-top: 30px;
            font-size: 18px;
            color: #666;
        }
    </style>
</head>
<body>
<nav>
    <a href="listClubs.jsp">View Clubs</a>
    <a href="displayEvents.jsp">View Events</a>
    <a href="addEvent.jsp">Create Event</a> <!-- Added Add Event link here -->
    <a href="login.jsp">Login</a>
    <a href="logout">Logout</a>
</nav>
<div class="container">
    <h1>Welcome to the Student Club Management System</h1>
    <p class="welcome-msg">Manage student clubs and events effortlessly.</p>
</div>
</body>
</html>
