<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Modify Event</title>
</head>
<body>
<h2>Modify Event</h2>
<form action="modifyEvent" method="post">
    <input type="hidden" name="eventId" value="${event.id}">
    <label for="title">Title:</label>
    <input type="text" id="title" name="title" value="${event.title}"><br><br>
    <label for="description">Description:</label>
    <textarea id="description" name="description">${event.description}</textarea><br><br>
    <label for="eventDate">Date:</label>
    <input type="date" id="eventDate" name="eventDate" value="${event.eventDate}"><br><br>
    <input type="submit" value="Update Event">
</form>
<a href="adminDashboard.jsp">Cancel</a>
</body>
</html>
