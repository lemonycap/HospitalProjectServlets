package hospital.utils.queries;

import hospital.entity.User;

/**
 * Basic queries for user table.
 * @author Yelyzaveta Onyshchenko
 * @version 1.01
 */
public interface UserQueries {
    /**
     * SELECT * FROM app_users WHERE email = ? AND password = ?;
     */
    public static final String FIND_BY_EMAIL_AND_PASS = "select * from " + User.TABLE_NAME + " where "
            + User.EMAIL_COLUMN + " = ? and " + User.PASSWORD_COLUMN + " = ?";
    /**
     * SELECT * FROM app_users;
     */
    public static final String FIND_ALL = "select * from " + User.TABLE_NAME;
    /**
     * SELECT * FROM app_users WHERE id = ?;
     */
    public static final String FIND_BY_ID = FIND_ALL + " where " + User.ID_COLUMN + " = ?";
    /**
     * INSERT INTO app_users(name,surname,email,password,role) VALUES (?, ?, ?, ?, ?);
     */
    public static final String INSERT_USER = "insert into " + User.TABLE_NAME
            + " (" + User.NAME_COLUMN + ", " + User.SURNAME_COLUMN + ", " +
            User.EMAIL_COLUMN + ", " + User.PASSWORD_COLUMN +", " + User.ROLE_COLUMN +  ") values (?, ?, ?, ?, ?)";
}

