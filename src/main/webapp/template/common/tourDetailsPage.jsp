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

<tf:chooseHeader role="${sessionScope.role}"/>

<div class="container">
    <div class="row">

        <h2 class="text-center"><fmt:message key="tourDetailsPage.title" /></h2>

        <c:if test="${requestScope.tourIsBought}">
            <h3 class="text-center mt-4"><fmt:message key="tourDetailsPage.alreadyBoughtMessage" /></h3>
        </c:if>

        <c:if test="${sessionScope.role.equals('ADMIN') && param.error != null}">
            <p class="text-center error-message mt-4"><fmt:message key="tourDetailsPage.cannotDeleteMessage" /></p>
        </c:if>

        <div class="col-10 col-lg-10 offset-1">

            <div class="tour-wrapper justify-content-center mt-4 mb-5">
                <div class="tour-block tour-burning-${requestScope.tour.burning} text-center">
                    <h3 class="text-center tour-name pt-2 pb-2"><c:out value="${requestScope.tour.name}" /></h3>

                    <div class="row justify-content-center">
                        <div class="col-2"><fmt:message key="tour.info.country" /></div>
                        <div class="col-2"><fmt:message key="tour.info.city" /></div>
                        <div class="col-1"><fmt:message key="tour.info.price" /></div>
                        <div class="col-3"><fmt:message key="tour.info.tourType" /></div>
                        <div class="col-2"><fmt:message key="catalogPage.hotelStars" /></div>
                        <div class="col-2"><fmt:message key="tour.info.persons" /></div>
                    </div>

                    <div class="row">
                        <div class="col-2"><c:out value="${requestScope.tour.city.country.country}" /></div>
                        <div class="col-2"><c:out value="${requestScope.tour.city.city}" /></div>
                        <div class="col-1"><c:out value="${requestScope.tour.price}$" /></div>
                        <div class="col-3"><fmt:message key="tourType.${requestScope.tour.tourType.value}" /></div>
                        <div class="col-2"><myTg:stars hotelType="${requestScope.tour.hotelType.value}" /></div>
                        <div class="col-2"><c:out value="${requestScope.tour.personNumber}" /></div>
                    </div>

                    <div class="row justify-content-center mt-4 mb-2">
                        <div class="col-8 tour-date"><c:out value="${requestScope.tour.startDate}  :  ${requestScope.tour.endDate}" /></div>
                        <div class="tour-nights"><c:out value="${requestScope.tour.nightsNumber} nights" /></div>
                    </div>

                    <c:if test="${requestScope.finalPrice != null}">
                        <h3 class="mb-3"><fmt:message key="tourDetailsPage.yourPrice" /> -> ${requestScope.finalPrice}$</h3>
                    </c:if>

                </div>

                <c:if test="${sessionScope.role.equals('USER') && !requestScope.tourIsBought}">
                    <form class="text-center" method="post" action="/User/tour/buy">
                        <input type="text" name="tourId" value="${requestScope.tour.id}" hidden>
                        <button class="btn btn-primary pe-4 ps-4"><fmt:message key="buyButton" /></button>
                    </form>
                </c:if>

                <c:if test="${sessionScope.role.equals('UNKNOWN')}">
                    <h3 class="text-center mt-3"><fmt:message key="tourDetailsPage.loginToBuyMessage" /></h3>
                </c:if>

                <c:if test="${sessionScope.role.equals('MANAGER') || sessionScope.role.equals('ADMIN')}">
                    <form class="text-center" method="post" action="/Manager/tour/changeBurningState">
                        <input type="text" name="tourId" value="${requestScope.tour.id}" hidden>

                        <c:if test="${requestScope.tour.burning}">
                            <button type="submit" class="btn btn-primary pe-4 ps-4"><fmt:message key="makeNonBurningButton" /></button>
                        </c:if>
                        <c:if test="${!requestScope.tour.burning}">
                            <button type="submit" class="btn btn-primary pe-4 ps-4"><fmt:message key="makeBurningButton" /></button>
                        </c:if>
                    </form>

                    <form class="text-center" method="get" action="/Manager/tour/changeDiscount">
                        <input type="text" name="tourId" value="${requestScope.tour.id}" hidden>

                        <button type="submit" class="btn btn-primary"><fmt:message key="changeDiscountButton" /></button>
                    </form>
                </c:if>

                <c:if test="${sessionScope.role.equals('ADMIN')}">
                    <form class="text-center" method="get" action="/Admin/tour/update">
                        <input type="text" name="tourId" value="${requestScope.tour.id}" hidden>

                        <button type="submit" class="btn btn-primary"><fmt:message key="updateButton" /></button>
                    </form>

                    <form class="text-center" method="post" action="/Admin/tour/delete">
                        <input type="text" name="tourId" value="${requestScope.tour.id}" hidden>

                        <button type="submit" class="btn btn-danger"><fmt:message key="deleteButton" /></button>
                    </form>
                </c:if>

            </div>
        </div>
    </div>
</div>

<%@include file="../partial/footer.jspf" %>

</body>
</html>
