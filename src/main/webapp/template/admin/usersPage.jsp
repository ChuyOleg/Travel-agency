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


        <div class="row user-block-header text-center mt-5 mb-3">
            <h2 class="mb-5">Users</h2>

            <div class="col-3 pt-2 pb-2">Username</div>
            <div class="col-3 pt-2 pb-2">Full name</div>
            <div class="col-4 pt-2 pb-2">Email</div>
            <div class="col-1 pt-2 pb-2">Balance</div>
            <div class="col-1 pt-2 pb-2">Action</div>
        </div>

        <c:forEach var="user" items="${requestScope.userList}">

            <form class="text-center" action="" method="post">
                <div class="row user-block blocked-${user.blocked}">
                    <input type="text" name="id" value="${user.id}" hidden>
                    <input type="text" name="isBlocked" value="${user.blocked}" hidden>

                    <div class="user-field col-3"><c:out value="${user.username}" /></div>
                    <div class="user-field col-3"><c:out value="${user.firstName} ${user.lastName}" /></div>
                    <div class="user-field col-4"><c:out value="${user.email}" /></div>
                    <div class="user-field col-1"><c:out value="${user.money}$" /></div>

                    <c:if test="${user.blocked}">
                        <button type="submit" class="btn btn-primary blocked-user col-1">Unblock</button>
                    </c:if>
                    <c:if test="${!user.blocked}">
                        <button type="submit" class="btn btn-primary notBlocked col-1">Block</button>
                    </c:if>
                </div>
            </form>

        </c:forEach>
</div>

</body>
</html>
