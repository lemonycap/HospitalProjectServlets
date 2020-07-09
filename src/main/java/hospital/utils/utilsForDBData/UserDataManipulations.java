package hospital.utils.utilsForDBData;

import hospital.dao.impl.RoleDAOImpl;
import hospital.dao.impl.UserDAOImpl;
import hospital.entity.Role;
import hospital.utils.factories.DAOFactory;

/**
 * Class, which used for operations with user data.
 * @author Yelyzaveta Onyshchenko
 * @version 1.01
 */
public class UserDataManipulations {
    /**
     * Instance of DAO factory
     */
    DAOFactory factory;
    /**
     * Instance of user DAO
     */
    UserDAOImpl userDAO;
    /**
     * Instance of role DAO
     */
    RoleDAOImpl roleDAO;

    /**
     * Constructor for creating new object.
     * @see UserDataManipulations(DAOFactory)
     */
    public UserDataManipulations() {
        this.factory = new DAOFactory();
        userDAO = factory.createUserDao();
        roleDAO = factory.createRoleDao();
    }
    /**
     * Constructor for creating new object.
     * @see UserDataManipulations()
     */
    public UserDataManipulations (DAOFactory factory) {
        this.factory = factory;
        userDAO = factory.createUserDao();
        roleDAO = factory.createRoleDao();
    }

    /**
     * Method which defines user role by id
     * @param id id of user
     * @return corresponding instance of Role class
     */
    public Role detectRole(int id) {
        return roleDAO.findById(id);
    }
}
