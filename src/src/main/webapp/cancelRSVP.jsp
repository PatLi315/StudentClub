<!DOCTYPE html>
<html>
<head>
    <title>Cancel RSVP</title>
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
            background-color: #d9534f;
        }
    </style>
</head>
<body>
<h2>Cancel Your RSVP</h2>
<form action="cancelRSVP" method="post">
    <label for="studentId">Student ID:</label>
    <input type="number" id="studentId" name="studentId" required>

    <label for="eventId">Event ID:</label>
    <input type="number" id="eventId" name="eventId" required>

    <input type="submit" value="Cancel RSVP">
</form>
</body>
</html>
