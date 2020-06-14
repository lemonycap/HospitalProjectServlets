package hospital.servlets;

import hospital.dao.impl.*;
import hospital.entity.PatientData;
import hospital.entity.Role;
import hospital.entity.User;
import org.apache.log4j.Logger;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registration")

public class CreateUserServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(CreateUserServlet.class);

   User user = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Entering a registration page");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("registration.jsp");
        requestDispatcher.forward(req, resp);
    }

        protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{
      try {
                req.setCharacterEncoding("UTF-8");

                final String role = req.getParameter("role").toUpperCase();
                final String name = req.getParameter("name");
                final String surname = req.getParameter("surname");
                final String email = req.getParameter("email");
                final String password = req.getParameter("password");
                Role userRole = RoleDAOImpl.findByName(role);
                User user = new User(name, surname, email, password, userRole.getId());
                log.debug("New user: " + user.getEmail() + user.getRole().getName());
                UserDAOImpl.insert(user);
                PatientData.refreshPatients();
                resp.sendRedirect(req.getContextPath() + "/");
            }
            catch (Exception ex) {
                log.error("An error occured while creating a new user",ex);
                getServletContext().getRequestDispatcher("/error.jsp").forward(req, resp);
            }
    }
}
