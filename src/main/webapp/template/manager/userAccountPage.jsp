<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/tld/myTagLib.tld" prefix="myTg"%>
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

<tf:chooseHeader role="${sessionScope.role}" pathToWebAppFolder="../../" />

<div class="container">

    <div class="text-center mt-4">
        <h3>User: ${requestScope.user.username} (id=${requestScope.user.id})</h3>
    </div>

    <div class="row justify-content-center mt-4">
        <div class="col-3 text-center user-info-cell p-2">
            Full name
        </div>
        <div class="col-3 text-center user-info-cell p-2">
            Email
        </div>
        <div class="col-2 text-center user-info-cell p-2">
            Balance
        </div>
    </div>

    <div class="row justify-content-center">
        <div class="col-3 text-center user-info-cell p-2">
            ${requestScope.user.firstName} ${requestScope.user.lastName}
        </div>
        <div class="col-3 text-center user-info-cell p-2">
            ${requestScope.user.email}
        </div>
        <div class="col-2 text-center user-info-cell p-2">
            ${requestScope.user.money}$
        </div>
    </div>

    <h3 class="text-center mt-5 mb-3">Tours</h3>

    <div class="row">
        <c:forEach var="order" items="${requestScope.orderList}">
            <div class="col-6 tour-wrapper justify-content-center mb-5">
                <div class="tour-block tour-status-${order.status.value.name()} text-center">
                    <h3 class="text-center tour-name pt-2 pb-2"><c:out value="${order.tour.name}" /></h3>

                    <div class="row justify-content-center">
                        <div class="col-2">Country</div>
                        <div class="col-2">City</div>
                        <div class="col-2">Final price</div>
                        <div class="col-3">Registration date</div>
                        <div class="col-3">Status</div>
                    </div>

                    <div class="row">
                        <div class="col-2"><c:out value="${order.tour.city.country.country}" /></div>
                        <div class="col-2"><c:out value="${order.tour.city.city}" /></div>
                        <div class="col-2"><c:out value="${order.finalPrice}$" /></div>
                        <div class="col-3"><c:out value="${order.creationDate}" /></div>
                        <div class="col-3"><c:out value="${order.status.value}" /></div>
                    </div>

                    <div class="row justify-content-center mt-4 mb-2">
                        <div class="col-8 tour-date"><c:out value="${order.tour.startDate} : ${order.tour.endDate}" /></div>
                    </div>

                </div>

                <c:if test="${order.status.value.name().equals('REGISTERED')}">
                    <div class="row justify-content-center">
                        <form class="text-center col-3" method="post" action="/order/changeStatus">
                            <input type="text" name="orderId" value="${order.id}" hidden>
                            <input type="text" name="userId" value="${requestScope.user.id}" hidden>
                            <input type="text" name="newStatus" value="PAID" hidden>

                            <button class="btn btn-success form-control" type="submit">Confirm</button>
                        </form>

                        <form class="text-center col-3" method="post" action="/order/changeStatus">
                            <input type="text" name="orderId" value="${order.id}" hidden>
                            <input type="text" name="userId" value="${requestScope.user.id}" hidden>
                            <input type="text" name="newStatus" value="CANCELED" hidden>

                            <button class="btn btn-danger form-control" type="submit">Cancel</button>
                        </form>
                    </div>
                </c:if>

            </div>
        </c:forEach>
    </div>

</div>

<jsp:include page="../partial/footer.jspx" />

</body>
</html>
