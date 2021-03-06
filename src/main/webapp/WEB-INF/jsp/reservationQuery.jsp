<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="log" uri="http://logging.apache.org/log4j/tld/log" %>

<html>
<head>
    <title>Reservation Query</title>
</head>

<body>
<%--<log:info message="reservationQuery.jsp accepted court {${court}} and items {${courts}}"/>--%>
<form:form modelAttribute="court" method="post">
    Court Name
    <form:select path="courtId">
        <form:options items="${courts}" itemLabel="name" itemValue="courtId"/>
    </form:select>
    <input type="submit" value="Query"/>
</form:form>
<%--<log:info><c:choose><c:when test="${reservations == null}">reservationQuery.jsp - there is no reservations from controller</c:when><c:otherwise>reservationQuery.jsp accepted next reservations:--%>
    <%--<c:forEach items="${reservations}" var="reservation">${reservation}--%>
    <%--</c:forEach></c:otherwise></c:choose></log:info>--%>
<table>
    <tr>
        <th>Court Name</th>
        <th>Date</th>
        <th>Hour</th>
        <th>Player</th>
    </tr>
    <c:forEach items="${reservations}" var="reservation">
        <tr>
            <td>${reservation.court.name}</td>
            <td><fmt:formatDate value="${reservation.date}" pattern="dd.MM.yyyy"/></td>
            <td>${reservation.hour}</td>
            <td>${reservation.player.name}</td>
        </tr>
    </c:forEach>
</table>
<a href="/welcome">Back</a>
</body>
</html>
