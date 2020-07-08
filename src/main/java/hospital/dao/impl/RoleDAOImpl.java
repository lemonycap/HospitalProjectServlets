package hospital.dao.impl;

import hospital.entity.Role;
import hospital.utils.ConnectionPool;
import hospital.utils.queries.RoleQueries;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleDAOImpl {
    private static final Logger log = Logger.getLogger(RoleDAOImpl.class);
    public  Role findById(int id) {
        Role role = null;
        try(Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(RoleQueries.FIND_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                role = new Role(resultSet.getInt("role_id"),
                        resultSet.getString("name"));
            }
        } catch (SQLException throwables) {
            log.error("Error",throwables);
        }
        return role;
    }

    public  Role findByName(String name) {
        Role role = null;
        try(Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(RoleQueries.FIND_BY_NAME);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                role = new Role(resultSet.getInt("role_id"),
                        resultSet.getString("name"));
            }
        } catch (SQLException throwables) {
            log.error("Error",throwables);
        }
        return role;
    }
}
