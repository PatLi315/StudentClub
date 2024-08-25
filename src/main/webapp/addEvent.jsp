<%--
  Created by IntelliJ IDEA.
  User: Patrick
  Date: 2024/8/23
  Time: 0:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Add Event</title>
</head>
<body>
<h2>Add Event</h2>
<form action="addEvent" method="post">
    <label for="title">Event Title:</label>
    <input type="text" id="title" name="title"><br><br>
    <label for="description">Event Description:</label>
    <textarea id="description" name="description"></textarea><br><br>
    <input type="submit" value="Add Event">
</form>
<a href="displayEvents">View Events</a>
</body>
</html>

