<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Create</title>
</head>
<body>
<h1 align="center">Here you can view statistics of Details:</h1>

<section align="center">
    <form method="post" action="/stats_id">

         <h2>ID of all Details:</h2>
         <c:forEach items="${allId}" var="allId">
             ${allId}<br>
         </c:forEach>

        <input type="text" name="id"><br/>
        <button type="submit">choose detail</button>

    </form>
</section>

<section align="center">
    <h2>Statistics of Detail ${detail.id}:</h2>
    <p>Time of start creating of Details: ${detail.start}</p>
    <p>Seconds spent for creating: ${detail.seconds}</p>
    <p>Count of fuel created: ${detail.fuel}</p>
    <p>Count of broken microchips: ${detail.microchips}</p>
</section>

<section>
    <p align="center"><a href="index.html">Home page</a></p>
</section>

</body>
</html>