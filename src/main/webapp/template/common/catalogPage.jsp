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
        <h2 class="text-center"><fmt:message key="catalogPage.title" /></h2>

        <c:if test="${requestScope.invalidPersonNumber}">
            <p class="text-center error-message"><fmt:message key="catalogPage.exception.personNumber" /></p>
        </c:if>

        <c:if test="${requestScope.invalidMinPrice}">
            <p class="text-center error-message"><fmt:message key="catalogPage.exception.minPrice" /></p>
        </c:if>

        <c:if test="${requestScope.invalidMaxPrice}">
            <p class="text-center error-message"><fmt:message key="catalogPage.exception.maxPrice" /></p>
        </c:if>

        <div class="col-5 col-lg-3">

            <div class="filter-block pe-4 ps-4 mb-5">
                <form class="row filter-form" autocomplete="off" method="get" action="/catalog">
                    <input name="page" value="1" hidden>

                    <div class="mb-2">
                        <p class="text-center mt-2 mb-0"><fmt:message key="catalogPage.tourTypes" /></p>
                        <c:forEach var="tourType" items="${requestScope.tourTypeList}">
                            <div class="row mt-2">
                                <label class="col-8" for="${tourType.name()}"><fmt:message key="tourType.${tourType.name()}" /></label>
                                <c:if test="${requestScope.containsKey(tourType.name())}">
                                    <input class="col-4" type="checkbox" checked name="${tourType.name()}" id="${tourType.name()}">
                                </c:if>
                                <c:if test="${!requestScope.containsKey(tourType.name())}">
                                    <input class="col-4" type="checkbox" name="${tourType.name()}" id="${tourType.name()}">
                                </c:if>
                            </div>
                        </c:forEach>
                    </div>

                    <div class="mb-2">
                        <p class="text-center mb-2"><fmt:message key="catalogPage.hotelStars" /></p>
                        <c:forEach var="hotelType" items="${requestScope.hotelTypeList}">
                            <div class="row mt-2">
                                <label class="col-8" for="${hotelType.name()}"><myTg:stars hotelType="${hotelType.name()}" /></label>
                                <c:if test="${requestScope.containsKey(hotelType.name())}">
                                    <input class="col-4" type="checkbox" checked name="${hotelType.name()}" id="${hotelType.name()}">
                                </c:if>
                                <c:if test="${!requestScope.containsKey(hotelType.name())}">
                                    <input class="col-4" type="checkbox" name="${hotelType.name()}" id="${hotelType.name()}">
                                </c:if>
                            </div>

                        </c:forEach>

                    </div>

                    <div class="row justify-content-center">
                        <div class="col-6 text-center">
                            <label class="mt-3" for="personNumber"><fmt:message key="catalogPage.personNumber" /></label><br>
                            <input class="form-control" type="text" value="${requestScope.personNumber}" name="personNumber" id="personNumber"><br>
                        </div>
                    </div>

                    <div class="row justify-content-center">
                        <div class="text-center col-5">
                            <label for="minPrice"><fmt:message key="catalogPage.minPrice" /></label><br>
                            <input class="form-control" type="text" value="${requestScope.minPrice}" name="minPrice" id="minPrice"><br>
                        </div>

                        <div class="text-center col-5">
                            <label for="maxPrice"><fmt:message key="catalogPage.maxPrice" /></label><br>
                            <input class="form-control" type="text" value="${requestScope.maxPrice}" name="maxPrice" id="maxPrice">
                        </div>
                    </div>

                    <div class="text-center mt-2">
                        <button class="btn btn-primary" type="submit"><fmt:message key="filterButton" /></button>
                    </div>
                </form>
            </div>
        </div>


        <div class="col-6 col-lg-8 offset-1">

            <c:if test="${requestScope.pagesNumber > 0}">
                <ul class="pagination-list text-center justify-content-center">
                    <li><button type="button" class="btn btn-primary" <c:if test="${requestScope.activePageNumber <= 1}">disabled</c:if> id="prevPageButton"><</button> </li>

                    <li value="${requestScope.activePageNumber}" class="pageNumber" id="pageNumber">${requestScope.activePageNumber}</li>

                    <li><button type="button" class="btn btn-primary" <c:if test="${requestScope.activePageNumber >= requestScope.pagesNumber}">disabled</c:if> id="nextPageButton">></button> </li>
                </ul>
            </c:if>
            <c:if test="${requestScope.pagesNumber <= 0}">
                <h2 class="text-center"><fmt:message key="catalogPage.noToursAccordingToFilter" /></h2>
            </c:if>


            <c:forEach var="tour" items="${requestScope.tourList}">
                <div class="tour-wrapper justify-content-center mb-5">
                    <div class="tour-block tour-burning-${tour.burning} text-center">
                        <h3 class="text-center tour-name pt-2 pb-2"><c:out value="${tour.name}" /></h3>

                        <div class="row justify-content-center">
                            <div class="col-2"><fmt:message key="tour.info.country" /></div>
                            <div class="col-2"><fmt:message key="tour.info.city" /></div>
                            <div class="col-1"><fmt:message key="tour.info.price" /></div>
                            <div class="col-3"><fmt:message key="tour.info.tourType" /></div>
                            <div class="col-2"><fmt:message key="tour.info.hotelType" /></div>
                            <div class="col-2"><fmt:message key="tour.info.persons" /></div>
                        </div>

                        <div class="row">
                            <div class="col-2"><c:out value="${tour.city.country.country}" /></div>
                            <div class="col-2"><c:out value="${tour.city.city}" /></div>
                            <div class="col-1"><c:out value="${tour.price}$" /></div>
                            <div class="col-3"><fmt:message key="tourType.${tour.tourType.value}" /></div>
                            <div class="col-2"><myTg:stars hotelType="${tour.hotelType.value}" /></div>
                            <div class="col-2"><c:out value="${tour.personNumber}" /></div>
                        </div>

                        <div class="row justify-content-center mt-4 mb-2">
                            <div class="col-8 tour-date"><c:out value="${tour.startDate}  :  ${tour.endDate}" /></div>
                        </div>

                    </div>

                    <form class="text-center" method="get" action="/tour/details">
                        <input type="text" name="id" value="${tour.id}" hidden>
                        <button class="btn btn-primary" type="submit"><fmt:message key="seeDetailsButton" /></button>
                    </form>
                </div>
            </c:forEach>
        </div>
    </div>
</div>

<%@include file="../partial/footer.jspf" %>

<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/pagination.js"></script>
</body>
</html>
