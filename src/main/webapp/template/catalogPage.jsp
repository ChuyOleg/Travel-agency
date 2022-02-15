<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/tld/myTagLib.tld" prefix="myTg"%>

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

            <h2 class="text-center">Catalog</h2>

            <c:if test="${requestScope.invalidPersonNumber}">
                <p class="text-center error-message">person number should be an integer</p>
            </c:if>

            <c:if test="${requestScope.invalidMinPrice}">
                <p class="text-center error-message">Min price should be correct number</p>
            </c:if>

            <c:if test="${requestScope.invalidMaxPrice}">
                <p class="text-center error-message">Max price should be correct number</p>
            </c:if>

            <div class="col-5 col-lg-3">

                <div class="filter-block pe-4 ps-4">
                    <form class="row filter-form" autocomplete="off" method="get" action="/catalog/filter">

                        <div class="mb-2">
                            <p class="text-center mt-2 mb-0">Tour types</p>
                            <c:forEach var="tourType" items="${requestScope.tourTypeList}">
                                <div class="row mt-2">
                                    <label class="col-8" for="${tourType.name()}">${tourType.name()}</label>
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
                            <p class="text-center mb-2">Hotel stars</p>
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
                                <label class="mt-3" for="personNumber">personNumber</label><br>
                                <input class="form-control" type="text" value="${requestScope.personNumber}" name="personNumber" id="personNumber"><br>
                            </div>
                        </div>

                        <div class="row justify-content-center">
                            <div class="text-center col-5">
                                <label for="minPrice">minPrice</label><br>
                                <input class="form-control" type="text" value="${requestScope.minPrice}" name="minPrice" id="minPrice"><br>
                            </div>

                            <div class="text-center col-5">
                                <label for="maxPrice">maxPrice</label><br>
                                <input class="form-control" type="text" value="${requestScope.maxPrice}" name="maxPrice" id="maxPrice">
                            </div>
                        </div>

                        <div class="text-center mt-2">
                            <button class="btn btn-primary" type="submit">Filter</button>
                        </div>
                    </form>
                </div>
            </div>


            <div class="col-6 col-lg-8 offset-1">
                <%--                <c:forEach begin="1" end="${requestScope.pagesNumber}" varStatus="loop">--%>
                <%--                    <input name="page" value="${loop.index}">--%>
                <%--                    <button class="btn btn-primary">${loop.index}</button>--%>
                <%--                </c:forEach>--%>

                <c:forEach var="tour" items="${requestScope.tourList}">
                    <div class="tour-block tour-burning-${tour.burning} mt-4 text-center">
                        <h2><c:out value="${tour.name}" /></h2>

                        <div class="row">
                            <div class="col-2">Country</div>
                            <div class="col-2">City</div>
                            <div class="col-1">Price</div>
                            <div class="col-3">Tour type</div>
                            <div class="col-3">Hotel stars</div>
                            <div class="col-1">Persons</div>
                        </div>

                        <div class="row">
                            <div class="col-2"><c:out value="${tour.city.country.country}" /></div>
                            <div class="col-2"><c:out value="${tour.city.city}" /></div>
                            <div class="col-1"><c:out value="${tour.price}$" /></div>
                            <div class="col-3"><c:out value="${tour.tourType.value}" /></div>
                            <div class="col-3"><myTg:stars hotelType="${tour.hotelType.value}" /></div>
                            <div class="col-1"><c:out value="${tour.personNumber}" /></div>
                        </div>

                        <div class="row justify-content-center mt-4">
                            <div class="col-8"><c:out value="${tour.startDate}  :  ${tour.endDate}" /></div>
                        </div>

                        <div class="justify-content-center">
                            <button class="btn btn-primary" type="button">See details</button>
                        </div>

                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</body>
</html>
