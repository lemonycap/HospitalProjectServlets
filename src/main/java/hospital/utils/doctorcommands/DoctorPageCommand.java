package hospital.utils.doctorcommands;

import hospital.dao.impl.PatientDataImpl;
import hospital.dao.impl.UserDAOImpl;
import hospital.entity.PatientData;
import hospital.entity.User;
import hospital.utils.DAOFactory;
import hospital.utils.PasswordEncryptorSHA256;
import hospital.utils.ServletCommand;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class DoctorPageCommand implements ServletCommand {
    private static final Logger log = Logger.getLogger(DoctorPageCommand.class);
    DAOFactory factory;

    public DoctorPageCommand() {
        this.factory = new DAOFactory();
    }

    public DoctorPageCommand(DAOFactory factory) {
        this.factory = factory;
    }

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
