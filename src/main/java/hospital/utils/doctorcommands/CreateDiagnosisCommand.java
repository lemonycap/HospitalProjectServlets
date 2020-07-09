package hospital.utils.doctorcommands;

import hospital.dao.impl.DiagnosisDAOImpl;
import hospital.dao.impl.PatientDataImpl;
import hospital.entity.PatientData;
import hospital.entity.diagnosis.Diagnosis;
import hospital.servlets.patientServlets.PatientPageServlet;
import hospital.utils.factories.DAOFactory;
import hospital.utils.RandomNumber;
import hospital.utils.factories.ServletCommand;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Class, which represents creating of the diagnosis command.
 * @author Yelyzaveta Onyshchenko
 * @version 1.01
 */

public class CreateDiagnosisCommand implements ServletCommand {
    /**
     * Instance of Logger
     */
    private static final Logger log = Logger.getLogger(CreateDiagnosisCommand.class);
    /**
     * Instance of DAO factory
     */
    DAOFactory factory;
    /**
     * Constructor for creating new object
     * @see CreateDiagnosisCommand(DAOFactory)
     */
    public CreateDiagnosisCommand() {
        this.factory = new DAOFactory();
    }

    /**
     * Constructor for creating new object,used for testing
     * @see CreateDiagnosisCommand()
     */
    public CreateDiagnosisCommand(DAOFactory factory) {
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
            log.info("Diagnosis establishment method");
            PatientDataImpl patientDataImpl = factory.createPatientDataDao();
            DiagnosisDAOImpl diagnosisDAO = factory.createDiagnosisDao();
            int id = Integer.parseInt(request.getParameter("id"));
            PatientData patient = patientDataImpl.findByPatientId(id);
            log.debug("Patient is: " + patient.getPatient().getName() + " " + patient.getPatient().getSurname());
            List<Diagnosis> diagnoses = diagnosisDAO.findAll();
            int number = RandomNumber.randNumber(0, diagnoses.size() - 1);
            patientDataImpl.updateDiagnosis(diagnoses.get(number).getId(), patient.getPatient().getId());
            log.info("Diagnosis successfuly established");
            request.getServletContext().getRequestDispatcher("/doctorPage").forward(request, response);
        } catch (IOException | ServletException e) {
            log.error("An error occured while diagnosis establishment:", e);
           request.getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
