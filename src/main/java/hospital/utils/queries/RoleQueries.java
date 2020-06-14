package hospital.utils.queries;


import hospital.entity.Role;

public interface RoleQueries {
    public static final String FIND_BY_ID = "select * from " + Role.TABLE_NAME + " where "
            + Role.ID_COLUMN + " = ?";

    public static final String FIND_BY_NAME = "select * from " + Role.TABLE_NAME + " where "
            + Role.NAME_COLUMN + " = ?";

}
