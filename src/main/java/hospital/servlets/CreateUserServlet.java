package hospital.servlets;

import hospital.dao.impl.*;
import hospital.entity.Role;
import hospital.entity.User;
import hospital.utils.InputDataCheck;
import hospital.utils.PasswordEncryptorSHA256;
import hospital.utils.RegexContainer;
import hospital.utils.UserAlreadyExistsException;
import hospital.utils.factories.DAOFactory;
import hospital.utils.utilsForDBData.PatientDataManipulations;
import org.apache.log4j.Logger;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registration")

public class CreateUserServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(CreateUserServlet.class);

   User user = null;
   PatientDataManipulations patientDataManipulations;
   DAOFactory factory;

   public CreateUserServlet() {
       this.factory = new DAOFactory();
       patientDataManipulations = new PatientDataManipulations();
   }

   public CreateUserServlet(DAOFactory daoFactory,PatientDataManipulations patientDataManipulations2) {
       this.factory = daoFactory;
       patientDataManipulations = patientDataManipulations2;
   }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Entering a registration page");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("registration.jsp");
        requestDispatcher.forward(req, resp);
    }

        protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
      try {
                req.setCharacterEncoding("UTF-8");

                final String role = req.getParameter("role").toUpperCase();
                final String name = req.getParameter("name");
                final String surname = req.getParameter("surname");
                final String email = req.getParameter("email");
                final String password = req.getParameter("password");
                boolean isValid = inputValidator(name,surname,email,password);
                if (!isValid) {
                    req.setAttribute("message","You entered non-valid credentials. Please,try again.");
                    req.getServletContext().getRequestDispatcher("/registration.jsp").forward(req, resp);
                }
                else {
                    RoleDAOImpl roleDAO = factory.createRoleDao();
                    UserDAOImpl userDAO = factory.createUserDao();

                    if (userDAO.findByEmailAndPass(email, PasswordEncryptorSHA256.encryptPasswordWithSHA256(password)) == null) {
                        Role userRole = roleDAO.findByName(role);
                        User user = new User(name, surname, email, password, userRole.getId());
                        log.debug("New user: " + user.getEmail() + user.getRole().getName());
                        userDAO.insert(user);
                        patientDataManipulations.refreshPatients();
                        resp.sendRedirect(req.getContextPath() + "/");
                    }
                    else {
                        log.debug("User with such credentials already exists.");
                        req.setAttribute("message","User with such credentials already exists!");
                       // req.getServletContext().getRequestDispatcher("/registration.jsp").forward(req, resp);
                        throw new UserAlreadyExistsException();
                    }
                }
            }
            catch (Exception ex) {
                log.error("An error occured while creating a new user",ex);
                req.getServletContext().getRequestDispatcher("/error.jsp").forward(req, resp);
            }
    }

    public static boolean inputValidator(String name, String surname, String email, String password ) {
        boolean isValid;
        isValid = InputDataCheck.inputCheck(name, RegexContainer.REGEX_NAME_ENG);
        if (!isValid) {
            log.debug("Non-valid name entered");
            return false;
        }
        isValid = InputDataCheck.inputCheck(surname, RegexContainer.REGEX_NAME_ENG);
        if (!isValid) {
            log.debug("Non-valid surname entered");
            return false;
        }
        isValid = InputDataCheck.inputCheck(email, RegexContainer.REGEX_EMAIL);
        if (!isValid) {
            log.debug("Non-valid email entered");
            return false;
        }
        isValid = InputDataCheck.inputCheck(password, RegexContainer.REGEX_PASSWORD);
        if (!isValid) {
            log.debug("Non-valid password entered");
            return false;
        }
        return true;
    }
}
