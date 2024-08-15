<!-- header.php -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><?php echo $title; ?></title> <!-- Dynamic title -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/header.css"> <!-- Your custom styles -->
</head>
<body>
    <header>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <a class="navbar-brand" href="#">Pen testing tool</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="booking.php">Home</a>
                    </li>
                    
                    <li class="nav-item">
                        <a class="nav-link" href="admin_booking.php">Buffer Overflow</a>
                    </li><li class="nav-item">
                        <a class="nav-link" href="admin_booking.php">Improper input validation</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="admin_booking.php">Data serialization</a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="admin_rego.php">Admin Sign Up</a>
                    </li>
                    
                    <li class="nav-item">
                        <a class="nav-link" href="registration.php">Sign Up</a>
                    </li>
                </ul>
            </div>
        </nav>
    </header>
