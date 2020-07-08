package hospital.utils.nursecommands;

import hospital.dao.impl.PatientDataImpl;
import hospital.dao.impl.UserDAOImpl;
import hospital.entity.PatientData;
import hospital.entity.User;
import hospital.utils.DAOFactory;
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
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class NursePageCommandTest {
    @Mock
    HttpServletResponse response;
    @Mock
    HttpServletRequest request;
    @Mock
    HttpSession session;
    @Mock
    ServletContext context;
    @Mock
    RequestDispatcher dispatcher;
    @Mock
    DAOFactory daoFactory;
    @Mock
    PatientDataImpl patientData;
    @InjectMocks
    NursePageCommand command;
    @Mock
    UserDAOImpl userDAO;
    User user;
    User emptyUser;
    List<PatientData> patientDataList;
    String email;
    String password;

    @Before
    public void setUp()  {
        command = new NursePageCommand(daoFactory);
        email = "bensmith@gmail.com";
        password = "bensmith";
        user = new User("Ben","Smith","bensmith@gmail.com","bensmith",2);
        emptyUser = null;
        patientDataList  = new ArrayList<>();
        patientDataList.add(new PatientData(1,2,3,4,1));
    }

    @After
    public void tearDown() throws Exception {
        request = null;
        response = null;
        email = null;
        password = null;
        command = null;
    }

    @Test
    public void execute() throws ServletException, IOException {
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(daoFactory.createUserDao()).thenReturn(userDAO);
        Mockito.when(daoFactory.createPatientDataDao()).thenReturn(patientData);
        Mockito.when(String.valueOf(session.getAttribute("email"))).thenReturn(email);
        Mockito.when(String.valueOf(session.getAttribute("password"))).thenReturn(password);
        Mockito.when(userDAO.findByEmailAndPass(email,password)).thenReturn(user);
        Mockito.when(patientData.findNursePatients(user.getId())).thenReturn(patientDataList);  when(request.getServletContext()).thenReturn(context);
        when(context.getRequestDispatcher("/nursesPage.jsp")).thenReturn(dispatcher);

        command.execute(request,response);
        verify(request,times(1)).getSession();
        verify(request,times(1)).setAttribute(eq("nurse"),Mockito.any(User.class));
        verify(request,times(1)).setAttribute(eq("activePatients"),Mockito.any());
        verify(context, times(1)).getRequestDispatcher("/nursesPage.jsp");
    }

    @Test (expected = Exception.class)
    public void executeIfUserIsNull() throws ServletException,IOException {
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(daoFactory.createUserDao()).thenReturn(userDAO);
        Mockito.when(String.valueOf(session.getAttribute("email"))).thenReturn(email);
        Mockito.when(String.valueOf(session.getAttribute("password"))).thenReturn(password);
        Mockito.when(userDAO.findByEmailAndPass(email,password)).thenReturn(emptyUser);
        command.execute(request,response);
        verify(context, times(1)).getRequestDispatcher("/error.jsp");
    }
}