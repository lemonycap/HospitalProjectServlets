package hospital.servlets.filter;

import hospital.dao.impl.UserDAOImpl;
import hospital.entity.User;
import hospital.utils.PasswordEncryptorSHA256;
import hospital.utils.factories.DAOFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AuthFilterTest {
    @Mock
    HttpServletResponse response;
    @Mock
    HttpServletRequest request;
    @Mock
    RequestDispatcher dispatcher;
    @Mock
    DAOFactory factory;
    @Mock
    UserDAOImpl userDAO;
    @Mock
    HttpSession session;
    @InjectMocks
    AuthFilter authFilter;
    User activeUser;
    User unactiveUser;
    FilterChain filterChain;
    String email;
    String password;
    @Before
    public void setUp() throws Exception {
        Mockito.when(factory.createUserDao()).thenReturn(userDAO);
        authFilter = new AuthFilter(factory);
        email = "email";
        password = "pass";

    }

    @After
    public void tearDown() throws Exception {
        authFilter = null;
        response = null;
        request = null;
        dispatcher = null;
        session = null;
        email = null;
        password = null;
    }

    @Test
    public void doctorAuth() throws IOException, ServletException, NoSuchAlgorithmException {
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(request.getParameter("email")).thenReturn(email);
        Mockito.when(request.getParameter("password")).thenReturn(password);
        activeUser = new User("Ben","Smith","bensmith@gmail.com","bensmith",2);
        Mockito.when(request.getRequestDispatcher("/doctorPage")).thenReturn(dispatcher);
        Mockito.when(userDAO.findByEmailAndPass(email, PasswordEncryptorSHA256.encryptPasswordWithSHA256(password))).thenReturn(activeUser);
        authFilter.doFilter((ServletRequest)request,(ServletResponse)response, filterChain);
        verify(request, times(1)).getRequestDispatcher("/doctorPage");
    }
    @Test
    public void nurseAuth() throws IOException, ServletException, NoSuchAlgorithmException {
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(request.getParameter("email")).thenReturn(email);
        Mockito.when(request.getParameter("password")).thenReturn(password);
        activeUser = new User("Ben","Smith","bensmith@gmail.com","bensmith",3);
        Mockito.when(request.getRequestDispatcher("/nursePage")).thenReturn(dispatcher);
        Mockito.when(userDAO.findByEmailAndPass(email, PasswordEncryptorSHA256.encryptPasswordWithSHA256(password))).thenReturn(activeUser);
        authFilter.doFilter((ServletRequest)request,(ServletResponse)response, filterChain);
        verify(request, times(1)).getRequestDispatcher("/nursePage");
    }
    @Test
    public void patientAuth() throws IOException, ServletException, NoSuchAlgorithmException {
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(request.getParameter("email")).thenReturn(email);
        Mockito.when(request.getParameter("password")).thenReturn(password);
        activeUser = new User("Ben","Smith","bensmith@gmail.com","bensmith",1);
        Mockito.when(request.getRequestDispatcher("/patientPage")).thenReturn(dispatcher);
        Mockito.when(userDAO.findByEmailAndPass(email, PasswordEncryptorSHA256.encryptPasswordWithSHA256(password))).thenReturn(activeUser);
        authFilter.doFilter((ServletRequest)request,(ServletResponse)response, filterChain);
        verify(request, times(1)).getRequestDispatcher("/patientPage");
    }

    @Test (expected = NullPointerException.class)
    public void noUserWithSuchCredentialsExists() throws NoSuchAlgorithmException, IOException, ServletException {
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(request.getParameter("email")).thenReturn(email);
        Mockito.when(request.getParameter("password")).thenReturn(password);
        Mockito.when(userDAO.findByEmailAndPass(email, PasswordEncryptorSHA256.encryptPasswordWithSHA256(password))).thenReturn(unactiveUser);
        authFilter.doFilter((ServletRequest)request,(ServletResponse)response, filterChain);
        verify(request, times(1)).getRequestDispatcher("/error.jsp");
    }
}