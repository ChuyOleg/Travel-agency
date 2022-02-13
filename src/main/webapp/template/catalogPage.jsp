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
            <c:forEach var="tour" items="${requestScope.tourList}">
                <div class="tour-block tour-burning-${tour.burning} mt-5 text-center col-4 offset-1">
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
    </div>
</body>
</html>
