<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="messages" />

<html>
<head>
    <title>Title</title>

    <link rel="stylesheet" href="<c:url value="/css/styles.css" />">

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
                        <div class="text-center language-buttons">
                            <a class="btn language-button" href="?lang=ea" id="englishLanguageButton"></a>
                            <a class="btn language-button" href="?lang=ua" id="ukrainianLanguageButton"></a>
                        </div>

                        <h3 class="text-center mt-4"><fmt:message key="loginPage.title" /></h3>

                        <div class="form-text text-center mt-4 mb-2">
                            <span><fmt:message key="loginPage.dontHaveAccount"/> <a href="/registration"><fmt:message key="loginPage.signUp" /></a></span>
                        </div>

                        <c:if test="${requestScope.authenticationException}">
                            <p class="text-center error-message mt-3"><fmt:message key="loginPage.exception.authentication" /></p>
                        </c:if>
                        <div>
                            <label for="username"><fmt:message key="loginPage.username" />:</label><br>
                            <input type="text" name="username" id="username" class="form-control">
                        </div>
                        <div>
                            <label for="password"><fmt:message key="loginPage.password" />:</label><br>
                            <input type="password" name="password" id="password" class="form-control">
                        </div>
                        <div class="text-center mt-3">
                            <button type="submit" class="btn btn-primary"><fmt:message key="loginPage.loginButton" /></button>
                        </div>

                        <div class="text-center mt-4">
                            <a class="btn btn-info" href="/catalog"><fmt:message key="loginPage.loginAsGuest" /></a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
