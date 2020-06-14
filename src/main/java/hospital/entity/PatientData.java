package hospital.entity;

import hospital.dao.impl.DiagnosisDAOImpl;
import hospital.dao.impl.PatientDataImpl;
import hospital.dao.impl.PrescriptionDAOImpl;
import hospital.dao.impl.UserDAOImpl;
import hospital.entity.diagnosis.Diagnosis;
import hospital.entity.prescription.Prescription;
import hospital.utils.RandomNumber;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class PatientData {
    public static final String TABLE_NAME = "patient_info";
    public static final String ID_COLUMN = "id";
    public static final String PATIENT_COLUMN = "patient_id";
    public static final String DOCTOR_COLUMN = "doctor_id";
    public static final String NURSE_COLUMN = "nurse_id";
    public static final String DIAGNOSIS_COLUMN = "diagnosis_id";
    public static final String STATUS_COLUMN = "patient_status";

    private int id;
    private User patient;
    private User doctor;
    private User nurse;
    private Diagnosis diagnosis;
    private int patientStatus;
    private Set<Prescription> prescriptionHistory;
    private Set<Prescription> currentPrescriptions;

    public PatientData(int patientId,int doctorId, int nurseId, int diagnosisId, int patientStatus) {
        this.patient = UserDAOImpl.findById(patientId);
        this.doctor = UserDAOImpl.findById(doctorId);
        this.nurse = UserDAOImpl.findById(nurseId);
        this.diagnosis = DiagnosisDAOImpl.findById(diagnosisId);
        this.patientStatus = patientStatus;
        this.prescriptionHistory = findPatientHistory(patientId);
        this.currentPrescriptions = findNewPrescriptions(patientId);
    }

    public static void refreshPatients() {
        List<User> users = UserDAOImpl.findAll();
        for (User user: users) {
            if (user.getRole().getName().equals("PATIENT")) {
                PatientData patientData = PatientDataImpl.findByPatientId(user.getId());
                if (patientData == null) {
                    PatientDataImpl.insert(user.getId());
                }
            }
            checkPrescriptionHistory();
        }

    }

    public static void checkPrescriptionHistory() {
        List<PatientData> allPatients = PatientDataImpl.findAll();
        for (int i = 0; i < allPatients.size(); i++) {
            if (allPatients.get(i).getPrescriptionHistory().isEmpty()) {
               createPatientHistory(allPatients.get(i).getPatient().getId());
            }
        }
    }

    public static void createPatientHistory(int patientId) {
        List<Prescription> allPrescriptions = PrescriptionDAOImpl.findAll();
        int numberOfPrescriptionsToInsert = RandomNumber.randNumber(0,3);
        int numberOfPrescr;
        for (int i = 0; i < numberOfPrescriptionsToInsert; i++) {
            numberOfPrescr = RandomNumber.randNumber(0,allPrescriptions.size() -1);
            PrescriptionDAOImpl.insertHistory(allPrescriptions.get(numberOfPrescr).getId(),patientId);
        }
    }

    public static void makePrescriptions(PatientData patientData) {
        Set<Prescription> history = findPatientHistory(patientData.getPatient().getId());
        List<Prescription> medicine = prepareActivePrescriptions(history,PrescriptionDAOImpl.findByClass("medicine"));
        List<Prescription> procedures =  prepareActivePrescriptions(history,PrescriptionDAOImpl.findByClass("procedures"));
        List<Prescription> operations =  prepareActivePrescriptions(history,PrescriptionDAOImpl.findByClass("operations"));
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
            PrescriptionDAOImpl.insertNewPrescriptions(prescription.getId(), patientData.getPatient().getId());
        }

    }

    public static List<Prescription> prepareActivePrescriptions(Set<Prescription> pastPrescriptions, List<Prescription> listToPrepare) {
        List<Prescription> newList = listToPrepare;
        for (int i = 0; i < listToPrepare.size(); i++) {
            if (pastPrescriptions.contains(listToPrepare.get(i))) {
                newList.remove(i);
            }
        }
        return newList;
    }

    public static Set<Prescription> findPatientHistory(int patientId) {
        List<Integer> numbersOfPrescriptions = PrescriptionDAOImpl.findByPatientHistory(patientId);
        Set<Prescription> prescriptions = new HashSet<>();
        for (int i = 0; i < numbersOfPrescriptions.size(); i++) {
            prescriptions.add(PrescriptionDAOImpl.findById(numbersOfPrescriptions.get(i)));
        }
        return prescriptions;
    }

    public static Set<Prescription> findNewPrescriptions(int patientId) {
        List<Integer> numbersOfPrescriptions = PrescriptionDAOImpl.findByActivePrescriptions(patientId);
        Set<Prescription> prescriptions = new HashSet<>();
        for (int i = 0; i < numbersOfPrescriptions.size(); i++) {
            prescriptions.add(PrescriptionDAOImpl.findById(numbersOfPrescriptions.get(i)));
        }
        return prescriptions;
    }


    public Set<Prescription> getPrescriptionHistory() {
        return prescriptionHistory;
    }

    public void setPrescriptionHistory(Set<Prescription> prescriptionHistory) {
        this.prescriptionHistory = prescriptionHistory;
    }

    public Set<Prescription> getCurrentPrescriptions() {
        return currentPrescriptions;
    }

    public void setCurrentPrescriptions(Set<Prescription> currentPrescriptions) {
        this.currentPrescriptions = currentPrescriptions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getPatient() {
        return patient;
    }

    public void setPatient(User patient) {
        this.patient = patient;
    }

    public User getDoctor() {
        return doctor;
    }

    public void setDoctor(User doctor) {
        this.doctor = doctor;
    }

    public User getNurse() {
        return nurse;
    }

    public void setNurse(User nurse) {
        this.nurse = nurse;
    }

    public Diagnosis getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(Diagnosis diagnosis) {
        this.diagnosis = diagnosis;
    }

    public int getPatientStatus() {
        return patientStatus;
    }

    public void setPatientStatus(int patientStatus) {
        this.patientStatus = patientStatus;
    }
}
