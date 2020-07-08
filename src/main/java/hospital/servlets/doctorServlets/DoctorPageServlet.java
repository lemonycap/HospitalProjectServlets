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
        ServletCommand command = CommandFactory.createCommand(url);
        command.execute(request,response);
    }
}
