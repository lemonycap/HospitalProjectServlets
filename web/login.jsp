<%--
  Created by IntelliJ IDEA.
  User: Liza
  Date: 14.04.2020
  Time: 19:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setBundle basename="messages"/>
<html>
<head>
    <title>Log in</title>
</head>
<style>
    <%@include file='styles/regStyle.css' %>
</style>
<body>
<div class = "container">
<form method="post" action=/login>
    <label>Email</label><br>
    <input name="email"/><br><br>
    <label>Password</label><br>
    <input name="password"/><br><br>
    <input type="submit" value="Send" />
</form>
</div>
</body>
</html>
