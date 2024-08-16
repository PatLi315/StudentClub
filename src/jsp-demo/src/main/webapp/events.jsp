<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Event List</title>
</head>
<body>
<h2>Events</h2>
<ul>
    <c:forEach var="event" items="${eventList}">
        <li>ID: ${event.id}, Name: ${event.name}
            <form action="eventServlet" method="get" style="display:inline;">
                <input type="hidden" name="id" value="${event.id}">
                <input type="hidden" name="action" value="remove">
                <input type="submit" value="Remove">
            </form>
        </li>
    </c:forEach>
</ul>

<h3>Add New Event</h3>
<form action="eventServlet" method="get">
    <input type="text" name="name" placeholder="Event Name" required>
    <input type="hidden" name="action" value="add">
    <input type="submit" value="Add" >
</form>
</body>
</html>
