<%--
  Created by IntelliJ IDEA.
  User: Liza
  Date: 13.04.2020
  Time: 20:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<style>
    <%@include file='styles/regStyle.css' %>
</style>
<body>
<div class = "container">
    <form method="post" action=/registration>

        <label>Position</label><br>
        <select name = "role">
            <option selected value = "doctor">Doctor</option>
            <option value = "nurse">Nurse</option>
            <option value = "patient">Patient</option>
        </select>
        <label>Name</label><br>
        <input type="text" name="name">
        <label>Surname</label><br>
        <input type="text" name="surname" class = "surname">
        <label>E-mail</label><br>
        <input type="text" name="email">
        <label>Password</label><br>
        <input type="text" name="password">

        <input type="submit" value="OK" name="Ok" class = "ok"><br>
    </form>
</div>
</body>
</html>
