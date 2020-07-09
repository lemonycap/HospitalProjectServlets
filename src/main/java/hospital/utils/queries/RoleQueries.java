package hospital.utils.queries;


import hospital.entity.Role;

/**
 * Basic queries for ROLE table.
 * @author Yelyzaveta Onyshchenko
 * @version 1.01
 */
public interface RoleQueries {
    /**
     * SELECT * FROM user_roles WHERE role_id = ?;
     */
    public static final String FIND_BY_ID = "select * from " + Role.TABLE_NAME + " where "
            + Role.ID_COLUMN + " = ?";
    /**
     * SELECT * FROM user_roles WHERE name = ?;
     */
    public static final String FIND_BY_NAME = "select * from " + Role.TABLE_NAME + " where "
            + Role.NAME_COLUMN + " = ?";

}
