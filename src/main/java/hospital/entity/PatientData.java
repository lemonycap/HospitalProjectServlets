package hospital.entity;

import hospital.entity.diagnosis.Diagnosis;
import hospital.entity.prescription.Prescription;
import hospital.utils.utilsForDBData.PatientDataManipulations;

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

    PatientDataManipulations patientDataManipulations;


    public PatientData(int patientId,int doctorId, int nurseId, int diagnosisId, int patientStatus) {
        patientDataManipulations = new PatientDataManipulations();
        this.patient = patientDataManipulations.detectPerson(patientId);
        this.doctor = patientDataManipulations.detectPerson(doctorId);
        this.nurse = patientDataManipulations.detectPerson(nurseId);
        this.diagnosis = patientDataManipulations.detectDiagnosis(diagnosisId);
        this.patientStatus = patientStatus;
        this.prescriptionHistory = patientDataManipulations.findPatientHistory(patientId);
        this.currentPrescriptions = patientDataManipulations.findNewPrescriptions(patientId);
    }

    public PatientData(int patientId,int doctorId, int nurseId, int diagnosisId, int patientStatus,PatientDataManipulations manipulations) {
        this.patientDataManipulations = manipulations;
        this.patient = patientDataManipulations.detectPerson(patientId);
        this.doctor = patientDataManipulations.detectPerson(doctorId);
        this.nurse = patientDataManipulations.detectPerson(nurseId);
        this.diagnosis = patientDataManipulations.detectDiagnosis(diagnosisId);
        this.patientStatus = patientStatus;
        this.prescriptionHistory = patientDataManipulations.findPatientHistory(patientId);
        this.currentPrescriptions = patientDataManipulations.findNewPrescriptions(patientId);
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
