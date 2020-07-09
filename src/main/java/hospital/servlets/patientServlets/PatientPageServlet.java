package hospital.servlets.patientServlets;

import hospital.dao.impl.PatientDataImpl;
import hospital.dao.impl.UserDAOImpl;
import hospital.entity.PatientData;
import hospital.entity.User;
import hospital.utils.factories.DAOFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Class, which represents patient page servlet.
 * @author Yelyzaveta Onyshchenko
 * @version 1.01
 */


@WebServlet ("/patientPage")

public class PatientPageServlet extends HttpServlet {
    /**
     * Instance of Logger
     */
    private static final Logger log = Logger.getLogger(PatientPageServlet.class);
    /**
     * Instance of DAO factory
     */
    DAOFactory factory;

    /**
     * Constructor for creating new object
     * @see PatientPageServlet(DAOFactory)
     */
    public PatientPageServlet () {
        this.factory = new DAOFactory();
    }
    /**
     * Constructor for creating new object
     * @param factory instance of DAO factory
     * @see PatientPageServlet(DAOFactory)
     */
    public PatientPageServlet(DAOFactory factory) {
        this.factory = factory;
    }

    /**
     * Method performing HHTP POST request
     * @param request HttpRequest
     * @param response HttpResponse
     * @throws ServletException On servlet error
     * @throws IOException On error fulfilling the request
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            log.info("Patient page");
            UserDAOImpl userDAO = factory.createUserDao();
            PatientDataImpl patientDataImpl = factory.createPatientDataDao();
            HttpSession session = request.getSession();
            String email = String.valueOf(session.getAttribute("email"));
            String password = (String.valueOf(session.getAttribute("password")));
            User activePatient = userDAO.findByEmailAndPass(email, password);
            if (activePatient != null) {
                request.setAttribute("patient", activePatient);
                PatientData patientData = patientDataImpl.findByPatientId(activePatient.getId());
                log.debug("Active patient is" + patientData.getPatient().getName() + patientData.getPatient().getSurname());
                request.setAttribute("patient",patientData);
            }
            log.info("Redirecting to patientPage.jsp");
            request.getServletContext().getRequestDispatcher("/patientPage.jsp").forward(request, response);
        }
        catch(Exception ex) {
            log.error("An error occured while attempting to enter a patient profile",ex);
            request.getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
