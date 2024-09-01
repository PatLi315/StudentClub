<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.unimelb.swen90007.studentclub.model.Club" %>
<!DOCTYPE html>
<html>
<head>
  <title>Club List</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 20px;
      background-color: #f4f4f4;
    }
    h2 {
      color: #333;
    }
    ul {
      list-style-type: none;
      padding: 0;
    }
    li {
      margin-bottom: 20px;
      padding: 10px;
      background-color: #fff;
      border: 1px solid #ddd;
    }
    strong {
      display: block;
      margin-bottom: 5px;
    }
  </style>
</head>
<body>
<h2>Club List</h2>
<ul>
  <%
    List<Club> clubs = (List<Club>) request.getAttribute("clubs");
    if (clubs != null && !clubs.isEmpty()) {
      for (Club club : clubs) {
  %>
  <li>
    <strong>ID:</strong> <%= club.getId() %>
    <strong>Name:</strong> <%= club.getName() %>
  </li>
  <%
    }
  } else {
  %>
  <li>No clubs found.</li>
  <%
    }
  %>
</ul>
</body>
</html>
