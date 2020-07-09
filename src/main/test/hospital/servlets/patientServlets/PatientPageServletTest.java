package hospital.servlets.patientServlets;

import hospital.dao.impl.PatientDataImpl;
import hospital.dao.impl.UserDAOImpl;
import hospital.entity.PatientData;
import hospital.entity.User;
import hospital.utils.factories.DAOFactory;
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
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Class for testing PatientPageServlet class.
 * @author Yelyzaveta Onyshchenko
 * @version 1.01
 */
@RunWith(MockitoJUnitRunner.class)
public class PatientPageServletTest {
    @Mock
    DAOFactory factory;
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    HttpSession session;
    @Mock
    ServletContext context;
    @Mock
    RequestDispatcher dispatcher;
    @Mock
    UserDAOImpl userDAO;
    @Mock
    PatientDataImpl patientData;
    @InjectMocks
    PatientPageServlet patientPageServlet;
    String email;
    String password;
    User activeUser;
    PatientData patient;

    @Before
    public void setUp() throws Exception {
        patientPageServlet = new PatientPageServlet(factory);
        email = "email";
        password = "pass";
        patient = new PatientData(1,2,3,4,1);
        activeUser = new User("Ben","Smith","bensmith@gmail.com","bensmith",1);
        patient.setPatient(activeUser);
    }


    @After
    public void tearDown() throws Exception {
        response = null;
        request = null;
        session = null;
        email = null;
        password = null;
        context = null;
        dispatcher = null;

    }

    /**
     * Tests the HTTP POST request of PatientPageServlet class. User is successfully redirected to PatientPage.
     * @throws IOException
     * @throws ServletException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void doPost() throws ServletException, IOException {
        Mockito.when(factory.createUserDao()).thenReturn(userDAO);
        Mockito.when(factory.createPatientDataDao()).thenReturn(patientData);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("email")).thenReturn(email);
        Mockito.when(session.getAttribute("password")).thenReturn(password);
        Mockito.when(userDAO.findByEmailAndPass(email, password)).thenReturn(activeUser);
        Mockito.when(patientData.findByPatientId(activeUser.getId())).thenReturn(patient);
        when(request.getServletContext()).thenReturn(context);
        when(context.getRequestDispatcher("/patientPage.jsp")).thenReturn(dispatcher);
        patientPageServlet.doPost(request,response);
        Mockito.verify(request).setAttribute(eq("patient"),any(PatientData.class));
        verify(context, times(1)).getRequestDispatcher("/patientPage.jsp");
        verify(context, times(0)).getRequestDispatcher("/error.jsp");
    }
}