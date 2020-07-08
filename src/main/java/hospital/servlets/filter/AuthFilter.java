package hospital.servlets.filter;

import hospital.dao.impl.*;
import hospital.entity.User;
import hospital.utils.PasswordEncryptorSHA256;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import org.apache.log4j.Logger;

import static java.util.Objects.nonNull;

@WebFilter("/login")

public class AuthFilter implements Filter {

    private static final Logger log = Logger.getLogger(AuthFilter.class);
    PatientDataImpl patientData = new PatientDataImpl();
    UserDAOImpl userDAO = new UserDAOImpl();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            final HttpServletRequest req = (HttpServletRequest) request;
            final HttpServletResponse res = (HttpServletResponse) response;

            final String email = req.getParameter("email");
            final String password = PasswordEncryptorSHA256.encryptPasswordWithSHA256(req.getParameter("password"));

            final HttpSession session = req.getSession();


            if (nonNull(session) &&
                    nonNull(session.getAttribute("email")) &&
                    nonNull(session.getAttribute("password"))) {
                log.info("User already logged in");
                User activeUser = userDAO.findByEmailAndPass(email,password);
                 sendToMenu(req, res, true,activeUser.getRole().getName());
            }

            else {
                boolean loginCheck  = false;
                User activeUser = userDAO.findByEmailAndPass(email,password);
                if (activeUser != null) {
                    loginCheck = true;
                    log.debug("Active user is " + activeUser.getName() + " " + activeUser.getSurname());
                }

                req.getSession().setAttribute("role", activeUser.getRole().getName());
                req.getSession().setAttribute("email", email);
                req.getSession().setAttribute("password", password);
                sendToMenu(req, res, loginCheck,activeUser.getRole().getName());
            }

        } catch (NoSuchAlgorithmException e) {
            log.error("sha-256 algorithm exception: ",e);
        }
    }


        @Override
    public void destroy() {

    }

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
