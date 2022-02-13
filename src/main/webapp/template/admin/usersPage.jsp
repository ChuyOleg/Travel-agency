<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
<div class="container">
    <div class="row">
        <c:forEach var="user" items="${requestScope.userList}">

            <form class="mt-3 text-center col-4 offset-1" action="" method="post">
                <div class="user-block blocked-${user.blocked}">
                    <input type="text" name="id" value="${user.id}" hidden>
                    <input type="text" name="isBlocked" value="${user.blocked}" hidden>

                    <div class="user-field"><c:out value="${user.username}" /></div>
                    <div class="user-field"><c:out value="${user.firstName} ${user.lastName}" /></div>
                    <div class="user-field"><c:out value="${user.email}" /></div>
                    <div class="user-field"><c:out value="${user.money}$" /></div>
                </div>

                <c:if test="${user.blocked}">
                    <button type="submit" class="btn btn-primary blocked-user">Unblock</button>
                </c:if>
                <c:if test="${!user.blocked}">
                    <button type="submit" class="btn btn-primary notBlocked">Block</button>
                </c:if>

            </form>

        </c:forEach>
    </div>
</div>

</body>
</html>
