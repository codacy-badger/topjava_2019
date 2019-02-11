<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="f" uri="http://lavgorush.com/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Meals</h2>
<section>
    <table cellpadding="2" border="2">
        <tr>
            <th>DateTime</th>
            <th>Description</th>
            <th>Calories</th>
        </tr>
        <c:forEach items="${meals}" var="meal">
            <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.MealTo"/>
            <tr style="color: ${meal.excess? "red" : "green"}">
                <td>${f:formatLocalDateTime(meal.dateTime, 'dd-MM-yyyy HH:mm')}</td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>