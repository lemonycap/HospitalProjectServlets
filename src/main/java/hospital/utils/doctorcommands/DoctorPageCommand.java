package hospital.utils.doctorcommands;

import hospital.dao.impl.PatientDataImpl;
import hospital.dao.impl.UserDAOImpl;
import hospital.entity.PatientData;
import hospital.entity.User;
import hospital.utils.factories.DAOFactory;
import hospital.utils.PasswordEncryptorSHA256;
import hospital.utils.factories.ServletCommand;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
/**
 * Class, which represents  doctor page command.
 * @author Yelyzaveta Onyshchenko
 * @version 1.01
 */
public class DoctorPageCommand implements ServletCommand {
    /**
     * Instance of Logger
     */
    private static final Logger log = Logger.getLogger(DoctorPageCommand.class);
    /**
     * Instance of DAO factory
     */
    DAOFactory factory;
    /**
     * Constructor for creating new object
     * @see DoctorPageCommand(DAOFactory)
     */
    public DoctorPageCommand() {
        this.factory = new DAOFactory();
    }
    /**
     * Constructor for creating new object
     * @see DoctorPageCommand()
     */
    public DoctorPageCommand(DAOFactory factory) {
        this.factory = factory;
    }

    /**
     * Execute command method
     * @param request HttpRequest
     * @param response HttpResponse
     * @throws ServletException on servlet exception
     * @throws IOException on error occured while processing the request
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PatientDataImpl patientDataImpl = factory.createPatientDataDao();
            UserDAOImpl userDAO = factory.createUserDao();
            HttpSession session = request.getSession();
            String email = String.valueOf(session.getAttribute("email"));
            String password = (String.valueOf(session.getAttribute("password")));
            User activeDoctor = userDAO.findByEmailAndPass(email, PasswordEncryptorSHA256.encryptPasswordWithSHA256(password));
            if (activeDoctor != null) {
                request.setAttribute("doctor", activeDoctor);
                log.debug("Active doctor is: " + activeDoctor.getName() + " " + activeDoctor.getSurname());
                List<PatientData> patients = patientDataImpl.findDoctorPatients(activeDoctor.getId());
                request.setAttribute("activePatients", patients);
            }
            log.info("redirecting to doctor page");
            request.getServletContext().getRequestDispatcher("/doctorsPage.jsp").forward(request, response);
        } catch (Exception ex) {
            log.error("An error occured at the time of redirecting to doctor page", ex);
            request.getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
