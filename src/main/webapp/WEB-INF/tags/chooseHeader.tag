<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="messages" />

<%@ attribute name="role" required="true" %>

<c:choose>

    <c:when test="${role.equals('UNKNOWN')}">
        <%@include file="../../template/partial/guestHeader.jspf" %>
    </c:when>

    <c:when test="${role.equals('USER')}">
        <%@include file="../../template/partial/userHeader.jspf" %>
    </c:when>

    <c:when test="${role.equals('MANAGER')}">
        <%@include file="../../template/partial/managerHeader.jspf" %>
    </c:when>

    <c:when test="${role.equals('ADMIN')}">
        <%@include file="../../template/partial/adminHeader.jspf" %>
    </c:when>

</c:choose>
