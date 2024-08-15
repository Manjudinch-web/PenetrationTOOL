<?php
// Connect to MySQL database
/*
MD nur hossen rafel;
id: 104330222
this file user registration form
*/
$host = "localhost";
$port = 3306; 
$username = "root";
$password = "";
$database = "taxi_booking";

$conn = new mysqli($host, $username, $password, $database, $port);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

// Function to sanitize input data
function sanitize_input($data) {
    return htmlspecialchars(stripslashes(trim($data)));
}

// Set the page title
$title = "pen testing Page";
include 'common_header.php';

// Initialize variables
$customer_name = $password = $password_retyped = $email = $phone_number = "";
$error_message = "";

// Register new customer
if ($_SERVER["REQUEST_METHOD"] == "POST" && isset($_POST['register'])) {
    $customer_name = sanitize_input($_POST['name']);
    $password = sanitize_input($_POST['password']);
    $password_retyped = sanitize_input($_POST['password_retyped']);
    $email = sanitize_input($_POST['email']);
    $phone_number = sanitize_input($_POST['phone']);

    // Validate inputs
    if (empty($customer_name) || empty($password) || empty($password_retyped) || empty($email) || empty($phone_number)) {
        $error_message = "All fields are required.";
    } elseif ($password != $password_retyped) {
        $error_message = "Passwords do not match.";
    } else {
        // Check if email already exists
        $check_email_query = "SELECT * FROM customer WHERE email = '$email'";
        $check_email_result = $conn->query($check_email_query);

        if ($check_email_result->num_rows > 0) {
            $error_message = "Email address already registered.";
        } else {
            // Insert new customer into database
            $insert_query = "INSERT INTO customer (name, password, email, phone) VALUES ('$customer_name', '$password', '$email', '$phone_number')";
            if ($conn->query($insert_query) === TRUE) {
                // Redirect to booking page with email address
                echo "<script>alert('Registration successful! Redirecting to booking page...'); window.location.href='booking.php?email=$email';</script>";
                exit();
            } else {
                $error_message = "Error: " . $conn->error;
            }
        }
    }
}

// Login existing customer
if ($_SERVER["REQUEST_METHOD"] == "POST" && isset($_POST['login'])) {
    $email = sanitize_input($_POST['email']);
    $password = sanitize_input($_POST['password']);

    // Check credentials
    $login_query = "SELECT * FROM customer WHERE email = '$email' AND password = '$password'";
    $login_result = $conn->query($login_query);

    if ($login_result->num_rows > 0) {
        // Redirect to booking page with email address
        header("Location: booking.php?email=$email");
        exit();
    } else {
        $login_error = "Invalid email address or password.";
        echo "<p>Error: $login_error</p>";
    }
}

$conn->close();
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Booking Page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="wrapper">
        <nav id="sidebar">
            <div class="sidebar-header">
                <h3>Welcome!</h3>
            </div>
            <ul class="list-unstyled components">
                <li class="active">
                    <a href="booking.php">Home</a>
                </li>
                <li>
                    <a href="#">Profile</a>
                </li>
                <li>
                    <a href="#">Booking History</a>
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
            <h2>User Registration</h2>
            <form action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]); ?>" method="post" class="row g-3">
                <div class="col-md-6">
                    <label for="name" class="form-label">Customer Name:</label>
                    <input type="text" id="name" name="name" value="<?php echo htmlspecialchars($customer_name); ?>" class="form-control" required>
                </div>
                <div class="col-md-6">
                    <label for="password" class="form-label">Password:</label>
                    <input type="password" id="password" name="password" class="form-control" required>
                </div>
                <div class="col-md-6">
                    <label for="password_retyped" class="form-label">Retype Password:</label>
                    <input type="password" id="password_retyped" name="password_retyped" class="form-control" required>
                </div>
                <div class="col-md-6">
                    <label for="email" class="form-label">Email Address:</label>
                    <input type="email" id="email" name="email" value="<?php echo htmlspecialchars($email); ?>" class="form-control" required>
                </div>
                <div class="col-md-6">
                    <label for="phone" class="form-label">Phone Number:</label>
                    <input type="text" id="phone" name="phone" value="<?php echo htmlspecialchars($phone_number); ?>" class="form-control">
                </div>
                <div class="col-12">
                    <input type="submit" value="Register" name="register" class="btn btn-primary">
                </div>
                <?php
                if (!empty($error_message)) {
                    echo '<div class="col-12"><p class="text-danger">' . $error_message . '</p></div>';
                }
                ?>
            </form>
        </div>
    </div>
<?php
include 'common_footer.php'; // Include the common footer
?>
</body>
</html>
