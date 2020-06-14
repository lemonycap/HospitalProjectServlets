package hospital.utils.queries;

import hospital.entity.diagnosis.Diagnosis;

public interface DiagnosisQueries {
    public static final String FIND_ALL = "select * from " + Diagnosis.TABLE_NAME;
    public static final String FIND_BY_ID = FIND_ALL + " where " + Diagnosis.ID_COLUMN + " = ?";
}
