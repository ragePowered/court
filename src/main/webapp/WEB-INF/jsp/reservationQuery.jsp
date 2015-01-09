<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Reservation Query</title>
</head>

<body>

<form:form modelAttribute="court" method="post">
    Court Name
    <form:select path="name">
        <form:options items="${courts}"/>
    </form:select>
    <input type="submit" value="Query"/>
</form:form>

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
            <td>${reservation.date}</td>
            <td>${reservation.hour}</td>
            <td>${reservation.player.name}</td>
        </tr>
    </c:forEach>
</table>
<a href="/welcome">Back</a>
</body>
</html>
