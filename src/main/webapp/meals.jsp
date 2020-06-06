<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<html lang="ru">
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<style type="text/css">
    table {
        width: 1000px;
        text-align: center;
        border-collapse: collapse;
    }

    table thead tr {
        color: #ffffff;
        font-weight: bold;
        background: #00bf80;
    }

    table thead tr td {
        border: 1px solid #01ab73;
    }

    table tbody tr td {
        border: 1px solid #e8e9eb;
    }

    table tbody tr:nth-child(2n) {
        background: #f4f4f4;
    }

    table tbody tr:hover {
        background: #ebffe8;
    }
</style>
<table>
    <tr>
        <th> Дата/Время</th>
        <th> Описание</th>
        <th> Калории</th>
    </tr>
    <c:forEach var="mealTo" items="${mealToList}">
        <tr style="color:${mealTo.excess ? 'red': 'green'}">
            <td>${mealTo.dateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm"))}</td>
            <td>${mealTo.description}</td>
            <td>${mealTo.calories}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>