package hospital.utils.doctorcommands;

import hospital.dao.impl.PatientDataImpl;
import hospital.dao.impl.UserDAOImpl;
import hospital.entity.PatientData;
import hospital.entity.User;
import hospital.utils.ServletCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class DoctorPageCommand implements ServletCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            String email = String.valueOf(session.getAttribute("email"));
            String password = (String.valueOf(session.getAttribute("password")));
            User activeDoctor = UserDAOImpl.findByEmailAndPass(email, password);
            if (activeDoctor != null) {
                request.setAttribute("doctor", activeDoctor);
               // log.debug("Active doctor is: " + activeDoctor.getName() + " " + activeDoctor.getSurname());
                List<PatientData> patients = PatientDataImpl.findDoctorPatients(activeDoctor.getId());
                request.setAttribute("activePatients", patients);
            }
            //log.info("redirecting to doctor page");
            request.getServletContext().getRequestDispatcher("/doctorsPage.jsp").forward(request, response);
        } catch (Exception ex) {
          //  log.error("An error occured at the time of redirecting to doctor page", ex);
            request.getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
