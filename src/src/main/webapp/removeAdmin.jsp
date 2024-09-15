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
  <title>Remove Admin</title>
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
  <h1>Remove Admin</h1>
  <form action="removeAdmin" method="post">
    <label for="adminId">Select Admin to Remove</label>
    <select id="adminId" name="adminId">
      <c:forEach var="admin" items="${admins}">
        <option value="${admin.id}">${admin.name}</option>
      </c:forEach>
    </select>
    <input type="submit" value="Remove Admin">
  </form>
</div>
</body>
</html>
