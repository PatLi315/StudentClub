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
  <title>All Clubs</title>
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

    .club-list {
      list-style: none;
      padding: 0;
    }

    .club-item {
      background-color: #007bff;
      color: white;
      padding: 15px;
      margin: 10px 0;
      border-radius: 5px;
    }

    .club-item:hover {
      background-color: #0056b3;
    }

    .club-item a {
      color: white;
      text-decoration: none;
      font-weight: bold;
    }

    .club-item a:hover {
      text-decoration: underline;
    }
  </style>
</head>
<body>
<div class="container">
  <h1>All Clubs</h1>
  <ul class="club-list">
    <c:forEach var="club" items="${clubs}">
      <li class="club-item">
        <a href="clubDetails.jsp?clubId=${club.id}">${club.name}</a>
      </li>
    </c:forEach>
  </ul>
</div>
</body>
</html>
