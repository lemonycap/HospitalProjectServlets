package hospital.utils.queries;

import hospital.entity.prescription.Prescription;
/**
 * Basic queries for prescription table.
 * @author Yelyzaveta Onyshchenko
 * @version 1.01
 */
public interface PrescriptionQueries {
    /**
     * SELECT * FROM prescription_table;
     */
    public static final String FIND_ALL = "select * from " + Prescription.TABLE_NAME;
    /**
     * SELECT * FROM previous_prescriptions WHERE patient_id = ?;
     */
    public static final String FIND_ALL_HISTORY = "select * from " + Prescription.CONNECTED_TABLE_HISTORY + " where " +
            Prescription.CONNECTED_TABLE_HISTORY_PATIENT + " = ?";
    /**
     * SELECT * FROM new_prescriptions WHERE patient_id = ?;
     */
    public static final String FIND_ALL_NEW = "select * from " + Prescription.CONNECTED_TABLE_NEW + " where " +
            Prescription.CONNECTED_TABLE_NEW_PATIENT + " = ?";
    /**
     * SELECT * FROM medicine_list;
     */
    public static final String FIND_ALL_MED = "select * from " + Prescription.MED_TABLE;
    /**
     * SELECT * FROM prescription_table WHERE id_prescription = ?;
     */
    public static final String FIND_BY_ID = FIND_ALL + " where " + Prescription.ID_COLUMN + " = ?";
    /**
     * SELECT * FROM prescription_table WHERE prescription_class = ?;
     */
    public static final String FIND_BY_CLASS = FIND_ALL + " where " + Prescription.CLASS_COLUMN + " = ?";
    /**
     * INSERT INTO previous_prescriptions(patient_id,prescription_id) VALUES (?,?);
     */
    public static final String INSERT_PRESCRIPTION_HISTORY = "insert into " + Prescription.CONNECTED_TABLE_HISTORY
            + " (" + Prescription.CONNECTED_TABLE_HISTORY_PATIENT + ", " + Prescription.CONNECTED_TABLE_HISTORY_PRESCR
           + ") values (?, ?)";
    /**
     * INSERT INTO new_prescriptions(patient_id,prescription_id) VALUES (?,?);
     */
    public static final String INSERT_NEW_PRESCRIPTION = "insert into " + Prescription.CONNECTED_TABLE_NEW
            + " (" + Prescription.CONNECTED_TABLE_NEW_PATIENT + ", " + Prescription.CONNECTED_TABLE_NEW_PRESCR
            + ") values (?, ?)";
    /**
     * DELETE FROM new_prescriptions WHERE prescription_id = ? AND patient_id = ?;
     */
    public static final String DELETE_PRESCRIPTION = "delete from " + Prescription.CONNECTED_TABLE_NEW + " where " + Prescription.CONNECTED_TABLE_NEW_PRESCR
            +" = ? and " + Prescription.CONNECTED_TABLE_NEW_PATIENT + " = ?";
    /**
     * SELECT * FROM medicine_list WHERE medicine_name = ?;
     */
    public static final String FIND_MED_LIST = FIND_ALL_MED + " where " + Prescription.MED_TABLE_NAME + " = ?";
    /**
     * UPDATE medicine_list SET medicine_amount = ? WHERE medicine_name = ?;
     */
    public static final String UPDATE_MED_LIST = "update " + Prescription.MED_TABLE + " set " + Prescription.MED_TABLE_AMOUNT +
            " = ? where " + Prescription.MED_TABLE_NAME + " = ?";

}


