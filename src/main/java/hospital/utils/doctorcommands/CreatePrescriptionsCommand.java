package hospital.utils.doctorcommands;

import hospital.dao.impl.PatientDataImpl;
import hospital.entity.PatientData;
import hospital.servlets.patientServlets.PatientPageServlet;
import hospital.utils.factories.DAOFactory;
import hospital.utils.utilsForDBData.PatientDataManipulations;
import hospital.utils.factories.ServletCommand;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class, which represents creating of prescriptions command.
 * @author Yelyzaveta Onyshchenko
 * @version 1.01
 */

public class CreatePrescriptionsCommand implements ServletCommand {
    /**
     * Instance of Logger
     */
    private static final Logger log = Logger.getLogger(CreatePrescriptionsCommand.class);
    /**
     * Instance of PatientDataManipulations used for operations with data
     */
    PatientDataManipulations patientDataManipulations;
    /**
     * Instance of DAO factory
     */
    DAOFactory factory;

    /**
     * Constructor for creating new object
     * @see CreatePrescriptionsCommand(DAOFactory)
     */
    public CreatePrescriptionsCommand() {
        this.factory = new DAOFactory();
        this.patientDataManipulations = new PatientDataManipulations();
    }
    /**
     * Constructor for creating new object,used for testing
     * @see CreatePrescriptionsCommand()
     */
    public CreatePrescriptionsCommand(DAOFactory daoFactory,PatientDataManipulations patientDataManipulations) {
        this.factory = daoFactory;
        this.patientDataManipulations = patientDataManipulations;
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
            log.info("Prescriptions establishment");
            int id = Integer.parseInt(request.getParameter("id"));
            PatientData patientData = patientDataImpl.findByPatientId(id);
            log.debug("Patient is" + patientData.getPatient().getName() + patientData.getPatient().getSurname());
            patientDataManipulations.makePrescriptions(patientData);
            log.info("Prescriptions established successfully");
            request.getServletContext().getRequestDispatcher("/doctorPage").forward(request, response);
        } catch (Exception ex) {
            log.error("An error occured while prescription establishment:", ex);
            request.getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
