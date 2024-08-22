<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>register</title>
</head>
<body>
<h1><%= "Welcome to event creation board!" %></h1>
<br/>
<form action="hello-servlet" method="post">
    <tr>
        <td>name</td>
        <td>
            <input name="name" type="text" id="name">
            <br>
        </td>
    </tr>
    <tr>
        <td>date</td>
        <td>
            <input name="date" type="text" id="date">
            <br>
        </td>
    </tr>
    <input value="register" type="submit" id="reg_btn"><br>
</form>

</body>
</html>
