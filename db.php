<?php
$host = "localhost";
$port = 3306; 
$username = "root";
$password = "";
$database = "taxi_booking";

// Create connection
$connection = new mysqli($host, $username, $password, $database, $port);


// Check connection
if ($connection->connect_error) {
    die("Connection failed: " . $connection->connect_error);
}

?>
