
<%--
  Created by IntelliJ IDEA.
  User: Liza
  Date: 14.04.2020
  Time: 23:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="Messages"/>
<html lang="${language}">
<head>
    <title>Patient page</title>
</head>
<body>
<h3> <fmt:message key="patient.profile" /></h3>
<h4>  <fmt:message key="greeting" /> ${patient.patient.name} ${patient.patient.surname} </h4>
<div class = "container">
<c:if test="${patient.patientStatus == 1}">
    <h4> <fmt:message key="patient.doctor" /> ${patient.doctor.name}   ${patient.doctor.surname}</h4>
    <c:if test = "${patient.doctor == null}">
        <p><fmt:message key="patient.noDoctor" /></p>
    </c:if>
    <h4> <fmt:message key="patient.nurse" /> ${patient.nurse.name} ${patient.nurse.surname} </h4>
    <c:if test = "${patient.doctor == null}">
        <p><fmt:message key="patient.noNurse" /></p>
    </c:if>
    <h4>  <fmt:message key="patient.diagnosis" /> ${patient.diagnosis.name}</h4>
    <c:if test = "${patient.diagnosis == null}">
        <p><fmt:message key="patient.noDiagnosis" /></p>
    </c:if>
    <h4>  <fmt:message key="patient.history" /></h4>
    <c:forEach var="prescription" items="${patient.prescriptionHistory}">
        ${prescription.name}
    </c:forEach>
    <h4> <fmt:message key="patient.prescriptions" /></h4>
    <c:if test="${patient.currentPrescriptions.isEmpty()}">
        <p> <fmt:message key="patient.noPrescriptions" /></p>
    </c:if>
    <c:forEach var="prescription" items="${patient.currentPrescriptions}">
        ${prescription.name}
    </c:forEach>
</c:if>
<c:if test="${patient.patientStatus == 0}">
    <p> <fmt:message key="patient.released" /></p>
    <p>  <fmt:message key="patient.diagnosis" />${patient.diagnosis.name} </p>
    <c:if test="${!patient.currentPrescriptions.isEmpty()}">
        <p>  <fmt:message key="patient.leftPrescr" /></p>
    </c:if>
    <c:forEach var="prescription" items="${patient.currentPrescriptions}">
        ${prescription.name}
    </c:forEach>
</c:if>
</div>
<a href='<c:url value="/" />'><fmt:message key="home"/></a>
<a href='<c:url value="/logout" />'> <fmt:message key="logout" /></a>
</body>
</html>
