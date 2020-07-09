package hospital.utils.doctorcommands;

import hospital.dao.impl.PatientDataImpl;
import hospital.entity.PatientData;
import hospital.entity.prescription.Prescription;
import hospital.utils.factories.DAOFactory;
import hospital.utils.factories.ServletCommand;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class, which represents release of the patient command.
 * @author Yelyzaveta Onyshchenko
 * @version 1.01
 */
public class ReleasePatientCommand implements ServletCommand {
    /**
     * Instance of Logger
     */
    private static final Logger log = Logger.getLogger(ReleasePatientCommand.class);
    /**
     * Instance of DAO factory
     */
    DAOFactory factory;

    /**
     * Constructor for creating new object
     * @see ReleasePatientCommand(DAOFactory)
     */
    public ReleasePatientCommand() {
        this.factory = new DAOFactory();
    }
    /**
     * Constructor for creating new object
     * @see ReleasePatientCommand()
     */
    public ReleasePatientCommand(DAOFactory factory) {
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
           log.info("Patient's release");
           PatientDataImpl patientDataImpl = factory.createPatientDataDao();
            int id = Integer.parseInt(request.getParameter("id"));
            PatientData patient = patientDataImpl.findByPatientId(id);
            log.debug("Patient is" + patient.getPatient().getName() + patient.getPatient().getSurname());
            boolean abilityToRelease = true;
            for (Prescription prescription : patient.getCurrentPrescriptions()) {
                if (prescription.getPrescriptionClass().equals("operations")) {
                    abilityToRelease = false;
                    log.info("Patient can't be released");
                }
            }
            if (abilityToRelease) {
                patientDataImpl.updatePatientStatus(id);
             log.info("Patient have been released");
            }
            request.getServletContext().getRequestDispatcher("/doctorPage").forward(request, response);
        } catch (Exception ex) {
            log.error("An error occured while patient's release procedure:", ex);
            request.getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
