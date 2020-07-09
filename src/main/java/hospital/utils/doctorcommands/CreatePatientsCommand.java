package hospital.utils.doctorcommands;

import hospital.utils.RandomPatient;
import hospital.utils.factories.DAOFactory;
import hospital.utils.factories.ServletCommand;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class, which represents creating of the patient command.
 * @author Yelyzaveta Onyshchenko
 * @version 1.01
 */

public class CreatePatientsCommand implements ServletCommand {
    /**
     * Instance of Logger
     */
    private static final Logger log = Logger.getLogger(CreatePatientsCommand.class);
    /**
     * Instance of the class used for randomizing patient number
     */
    RandomPatient randomPatient;
    /**
     * Constructor for creating new object
     * @see CreatePatientsCommand( RandomPatient )
     */
    public CreatePatientsCommand() {
        this.randomPatient = new RandomPatient();
    }
    /**
     * Constructor for creating new object
     * @see CreatePatientsCommand()
     */
    public CreatePatientsCommand(RandomPatient randomPatient) {
        this.randomPatient = randomPatient;
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
           log.info("Creating patients for doctor");
            int id = Integer.parseInt(request.getParameter("id"));
           randomPatient.randomPatientDoc(id);
           log.info("Patients have been created.");
            request.getServletContext().getRequestDispatcher("/doctorPage").forward(request, response);
        } catch (Exception ex) {
            log.error("An error occured while creating patients for doctor", ex);
            request.getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
