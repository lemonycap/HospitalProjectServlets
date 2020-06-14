package hospital.servlets.nurseServlets;


import hospital.dao.impl.PatientDataImpl;
import hospital.dao.impl.PrescriptionDAOImpl;
import hospital.dao.impl.UserDAOImpl;
import hospital.entity.PatientData;
import hospital.entity.User;
import hospital.utils.Command;
import hospital.utils.CommandFactory;
import hospital.utils.ServletCommand;
import hospital.utils.SingleTransaction;
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
         /*   Command command = null;

            if (url.equals("http://localhost:8000/nursePage")) {
                command = () -> {
                    try {
                        User activeNurse = UserDAOImpl.findByEmailAndPass(email, password);
                        if (activeNurse != null) {
                            request.setAttribute("nurse", activeNurse);
                            log.info("Active nurse:" + activeNurse.getName() + activeNurse.getSurname());
                            List<PatientData> patients = PatientDataImpl.findNursePatients(activeNurse.getId());
                            request.setAttribute("activePatients", patients);
                        }
                        log.info("Redicrecting to nurse page");
                        getServletContext().getRequestDispatcher("/nursesPage.jsp").forward(request, response);
                    }
                    catch(Exception ex) {
                        log.error("An error occured while redirecting to nurse page",ex);
                        getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
                    }
                };
            }
            else if (url.equals("http://localhost:8000/nursePage/doPrescriptionNurse")) {
                command = () -> {
                    try {
                        log.info("Prescription performing");
                        int id = Integer.parseInt(request.getParameter("id"));
                        int patientId = Integer.parseInt(request.getParameter("patientId"));
                        String prescriptionClass =  PrescriptionDAOImpl.findById(id).getPrescriptionClass();
                        log.debug("Prescription class" + prescriptionClass);
                        if (prescriptionClass.equals("medicine")) {
                            SingleTransaction.transfer(id,patientId);
                        }
                        else {
                            PrescriptionDAOImpl.delete(id,patientId);
                        }
                        log.info("Prescription successfully performed");
                        getServletContext().getRequestDispatcher("/nursePage").forward(request, response);
                    }
                    catch(Exception ex) {
                        log.error("An error occured during prescription performing",ex);
                        getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
                    }
                };
            }
            else if (url.equals("http://localhost:8000/nursePage/createNursePatients")) {
                command = () -> {
                    try {
                        log.info("creating patients for nurse");
                        int id = Integer.parseInt(request.getParameter("id"));
                        randomPatient(id);
                        log.info("patients have been successfully created");
                        getServletContext().getRequestDispatcher("/nursePage").forward(request, response);
                    }
                    catch(Exception ex) {
                        log.error("error occured",ex);
                        getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
                    }
                };
            }
           if (command != null) {
                command.execute();
            }*/

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
