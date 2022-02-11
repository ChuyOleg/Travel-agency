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
                        <h3 class="text-center mt-2">Create tour</h3>

                        <div>
                            <label for="name">Name:</label><br>
                            <input type="text" name="name" id="name" class="form-control">
                        </div>

                        <div>
                            <label for="price">Price:</label><br>
                            <input type="number" name="price" id="price" class="form-control">
                        </div>

                        <div>
                            <label for="country">Country:</label><br>
                            <input type="text" name="country" id="country" class="form-control">
                        </div>

                        <div>
                            <label for="city">City:</label><br>
                            <input type="text" name="city" id="city" class="form-control">
                        </div>

                        <div>
                            <label for="description">Description:</label><br>
                            <textarea name="description" id="description" class="form-control"></textarea>
                        </div>

                        <div>
                            <label for="maxDiscount">Max discount:</label><br>
                            <input type="number" name="maxDiscount" id="maxDiscount" class="form-control">
                        </div>

                        <div>
                            <label for="discountStep">Discount step:</label><br>
                            <input type="number" name="discountStep" id="discountStep" class="form-control">
                        </div>

                        <div class="text-center mt-2">
                            <label for="tourType">Tour type:</label><br>
                            <select name="tourType" id="tourType">
                                <option value="" hidden></option>
                                <c:forEach var="tourType" items="${requestScope.tourTypeList}">
                                    <option value="${tourType}"><c:out value="${tourType}" /></option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="text-center mt-2">
                            <label for="hotelType">Hotel type:</label><br>
                            <select name="hotelType" id="hotelType">
                                <option value="" hidden></option>
                                <c:forEach var="hotelType" items="${requestScope.hotelTypeList}">
                                    <option value="${hotelType}"><c:out value="${hotelType}" /></option>
                                </c:forEach>
                            </select>
                        </div>

                        <div>
                            <label for="personNumber">Person number:</label><br>
                            <input type="number" name="personNumber" id="personNumber" class="form-control">
                        </div>

                        <div>
                            <label for="startDate">Start date:</label><br>
                            <input type="date" name="startDate" id="startDate" class="form-control">
                        </div>

                        <div>
                            <label for="endDate">End date:</label><br>
                            <input type="date" name="endDate" id="endDate" class="form-control">
                        </div>

                        <div class="text-center mt-2">
                            <label for="isBurning">Is burning:</label><br>
                            <input type="checkbox" name="isBurning" id="isBurning">
                        </div>

                        <div class="text-center mt-4">
                            <button type="submit" class="btn btn-primary">create</button>
                        </div>

                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
