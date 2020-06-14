package hospital.utils.doctorcommands;

import hospital.dao.impl.PatientDataImpl;
import hospital.entity.PatientData;
import hospital.utils.ServletCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreatePrescriptionsCommand implements ServletCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
        //    log.info("Prescriptions establishment");
            int id = Integer.parseInt(request.getParameter("id"));
            PatientData patientData = PatientDataImpl.findByPatientId(id);
          //  log.debug("Patient is" + patientData.getPatient().getName() + patientData.getPatient().getSurname());
            PatientData.makePrescriptions(patientData);
            //log.info("Prescriptions established successfully");
            request.getServletContext().getRequestDispatcher("/doctorPage").forward(request, response);
        } catch (Exception ex) {
          //  log.error("An error occured while prescription establishment:", ex);
            request.getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
