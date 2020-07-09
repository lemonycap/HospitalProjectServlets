package hospital.utils.queries;


import hospital.entity.PatientData;
/**
 * Basic queries for patient data table.
 * @author Yelyzaveta Onyshchenko
 * @version 1.01
 */

public interface PatientDataQueries {
    /**
     * SELECT * FROM patient_info;
     */
    public static final String FIND_ALL = "select * from " + PatientData.TABLE_NAME;
    /**
     * SELECT * FROM patient_info WHERE doctor_id = ? AND patient_status = 1;
     */
    public static final String FIND_DOCTOR_PATIENTS = "select * from " + PatientData.TABLE_NAME + " where "
            + PatientData.DOCTOR_COLUMN + " = ? and " + PatientData.STATUS_COLUMN +  " = 1" ;
    /**
     * SELECT * FROM patient_info WHERE nurse_id = ? AND patient_status = 1;
     */
    public static final String FIND_NURSE_PATIENTS = "select * from " + PatientData.TABLE_NAME + " where "
            + PatientData.NURSE_COLUMN + " = ? and " + PatientData.STATUS_COLUMN +  " = 1" ;

    /**
     * SELECT * FROM patient_info WHERE patient_id = ?
     */
    public static final String FIND_PATIENT = "select * from " + PatientData.TABLE_NAME + " where "
            + PatientData.PATIENT_COLUMN + " = ?";
    /**
     * SELECT * FROM patient_info WHERE doctor_id IS NULL AND patient_status = 1;
     */
    public static final String FIND_WITHOUT_DOC = "select * from " + PatientData.TABLE_NAME + " where "
            + PatientData.DOCTOR_COLUMN + " IS NULL and " + PatientData.STATUS_COLUMN +  " = 1";
    /**
     * SELECT * FROM patient_info WHERE nurse_id IS NULL AND patient_status = 1;
     */
    public static final String FIND_WITHOUT_NURSE = "select * from " + PatientData.TABLE_NAME + " where "
            + PatientData.NURSE_COLUMN + " IS NULL and " + PatientData.STATUS_COLUMN +  " = 1";
    /**
     * INSERT INTO patient_info (patient_id) VALUES (?);
     */
    public static final String INSERT_PATIENT = "insert into " + PatientData.TABLE_NAME
            + " (" + PatientData.PATIENT_COLUMN +  ") values (?)";
    /**
     * UPDATE patient_info SET patient_status = 0, doctor_id = NULL, nurse_id = NULL WHERE patient_id = ?;
     */
    public static final String UPDATE_PATIENT_STATUS = "update " + PatientData.TABLE_NAME + " set " + PatientData.STATUS_COLUMN +" = 0, " +
            PatientData.DOCTOR_COLUMN + " = NULL, " + PatientData.NURSE_COLUMN + " = NULL  where " +
            PatientData.PATIENT_COLUMN + " = ?";
    /**
     * UPDATE patient_info SET doctor_id = ? WHERE patient_id = ?;
     */
    public static final String ADD_DOCTOR = "update " + PatientData.TABLE_NAME + " set " + PatientData.DOCTOR_COLUMN +" = ? where " + PatientData.PATIENT_COLUMN + " = ?";
    /**
     * UPDATE patient_info SET nurse_id = ? WHERE patient_id = ?;
     */
    public static final String ADD_NURSE = "update " + PatientData.TABLE_NAME + " set " + PatientData.NURSE_COLUMN +" = ? where " + PatientData.PATIENT_COLUMN + " = ?";
    /**
     * UPDATE patient_info SET diagnosis_id = ? WHERE patient_id = ?;
     */
    public static final String ADD_DIAGNOSIS = "update " + PatientData.TABLE_NAME + " set " + PatientData.DIAGNOSIS_COLUMN +" = ? where " + PatientData.PATIENT_COLUMN + " = ?";
}
