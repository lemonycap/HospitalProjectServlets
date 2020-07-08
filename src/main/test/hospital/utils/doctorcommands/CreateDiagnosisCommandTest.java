package hospital.utils.doctorcommands;

import hospital.dao.impl.DiagnosisDAOImpl;
import hospital.dao.impl.PatientDataImpl;
import hospital.entity.PatientData;
import hospital.entity.diagnosis.Diagnosis;
import hospital.utils.DAOFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CreateDiagnosisCommandTest {
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
    PatientDataImpl patientData;
    @Mock
    DiagnosisDAOImpl diagnosisDAO;
    @InjectMocks
    CreateDiagnosisCommand createDiagnosisCommand;
    int id;

    PatientData patient;
    List<Diagnosis> diagnoses;

    @Before
    public void setUp() throws Exception {
        createDiagnosisCommand = new CreateDiagnosisCommand(daoFactory);
        id = 5;
        patient = new PatientData(1,2,3,4,1);
        diagnoses = new ArrayList<>();
        diagnoses.add(new Diagnosis(1,"2", "low"));
    }

    @After
    public void tearDown() {
        request = null;
        response = null;
        context = null;
        dispatcher = null;
    }

    @Test
    public void execute() throws ServletException, IOException {
        Mockito.when(daoFactory.createDiagnosisDao()).thenReturn(diagnosisDAO);
        Mockito.when(daoFactory.createPatientDataDao()).thenReturn(patientData);
        Mockito.when(request.getParameter("id")).thenReturn(String.valueOf(id));
        Mockito.when(patientData.findByPatientId(id)).thenReturn(patient);
        Mockito.when(diagnosisDAO.findAll()).thenReturn(diagnoses);
        doNothing().when(patientData).updateDiagnosis(any(Integer.class), any(Integer.class));
        when(request.getServletContext()).thenReturn(context);
        when(context.getRequestDispatcher("/doctorPage")).thenReturn(dispatcher);
        createDiagnosisCommand.execute(request,response);
        verify(context, times(1)).getRequestDispatcher("/doctorPage");
    }

    @Test (expected = NumberFormatException.class)
    public void exceptionExecute() throws  ServletException,IOException {
        Integer integer = null;
        Mockito.when(request.getParameter("id")).thenReturn(String.valueOf(integer));
        createDiagnosisCommand.execute(request,response);
        verify(context, times(1)).getRequestDispatcher("/error.jsp");
    }
}