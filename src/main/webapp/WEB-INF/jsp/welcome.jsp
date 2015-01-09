<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <META http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title><fmt:message key="welcome.title"/></title>
</head>

<body>
<h2><fmt:message key="welcome.message"/></h2>
Today is <fmt:formatDate value="${today}" pattern="dd.MM.yyyy"/><br>
<a href="/reservationForm">Reserve court now</a><br>
<a href="/reservationQuery">View reserved court by name</a><br>
<a href="/periodicReservationForm">Periodic reservation</a><br>
<a href="?lang=ua">UA</a>&nbsp;<a href="?lang=en">EN</a>
</body>
</html>