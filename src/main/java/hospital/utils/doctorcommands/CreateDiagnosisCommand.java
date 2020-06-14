package hospital.utils.doctorcommands;

import hospital.dao.impl.DiagnosisDAOImpl;
import hospital.dao.impl.PatientDataImpl;
import hospital.entity.PatientData;
import hospital.entity.diagnosis.Diagnosis;
import hospital.utils.RandomNumber;
import hospital.utils.ServletCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CreateDiagnosisCommand implements ServletCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
           // log.info("Diagnosis establishment method");
            int id = Integer.parseInt(request.getParameter("id"));
            PatientData patient = PatientDataImpl.findByPatientId(id);
           // log.debug("Patient is: " + patient.getPatient().getName() + " " + patient.getPatient().getSurname());
            List<Diagnosis> diagnoses = DiagnosisDAOImpl.findAll();
            int number = RandomNumber.randNumber(0, diagnoses.size() - 1);
            PatientDataImpl.updateDiagnosis(diagnoses.get(number).getId(), patient.getPatient().getId());
           // log.info("Diagnosis successfuly established");
            request.getServletContext().getRequestDispatcher("/doctorPage").forward(request, response);
        } catch (ServletException | IOException e) {
           // log.error("An error occured while diagnosis establishment:", e);
           request.getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
