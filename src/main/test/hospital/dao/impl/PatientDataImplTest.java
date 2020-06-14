package hospital.dao.impl;

import hospital.entity.PatientData;
import hospital.entity.diagnosis.Diagnosis;
import hospital.utils.RandomNumber;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class PatientDataImplTest {

    @Test
    public void updateDoctor() {
        List<PatientData> patientDataList = PatientDataImpl.findAllWhereNoDoc();
        System.out.println(patientDataList.get(0).getPatient().getId());
        for (int i = 0; i < patientDataList.size(); i++) {
            System.out.println(patientDataList.get(i).getPatient().getName() + " " + patientDataList.get(i).getPatient().getId());
            System.out.println(patientDataList.get(i).getPatient().getEmail() + " " + patientDataList.get(i).getPatient().getPassword() );

        }
        PatientDataImpl.updateDoctor(1, patientDataList.get(0).getPatient().getId());
    }

    @Test
    public void establishDiagnosis() {
        PatientData patient = PatientDataImpl.findByPatientId(2);
        List<Diagnosis> diagnoses = DiagnosisDAOImpl.findAll();
        int number = RandomNumber.randNumber(0, diagnoses.size() - 1);
        if (patient.getDiagnosis() == null) {
            PatientDataImpl.updateDiagnosis(diagnoses.get(number).getId(),patient.getPatient().getId());
            // PatientDAOImpl.updateDiagnosis(patient, diagnoses.get(number).getId());
        }
    }
}