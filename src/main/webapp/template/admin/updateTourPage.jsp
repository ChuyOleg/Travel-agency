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
    <div class="row justify-content-center">
        <div class="col-12 col-lg-6 mt-4 mb-4 p-3 justify-content-center tour-creation-block">
            <form id="tour-form" autocomplete="off" class="form" action="/tour/update?tourId=${param.tourId}" method="post">
                <h3 class="text-center mt-2">Update tour</h3>

                <c:if test="${param.success != null}">
                    <h3 class="text-center text-success">Tour has been successfully updated</h3>
                </c:if>

                <c:if test="${requestScope.priceIsNotValidException}">
                    <p class="text-center error-message"><fmt:message key="createTourPage.exception.priceIsNotValidException" /></p>
                </c:if>

                <c:if test="${requestScope.countryIsEmptyException}">
                    <p class="text-center error-message"><fmt:message key="createTourPage.exception.countryIsEmptyException" /></p>
                </c:if>

                <c:if test="${requestScope.countryIsUndefined}">
                    <p class="text-center error-message"><fmt:message key="createTourPage.exception.countryIsUndefined" /></p>
                </c:if>

                <c:if test="${requestScope.cityIsEmptyException}">
                    <p class="text-center error-message"><fmt:message key="createTourPage.exception.cityIsEmptyException" /></p>
                </c:if>

                <c:if test="${requestScope.cityIsUndefined}">
                    <p class="text-center error-message"><fmt:message key="createTourPage.exception.cityIsUndefined" /></p>
                </c:if>

                <c:if test="${requestScope.descriptionIsEmptyException}">
                    <p class="text-center error-message"><fmt:message key="createTourPage.exception.descriptionIsEmptyException" /></p>
                </c:if>

                <c:if test="${requestScope.maxDiscountIsNotValidException}">
                    <p class="text-center error-message"><fmt:message key="createTourPage.exception.maxDiscountIsNotValidException" /></p>
                </c:if>

                <c:if test="${requestScope.discountStepIsNotValidException}">
                    <p class="text-center error-message"><fmt:message key="createTourPage.exception.discountStepIsNotValidException" /></p>
                </c:if>

                <c:if test="${requestScope.tourTypeIsEmptyException}">
                    <p class="text-center error-message"><fmt:message key="createTourPage.exception.tourTypeIsEmptyException" /></p>
                </c:if>

                <c:if test="${requestScope.hotelTypeIsEmptyException}">
                    <p class="text-center error-message"><fmt:message key="createTourPage.exception.hotelTypeIsEmptyException" /></p>
                </c:if>

                <c:if test="${requestScope.personNumberIsNotValidException}">
                    <p class="text-center error-message"><fmt:message key="createTourPage.exception.personNumberIsNotValidException" /></p>
                </c:if>

                <c:if test="${requestScope.startDateIsNotValidException}">
                    <p class="text-center error-message"><fmt:message key="createTourPage.exception.startDateIsNotValidException" /></p>
                </c:if>

                <c:if test="${requestScope.endDateIsNotValidException}">
                    <p class="text-center error-message"><fmt:message key="createTourPage.exception.endDateIsNotValidException" /></p>
                </c:if>

                <div class="row justify-content-center">
                    <div class="text-center col-10">
                        <input type="text" value="${requestScope.tourDto.name}" name="name" hidden>
                        <h3>${requestScope.tourDto.name}</h3>
                    </div>

                    <div class="text-center col-4">
                        <label for="price"><fmt:message key="createTourPage.price" />:</label><br>
                        <input type="text" value="${requestScope.tourDto.price}" name="price" id="price" class="form-control">
                    </div>
                </div>

                <div class="row justify-content-center align-items-center">
                    <div class="col-4 text-center">
                        <label for="country"><fmt:message key="createTourPage.country" />:</label><br>
                        <input type="text" value="${requestScope.tourDto.country}" name="country" id="country" class="form-control">
                    </div>

                    <div class="col-4 text-center">
                        <label for="city"><fmt:message key="createTourPage.city" />:</label><br>
                        <input type="text" value="${requestScope.tourDto.city}" name="city" id="city" class="form-control">
                    </div>
                </div>

                <div class="text-center">
                    <label for="description"><fmt:message key="createTourPage.description" />:</label><br>
                    <textarea name="description" id="description" class="form-control"><c:out value="${requestScope.tourDto.description}" />
                        </textarea>
                </div>

                <div class="row justify-content-center">
                    <div class="col-3 text-center">
                        <label for="maxDiscount"><fmt:message key="createTourPage.maxDiscount" />:</label><br>
                        <input type="text" value="${requestScope.tourDto.maxDiscount}" name="maxDiscount" id="maxDiscount" class="form-control">
                    </div>

                    <div class="col-3 text-center">
                        <label for="discountStep"><fmt:message key="createTourPage.discountStep" />:</label><br>
                        <input type="text" value="${requestScope.tourDto.discountStep}" name="discountStep" id="discountStep" class="form-control">
                    </div>
                </div>

                <div class="row justify-content-center">
                    <div class="col-3 text-center">
                        <label for="tourType"><fmt:message key="createTourPage.tourType" />:</label><br>
                        <select class="form-select" name="tourType" id="tourType">
                            <option value="" hidden></option>
                            <c:forEach var="tourType" items="${requestScope.tourTypeList}">
                                <c:if test="${requestScope.tourDto.tourType.equals(tourType.name())}" >
                                    <option selected value="${tourType}"><c:out value="${tourType}" /></option>
                                </c:if>
                                <c:if test="${!requestScope.tourDto.tourType.equals(tourType.name())}" >
                                    <option value="${tourType}"><c:out value="${tourType}" /></option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="col-3 text-center">
                        <label for="hotelType"><fmt:message key="createTourPage.hotelType" />:</label><br>
                        <select class="form-select" name="hotelType" id="hotelType">
                            <option value="" hidden></option>
                            <c:forEach var="hotelType" items="${requestScope.hotelTypeList}">
                                <c:if test="${requestScope.tourDto.hotelType.equals(hotelType.name())}">
                                    <option selected value="${hotelType}"><myTg:stars hotelType="${hotelType}" /></option>
                                </c:if>
                                <c:if test="${!requestScope.tourDto.hotelType.equals(hotelType.name())}">
                                    <option value="${hotelType}"><myTg:stars hotelType="${hotelType}" /></option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <div class="row justify-content-center">
                    <div class="col-3">
                        <label for="personNumber"><fmt:message key="createTourPage.personNumber" />:</label><br>
                        <input type="text" value="${requestScope.tourDto.personNumber}" name="personNumber" id="personNumber" class="form-control">
                    </div>
                </div>

                <div class="row justify-content-center mt-2">
                    <div class="text-center col-4">
                        <label for="startDate"><fmt:message key="createTourPage.startDate" />:</label><br>
                        <input type="date" value="${requestScope.tourDto.startDate}" name="startDate" id="startDate" class="form-control">
                    </div>

                    <div class="text-center col-4">
                        <label for="endDate"><fmt:message key="createTourPage.endDate" />:</label><br>
                        <input type="date" value="${requestScope.tourDto.endDate}" name="endDate" id="endDate" class="form-control">
                    </div>
                </div>

                <div class="text-center mt-2">
                    <label for="isBurning"><fmt:message key="createTourPage.isBurning" />:</label><br>
                    <c:if test="${requestScope.tourDto.isBurning().equals('on')}">
                        <input type="checkbox" checked name="isBurning" id="isBurning">
                    </c:if>
                    <c:if test="${!requestScope.tourDto.isBurning().equals('on')}">
                        <input type="checkbox" name="isBurning" id="isBurning">
                    </c:if>
                </div>

                <div class="text-center mt-4">
                    <button type="submit" class="btn btn-primary">
                        Update
                    </button>
                </div>

            </form>
        </div>
    </div>
</div>
</body>
</html>
