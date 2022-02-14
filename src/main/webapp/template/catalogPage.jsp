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

        <form class="row filter-form" autocomplete="off" method="get" action="/catalog/filter">

            <div class="col-4 col-md-2 mt-2">
                <p class="mt-4 mb-0">Tour types</p>
                <c:forEach var="tourType" items="${requestScope.tourTypeList}">
                    <c:if test="${requestScope.containsKey(tourType.name())}">
                        <input type="checkbox" checked name="${tourType.name()}" id="${tourType.name()}">
                    </c:if>
                    <c:if test="${!requestScope.containsKey(tourType.name())}">
                        <input type="checkbox" name="${tourType.name()}" id="${tourType.name()}">
                    </c:if>
                    <label for="${tourType.name()}">${tourType.name()}</label><br>
                </c:forEach>
                <br>

                <p class="mb-0">Hotel stars</p>
                <c:forEach var="hotelType" items="${requestScope.hotelTypeList}">
                    <c:if test="${requestScope.containsKey(hotelType.name())}">
                        <input type="checkbox" checked name="${hotelType.name()}" id="${hotelType.name()}">
                    </c:if>
                    <c:if test="${!requestScope.containsKey(hotelType.name())}">
                        <input type="checkbox" name="${hotelType.name()}" id="${hotelType.name()}">
                    </c:if>
                    <label for="${hotelType.name()}">${hotelType.name()}</label><br>
                </c:forEach>

                <label class="mt-3" for="personNumber">personNumber</label><br>
                <input class="form-control" type="text" value="${requestScope.personNumber}" name="personNumber" id="personNumber"><br>
                <c:if test="${requestScope.invalidPersonNumber}">
                    <p class="error-message">person number should be an integer</p>
                </c:if>

                <label for="minPrice">minPrice</label><br>
                <input class="form-control" type="text" value="${requestScope.minPrice}" name="minPrice" id="minPrice"><br>
                <c:if test="${requestScope.invalidMinPrice}">
                    <p class="error-message">Min price should be correct number</p>
                </c:if>

                <label for="maxPrice">maxPrice</label><br>
                <input class="form-control" type="text" value="${requestScope.maxPrice}" name="maxPrice" id="maxPrice">
                <c:if test="${requestScope.invalidMaxPrice}">
                    <p class="error-message">Max price should be correct number</p>
                </c:if>

                <div class="text-center mt-2">
                    <button class="btn btn-primary" type="submit">Filter</button>
                </div>
            </div>

            <div class="col-6 col-md-8 offset-1">
                <c:forEach var="tour" items="${requestScope.tourList}">
                    <div class="tour-block tour-burning-${tour.burning} mt-4 text-center">
                        <div class=""><c:out value="${tour.name}" /></div>
                        <div><c:out value="${tour.city.country.country}: ${tour.city.city}" /></div>
                        <div><c:out value="${tour.price}$" /></div>
                        <div><c:out value="${tour.tourType.value}" /></div>
                        <div><c:out value="${tour.hotelType.value}" /></div>
                        <div><c:out value="${tour.personNumber} persons" /></div>
                        <div><c:out value="${tour.startDate} : ${tour.endDate}" /></div>
                        <div><c:out value="${tour.nightsNumber} nights" /></div>
                    </div>
                </c:forEach>
            </div>
        </form>
    </div>
</body>
</html>
