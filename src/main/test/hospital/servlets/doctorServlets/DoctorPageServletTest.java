package hospital.servlets.doctorServlets;

import hospital.dao.impl.PatientDataImpl;
import hospital.entity.PatientData;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DoctorPageServletTest {

    @Test
    public void randomPatient() {
        int id = 1;
        List<PatientData> patientData = PatientDataImpl.findAll();
        for (int i = 0; i < patientData.size(); i++) {
            System.out.println(patientData.get(i).getPatient().getName());
        }
        System.out.println("TEST");
        List<PatientData> activePatients = PatientDataImpl.findDoctorPatients(id);
        for (int i = 0; i < activePatients.size(); i++) {
            System.out.println(activePatients.get(i).getPatient().getName());
        }
        int size = activePatients.size();
        if (size < 5) {
            PatientDataImpl.updateDoctor(id, patientData.get(0).getPatient().getId());
        }
    }
}