<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Reservation not available</title>
</head>
<body>
Reservation for ${exception.reservation.court.name} is not available on
<fmt:formatDate value="${exception.reservation.date}" pattern="dd.MM.yyyy"/> at
${exception.reservation.hour}:00.
<br/>
<a href="/welcome">Main</a>
</body>
</html>
