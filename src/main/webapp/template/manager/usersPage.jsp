<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tf" %>

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

<tf:chooseHeader role="${sessionScope.role}"/>

<div class="container">

        <div class="row user-block-header text-center mt-5 mb-3">
            <h2 class="mb-5"><fmt:message key="usersPage.title" /></h2>

            <div class="col-3 pt-2 pb-2"><fmt:message key="user.info.username" /></div>
            <div class="col-3 pt-2 pb-2"><fmt:message key="user.info.fullName" /></div>
            <div class="col-3 pt-2 pb-2"><fmt:message key="user.info.email" /></div>
            <div class="col-3 pt-2 pb-2"><fmt:message key="usersPage.action" /></div>
        </div>

        <c:forEach var="user" items="${requestScope.userList}">

            <div class="row user-block blocked-${user.blocked}">
                <div class="user-field col-3"><c:out value="${user.username}" /></div>
                <div class="user-field col-3"><c:out value="${user.firstName} ${user.lastName}" /></div>
                <div class="user-field col-3"><c:out value="${user.email}" /></div>

                <c:if test="${sessionScope.role.equals('ADMIN')}">
                    <form class="text-center col-1" action="/Manager/user" method="get">
                        <input type="text" name="userId" value="${user.id}" hidden>

                        <button type="submit" class="btn btn-primary form-control"><fmt:message key="accountButton" /></button>
                    </form>

                    <form class="text-center col-2" action="" method="post">
                        <input type="text" name="id" value="${user.id}" hidden>
                        <input type="text" name="isBlocked" value="${user.blocked}" hidden>

                        <c:if test="${user.blocked}">
                            <button type="submit" class="btn btn-primary blocked-user form-control"><fmt:message key="unblockButton" /></button>
                        </c:if>
                        <c:if test="${!user.blocked}">
                            <button type="submit" class="btn btn-primary notBlocked form-control"><fmt:message key="blockButton" /></button>
                        </c:if>
                    </form>

                </c:if>

                <c:if test="${sessionScope.role.equals('MANAGER')}">
                    <form class="text-center col-3" action="/Manager/user" method="get">
                        <input type="text" name="userId" value="${user.id}" hidden>

                        <button type="submit" class="btn btn-primary form-control"><fmt:message key="accountButton" /></button>
                    </form>
                </c:if>

            </div>

        </c:forEach>
</div>

<%@include file="../partial/footer.jspf" %>

</body>
</html>
