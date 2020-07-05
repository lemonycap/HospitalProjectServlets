<%--
  Created by IntelliJ IDEA.
  User: Liza
  Date: 14.04.2020
  Time: 23:19
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
    <title>Doctor page</title>
</head>
<style>
    <%@include file='styles/docStyle.css' %>
</style>
<body>
<a href='<c:url value="/" />'><fmt:message key="home"/></a>
<a href='<c:url value="/logout" />'><fmt:message key="logout" /></a>
<div class = "titles">
<h3> <fmt:message key="doctor.page" /></h3>
<h3> <fmt:message key="greeting" /> ${doctor.name} ${doctor.surname}</h3>
</div>
<div class = "container">
<h4> <fmt:message key="patients" /></h4>
<table border = "1">
    <tr>
        <th> <fmt:message key="name" /> </th>
        <th> <fmt:message key="surname" /> </th>
        <th> <fmt:message key="diagnosis" /> </th>
        <th> <fmt:message key="prescriptionHistory" /> </th>
        <th> <fmt:message key="prescriptions" /> </th>
    </tr>
    <c:forEach var="patient" items="${activePatients}">
        <c:if test="${patient.patientStatus == 1}">
        <tr>
            <td>${patient.patient.name}</td>
            <td>${patient.patient.surname}</td>
            <td>
                <c:if test="${patient.diagnosis == null}">
                    <form method="post" action='<c:url value="/doctorPage/createPatientDiagnosis" />' style="display:inline;">
                        <input  type = "hidden" name="id" value="${patient.patient.id}">
                        <input type="submit" value="<fmt:message key="establishDiagnosis" />">
                    </form>
                </c:if>
                    ${patient.diagnosis.name}
            </td>
            <td>
                <c:forEach var="prescription" items="${patient.prescriptionHistory}">
                    <p> ${prescription.name} </p>
                </c:forEach>
            </td>
            <td>
                <c:if test = "${patient.currentPrescriptions.isEmpty() && patient.diagnosis != null}">
                    <form method="post" action='<c:url value="/doctorPage/createPatientPrescriptions" />' style="display:inline;">
                        <input  type = "hidden" name="id" value="${patient.patient.id}">
                        <input type="submit" value="<fmt:message key="makePrescriptions" />">
                    </form>
                </c:if>
                <c:if test = "${patient.currentPrescriptions.isEmpty() && patient.diagnosis == null}">
                    <p> <fmt:message key="firstDiagnosis" /></p>
                </c:if>
                <c:if test = "${!patient.currentPrescriptions.isEmpty()}">
                    <c:forEach var="prescription" items="${patient.currentPrescriptions}">
                        <p> ${prescription.name} </p>
                        <form method="post" action='<c:url value="/doctorPage/doPrescriptionDoctor" />' style="display:inline;">
                            <input  type = "hidden" name="patientId" value="${patient.patient.id}">
                            <input  type = "hidden" name="id" value="${prescription.id}">
                            <input type="submit" value="<fmt:message key="doPrescription" />">
                        </form>
                    </c:forEach>
                </c:if>
            </td>
            <td>
                <form method="post" action='<c:url value="/doctorPage/releasePatient" />' style="display:inline;">
                    <input  type = "hidden" name="id" value="${patient.patient.id}">
                    <input type="submit" value="<fmt:message key="releasePatient" />">
                </form>
            </td>
        </tr>
        </c:if>
    </c:forEach>
</table>
<c:if test = "${activePatients.size() < 5}">
    <form method="post" action='<c:url value="/doctorPage/createPatients" />' style="display:inline;">
        <input  type = "hidden" name="id" value="${doctor.id}">
        <input type="submit" value="<fmt:message key="findPatients"/>">
    </form>
</c:if>
<c:if test = "${activePatients.size() >= 5}">
    <fmt:message key="maxNumberOfPatients" />
</c:if>
</div>
</body>
</html>
