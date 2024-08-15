<?php
session_start();
include 'db.php'; // Include your database connection script

if (isset($_POST['email']) && isset($_POST['password'])) {
    $email = $_POST['email'];
    $password = $_POST['password'];

    // Prevent SQL injection
    $email = $connection->real_escape_string($email);
    $password = $connection->real_escape_string($password);

    // Query to check the user credentials
    $sql = "SELECT * FROM customer WHERE email='$email' AND password='$password'";
    $result = $connection->query($sql);

    if ($result->num_rows > 0) {
        // Successful login
        $row = $result->fetch_assoc();
        $_SESSION['Email'] = $row['email']; // Store user email in session
        $_SESSION['Name'] = $row['name']; // Store user name in session
        $_SESSION['IsAdmin'] = $row['is_admin'];
        header("Location: test.php"); // Redirect to a protected page
    } else {
        // Failed login
        header("Location: login.php?msg=failed");
    }
} else {
    // If the email or password is not set, redirect to the login page
    header("Location: login.php?msg=empty");
}
?>
