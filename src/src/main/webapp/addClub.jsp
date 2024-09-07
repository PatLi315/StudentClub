<!DOCTYPE html>
<html>
<head>
    <title>Add New Club</title>
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
        input[type="text"] {
            padding: 8px;
            width: 300px;
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
<h2>Add a New Club</h2>
<form action="addClub" method="post">
    <label for="name">Club Name:</label>
    <input type="text" id="name" name="name" required>
    <input type="submit" value="Create Club">
</form>
</body>
</html>
