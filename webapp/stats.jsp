<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Create</title>
</head>

<body>
<h1 align="center">Here you can view statistics of all Details:</h1>
<section align="center">
    <form method="post">
        <button type="submit">Update all statistics</button>
    </form>
</section>

<section align="center">
    <h2>Statistics of all Details:</h2>
    <p>Count of Details: ${statistic.details}</p>
    <p>Count of broken microchips: ${statistic.microchips}</p>
    <p>Count of created fuel: ${statistic.fuel}</p>
</section>

<section>
    <p align="center"><a href="index.html">Home page</a></p>
</section>

</body>
</html>