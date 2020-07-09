package hospital.utils.doctorcommands;

import hospital.dao.impl.DiagnosisDAOImpl;
import hospital.dao.impl.PatientDataImpl;
import hospital.dao.impl.PrescriptionDAOImpl;
import hospital.dao.impl.UserDAOImpl;
import hospital.entity.PatientData;
import hospital.entity.User;
import hospital.entity.diagnosis.Diagnosis;
import hospital.entity.prescription.Prescription;
import hospital.utils.factories.DAOFactory;
import hospital.utils.utilsForDBData.PatientDataManipulations;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ReleasePatientCommandTest {
    @Mock
    HttpServletResponse response;
    @Mock
    HttpServletRequest request;
    @Mock
    ServletContext context;
    @Mock
    RequestDispatcher dispatcher;
    @Mock
    PatientDataImpl patientDataImpl;
    @Mock
    PrescriptionDAOImpl prescriptionDAO;
    @Mock
    UserDAOImpl userDAO;
    @Mock
    DiagnosisDAOImpl diagnosisDAO;
    @Mock
    DAOFactory factory;
    @InjectMocks
    ReleasePatientCommand releasePatientCommand;

    PatientDataManipulations patientDataManipulations;
    PatientData patient1;
    PatientData patient2;
    List<Integer> numbers1;
    List<Integer> numbers2;
    Prescription prescription1;
    Prescription prescription2;
    int id;
    User user;
    Diagnosis diagnosis;

    @Before
    public void setUp() throws Exception {
        numbers2 = new ArrayList<>();
        numbers2.add(1);
        numbers1 = new ArrayList<>();
        numbers1.add(1);
        Mockito.when(factory.createPatientDataDao()).thenReturn(patientDataImpl);
        Mockito.when(factory.createUserDao()).thenReturn(userDAO);
        Mockito.when(factory.createDiagnosisDao()).thenReturn(diagnosisDAO);
        Mockito.when(factory.createPrescriptionDao()).thenReturn(prescriptionDAO);
        Mockito.when(userDAO.findById(any(Integer.class))).thenReturn(user);
        Mockito.when(diagnosisDAO.findById(any(Integer.class))).thenReturn(diagnosis);
        Mockito.when(prescriptionDAO.findByPatientHistory(any(Integer.class))).thenReturn(new ArrayList<>());
        patientDataManipulations = new PatientDataManipulations(factory);
        releasePatientCommand = new ReleasePatientCommand(factory);
        user = new User("Ben","Smith","bensmith@gmail.com","bensmith",2);
        id = 5;


    }

    @After
    public void tearDown() throws Exception {
        request = null;
        response = null;
        context = null;
        dispatcher = null;
    }

    @Test
    public void executeReleasePatient() throws ServletException, IOException {
        patient1 = new PatientData(id,2,2,3,1,patientDataManipulations);
        patient1.setPatient(user);
        prescription1 = new Prescription(1,"1","medicine");
        Mockito.when(request.getParameter("id")).thenReturn(String.valueOf(id));
        Mockito.when(patientDataImpl.findByPatientId(id)).thenReturn(patient1);
        doNothing().when(patientDataImpl).updatePatientStatus(id);
        when(request.getServletContext()).thenReturn(context);
        when(context.getRequestDispatcher("/doctorPage")).thenReturn(dispatcher);
        releasePatientCommand.execute(request,response);
        verify(patientDataImpl,times(1)).updatePatientStatus(id);
    }

    @Test
    public void cannotReleasePatientTest() throws ServletException, IOException {
        patient2 = new PatientData(id,2,3,1,1,patientDataManipulations);
        patient2.setPatient(user);
        Set<Prescription> prescriptionSet = new HashSet<>();
        prescription2 = new Prescription(2,"2","operations");
        prescriptionSet.add(prescription2);
        patient2.setCurrentPrescriptions(prescriptionSet);
        Mockito.when(request.getParameter("id")).thenReturn(String.valueOf(id));
        Mockito.when(patientDataImpl.findByPatientId(id)).thenReturn(patient2);
        when(request.getServletContext()).thenReturn(context);
        when(context.getRequestDispatcher("/doctorPage")).thenReturn(dispatcher);
        releasePatientCommand.execute(request,response);
        verify(patientDataImpl,times(0)).updatePatientStatus(id);
        assertEquals(patient2.getPatientStatus(),1);
    }

}