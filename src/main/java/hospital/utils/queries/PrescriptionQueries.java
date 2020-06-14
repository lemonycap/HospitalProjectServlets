package hospital.utils.queries;

import hospital.entity.prescription.Prescription;

public interface PrescriptionQueries {
    public static final String FIND_ALL = "select * from " + Prescription.TABLE_NAME;
    public static final String FIND_ALL_HISTORY = "select * from " + Prescription.CONNECTED_TABLE_HISTORY + " where " +
            Prescription.CONNECTED_TABLE_HISTORY_PATIENT + " = ?";
    public static final String FIND_ALL_NEW = "select * from " + Prescription.CONNECTED_TABLE_NEW + " where " +
            Prescription.CONNECTED_TABLE_NEW_PATIENT + " = ?";
    public static final String FIND_ALL_MED = "select * from " + Prescription.MED_TABLE;
    public static final String FIND_BY_ID = FIND_ALL + " where " + Prescription.ID_COLUMN + " = ?";
    public static final String FIND_BY_CLASS = FIND_ALL + " where " + Prescription.CLASS_COLUMN + " = ?";
    public static final String INSERT_PRESCRIPTION_HISTORY = "insert into " + Prescription.CONNECTED_TABLE_HISTORY
            + " (" + Prescription.CONNECTED_TABLE_HISTORY_PATIENT + ", " + Prescription.CONNECTED_TABLE_HISTORY_PRESCR
           + ") values (?, ?)";

    public static final String INSERT_NEW_PRESCRIPTION = "insert into " + Prescription.CONNECTED_TABLE_NEW
            + " (" + Prescription.CONNECTED_TABLE_NEW_PATIENT + ", " + Prescription.CONNECTED_TABLE_NEW_PRESCR
            + ") values (?, ?)";
    public static final String DELETE_PRESCRIPTION = "delete from " + Prescription.CONNECTED_TABLE_NEW + " where " + Prescription.CONNECTED_TABLE_NEW_PRESCR
            +" = ? and " + Prescription.CONNECTED_TABLE_NEW_PATIENT + " = ?";
    public static final String FIND_MED_LIST = FIND_ALL_MED + " where " + Prescription.MED_TABLE_NAME + " = ?";
    public static final String UPDATE_MED_LIST = "update " + Prescription.MED_TABLE + " set " + Prescription.MED_TABLE_AMOUNT +
            " = ? where " + Prescription.MED_TABLE_NAME + " = ?";
    /*
    public static final String FIND_MED_LIST = FIND_ALL_MED + " where " + Prescription.MED_TABLE_NAME + " = ?";
    public static final String FIND_BY_PATIENT_ID = FIND_ALL_CONNECTED + " where " + Prescription.CONNECTED_TABLE_PATIENT + " = ?";
    public static final String UPDATE_MED_LIST = "update " + Prescription.MED_TABLE + " set " + Prescription.MED_TABLE_AMOUNT +
            " = ? where " + Prescription.MED_TABLE_NAME + " = ?";*/
}


