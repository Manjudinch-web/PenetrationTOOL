<?php
session_start();
/*
MD nur hossen rafel;
id: 104330222
this file is for booking form
*/

// Check if the user is logged in
if (!isset($_SESSION['Name'] )) {
    // If not, redirect to the login page
    header("Location: login.php");
    exit();
}

// Get the session data
$name = $_SESSION['Name'];
//$email=$_SESSION['Email'];

// Set the page title
$title = "pentesting Page";
include 'common_header.php'; // Include the common header

?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pen testing Page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
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
                    <a href="booking.php">Home</a>
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
            <h2>Test Your Code</h2>
            <form action="process_test.php" method="post" class="row g-3">
                <div class="col-md-6">
                    <label for="passenger_name" class="form-label">Enter Your Code:</label>
                    <input type="text-area" id="passenger_name" name="passenger_name" class="form-control" required>
                </div>
                
                <div class="col-md-6">
                    <label for="pickup_datetime" class="form-label">Testing Date/Time:</label>
                    <input type="datetime-local" id="pickup_datetime" name="pickup_datetime" class="form-control" required>
                </div>
                <div class="col-12">
                    <button type="submit" class="btn btn-primary">Submit</button>
                </div>
            </form>
        </div>
    </div>
<?php
include 'common_footer.php'; // Include the common footer
?>
</body>
</html>
