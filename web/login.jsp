<%--
  Created by IntelliJ IDEA.
  User: Liza
  Date: 14.04.2020
  Time: 19:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="Messages"/>
<html lang="${language}">
<head>
    <title>Log in</title>
</head>
<style>
    <%@include file='styles/regStyle.css' %>
</style>
<body>
<div class = "container">
<form method="post" action=/login>
    <label><fmt:message key="email" /></label><br>
    <input name="email"/><br><br>
    <label><fmt:message key="password" /></label><br>
    <input name="password"/><br><br>
    <input type="submit" value='<fmt:message key="send"/>'>
</form>
</div>
<a href='<c:url value="/" />'><fmt:message key="home"/></a>
</body>
</html>
