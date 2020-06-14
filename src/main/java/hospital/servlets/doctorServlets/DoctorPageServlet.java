package hospital.servlets.doctorServlets;


import hospital.dao.impl.*;
import hospital.entity.PatientData;
import hospital.entity.User;
import hospital.entity.diagnosis.Diagnosis;
import hospital.entity.prescription.Prescription;
import hospital.utils.*;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet ("/doctorPage/*")

public class DoctorPageServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(DoctorPageServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String email = String.valueOf(session.getAttribute("email"));
        String password = (String.valueOf(session.getAttribute("password")));
        String url = String.valueOf(request.getRequestURL());
        ServletCommand command = CommandFactory.createCommand(url,request,response);
        command.execute(request,response);
        /*Command command = null;
        if (url.equals("http://localhost:8000/doctorPage")) {
            command = () -> {
                try {
                    User activeDoctor = UserDAOImpl.findByEmailAndPass(email, password);
                    if (activeDoctor != null) {
                        request.setAttribute("doctor", activeDoctor);
                        log.debug("Active doctor is: " + activeDoctor.getName() + " " + activeDoctor.getSurname());
                        List<PatientData> patients = PatientDataImpl.findDoctorPatients(activeDoctor.getId());
                        request.setAttribute("activePatients", patients);
                    }
                    log.info("redirecting to doctor page");
                    getServletContext().getRequestDispatcher("/doctorsPage.jsp").forward(request, response);
                } catch (Exception ex) {
                    log.error("An error occured at the time of redirecting to doctor page", ex);
                    getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
                }
            };
        } else if (url.equals("http://localhost:8000/doctorPage/createPatientDiagnosis")) {
            command = () -> {
                try {
                    log.info("Diagnosis establishment method");
                    int id = Integer.parseInt(request.getParameter("id"));
                    PatientData patient = PatientDataImpl.findByPatientId(id);
                    log.debug("Patient is: " + patient.getPatient().getName() + " " + patient.getPatient().getSurname());
                    List<Diagnosis> diagnoses = DiagnosisDAOImpl.findAll();
                    int number = RandomNumber.randNumber(0, diagnoses.size() - 1);
                    PatientDataImpl.updateDiagnosis(diagnoses.get(number).getId(), patient.getPatient().getId());
                    log.info("Diagnosis successfuly established");
                    getServletContext().getRequestDispatcher("/doctorPage").forward(request, response);
                } catch (ServletException | IOException e) {
                    log.error("An error occured while diagnosis establishment:", e);
                    getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
                }
            };
        } else if (url.equals("http://localhost:8000/doctorPage/createPatientPrescriptions")) {
            command = () -> {
                try {
                    log.info("Prescriptions establishment");
                    int id = Integer.parseInt(request.getParameter("id"));
                    PatientData patientData = PatientDataImpl.findByPatientId(id);
                    log.debug("Patient is" + patientData.getPatient().getName() + patientData.getPatient().getSurname());
                    PatientData.makePrescriptions(patientData);
                    log.info("Prescriptions established successfully");
                    getServletContext().getRequestDispatcher("/doctorPage").forward(request, response);
                } catch (Exception ex) {
                    log.error("An error occured while prescription establishment:", ex);
                    getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
                }
            };
        } else if (url.equals("http://localhost:8000/doctorPage/doPrescriptionDoctor")) {
            command = () -> {
                try {
                    log.info("Prescriptions performing");
                    int id = Integer.parseInt(request.getParameter("id"));
                    int patientId = Integer.parseInt(request.getParameter("patientId"));
                    String prescriptionClass = PrescriptionDAOImpl.findById(id).getPrescriptionClass();
                    log.debug("Prescription class is" + prescriptionClass);
                    if (prescriptionClass.equals("medicine")) {
                        SingleTransaction.transfer(id, patientId);
                    } else {
                        PrescriptionDAOImpl.delete(id, patientId);
                    }
                    log.info("Prescriptions performed successfully");
                    getServletContext().getRequestDispatcher("/doctorPage").forward(request, response);
                } catch (Exception ex) {
                    log.error("An error occured while prescription performing:", ex);
                    getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
                }
            };
        } else if (url.equals("http://localhost:8000/doctorPage/releasePatient")) {
            command = () -> {
                try {
                    log.info("Patient's release");
                    int id = Integer.parseInt(request.getParameter("id"));
                    PatientData patient = PatientDataImpl.findByPatientId(id);
                    log.debug("Patient is" + patient.getPatient().getName() + patient.getPatient().getSurname());
                    log.debug("Patient is" + patient.getPatient().getName() + patient.getPatient().getSurname());
                    boolean abilityToRelease = true;
                      for (Prescription prescription : patient.getCurrentPrescriptions()) {
                        if (prescription.getPrescriptionClass().equals("operations")) {
                            abilityToRelease = false;
                        }
                    }
                    if (abilityToRelease) {
                        PatientDataImpl.updatePatientStatus(id);
                        log.info("Patient have been released");
                    }
                    getServletContext().getRequestDispatcher("/doctorPage").forward(request, response);
                } catch (Exception ex) {
                    log.error("An error occured while patient's release procedure:", ex);
                    getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
                }
            };
        } else if (url.equals("http://localhost:8000/doctorPage/createPatients")) {
            command = () -> {
                try {
                    log.info("Creating patients for doctor");
                    int id = Integer.parseInt(request.getParameter("id"));
                    randomPatient(id);
                    log.info("Patients have been created.");
                    getServletContext().getRequestDispatcher("/doctorPage").forward(request, response);
                } catch (Exception ex) {
                    log.error("An error occured while creating patients for doctor", ex);
                    getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
                }
            };
        }
        if (command != null) {
            command.execute();
        }*/

    }


    public static void randomPatient(int id) {
        List<PatientData> patientData = PatientDataImpl.findAllWhereNoDoc();
        List<PatientData> existingPatients = PatientDataImpl.findDoctorPatients(id);
        if (!patientData.isEmpty()) {
            for (int i = 0; i< patientData.size(); i++) {
                if (patientData.get(i).getPatientStatus() == 0) {
                    patientData.remove(patientData.get(i));
                }
            }
            log.debug("amount of active patients without doctor:" + patientData.size());
            log.debug("amount of existing patients: " + existingPatients.size());
            if (existingPatients.size() < 5 && !patientData.isEmpty()) {
                PatientDataImpl.updateDoctor(id, patientData.get(0).getPatient().getId());
            }
        }
        else {
            log.info("no patients need a doctor right now");
        }
    }
}
