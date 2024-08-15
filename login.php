<!DOCTYPE html>
<!--
Md Nur hossen Rafel
id:104330222
this page for login interface
-->
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Login Page</title>
    <link rel="stylesheet" href="css/login.css"/>
</head>
<body>
    <div class="container">
        <div class="login-form">
            <form action="login_data.php" method="post" id="form-login">
                <fieldset>
                    <legend>Login</legend>
                    <div class="form-group">
                        <label for="email">Email</label>
                        <input type="text" id="username" name="email" placeholder="email" autocomplete="off">
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <input type="password" id="password" name="password" placeholder="Password">
                    </div>
                    <div class="checkbox">
                        <label>
                            <input type="checkbox"> Remember me
                        </label>
                        <label>Not Registered?</label>
                        <label><a href="registration.php"><strong>Sign Up Here</strong></a></label>
                    </div>
                    <input type="submit" class="btn" name="login" value="Login"/>
                    <div class="error-message">
                        <!-- PHP code to handle login message will go here -->
                        <?php 
                            if(isset($_GET['msg'])) {
                                echo "<div class=\"alert alert-danger\">
                                          <a href=\"login.php\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>
                                          <strong>Login Failed</strong><br> Insert Email or Password Correctly.
                                        </div>";
                            }
                        ?>
                    </div>
                </fieldset>
            </form>
        </div>
    </div>
</body>
</html>
