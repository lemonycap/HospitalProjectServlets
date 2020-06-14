package hospital.utils.queries;


import hospital.entity.PatientData;


public interface PatientDataQueries {
    public static final String FIND_ALL = "select * from " + PatientData.TABLE_NAME;
    public static final String FIND_DOCTOR_PATIENTS = "select * from " + PatientData.TABLE_NAME + " where "
            + PatientData.DOCTOR_COLUMN + " = ? and " + PatientData.STATUS_COLUMN +  " = 1" ;
    public static final String FIND_NURSE_PATIENTS = "select * from " + PatientData.TABLE_NAME + " where "
            + PatientData.NURSE_COLUMN + " = ? and " + PatientData.STATUS_COLUMN +  " = 1" ;

    public static final String FIND_PATIENT = "select * from " + PatientData.TABLE_NAME + " where "
            + PatientData.PATIENT_COLUMN + " = ?";

    public static final String FIND_WITHOUT_DOC = "select * from " + PatientData.TABLE_NAME + " where "
            + PatientData.DOCTOR_COLUMN + " IS NULL and " + PatientData.STATUS_COLUMN +  " = 1";

    public static final String FIND_WITHOUT_NURSE = "select * from " + PatientData.TABLE_NAME + " where "
            + PatientData.NURSE_COLUMN + " IS NULL and " + PatientData.STATUS_COLUMN +  " = 1";

    public static final String INSERT_PATIENT = "insert into " + PatientData.TABLE_NAME
            + " (" + PatientData.PATIENT_COLUMN +  ") values (?)";

    public static final String UPDATE_PATIENT_STATUS = "update " + PatientData.TABLE_NAME + " set " + PatientData.STATUS_COLUMN +" = 0, " +
            PatientData.DOCTOR_COLUMN + " = NULL, " + PatientData.NURSE_COLUMN + " = NULL  where " +
            PatientData.PATIENT_COLUMN + " = ?";

    public static final String ADD_DOCTOR = "update " + PatientData.TABLE_NAME + " set " + PatientData.DOCTOR_COLUMN +" = ? where " + PatientData.PATIENT_COLUMN + " = ?";
    public static final String ADD_NURSE = "update " + PatientData.TABLE_NAME + " set " + PatientData.NURSE_COLUMN +" = ? where " + PatientData.PATIENT_COLUMN + " = ?";
    public static final String ADD_DIAGNOSIS = "update " + PatientData.TABLE_NAME + " set " + PatientData.DIAGNOSIS_COLUMN +" = ? where " + PatientData.PATIENT_COLUMN + " = ?";
}
