package hospital.utils.nursecommands;

import hospital.servlets.nurseServlets.NursePageServlet;
import hospital.utils.ServletCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NurseFindPatientsCommand implements ServletCommand {


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
          //  log.info("creating patients for nurse");
            int id = Integer.parseInt(request.getParameter("id"));
            NursePageServlet.randomPatient(id);
           // log.info("patients have been successfully created");
            request.getServletContext().getRequestDispatcher("/nursePage").forward(request, response);
        }
        catch(Exception ex) {
            //log.error("error occured",ex);
            request.getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
