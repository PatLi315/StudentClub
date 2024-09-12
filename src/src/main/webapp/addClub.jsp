<%@ page session="true" %>
<%
    if (session.getAttribute("student") == null) {
        response.sendRedirect("login.jsp");
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Add New Club</title>
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
        label, input {
            display: block;
            margin-bottom: 10px;
        }
        input[type="text"] {
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
<h2>Add New Club</h2>
<form action="addClub" method="post">
    <label for="clubName">Club Name:</label>
    <input type="text" id="clubName" name="clubName" required>

    <input type="submit" value="Add Club">
</form>
</body>
</html>
