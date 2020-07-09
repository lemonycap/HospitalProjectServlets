package hospital.entity;

import hospital.entity.diagnosis.Diagnosis;
import hospital.entity.prescription.Prescription;
import hospital.utils.utilsForDBData.PatientDataManipulations;

import java.util.Set;

/**
 * Class, which represents patient data.
 * @author Yelyzaveta Onyshchenko
 * @version 1.01
 */
public class PatientData {
    /**
     * The corresponding name of table in database.
     */
    public static final String TABLE_NAME = "patient_info";
    /**
     * The corresponding name of id column in table.
     */
    public static final String ID_COLUMN = "id";
    /**
     * The corresponding name of patient id column in table.
     */
    public static final String PATIENT_COLUMN = "patient_id";
    /**
     * The corresponding name of doctor id column in table.
     */
    public static final String DOCTOR_COLUMN = "doctor_id";
    /**
     * The corresponding name of nurse id column in table.
     */
    public static final String NURSE_COLUMN = "nurse_id";
    /**
     * The corresponding name of diagnosis id column in table.
     */
    public static final String DIAGNOSIS_COLUMN = "diagnosis_id";
    /**
     * The corresponding name of patient status column in table.
     */
    public static final String STATUS_COLUMN = "patient_status";

    /**
     * Id of entity.
     */
    private int id;
    /**
     * Patient, who is the data about.
     */
    private User patient;
    /**
     * Patient's doctor.
     */
    private User doctor;
    /**
     * Patient's nurse.
     */
    private User nurse;
    /**
     * Patient's diagnosis.
     */
    private Diagnosis diagnosis;
    /**
     * Patient's status.
     */
    private int patientStatus;
    /**
     * Patient's prescription history.
     */
    private Set<Prescription> prescriptionHistory;
    /**
     * Patient's current prescriptions.
     */
    private Set<Prescription> currentPrescriptions;
    /**
     * Entity of the helper class which is used for operations with data.
     */
     PatientDataManipulations patientDataManipulations;


    /**
     * Constructor for creating new object.
     * @param patientId  corresponding patient id
     * @param doctorId  corresponding doctor id
     * @param nurseId corresponding nurse id
     * @param diagnosisId corresponding diagnosis id
     * @param patientStatus corresponding patient status
     * @see PatientData(int,int,int,int,int,PatientDataManipulations)
     */
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
    /**
     * Constructor for creating new object, used for testing.
     * @param patientId  corresponding patient id
     * @param doctorId  corresponding doctor id
     * @param nurseId corresponding nurse id
     * @param diagnosisId corresponding diagnosis id
     * @param patientStatus corresponding patient status
     * @param manipulations corresponding instance of PatientDataManipulations class
     * @see PatientData(int,int,int,int,int)
     */
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

    /**
     * Gets prescription history
     * @return prescription history
     */
    public Set<Prescription> getPrescriptionHistory() {
        return prescriptionHistory;
    }

    /**
     * Sets prescription history
     * @param prescriptionHistory prescription history to set
     */
    public void setPrescriptionHistory(Set<Prescription> prescriptionHistory) {
        this.prescriptionHistory = prescriptionHistory;
    }

    /**
     * Gets current prescriptions
     * @return current prescriptions
     */
    public Set<Prescription> getCurrentPrescriptions() {
        return currentPrescriptions;
    }

    /**
     * Sets current prescriptions
     * @param currentPrescriptions current prescriptions to set
     */
    public void setCurrentPrescriptions(Set<Prescription> currentPrescriptions) {
        this.currentPrescriptions = currentPrescriptions;
    }

    /**
     * Gets id of instance
     * @return id of instance
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id of instance
     * @param id id of instance to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets patient
     * @return patient
     */
    public User getPatient() {
        return patient;
    }

    /**
     * Sets patient
     * @param patient patient to set to current instance
     */
    public void setPatient(User patient) {
        this.patient = patient;
    }

    /**
     * Gets patient's doctor.
     * @return patient's doctor
     */
    public User getDoctor() {
        return doctor;
    }

    /**
     * Sets patient's doctor
     * @param doctor patient's doctor to set
     */
    public void setDoctor(User doctor) {
        this.doctor = doctor;
    }
    /**
     * Gets patient's nurse.
     * @return patient's nurse
     */
    public User getNurse() {
        return nurse;
    }
    /**
     * Sets patient's nurse
     * @param nurse patient's nurse to set
     */
    public void setNurse(User nurse) {
        this.nurse = nurse;
    }
    /**
     * Gets patient's diagnosis.
     * @return patient's diagnosis
     */
    public Diagnosis getDiagnosis() {
        return diagnosis;
    }
    /**
     * Sets patient's diagnosis
     * @param diagnosis patient's diagnosis to set
     */
    public void setDiagnosis(Diagnosis diagnosis) {
        this.diagnosis = diagnosis;
    }
    /**
     * Gets patient's status.
     * @return patient's status
     */
    public int getPatientStatus() {
        return patientStatus;
    }
    /**
     * Sets patient's status
     * @param patientStatus patient's status to set
     */
    public void setPatientStatus(int patientStatus) {
        this.patientStatus = patientStatus;
    }
}
