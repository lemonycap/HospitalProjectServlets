package hospital.utils.utilsForDBData;

import hospital.dao.impl.DiagnosisDAOImpl;
import hospital.dao.impl.PatientDataImpl;
import hospital.dao.impl.PrescriptionDAOImpl;
import hospital.dao.impl.UserDAOImpl;
import hospital.entity.PatientData;
import hospital.entity.User;
import hospital.entity.diagnosis.Diagnosis;
import hospital.entity.prescription.Prescription;
import hospital.utils.RandomNumber;
import hospital.utils.factories.DAOFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PatientDataManipulations {

    DAOFactory factory;
    UserDAOImpl userDAO;
    PatientDataImpl patientDataImpl;
    PrescriptionDAOImpl prescriptionDAO;
    DiagnosisDAOImpl diagnosisDAO;

    public PatientDataManipulations() {
        this.factory = new DAOFactory();
        userDAO = factory.createUserDao();
        patientDataImpl = factory.createPatientDataDao();
        prescriptionDAO = factory.createPrescriptionDao();
        diagnosisDAO = factory.createDiagnosisDao();
    }

    public PatientDataManipulations(DAOFactory factory) {
        this.factory = factory;
        userDAO = factory.createUserDao();
        patientDataImpl = factory.createPatientDataDao();
        prescriptionDAO = factory.createPrescriptionDao();
        diagnosisDAO = factory.createDiagnosisDao();
    }

    public User detectPerson(int id) {
        return userDAO.findById(id);
    }


    public Diagnosis detectDiagnosis(int id) {
        return diagnosisDAO.findById(id);
    }


    public void refreshPatients() {

        List<User> users = userDAO.findAll();
        for (User user: users) {
            if (user.getRole().getName().equals("PATIENT")) {
                PatientData patientData = patientDataImpl.findByPatientId(user.getId());
                if (patientData == null) {
                    patientDataImpl.insert(user.getId());
                }
            }
            checkPrescriptionHistory();
        }

    }

    public  void checkPrescriptionHistory() {
        List<PatientData> allPatients = patientDataImpl.findAll();
        for (int i = 0; i < allPatients.size(); i++) {
            if (allPatients.get(i).getPrescriptionHistory().isEmpty()) {
                createPatientHistory(allPatients.get(i).getPatient().getId());
            }
        }
    }

    public  void createPatientHistory(int patientId) {
        List<Prescription> allPrescriptions = prescriptionDAO.findAll();
        int numberOfPrescriptionsToInsert = RandomNumber.randNumber(0,3);
        int numberOfPrescr;
        for (int i = 0; i < numberOfPrescriptionsToInsert; i++) {
            numberOfPrescr = RandomNumber.randNumber(0,allPrescriptions.size() -1);
            prescriptionDAO.insertHistory(allPrescriptions.get(numberOfPrescr).getId(),patientId);
        }
    }

    public  void makePrescriptions(PatientData patientData) {
        Set<Prescription> history = findPatientHistory(patientData.getPatient().getId());
        List<Prescription> medicine = prepareActivePrescriptions(history,prescriptionDAO.findByClass("medicine"));
        List<Prescription> procedures =  prepareActivePrescriptions(history,prescriptionDAO.findByClass("procedures"));
        List<Prescription> operations =  prepareActivePrescriptions(history,prescriptionDAO.findByClass("operations"));
        Set<Prescription> newPrescriptions = new HashSet<>();
        int number;
        if (patientData.getDiagnosis().getDifficulty().equals("low")) {
            number = RandomNumber.randNumber(0, medicine.size() - 1);
            newPrescriptions.add(medicine.get(number));
        }
        else if (patientData.getDiagnosis().getDifficulty().equals("medium")) {
            number = RandomNumber.randNumber(0, medicine.size() - 1);
            newPrescriptions.add(medicine.get(number));
            number = RandomNumber.randNumber(0, procedures.size() - 1);
            newPrescriptions.add(procedures.get(number));
        }
        else if (patientData.getDiagnosis().getDifficulty().equals("hard")) {
            number = RandomNumber.randNumber(0, medicine.size() - 1);
            newPrescriptions.add(medicine.get(number));
            number = RandomNumber.randNumber(0, operations.size() - 1);
            newPrescriptions.add(operations.get(number));
        }
        for (Prescription prescription: newPrescriptions) {
            prescriptionDAO.insertNewPrescriptions(prescription.getId(), patientData.getPatient().getId());
        }

    }

    public List<Prescription> prepareActivePrescriptions(Set<Prescription> pastPrescriptions, List<Prescription> listToPrepare) {
        List<Prescription> newList = listToPrepare;
        for (int i = 0; i < listToPrepare.size(); i++) {
            if (pastPrescriptions.contains(listToPrepare.get(i))) {
                newList.remove(i);
            }
        }
        return newList;
    }

    public  Set<Prescription> findPatientHistory(int patientId) {
        List<Integer> numbersOfPrescriptions = prescriptionDAO.findByPatientHistory(patientId);
        Set<Prescription> prescriptions = new HashSet<>();
        for (int i = 0; i < numbersOfPrescriptions.size(); i++) {
            prescriptions.add(prescriptionDAO.findById(numbersOfPrescriptions.get(i)));
        }
        return prescriptions;
    }

    public  Set<Prescription> findNewPrescriptions(int patientId) {
        List<Integer> numbersOfPrescriptions = prescriptionDAO.findByActivePrescriptions(patientId);
        Set<Prescription> prescriptions = new HashSet<>();
        for (int i = 0; i < numbersOfPrescriptions.size(); i++) {
            prescriptions.add(prescriptionDAO.findById(numbersOfPrescriptions.get(i)));
        }
        return prescriptions;
    }
}
