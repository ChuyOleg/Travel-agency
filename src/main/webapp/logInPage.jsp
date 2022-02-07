<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="ua" />
<fmt:setBundle basename="messages" />

<html>
<head>
    <title>Title</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<div id="login">
    <div class="container">
        <div id="login-row" class="row justify-content-center align-items-center mt-4">
            <div id="login-column" class="col-md-6">
                <div id="login-box" class="col-md-12">
                    <form id="login-form" class="form" action="" method="post">
                        <h3 class="text-center"><fmt:message key="loginPage.login" /></h3>
                        <div>
                            <label for="username"><fmt:message key="loginPage.username" />:</label><br>
                            <input type="text" name="username" id="username" class="form-control">
                        </div>
                        <div>
                            <label for="password"><fmt:message key="loginPage.password" />:</label><br>
                            <input type="text" name="password" id="password" class="form-control">
                        </div>
                        <div class="text-center mt-3">
                            <button type="button" class="btn btn-primary"><fmt:message key="loginPage.loginButton" /></button>
                        </div>

                        <a class="login-guest" href="/catalog">Log in as a Guest</a>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
