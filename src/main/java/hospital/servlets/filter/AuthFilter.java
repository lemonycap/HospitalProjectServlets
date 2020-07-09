package hospital.servlets.filter;

import hospital.dao.impl.*;
import hospital.entity.User;
import hospital.servlets.patientServlets.PatientPageServlet;
import hospital.utils.PasswordEncryptorSHA256;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import hospital.utils.factories.DAOFactory;
import org.apache.log4j.Logger;

import static java.util.Objects.nonNull;
/**
 * Class, which represents authentication filter.
 * @author Yelyzaveta Onyshchenko
 * @version 1.01
 */
@WebFilter("/login")

public class AuthFilter implements Filter {
    /**
     * Instance of Logger
     */
    private static final Logger log = Logger.getLogger(AuthFilter.class);
    /**
     * Instance of User DAO
     */
    UserDAOImpl userDAO;
    /**
     * Instance of DAO factory
     */
    DAOFactory factory;
    /**
     * Constructor for creating new object
     * @see AuthFilter(DAOFactory)
     */
    public AuthFilter () {
        this.factory = new DAOFactory();
        userDAO = factory.createUserDao();
    }
    /**
     * Constructor for creating new object
     * @see AuthFilter()
     */
    public AuthFilter(DAOFactory daoFactory) {
        this.factory = daoFactory;
        userDAO = factory.createUserDao();
    }

    /**
     * Init filter method
     * @param filterConfig filter config
     * @throws ServletException on servlet error
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    /**
     * Method which perfoms filtering
     * @param request HttpRequest
     * @param response HttpResponse
     * @param chain FilterChain instance
     * @throws IOException on error of performing filtering
     * @throws ServletException on servlet error
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            final HttpServletRequest req = (HttpServletRequest) request;
            final HttpServletResponse res = (HttpServletResponse) response;

            final String email = String.valueOf(req.getParameter("email"));
            final String password = String.valueOf(req.getParameter("password"));

            final HttpSession session = req.getSession();


            if (nonNull(session) &&
                    nonNull(session.getAttribute("email")) &&
                    nonNull(session.getAttribute("password"))) {
                log.info("User already logged in");
                String login = String.valueOf(session.getAttribute("email"));
                String pass = String.valueOf(session.getAttribute("password"));
                User activeUser = userDAO.findByEmailAndPass(login,PasswordEncryptorSHA256.encryptPasswordWithSHA256(pass));
                 sendToMenu(req, res, true,activeUser.getRole().getName());
            }

            else {
                boolean loginCheck  = false;
                User activeUser = userDAO.findByEmailAndPass(email,PasswordEncryptorSHA256.encryptPasswordWithSHA256(password));
                if (activeUser != null) {
                    loginCheck = true;
                    log.debug("Active user is " + activeUser.getName() + " " + activeUser.getSurname());
                }

                session.setAttribute("role", activeUser.getRole().getName());
                session.setAttribute("email", email);
                session.setAttribute("password", password);
                sendToMenu(req, res, loginCheck,activeUser.getRole().getName());
            }

        } catch (NoSuchAlgorithmException e) {
            log.error("sha-256 algorithm exception: ",e);
        }
    }

    /**
     * On case of calling this method, doFilter method will not perform again on current instance
     */
        @Override
    public void destroy() {

    }

    /**
     * Method which defines on which page user will be redirected
     * @param req HttpRequest
     * @param res HttpResponse
     * @param loginCheck boolean which defines if user successfully signed in or not
     * @param role role of user
     * @throws ServletException on servlet error
     * @throws IOException on error occured while processing the request
     */
    private void sendToMenu (final HttpServletRequest req,
                             final HttpServletResponse res,
                             boolean loginCheck,String role)  throws ServletException, IOException {
        if (loginCheck) {
            System.out.println(role);
            if (role.equals("DOCTOR")) {
                log.info("User successfully loged in as a doctor");
                req.getRequestDispatcher("/doctorPage").forward(req, res);
            }
            else if (role.equals("NURSE")) {
                log.info("User successfully loged in as a nurse");
                req.getRequestDispatcher("/nursePage").forward(req, res);
            }
            else if (role.equals("PATIENT")) {
                log.info("User successfully loged in as a patient");
                req.getRequestDispatcher("/patientPage").forward(req, res);
            }
        }
        else {
            req.getSession().removeAttribute("role");
            req.getSession().removeAttribute("email");
            req.getSession().removeAttribute("password");
            log.error("No user with entered credentials exists!");
            req.getRequestDispatcher("error.jsp").forward(req, res);
        }
    }
}
