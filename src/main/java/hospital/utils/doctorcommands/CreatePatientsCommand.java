package hospital.utils.doctorcommands;

import hospital.utils.RandomPatient;
import hospital.utils.factories.ServletCommand;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreatePatientsCommand implements ServletCommand {
    private static final Logger log = Logger.getLogger(CreatePatientsCommand.class);
    RandomPatient randomPatient;

    public CreatePatientsCommand() {
        this.randomPatient = new RandomPatient();
    }

    public CreatePatientsCommand(RandomPatient randomPatient) {
        this.randomPatient = randomPatient;
    }

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
