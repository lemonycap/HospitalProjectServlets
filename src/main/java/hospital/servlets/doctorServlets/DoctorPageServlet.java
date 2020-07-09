package hospital.servlets.doctorServlets;


import hospital.utils.factories.CommandFactory;
import hospital.utils.factories.ServletCommand;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Class, which represents doctor page servlet.
 * @author Yelyzaveta Onyshchenko
 * @version 1.01
 */

@WebServlet ("/doctorPage/*")

public class DoctorPageServlet extends HttpServlet {
    /**
     * Instance of Logger
     */
    private static final Logger log = Logger.getLogger(DoctorPageServlet.class);

    /**
     * Method performing HHTP POST request
     * @param request HttpRequest
     * @param response HttpResponse
     * @throws ServletException On servlet error
     * @throws IOException On error fulfilling the request
     */
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
