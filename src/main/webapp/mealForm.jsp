<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Form</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>${meal.id == null ? "Add meal":"Edit"}</h2>
<form method="POST" action="meals">
    <input type="hidden" name="id" value="${meal.id}"/>
    DateTime : <input type="datetime-local" name="dateTime" value="${meal.dateTime}"/><br/>
    Description : <input type="text" name="description" value="${meal.description}"/><br/>
    Calories : <input type="number" name="calories" value="${meal.calories}"/><br/>
    <input type="submit" value="Ok"/>
</form>
<button onclick="window.history.back()">Cancel</button>
</body>
</html>