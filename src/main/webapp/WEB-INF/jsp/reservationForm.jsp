<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Reservation form</title>
    <style>
        .error {
            color: #e40000;
            font-weight: bold;
        }
    </style>
</head>
<body>
<form:form method="post" modelAttribute="reservation">
    <form:errors path="*" cssClass="error"/>
    <table>
        <tr>
            <td>Court Name</td>
            <td><form:input path="court.name"/></td>
            <td><form:errors path="court.name" cssClass="error"/></td>
        </tr>
        <tr>
            <td>Date</td>
            <td><form:input path="date"/></td>
            <td><form:errors path="date" cssClass="error"/></td>
        </tr>
        <tr>
            <td>Hour</td>
            <td><form:input path="hour"/></td>
            <td><form:errors path="hour" cssClass="error"/></td>
        </tr>
        <tr>
            <td>Player name</td>
            <td><form:input path="player.name"/></td>
            <td><form:errors path="player.name" cssClass="error"/></td>
        </tr>
        <tr>
            <td>Player phone</td>
            <td><form:input path="player.phone"/></td>
            <td><form:errors path="player.phone" cssClass="error"/></td>
        </tr>
        <tr>
            <td>Sport Type</td>
            <td><form:select path="sportType" items="${sportTypes}" itemValue="id" itemLabel="name"/></td>
            <td><form:errors path="sportType" cssClass="error"/></td>
        </tr>
        <tr>
            <td colspan="3"><input type="submit"/></td>
        </tr>
    </table>
</form:form>
<a href="/welcome">Back</a>
</body>
</html>
