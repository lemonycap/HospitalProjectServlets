package hospital.servlets.patientServlets;

import hospital.dao.impl.PatientDataImpl;
import hospital.dao.impl.UserDAOImpl;
import hospital.entity.PatientData;
import hospital.entity.User;
import hospital.utils.DAOFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet ("/patientPage")

public class PatientPageServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(PatientPageServlet.class);

    DAOFactory factory;
    public PatientPageServlet () {
        this.factory = new DAOFactory();
    }

    public PatientPageServlet(DAOFactory factory) {
        this.factory = factory;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            log.info("Patient page");
            UserDAOImpl userDAO = factory.createUserDao();
            PatientDataImpl patientDataImpl = factory.createPatientDataDao();
            HttpSession session = request.getSession();
            String email = String.valueOf(session.getAttribute("email"));
            String password = (String.valueOf(session.getAttribute("password")));
            String url = String.valueOf(request.getRequestURL());
            User activePatient = userDAO.findByEmailAndPass(email, password);
            if (activePatient != null) {
                request.setAttribute("patient", activePatient);
                PatientData patientData = patientDataImpl.findByPatientId(activePatient.getId());
                log.debug("Active patient is" + patientData.getPatient().getName() + patientData.getPatient().getSurname());
                request.setAttribute("patient",patientData);
            }
            log.info("Redirecting to patientPage.jsp");
            getServletContext().getRequestDispatcher("/patientPage.jsp").forward(request, response);
        }
        catch(Exception ex) {
            log.error("An error occured while attempting to enter a patient profile",ex);
            getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
