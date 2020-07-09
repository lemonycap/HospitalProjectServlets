package hospital.utils.queries;

import hospital.entity.diagnosis.Diagnosis;
/**
 * Basic queries for diagnosis table.
 * @author Yelyzaveta Onyshchenko
 * @version 1.01
 */
public interface DiagnosisQueries {
    /**
     * SELECT * FROM diagnosis_table;
     */
    public static final String FIND_ALL = "select * from " + Diagnosis.TABLE_NAME;
    /**
     * SELECT * FROM diagnosis_table WHERE id_diagnosis = ?;
     */
    public static final String FIND_BY_ID = FIND_ALL + " where " + Diagnosis.ID_COLUMN + " = ?";
}
