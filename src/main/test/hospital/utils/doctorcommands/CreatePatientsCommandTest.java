package hospital.utils.doctorcommands;

import hospital.dao.impl.PatientDataImpl;
import hospital.entity.PatientData;
import hospital.entity.User;
import hospital.utils.factories.DAOFactory;
import hospital.utils.RandomPatient;
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
import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
/**
 * Class for testing of creating patients for doctor
 * @author Yelyzaveta Onyshchenko
 * @version 1.01
 */
@RunWith(MockitoJUnitRunner.class)
public class CreatePatientsCommandTest {
    @Mock
    HttpServletResponse response;
    @Mock
    HttpServletRequest request;
    @Mock
    ServletContext context;
    @Mock
    RequestDispatcher dispatcher;
    @Mock
    PatientDataImpl patientData;
    @Mock
    DAOFactory daoFactory;
    @InjectMocks
    CreatePatientsCommand createPatientsCommand;

    RandomPatient randomPatient;
    ArrayList<PatientData> list;
    ArrayList<PatientData> activeList;
    User user;
    int id;

    @Before
    public void setUp() throws Exception {
        randomPatient = new RandomPatient(daoFactory);
        createPatientsCommand = new CreatePatientsCommand(randomPatient);
        user = new User("Ben","Smith","bensmith@gmail.com","bensmith",2);
        list = new ArrayList<>();
        list.add(new PatientData(1,2,3,4,1));
        activeList = new ArrayList<>();
        id = 5;
        activeList.add(new PatientData(1,2,3,4,1));
    }

    @After
    public void tearDown() throws Exception {
        randomPatient = null;
        createPatientsCommand = null;
        request = null;
        response = null;
        context = null;
        dispatcher = null;
        user = null;
    }
    /**
     * Testing of correct command execution
     * @throws ServletException
     * @throws IOException
     */
    @Test
    public void execute() throws ServletException, IOException {
        Mockito.when(request.getParameter("id")).thenReturn(String.valueOf(id));
        when(daoFactory.createPatientDataDao()).thenReturn(patientData);
        when(patientData.findAllWhereNoDoc()).thenReturn(list);
        doNothing().when(patientData).updateDoctor(any(Integer.class), any(Integer.class));
        when(request.getServletContext()).thenReturn(context);
        when(context.getRequestDispatcher("/doctorPage")).thenReturn(dispatcher);
        createPatientsCommand.execute(request,response);
        verify(context, times(1)).getRequestDispatcher("/doctorPage");
    }
    /**
     * Testing of method work with passing to it null value of id parameter
     * @throws ServletException
     * @throws IOException
     */
    @Test (expected =NullPointerException.class)
    public void exceptionExecute() throws  ServletException,IOException {
        Integer integer = null;
        Mockito.when(request.getParameter("id")).thenReturn(String.valueOf(integer));
        createPatientsCommand.execute(request,response);
        verify(context, times(1)).getRequestDispatcher("/error.jsp");
    }
}