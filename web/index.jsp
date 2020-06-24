<%--
  Created by IntelliJ IDEA.
  User: Liza
  Date: 09.04.2020
  Time: 16:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="Messages"/>
<style>
  <%@include file='styles/mainStyle.css' %>
</style>
<html lang="${language}">
  <head>
    <title>Main page</title>
  </head>
  <body>
  <form>
    <select id="language" name="language" onchange="submit()">
      <option value="ru" ${language == 'ru' ? 'selected' : ''}>Russian</option>
      <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
    </select>
  </form>
  <div class = "container">
  <button onclick="location.href='/login.jsp'"> <fmt:message key="login" /> </button>
    <button onclick="location.href='/registration'"><fmt:message key="registration" /></button>
  </div>
  </body>
</html>
