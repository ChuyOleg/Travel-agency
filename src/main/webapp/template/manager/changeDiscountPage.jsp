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
    <div class="row justify-content-center">
        <div class="col-12 col-lg-6 mt-4 mb-4 p-3 justify-content-center tour-creation-block">
            <form id="tour-form" autocomplete="off" class="form" action="/Manager/tour/changeDiscount?tourId=${param.tourId}" method="post">
                <h3 class="text-center mt-2">Change discount of the tour</h3>

                <c:if test="${param.success != null}">
                    <h3 class="text-center text-success">Discount has been successfully updated</h3>
                </c:if>

                <c:if test="${requestScope.maxDiscountIsNotValidException}">
                    <p class="text-center error-message"><fmt:message key="createTourPage.exception.maxDiscountIsNotValidException" /></p>
                </c:if>

                <c:if test="${requestScope.discountStepIsNotValidException}">
                    <p class="text-center error-message"><fmt:message key="createTourPage.exception.discountStepIsNotValidException" /></p>
                </c:if>

                <div class="row justify-content-center">
                    <div class="text-center col-10">
                        <h3>${requestScope.tourDto.name}</h3>
                    </div>

                    <div class="text-center col-4">
                        <label for="price">Price:</label>
                        <p id="price">${requestScope.tourDto.price}$</p>
                    </div>
                </div>

                <div class="row justify-content-center align-items-center">
                    <div class="col-4 text-center">
                        <label for="country"><fmt:message key="createTourPage.country" />:</label><br>
                        <p id="country">${requestScope.tourDto.country}</p>
                    </div>

                    <div class="col-4 text-center">
                        <label for="city"><fmt:message key="createTourPage.city" />:</label><br>
                        <p id="city">${requestScope.tourDto.city}</p>
                    </div>
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

                <div class="text-center mt-4">
                    <button type="submit" class="btn btn-primary">
                        Update discount
                    </button>
                </div>

            </form>
        </div>
    </div>
</div>

<jsp:include page="../partial/footer.jspx" />

</body>
</html>
