<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Admin</title>
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

        select, input[type="submit"] {
            padding: 10px;
            margin-top: 5px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }

        input[type="submit"] {
            margin-top: 20px;
            background-color: #28a745;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Add Admin to Club</h1>
    <form action="addAdmin" method="post">
        <label for="studentId">Select Student</label>
        <select id="studentId" name="studentId">
            <c:forEach var="student" items="${students}">
                <option value="${student.id}">${student.name}</option>
            </c:forEach>
        </select>

        <label for="clubId">Select Club</label>
        <select id="clubId" name="clubId">
            <c:forEach var="club" items="${clubs}">
                <option value="${club.id}">${club.name}</option>
            </c:forEach>
        </select>

        <input type="submit" value="Add Admin">
    </form>
</div>
</body>
</html>
