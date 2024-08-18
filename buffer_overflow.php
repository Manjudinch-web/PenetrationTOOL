<?php
session_start();
/*
MD nur hossen rafel;
id: 104330222
this file is for testing
*/

// Check if the user is logged in
if (!isset($_SESSION['Name'] )) {
    // If not, redirect to the login page
    // header("Location: login.php");
    // exit();
}

// Get the session data
$name = $_SESSION['Name'];
//$email=$_SESSION['Email'];

// Set the page title
$title = "pentesting Page";
include 'common_header.php'; // Include the common header

?>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pen testing Page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="css/style.css">

</head>
<body>
    <div class="wrapper">
        <nav id="sidebar">
            <div class="sidebar-header">
                <h3>Welcome, <?php echo htmlspecialchars($name); ?>!</h3>
            </div>
            <ul class="list-unstyled components">
                <li class="active">
                    <a href="test.php">Home</a>
                </li>
                <li>
                    <a href="#">Profile</a>
                </li>
                <li>
                    <a href="#">Code Review</a>
                </li>
                <li>
                    <a href="#">Settings</a>
                </li>
                <li>
                    <a href="logout.php">Logout</a>
                </li>
            </ul>
        </nav>

        <div id="content" class="container mt-4">
            <h2>We are still working...</h2>
            
            <div id="validationResult" style="color: red;"></div>
        </div>
    </div>
    <?php include 'common_footer.php'; ?>
    <script src="validation.js"></script>
</body>
</html>
