package hospital.utils.doctorcommands;

import hospital.servlets.doctorServlets.DoctorPageServlet;
import hospital.utils.ServletCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreatePatientsCommand implements ServletCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
         //  log.info("Creating patients for doctor");
            int id = Integer.parseInt(request.getParameter("id"));
           DoctorPageServlet.randomPatient(id);
        //    log.info("Patients have been created.");
            request.getServletContext().getRequestDispatcher("/doctorPage").forward(request, response);
        } catch (Exception ex) {
           // log.error("An error occured while creating patients for doctor", ex);
            request.getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
