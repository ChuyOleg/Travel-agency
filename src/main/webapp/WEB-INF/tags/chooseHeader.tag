<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="role" required="true" %>
<%@ attribute name="pathToWebAppFolder" required="true" %>

<c:choose>

    <c:when test="${role.equals('UNKNOWN')}">
        <jsp:include page="${pathToWebAppFolder}template/partial/guestHeader.jspx" />
    </c:when>

    <c:when test="${role.equals('USER')}">
        <jsp:include page="${pathToWebAppFolder}template/partial/userHeader.jspx" />
    </c:when>

    <c:when test="${role.equals('MANAGER')}">
        <jsp:include page="${pathToWebAppFolder}template/partial/managerHeader.jspx" />
    </c:when>

    <c:when test="${role.equals('ADMIN')}">
        <jsp:include page="${pathToWebAppFolder}template/partial/adminHeader.jspx" />
    </c:when>

</c:choose>
