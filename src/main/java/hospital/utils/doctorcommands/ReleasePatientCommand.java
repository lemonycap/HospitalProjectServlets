package hospital.utils.doctorcommands;

import hospital.dao.impl.PatientDataImpl;
import hospital.entity.PatientData;
import hospital.entity.prescription.Prescription;
import hospital.utils.ServletCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ReleasePatientCommand implements ServletCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
          //  log.info("Patient's release");
            int id = Integer.parseInt(request.getParameter("id"));
            PatientData patient = PatientDataImpl.findByPatientId(id);
           // log.debug("Patient is" + patient.getPatient().getName() + patient.getPatient().getSurname());
           // log.debug("Patient is" + patient.getPatient().getName() + patient.getPatient().getSurname());
            boolean abilityToRelease = true;
            for (Prescription prescription : patient.getCurrentPrescriptions()) {
                if (prescription.getPrescriptionClass().equals("operations")) {
                    abilityToRelease = false;
                }
            }
            if (abilityToRelease) {
                PatientDataImpl.updatePatientStatus(id);
             //   log.info("Patient have been released");
            }
            request.getServletContext().getRequestDispatcher("/doctorPage").forward(request, response);
        } catch (Exception ex) {
          //  log.error("An error occured while patient's release procedure:", ex);
            request.getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
