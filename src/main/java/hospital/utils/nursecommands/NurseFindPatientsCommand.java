package hospital.utils.nursecommands;

import hospital.utils.RandomPatient;
import hospital.utils.doctorcommands.CreateDiagnosisCommand;
import hospital.utils.factories.ServletCommand;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class, which represents creating of patients command.
 * @author Yelyzaveta Onyshchenko
 * @version 1.01
 */
public class NurseFindPatientsCommand implements ServletCommand {
    /**
     * Instance of Logger
     */
    private static final Logger log = Logger.getLogger(NurseFindPatientsCommand.class);
    /**
     * Instance of RandomPatient which is used to define nurse's patients
     */
    RandomPatient randomPatientClass;
    /**
     * Constructor for creating new object,used for testing
     * @see NurseFindPatientsCommand(RandomPatient)
     */
    public NurseFindPatientsCommand() {
         randomPatientClass = new RandomPatient();
    }
    /**
     * Constructor for creating new object,used for testing
     * @see NurseFindPatientsCommand()
     */
    public NurseFindPatientsCommand(RandomPatient randomPatient) {
        this.randomPatientClass = randomPatient;
    }

    /**
     * Execute command method
     * @param request HttpRequest
     * @param response HttpResponse
     * @throws ServletException on servlet exception
     * @throws IOException on error occurred while processing the request
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            log.info("creating patients for nurse");
            int id = Integer.parseInt(request.getParameter("id"));
            randomPatientClass.randomPatientNurse(id);
            log.info("patients have been successfully created");
            request.getServletContext().getRequestDispatcher("/nursePage").forward(request, response);
        }
        catch(Exception ex) {
            log.error("error occured",ex);
            request.getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
