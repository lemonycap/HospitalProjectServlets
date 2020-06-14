package hospital.utils.queries;


import hospital.entity.User;


public interface UserQueries {
    public static final String FIND_BY_EMAIL_AND_PASS = "select * from " + User.TABLE_NAME + " where "
            + User.EMAIL_COLUMN + " = ? and " + User.PASSWORD_COLUMN + " = ?";
    public static final String FIND_ALL = "select * from " + User.TABLE_NAME;
    public static final String FIND_BY_ID = FIND_ALL + " where " + User.ID_COLUMN + " = ?";
    public static final String INSERT_USER = "insert into " + User.TABLE_NAME
            + " (" + User.NAME_COLUMN + ", " + User.SURNAME_COLUMN + ", " +
            User.EMAIL_COLUMN + ", " + User.PASSWORD_COLUMN +", " + User.ROLE_COLUMN +  ") values (?, ?, ?, ?, ?)";
}

