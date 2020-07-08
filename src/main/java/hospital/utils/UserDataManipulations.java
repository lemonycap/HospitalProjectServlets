package hospital.utils;

import hospital.dao.impl.RoleDAOImpl;
import hospital.dao.impl.UserDAOImpl;
import hospital.entity.Role;

public class UserDataManipulations {
    DAOFactory factory;
    UserDAOImpl userDAO;
    RoleDAOImpl roleDAO;

    public UserDataManipulations() {
        this.factory = new DAOFactory();
        userDAO = factory.createUserDao();
        roleDAO = factory.createRoleDao();
    }

    public UserDataManipulations (DAOFactory factory) {
        this.factory = factory;
        userDAO = factory.createUserDao();
        roleDAO = factory.createRoleDao();
    }

    public Role detectRole(int id) {
        return roleDAO.findById(id);
    }
}
