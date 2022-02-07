<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<div id="registration">
    <div class="container">
        <div id="registration-row" class="row justify-content-center align-items-center mt-4">
            <div id="registration-column" class="col-md-6">
                <div id="registration-box" class="col-md-12">
                    <form id="registration-form" class="form" action="" method="post">
                        <h3 class="text-center">Sign Up</h3>
                        <div class="form-group">
                            <label for="username">Username:</label><br>
                            <input type="text" name="username" id="username" class="form-control">
                        </div>
                        <div class="form-group">
                            <label for="password">Password:</label><br>
                            <input type="text" name="password" id="password" class="form-control">
                        </div>
                        <div class="form-group">
                            <label for="passwordCopy">Confirm Password:</label><br>
                            <input type="text" name="passwordCopy" id="passwordCopy" class="form-control">
                        </div>
                        <div class="form-group">
                            <label for="firstName">First Name:</label><br>
                            <input type="text" name="firstName" id="firstName" class="form-control">
                        </div>
                        <div class="form-group">
                            <label for="lastName">Last Name:</label><br>
                            <input type="text" name="lastName" id="lastName" class="form-control">
                        </div>
                        <div class="form-group">
                            <label for="email">Email:</label><br>
                            <input type="text" name="email" id="email" class="form-control">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
