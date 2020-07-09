package hospital.servlets;

import hospital.dao.impl.PatientDataImpl;
import hospital.dao.impl.RoleDAOImpl;
import hospital.dao.impl.UserDAOImpl;
import hospital.entity.Role;
import hospital.entity.User;
import hospital.utils.factories.DAOFactory;
import hospital.utils.utilsForDBData.PatientDataManipulations;
import hospital.utils.utilsForDBData.UserDataManipulations;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
/**
 * Class for testing of user registration.
 * @author Yelyzaveta Onyshchenko
 * @version 1.01
 */
@RunWith(MockitoJUnitRunner.class)
public class CreateUserServletTest {
    @Mock
    HttpServletResponse response;
    @Mock
    HttpServletRequest request;
    @Mock
    ServletContext context;
    @Mock
    RequestDispatcher dispatcher;
    @Mock
    DAOFactory daoFactory;
    @Mock
    UserDAOImpl userDAO;
    @Mock
    RoleDAOImpl roleDao;
    @InjectMocks
    CreateUserServlet createUserServlet;

    String email;
    String password;
    String name;
    String surname;
    String role;
    User user;
    Role roleClass;

    PatientDataManipulations patientDataManipulations;

    UserDataManipulations userDataManipulations;

    @Before
    public void setUp() throws Exception {
        when(daoFactory.createRoleDao()).thenReturn(roleDao);
        when(daoFactory.createUserDao()).thenReturn(userDAO);
        patientDataManipulations = new PatientDataManipulations(daoFactory);
        createUserServlet = new CreateUserServlet(daoFactory,patientDataManipulations);
        userDataManipulations = new UserDataManipulations(daoFactory);
        password = "chinese197";
        name = "Alexandr";
        surname = "Alexandrov";
        role = "doctor";
        roleClass = new Role(1,"PATIENT");
        user = new User("Ben","Smith","bensmith@gmail.com","bensmith",2);

    }


    @After
    public void tearDown() throws Exception {
        request = null;
        response = null;
        context = null;
        dispatcher = null;
        email = null;
        surname = null;
        name = null;
        password = null;
        role = null;
        roleClass = null;
    }
    /**
     * Tests the HTTP GET request.
     * @throws IOException
     * @throws ServletException
     */
    @Test
    public void doGet() throws ServletException, IOException {
        Mockito.when(request.getRequestDispatcher("registration.jsp")).thenReturn(dispatcher);
        createUserServlet.doGet(request,response);
        verify(dispatcher, times(1)).forward(request,response);
    }

    /**
     * Tests the HTTP POST request. User already exists in database
     * @throws IOException
     * @throws ServletException
     */
    @Test
    public void doPostCorrectDataUserExists() throws ServletException, IOException {

        email = "lemonycap@gmail.com";
        when(request.getParameter("role")).thenReturn(role);
        when(request.getParameter("email")).thenReturn(email);
        when(request.getParameter("password")).thenReturn(password);
        when(request.getParameter("name")).thenReturn(name);
        when(request.getParameter("surname")).thenReturn(surname);
        when(userDAO.findByEmailAndPass(email,password)).thenReturn(user);
        when(request.getServletContext()).thenReturn(context);
        when(context.getRequestDispatcher("/registration.jsp")).thenReturn(dispatcher);
        createUserServlet.doPost(request,response);
        verify(dispatcher, times(1)).forward(request,response);
    }
    /**
     * Tests the HTTP POST request. User doesn't exist in database
     * @throws IOException
     * @throws ServletException
     */
    @Test
    public void doPostCorrectDataUserDoesntExist() throws ServletException, IOException {
        roleClass = new Role(1,"PATIENT");
        roleClass.setId(1);
        when(roleDao.findByName(any(String.class))).thenReturn(roleClass);
        email = "lemonycap@gmail.com";
        when(request.getParameter("role")).thenReturn(role);
        when(request.getParameter("email")).thenReturn(email);
        when(request.getParameter("password")).thenReturn(password);
        when(request.getParameter("name")).thenReturn(name);
        when(request.getParameter("surname")).thenReturn(surname);
        when(userDAO.findAll()).thenReturn(new ArrayList<>());
        when(request.getContextPath()).thenReturn("localhost:8080");
        createUserServlet.doPost(request,response);
        verify(response).sendRedirect(request.getContextPath() + "/");
    }

    /**
     * Tests the HTTP POST request. User enters non-valid credentials
     * @throws IOException
     * @throws ServletException
     */
    @Test
    public void doPostIncorrectData() throws ServletException, IOException {
        email = "lemonycap@gil.c";
        when(request.getParameter("role")).thenReturn(role);
        when(request.getParameter("email")).thenReturn(email);
        when(request.getParameter("password")).thenReturn(password);
        when(request.getParameter("name")).thenReturn(name);
        when(request.getParameter("surname")).thenReturn(surname);
        when(request.getServletContext()).thenReturn(context);
        when(context.getRequestDispatcher("/registration.jsp")).thenReturn(dispatcher);
        createUserServlet.doPost(request,response);
        verify(request, times(1)).setAttribute(eq("message"),any(String.class));
        verify(dispatcher, times(1)).forward(request,response);
    }
}