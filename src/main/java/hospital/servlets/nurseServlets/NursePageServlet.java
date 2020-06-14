package hospital.servlets.nurseServlets;


import hospital.dao.impl.PatientDataImpl;
import hospital.entity.PatientData;
import hospital.utils.CommandFactory;
import hospital.utils.ServletCommand;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet ("/nursePage/*")

public class NursePageServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(NursePageServlet.class);
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

            HttpSession session = request.getSession();
            String email = String.valueOf(session.getAttribute("email"));
            String password = (String.valueOf(session.getAttribute("password")));
            String url = String.valueOf(request.getRequestURL());
            ServletCommand command = CommandFactory.createCommand(url,request,response);
            command.execute(request,response);


    }
    public static void randomPatient(int id) {
        List<PatientData> patientData = PatientDataImpl.findAllWhereNoNurse();
        List<PatientData> existingPatients = PatientDataImpl.findNursePatients(id);
        if (!patientData.isEmpty()) {
            for (int i = 0; i< patientData.size(); i++) {
                if (patientData.get(i).getPatientStatus() == 0) {
                    patientData.remove(patientData.get(i));
                }
            }
            log.debug("amount of active patients without doctor:" + patientData.size());
            log.debug("amount of existing patients: " + existingPatients.size());
            if (existingPatients.size() < 5) {

                PatientDataImpl.updateNurse(id, patientData.get(0).getPatient().getId());
            }
        }
    }
}
