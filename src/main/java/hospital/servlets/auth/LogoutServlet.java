package hospital.servlets.auth;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Class, which represents log out servlet.
 * @author Yelyzaveta Onyshchenko
 * @version 1.01
 */
@WebServlet ("/logout")
public class LogoutServlet extends HttpServlet {
    /**
     * Method performing HTTP GET request
     * @param req HttpRequest
     * @param resp HttpResponse
     * @throws ServletException On servlet error
     * @throws IOException On error fulfilling the request
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        final HttpSession session = req.getSession();

        session.removeAttribute("password");
        session.removeAttribute("email");
        session.removeAttribute("role");
        session.removeAttribute("name");
        session.removeAttribute("surname");

        resp.sendRedirect(super.getServletContext().getContextPath() + "/");
    }
}
