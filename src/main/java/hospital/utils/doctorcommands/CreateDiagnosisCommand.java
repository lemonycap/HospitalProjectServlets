package hospital.utils.doctorcommands;

import hospital.dao.impl.DiagnosisDAOImpl;
import hospital.dao.impl.PatientDataImpl;
import hospital.entity.PatientData;
import hospital.entity.diagnosis.Diagnosis;
import hospital.utils.DAOFactory;
import hospital.utils.RandomNumber;
import hospital.utils.ServletCommand;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CreateDiagnosisCommand implements ServletCommand {
    private static final Logger log = Logger.getLogger(CreateDiagnosisCommand.class);
    DAOFactory factory;
    public CreateDiagnosisCommand() {
        this.factory = new DAOFactory();
    }

    public CreateDiagnosisCommand(DAOFactory factory) {
        this.factory = factory;
    }

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
