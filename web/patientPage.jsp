
<%--
  Created by IntelliJ IDEA.
  User: Liza
  Date: 14.04.2020
  Time: 23:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Patient page</title>
</head>
<body>
<h3> Patient's page</h3>
<h4> Hello, ${patient.patient.name} ${patient.patient.surname} </h4>
<div class = "container">
<c:if test="${patient.patientStatus == 1}">
    <h4>Your doctor: ${patient.doctor.name}   ${patient.doctor.surname}</h4>
    <c:if test = "${patient.doctor == null}">
        <p>No doctor at the moment</p>
    </c:if>
    <h4>Your nurse: ${patient.nurse.name} ${patient.nurse.surname} </h4>
    <c:if test = "${patient.doctor == null}">
        <p>No nurse at the moment</p>
    </c:if>
    <h4> Your diagnosis:  ${patient.diagnosis.name}</h4>
    <c:if test = "${patient.diagnosis == null}">
        <p>No diagnosis at the moment</p>
    </c:if>
    <h4> Your history of prescriptions:</h4>
    <c:forEach var="prescription" items="${patient.prescriptionHistory}">
        ${prescription.name}
    </c:forEach>
    <h4>Your prescriptions:</h4>
    <c:if test="${patient.currentPrescriptions.isEmpty()}">
        <p> You have no prescriptions</p>
    </c:if>
    <c:forEach var="prescription" items="${patient.currentPrescriptions}">
        ${prescription.name}
    </c:forEach>
</c:if>
<c:if test="${patient.patientStatus == 0}">
    <p> You have been released from hospital.</p>
    <p> Your diagnosis: ${patient.diagnosis.name} </p>
</c:if>
</div>
<a href='<c:url value="/logout" />'>Log out</a>
</body>
</html>
