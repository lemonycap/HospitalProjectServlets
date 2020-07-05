<%--
  Created by IntelliJ IDEA.
  User: Liza
  Date: 13.04.2020
  Time: 20:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="Messages"/>
<html lang="${language}">
<head>
    <title>Registration</title>
</head>
<style>
    <%@include file='styles/regStyle.css' %>
</style>
<body>
<div class = "container">
    <form method="post" action=/registration>

        <label><fmt:message key="reg.position" /></label><br>
        <select name = "role">
            <option selected value = "doctor"><fmt:message key="reg.doctor" /></option>
            <option value = "nurse"><fmt:message key="reg.nurse" /></option>
            <option value = "patient"><fmt:message key="reg.patient" /></option>
        </select>
        <label><fmt:message key="name" /></label><br>
        <input type="text" name="name">
        <label><fmt:message key="surname" /></label><br>
        <input type="text" name="surname" class = "surname">
        <label><fmt:message key="email" /></label><br>
        <input type="text" name="email">
        <label><fmt:message key="password" /></label><br>
        <input type="text" name="password">

        <input type="submit" value="<fmt:message key="send" />" name="Ok" class = "ok"><br>
    </form>
</div>
<a href='<c:url value="/" />'><fmt:message key="home"/></a>
</body>
</html>
