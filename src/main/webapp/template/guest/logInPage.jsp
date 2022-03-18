<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="messages" />

<html>
<head>
    <title>Title</title>

    <link rel="stylesheet" href="<c:url value="/static/css/styles.css" />">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>

<%@include file="../partial/languageButtons.jspf" %>

<div id="login">
    <div class="container">
        <div id="login-row" class="row justify-content-center align-items-center mt-4">
            <div class="login-form mt-5 p-4 col-lg-6 col-10">
                <form action="" method="post" autocomplete="off">
                    <h3 class="text-center mt-4"><fmt:message key="loginPage.title" /></h3>

                    <div class="form-text text-center mt-4 mb-2">
                        <span><fmt:message key="loginPage.dontHaveAccount"/> <a href="/registration"><fmt:message key="loginPage.signUp" /></a></span>
                    </div>

                    <c:if test="${requestScope.authenticationException}">
                        <p class="text-center error-message mt-3"><fmt:message key="loginPage.exception.authentication" /></p>
                    </c:if>
                    <c:if test="${requestScope.accountIsBlocked}">
                        <p class="text-center error-message mt-3"><fmt:message key="loginPage.exception.accountIsBlocked" /></p>
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

<%@include file="../partial/footer.jspf" %>

</body>
</html>
