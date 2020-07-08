package hospital.utils.doctorcommands;

import hospital.dao.impl.PatientDataImpl;
import hospital.entity.PatientData;
import hospital.utils.DAOFactory;
import hospital.utils.PatientDataManipulations;
import hospital.utils.ServletCommand;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreatePrescriptionsCommand implements ServletCommand {
    private static final Logger log = Logger.getLogger(CreatePrescriptionsCommand.class);
    PatientDataManipulations patientDataManipulations;
    DAOFactory factory;

    public CreatePrescriptionsCommand() {
        this.factory = new DAOFactory();
        this.patientDataManipulations = new PatientDataManipulations();
    }

    public CreatePrescriptionsCommand(DAOFactory daoFactory,PatientDataManipulations patientDataManipulations) {
        this.factory = daoFactory;
        this.patientDataManipulations = patientDataManipulations;
    }

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
