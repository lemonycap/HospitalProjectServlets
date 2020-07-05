<%--
  Created by IntelliJ IDEA.
  User: Liza
  Date: 14.04.2020
  Time: 23:19
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
    <title>Nurse page</title>
</head>
<style>
    <%@include file='styles/docStyle.css' %>
</style>
<body>
<a href='<c:url value="/" />'><fmt:message key="home"/></a>
<a href='<c:url value="/logout" />'><fmt:message key="logout"/></a>
<div class = "titles">
<h3> Nurse's page</h3>
<h3> <fmt:message key="greeting" /> ${nurse.name} ${nurse.surname}</h3>
</div>
<div class = "container">
<h4> Your patients:</h4>
<table border = "1">
    <tr>
        <th> <fmt:message key="name" /> </th>
        <th> <fmt:message key="surname" /></th>
        <th> <fmt:message key="diagnosis" /> </th>
        <th> <fmt:message key="prescriptionHistory" /></th>
        <th> <fmt:message key="prescriptions" /> </th>
    </tr>
    <c:forEach var="patient" items="${activePatients}">
        <tr><td>${patient.patient.name}</td>
            <td>${patient.patient.surname}</td>
            <td><c:if test="${patient.diagnosis == null}">
               <p> <fmt:message key="noDiagnosis" /> </p>
            </c:if>
                    ${patient.diagnosis.name}</td>
            <td>
                <c:forEach var="prescription" items="${patient.prescriptionHistory}">
                    <p> ${prescription.name} </p>
                </c:forEach>
            </td>
            <td>
                <c:if test="${patient.currentPrescriptions.isEmpty()}">
                   <p> <fmt:message key="noPrescriptions" /> </p>
                </c:if>
                <c:forEach var="prescription" items="${patient.currentPrescriptions}">
                    ${prescription.name}
                    <c:if test = "${prescription.prescriptionClass != 'operations'}">
                        <form method="post" action='<c:url value="/nursePage/doPrescriptionNurse" />' style="display:inline;">
                            <input  type = "hidden" name="id" value="${prescription.id}">
                            <input  type = "hidden" name="patientId" value="${patient.patient.id}">
                            <input type="submit" value= '<fmt:message key="doPrescription"/>' >
                        </form>
                    </c:if>
                </c:forEach>
            </td>
        </tr>
    </c:forEach>
</table>
<c:if test = "${activePatients.size() < 5}">
<form method="post" action='<c:url value="/nursePage/createNursePatients" />' style="display:inline;">
    <input  type = "hidden" name="id" value="${nurse.id}">
    <input type="submit" value='<fmt:message key="findPatients"/>'>
</form>
</c:if>
<c:if test = "${activePatients.size() >= 5}">
    <fmt:message key="maxNumberOfPatients" />
</c:if>
</div>
</body>
</html>
