package hospital.utils.nursecommands;

import hospital.dao.impl.PatientDataImpl;
import hospital.dao.impl.UserDAOImpl;
import hospital.entity.PatientData;
import hospital.entity.User;
import hospital.utils.PasswordEncryptorSHA256;
import hospital.utils.factories.DAOFactory;
import hospital.utils.factories.ServletCommand;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class NursePageCommand implements ServletCommand {
    private static final Logger log = Logger.getLogger(NursePageCommand.class);
    DAOFactory factory;
    public NursePageCommand () {
        factory = new DAOFactory();
    }

    public NursePageCommand (DAOFactory factory) {
        this.factory = factory;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PatientDataImpl patientData = factory.createPatientDataDao();
            UserDAOImpl userDAO = factory.createUserDao();
            HttpSession session = request.getSession();
            String email = String.valueOf(session.getAttribute("email"));
            String password = (String.valueOf(session.getAttribute("password")));
            User activeNurse = userDAO.findByEmailAndPass(email, PasswordEncryptorSHA256.encryptPasswordWithSHA256(password));
            if (activeNurse != null) {
                request.setAttribute("nurse", activeNurse);
                log.info("Active nurse:" + activeNurse.getName() + activeNurse.getSurname());
                List<PatientData> patients = patientData.findNursePatients(activeNurse.getId());
                request.setAttribute("activePatients", patients);
            }
            log.info("Redicrecting to nurse page");
            request.getServletContext().getRequestDispatcher("/nursesPage.jsp").forward(request, response);
        }
        catch(Exception ex) {
            log.error("An error occured while redirecting to nurse page",ex);
            request.getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
