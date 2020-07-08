package hospital.utils;

import hospital.dao.impl.PatientDataImpl;
import hospital.entity.PatientData;
import org.apache.log4j.Logger;

import java.util.List;

public class RandomPatient {
    private static final Logger log = Logger.getLogger(RandomPatient.class);
    DAOFactory factory;

    public RandomPatient() {
        this.factory = new DAOFactory();
    }

    public RandomPatient(DAOFactory factory) {
        this.factory = factory;
    }

    public void randomPatientNurse(int id) {
        PatientDataImpl patientDataImpl = factory.createPatientDataDao();
        List<PatientData> patientData = patientDataImpl.findAllWhereNoNurse();
        List<PatientData> existingPatients = patientDataImpl.findNursePatients(id);
        if (!patientData.isEmpty()) {
            for (int i = 0; i< patientData.size(); i++) {
                if (patientData.get(i).getPatientStatus() == 0) {
                    patientData.remove(patientData.get(i));
                }
            }
            log.debug("amount of active patients without doctor:" + patientData.size());
            log.debug("amount of existing patients: " + existingPatients.size());
            if (existingPatients.size() < 5) {
                patientDataImpl.updateNurse(id, patientData.get(0).getPatient().getId());
            }
        }
    }

    public  void randomPatientDoc(int id) {
        PatientDataImpl patientDataImpl = factory.createPatientDataDao();
        List<PatientData> patientData = patientDataImpl.findAllWhereNoDoc();
        List<PatientData> existingPatients = patientDataImpl.findDoctorPatients(id);
        if (!patientData.isEmpty()) {
            for (int i = 0; i< patientData.size(); i++) {
                if (patientData.get(i).getPatientStatus() == 0) {
                    patientData.remove(patientData.get(i));
                }
            }
            log.debug("amount of active patients without doctor:" + patientData.size());
            log.debug("amount of existing patients: " + existingPatients.size());
            if (existingPatients.size() < 5 && !patientData.isEmpty()) {
                patientDataImpl.updateDoctor(id, patientData.get(0).getPatient().getId());
            }
        }
        else {
            log.info("no patients need a doctor right now");
        }
    }
}
