package hospital.utils.nursecommands;

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
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class NurseFindPatientsCommandTest {
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
    NurseFindPatientsCommand nurseFindPatientsCommand;

    RandomPatient randomPatient;
    ArrayList<PatientData> list;
    ArrayList<PatientData> activeList;
    User user;
    int id;

    @Before
    public void setUp() throws Exception {
        randomPatient = new RandomPatient(daoFactory);
        nurseFindPatientsCommand = new NurseFindPatientsCommand(randomPatient);
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
        nurseFindPatientsCommand = null;
        request = null;
        response = null;
        context = null;
        dispatcher = null;
        user = null;
    }

    @Test
    public void execute() throws ServletException, IOException {
        Mockito.when(request.getParameter("id")).thenReturn(String.valueOf(id));
        when(daoFactory.createPatientDataDao()).thenReturn(patientData);
        when(patientData.findAllWhereNoNurse()).thenReturn(list);
        doNothing().when(patientData).updateNurse(any(Integer.class), any(Integer.class));
        when(request.getServletContext()).thenReturn(context);
        when(context.getRequestDispatcher("/nursePage")).thenReturn(dispatcher);
        nurseFindPatientsCommand.execute(request,response);
        verify(context, times(1)).getRequestDispatcher("/nursePage");
    }

    @Test (expected = Exception.class)
    public void exceptionExecute() throws  ServletException,IOException {
        Integer integer = null;
        when(request.getServletContext()).thenReturn(context);
        Mockito.when(request.getParameter("id")).thenReturn(String.valueOf(integer));
        nurseFindPatientsCommand.execute(request,response);
        verify(context, times(1)).getRequestDispatcher("/error.jsp");
    }
 }