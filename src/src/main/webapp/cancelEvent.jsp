<!DOCTYPE html>
<html>
<head>
    <title>Cancel Event</title>
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
            background-color: #d9534f;
            color: #fff;
            border: none;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #c9302c;
        }
    </style>
</head>
<body>
<h2>Cancel an Event</h2>
<form action="cancelEvent" method="post">
    <label for="id">Event ID:</label>
    <input type="number" id="id" name="id" required>
    <input type="submit" value="Cancel Event">
</form>
</body>
</html>
