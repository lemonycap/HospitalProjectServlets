package hospital.utils;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;
/**
 * Class, which represents connection pool
 * @author Yelyzaveta Onyshchenko
 * @version 1.01
 */
public class ConnectionPool {
    /**
     * MySQl user's name
     */
    public static final String USER = "root";
    /**
     * MySQl user's password
     */
    public static final String PASSWORD = "root";
    /**
     * Url for accessing the database
     */
    public static final String URL =  "jdbc:mysql://localhost:3306/hospitaldb?useSSL=false&serverTimezone=UTC";
    /**
     * BasicDataSource instance
     */
    private static BasicDataSource ds = new BasicDataSource();

    static {
        ds.setUrl(URL);
        ds.setUsername(USER);
        ds.setPassword(PASSWORD);
        ds.setMinIdle(5);
        ds.setMaxIdle(10);
        ds.setMaxOpenPreparedStatements(100);
    }

    /**
     * Method for getting connection
     * @return connection to database
     * @throws SQLException SQLException
     */
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
