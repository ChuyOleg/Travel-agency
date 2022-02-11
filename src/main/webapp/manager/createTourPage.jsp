<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="messages" />


<html>
<head>
    <title>Title</title>

    <link rel="stylesheet" href="<c:url value="/css/styles.css" />">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<div id="tour">
    <div class="container">
        <div id="tour-row" class="row justify-content-center align-items-center mt-4">
            <div id="tour-column" class="col-md-6">
                <div id="tour-box" class="col-md-12">
                    <form id="tour-form" autocomplete="off" class="form" action="" method="post">
                        <h3 class="text-center mt-2"><fmt:message key="createTourPage.title" />:</h3>

                        <div>
                            <label for="name"><fmt:message key="createTourPage.name" />:</label><br>
                            <input type="text" value="${requestScope.name}" name="name" id="name" class="form-control">
                        </div>
                        <c:if test="${requestScope.nameIsEmptyException}">
                            <p class="error-message"><fmt:message key="createTourPage.exception.nameIsEmptyException" /></p>
                        </c:if>

                        <div>
                            <label for="price"><fmt:message key="createTourPage.price" />:</label><br>
                            <input type="text" value="${requestScope.price}" name="price" id="price" class="form-control">
                        </div>
                        <c:if test="${requestScope.priceIsNotValidException}">
                            <p class="error-message"><fmt:message key="createTourPage.exception.priceIsNotValidException" /></p>
                        </c:if>

                        <div>
                            <label for="country"><fmt:message key="createTourPage.country" />:</label><br>
                            <input type="text" value="${requestScope.country}" name="country" id="country" class="form-control">
                        </div>
                        <c:if test="${requestScope.countryIsEmptyException}">
                            <p class="error-message"><fmt:message key="createTourPage.exception.countryIsEmptyException" /></p>
                        </c:if>
                        <c:if test="${requestScope.countryIsUndefined}">
                            <p class="error-message"><fmt:message key="createTourPage.exception.countryIsUndefined" /></p>
                        </c:if>

                        <div>
                            <label for="city"><fmt:message key="createTourPage.city" />:</label><br>
                            <input type="text" value="${requestScope.city}" name="city" id="city" class="form-control">
                        </div>
                        <c:if test="${requestScope.cityIsEmptyException}">
                            <p class="error-message"><fmt:message key="createTourPage.exception.cityIsEmptyException" /></p>
                        </c:if>
                        <c:if test="${requestScope.cityIsUndefined}">
                            <p class="error-message"><fmt:message key="createTourPage.exception.cityIsUndefined" /></p>
                        </c:if>

                        <div>
                            <label for="description"><fmt:message key="createTourPage.description" />:</label><br>
                            <textarea name="description" id="description" class="form-control"><c:out value="${requestScope.description}" />
                            </textarea>
                        </div>
                        <c:if test="${requestScope.descriptionIsEmptyException}">
                            <p class="error-message"><fmt:message key="createTourPage.exception.descriptionIsEmptyException" /></p>
                        </c:if>

                        <div>
                            <label for="maxDiscount"><fmt:message key="createTourPage.maxDiscount" />:</label><br>
                            <input type="text" value="${requestScope.maxDiscount}" name="maxDiscount" id="maxDiscount" class="form-control">
                        </div>
                        <c:if test="${requestScope.maxDiscountIsNotValidException}">
                            <p class="error-message"><fmt:message key="createTourPage.exception.maxDiscountIsNotValidException" /></p>
                        </c:if>

                        <div>
                            <label for="discountStep"><fmt:message key="createTourPage.discountStep" />:</label><br>
                            <input type="text" value="${requestScope.discountStep}" name="discountStep" id="discountStep" class="form-control">
                        </div>
                        <c:if test="${requestScope.discountStepIsNotValidException}">
                            <p class="error-message"><fmt:message key="createTourPage.exception.discountStepIsNotValidException" /></p>
                        </c:if>

                        <div class="text-center mt-2">
                            <label for="tourType"><fmt:message key="createTourPage.tourType" />:</label><br>
                            <select name="tourType" id="tourType">
                                <option value="" hidden></option>
                                <c:forEach var="tourType" items="${requestScope.tourTypeList}">
                                    <c:if test="${requestScope.tourType.equals(tourType.name())}" >
                                        <option selected value="${tourType}"><c:out value="${tourType}" /></option>
                                    </c:if>
                                    <c:if test="${!requestScope.tourType.equals(tourType.name())}" >
                                        <option value="${tourType}"><c:out value="${tourType}" /></option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="text-center">
                            <c:if test="${requestScope.tourTypeIsEmptyException}">
                                <p class="error-message"><fmt:message key="createTourPage.exception.tourTypeIsEmptyException" /></p>
                            </c:if>
                        </div>

                        <div class="text-center mt-2">
                            <label for="hotelType"><fmt:message key="createTourPage.hotelType" />:</label><br>
                            <select name="hotelType" id="hotelType">
                                <option value="" hidden></option>
                                <c:forEach var="hotelType" items="${requestScope.hotelTypeList}">
                                    <c:if test="${requestScope.hotelType.equals(hotelType.name())}">
                                        <option selected value="${hotelType}"><c:out value="${hotelType}" /></option>
                                    </c:if>
                                    <c:if test="${!requestScope.hotelType.equals(hotelType.name())}">
                                        <option value="${hotelType}"><c:out value="${hotelType}" /></option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="text-center">
                            <c:if test="${requestScope.hotelTypeIsEmptyException}">
                                <p class="error-message"><fmt:message key="createTourPage.exception.hotelTypeIsEmptyException" /></p>
                            </c:if>
                        </div>

                        <div>
                            <label for="personNumber"><fmt:message key="createTourPage.personNumber" />:</label><br>
                            <input type="text" value="${requestScope.personNumber}" name="personNumber" id="personNumber" class="form-control">
                        </div>
                        <c:if test="${requestScope.personNumberIsNotValidException}">
                            <p class="error-message"><fmt:message key="createTourPage.exception.personNumberIsNotValidException" /></p>
                        </c:if>

                        <div>
                            <label for="startDate"><fmt:message key="createTourPage.startDate" />:</label><br>
                            <input type="date" value="${requestScope.startDate}" name="startDate" id="startDate" class="form-control">
                        </div>
                        <c:if test="${requestScope.startDateIsNotValidException}">
                            <p class="error-message"><fmt:message key="createTourPage.exception.startDateIsNotValidException" /></p>
                        </c:if>

                        <div>
                            <label for="endDate"><fmt:message key="createTourPage.endDate" />:</label><br>
                            <input type="date" value="${requestScope.endDate}" name="endDate" id="endDate" class="form-control">
                        </div>
                        <c:if test="${requestScope.endDateIsNotValidException}">
                            <p class="error-message"><fmt:message key="createTourPage.exception.endDateIsNotValidException" /></p>
                        </c:if>

                        <div class="text-center mt-2">
                            <label for="isBurning"><fmt:message key="createTourPage.isBurning" />:</label><br>
                            <c:if test="${requestScope.isBurning.equals('on')}">
                                <input type="checkbox" checked name="isBurning" id="isBurning">
                            </c:if>
                            <c:if test="${!requestScope.isBurning.equals('on')}">
                                <input type="checkbox" name="isBurning" id="isBurning">
                            </c:if>
                        </div>

                        <div class="text-center mt-4">
                            <button type="submit" class="btn btn-primary">
                                <fmt:message key="createTourPage.button.create" />
                            </button>
                        </div>

                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
