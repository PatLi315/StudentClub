<!DOCTYPE html>
<html>
<head>
    <title>Add Admin to Club</title>
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
        input[type="number"] {
            padding: 8px;
            width: 100px;
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
<h2>Add Admin to Club</h2>
<form action="addAdmin" method="post">
    <label for="studentId">Student ID:</label>
    <input type="number" id="studentId" name="studentId" required>

    <label for="clubId">Club ID:</label>
    <input type="number" id="clubId" name="clubId" required>

    <input type="submit" value="Add Admin">
</form>
</body>
</html>
