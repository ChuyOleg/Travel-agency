<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>

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
<div id="registration">
    <div class="container">
        <div id="registration-row" class="row justify-content-center align-items-center mt-4">
            <div class="col-10 col-lg-6 p-4 registration-form">
                <form action="${pageContext.request.contextPath}/registration" method="post" autocomplete="off">
                    <div class="text-center">
                        <jsp:include page="partial/languageButtons.jspx" />
                    </div>

                    <h3 class="text-center mt-3"><fmt:message key="registrationPage.title" /></h3>

                    <div class="form-group">
                        <label for="username"><fmt:message key="registrationPage.username" />:</label><br>
                        <input type="text" name="username" value="${requestScope.username}" id="username" maxlength="64" class="form-control">
                    </div>
                    <c:if test="${requestScope.usernameSizeOutOfBoundsException}">
                    <p class="error-message"><fmt:message key="registrationPage.exception.usernameSizeOutOfBounds" /></p>
                    </c:if>
                    <c:if test="${requestScope.usernameIsReserved}">
                    <p class="error-message"><fmt:message key="registrationPage.exception.usernameIsReserved" /></p>
                    </c:if>

                    <div class="form-group">
                        <label for="password"><fmt:message key="registrationPage.password" />:</label><br>
                        <input type="password" name="password" id="password" maxlength="64" class="form-control">
                    </div>
                    <c:if test="${requestScope.passwordSizeOutOfBoundsException}">
                    <p class="error-message"><fmt:message key="registrationPage.exception.passwordSizeOutOfBounds" /></p>
                    </c:if>
                    <c:if test="${requestScope.passwordNotMatchTemplateException}">
                    <p class="error-message"><fmt:message key="registrationPage.exception.passwordNotMatchTemplate" /></p>
                    </c:if>

                    <div class="form-group">
                        <label for="passwordCopy"><fmt:message key="registrationPage.confirmPassword" />:</label><br>
                        <input type="password" name="passwordCopy" id="passwordCopy" class="form-control">
                    </div>
                    <c:if test="${requestScope.passwordsNotMatchException}">
                    <p class="error-message"><fmt:message key="registrationPage.exception.passwordsNotMatch" /></p>
                    </c:if>

                    <div class="form-group">
                        <label for="firstName"><fmt:message key="registrationPage.firstName" />:</label><br>
                        <input type="text" name="firstName" value="${requestScope.firstName}" id="firstName" maxlength="32" class="form-control">
                    </div>
                    <c:if test="${requestScope.firstNameSizeOutOfBoundsException}">
                    <p class="error-message"><fmt:message key="registrationPage.exception.firstNameSizeOutOfBounds" /></p>
                    </c:if>

                    <div class="form-group">
                        <label for="lastName"><fmt:message key="registrationPage.lastName" />:</label><br>
                        <input type="text" name="lastName" value="${requestScope.lastName}" id="lastName" maxlength="32" class="form-control">
                    </div>
                    <c:if test="${requestScope.lastNameSizeOutOfBoundsException}">
                    <p class="error-message"><fmt:message key="registrationPage.exception.lastNameSizeOutOfBounds" /></p>
                    </c:if>

                    <div class="form-group">
                        <label for="email"><fmt:message key="registrationPage.email" />:</label><br>
                        <input type="text" name="email" value="${requestScope.email}" id="email" maxlength="128" class="form-control">
                    </div>
                    <c:if test="${requestScope.emailSizeOutOfBoundsException}">
                    <p class="error-message"><fmt:message key="registrationPage.exception.emailSizeOutOfBounds" /></p>
                    </c:if>
                    <c:if test="${requestScope.emailNotMatchTemplateException}">
                    <p class="error-message"><fmt:message key="registrationPage.exception.emailNotMatchTemplate" /></p>
                    </c:if>

                    <div class="form-text text-center mt-4 mb-2">
                        <span><fmt:message key="registrationPage.alreadyHaveAnAccount"/> <a href="/login"><fmt:message key="registrationPage.signIn" /></a></span>
                    </div>

                    <div class="text-center mt-3">
                        <button type="submit" class="btn btn-primary"><fmt:message key="registrationPage.signUpButton" /></button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
