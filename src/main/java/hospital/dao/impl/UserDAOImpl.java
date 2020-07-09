package hospital.dao.impl;

import hospital.entity.User;
import hospital.utils.ConnectionPool;
import hospital.utils.queries.UserQueries;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Class, which represents User DAO
 * @author Yelyzaveta Onyshchenko
 * @version 1.01
 */
public class UserDAOImpl {
    /**
     * Instance of Logger
     */
    private static final Logger log = Logger.getLogger(UserDAOImpl.class);
    /**
     * Find all users
     * @return list of users
     */
    public  List<User> findAll() {
        List<User> result = new ArrayList<User>();
        try(Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UserQueries.FIND_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                int role = resultSet.getInt("role");
                User user = new User(id,name, surname, email, password, role);

                result.add(user);
            }
        } catch (Exception e) {
            log.error("Error",e);
        }

        return result;
    }
    /**
     * Find user by email and password
     * @return corresponding user
     */
    public  User findByEmailAndPass(String email, String password) {
        User user = null;
        try(Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UserQueries.FIND_BY_EMAIL_AND_PASS);
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = new User(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getInt("role")
                );
            }
        } catch (SQLException throwables) {
            log.error("Error",throwables);
        }
        return user;
    }
    /**
     * Insert new user in database
     */
    public  void insert(User user) {

        try(Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UserQueries.INSERT_USER, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setInt(5,user.getRole().getId());
            statement.execute();

        }
        catch (SQLException throwables) {
            log.error("Error",throwables);
        }

    }
    /**
     * Find user by id
     * @return corresponding user
     */
    public  User findById(int id) {
        User user = null;
        try(Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UserQueries.FIND_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = new User(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getInt("role"));
            }
        } catch (SQLException throwables) {
            log.error("Error",throwables);
        }
        return user;
    }
}
