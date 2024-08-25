<%--
  Created by IntelliJ IDEA.
  User: Patrick
  Date: 2024/8/23
  Time: 0:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.unimelb.swen90007.studentclub.model.Event"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
    <title>Display Events</title>
</head>
<body>
<h2>Event List</h2>
<%
    List<Event> eventList = (List<Event>) request.getAttribute("eventList");
    if (eventList != null && !eventList.isEmpty()) {
        for (Event event : eventList) {
            out.println("<p>ID: " + event.getId() + ", Title: " + event.getTitle() + ", Description: " + event.getDescription() + "</p>");
        }
    } else {
        out.println("<p>No events available.</p>");
    }
%>
<a href="addEvent.jsp">Add More Events</a>
</body>
</html>

