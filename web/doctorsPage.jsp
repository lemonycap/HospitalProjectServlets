<%--
  Created by IntelliJ IDEA.
  User: Liza
  Date: 14.04.2020
  Time: 23:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Doctor page</title>
</head>
<body>
<h3> Doctor's page</h3>
<h3> Hello, ${doctor.name} ${doctor.surname}</h3>
<h4> Your patients:</h4>
<table border = "1">
    <tr>
        <th> Name </th>
        <th> Surname </th>
        <th> Diagnosis </th>
        <th> Prescription history </th>
        <th> Prescriptions </th>
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
                        <input type="submit" value="Establish diagnosis">
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
                        <input type="submit" value="Make prescriptions">
                    </form>
                </c:if>
                <c:if test = "${patient.currentPrescriptions.isEmpty() && patient.diagnosis == null}">
                    <p> First, establish diagnosis for patient.</p>
                </c:if>
                <c:if test = "${!patient.currentPrescriptions.isEmpty()}">
                    <c:forEach var="prescription" items="${patient.currentPrescriptions}">
                        <p> ${prescription.name} </p>
                        <form method="post" action='<c:url value="/doctorPage/doPrescriptionDoctor" />' style="display:inline;">
                            <input  type = "hidden" name="patientId" value="${patient.patient.id}">
                            <input  type = "hidden" name="id" value="${prescription.id}">
                            <input type="submit" value="Do prescription">
                        </form>
                    </c:forEach>
                </c:if>
            </td>
            <td>
                <form method="post" action='<c:url value="/doctorPage/releasePatient" />' style="display:inline;">
                    <input  type = "hidden" name="id" value="${patient.patient.id}">
                    <input type="submit" value="Release patient">
                </form>
            </td>
        </tr>
        </c:if>
    </c:forEach>
</table>
<c:if test = "${activePatients.size() < 5}">
    <form method="post" action='<c:url value="/doctorPage/createPatients" />' style="display:inline;">
        <input  type = "hidden" name="id" value="${doctor.id}">
        <input type="submit" value="Find patients">
    </form>
</c:if>
<c:if test = "${activePatients.size() >= 5}">
    You have max amount of patients.
</c:if>
<a href='<c:url value="/logout" />'>Log out</a>
</body>
</html>
