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
    <title>Nurse page</title>
</head>
<body>
<h3> Nurse's page</h3>
<h3> Hello, ${nurse.name} ${nurse.surname}</h3>
<h4> Your patients:</h4>
<table border = "1">
    <tr>
        <th> Name </th>
        <th> Surname </th>
        <th> Diagnosis </th>
        <th> Prescription History</th>
        <th> Prescriptions </th>
    </tr>
    <c:forEach var="patient" items="${activePatients}">
        <tr><td>${patient.patient.name}</td>
            <td>${patient.patient.surname}</td>
            <td><c:if test="${patient.diagnosis == null}">
               <p> No diagnosis at the moment </p>
            </c:if>
                    ${patient.diagnosis.name}</td>
            <td>
                <c:forEach var="prescription" items="${patient.prescriptionHistory}">
                    <p> ${prescription.name} </p>
                </c:forEach>
            </td>
            <td>
                <c:if test="${patient.currentPrescriptions.isEmpty()}">
                   <p> No prescriptions at the moment </p>
                </c:if>
                <c:forEach var="prescription" items="${patient.currentPrescriptions}">
                    ${prescription.name}
                    <c:if test = "${prescription.prescriptionClass != 'operations'}">
                        <form method="post" action='<c:url value="/nursePage/doPrescriptionNurse" />' style="display:inline;">
                            <input  type = "hidden" name="id" value="${prescription.id}">
                            <input  type = "hidden" name="patientId" value="${patient.patient.id}">
                            <input type="submit" value="Do prescription">
                        </form>
                    </c:if>
                </c:forEach>
            </td>
        </tr>
    </c:forEach>
</table>
<form method="post" action='<c:url value="/nursePage/createNursePatients" />' style="display:inline;">
    <input  type = "hidden" name="id" value="${nurse.id}">
    <input type="submit" value="Find patients">
</form>
<a href='<c:url value="/logout" />'>Log out</a>
</body>
</html>
