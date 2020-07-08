package hospital.utils.nursecommands;

import hospital.utils.RandomPatient;
import hospital.utils.ServletCommand;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NurseFindPatientsCommand implements ServletCommand {
    RandomPatient randomPatientClass;

    public NurseFindPatientsCommand() {
         randomPatientClass = new RandomPatient();
    }

    public NurseFindPatientsCommand(RandomPatient randomPatient) {
        this.randomPatientClass = randomPatient;
    }

    private static final Logger log = Logger.getLogger(NurseFindPatientsCommand.class);
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
