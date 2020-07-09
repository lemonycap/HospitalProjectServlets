package hospital.utils.doctorcommands;

import hospital.dao.impl.PrescriptionDAOImpl;
import hospital.entity.prescription.Prescription;
import hospital.utils.factories.DAOFactory;
import hospital.utils.SingleTransaction;
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
import java.sql.SQLException;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
/**
 * Class for testing of performing prescription for patient
 * @author Yelyzaveta Onyshchenko
 * @version 1.01
 */
@RunWith(MockitoJUnitRunner.class)
public class DoPrescriptionCommandTest {
    @Mock
    HttpServletResponse response;
    @Mock
    HttpServletRequest request;
    @Mock
    ServletContext context;
    @Mock
    RequestDispatcher dispatcher;
    @Mock
    PrescriptionDAOImpl prescriptionDAO;
    @Mock
    SingleTransaction singleTransaction;
    @Mock
    DAOFactory factory;
    @Mock
    Prescription prescription1;

    @InjectMocks
    DoPrescriptionCommand doPrescriptionCommand;

    int id;
    int patientId;
    String prescription;
    String medicinePrecr;


    @Before
    public void setUp() throws Exception {
        doPrescriptionCommand = new DoPrescriptionCommand(factory);
        id = 5;
        patientId = 7;
        prescription = "operations";
        medicinePrecr = "medicine";
    }

    @After
    public void tearDown() throws Exception {
        request = null;
        response = null;
        context = null;
        dispatcher = null;
        prescription = null;
        medicinePrecr = null;
    }

    /**
     * Testing of correct command execution when prescription is medical
     * @throws ServletException
     * @throws IOException
     */
    @Test
    public void executeMedicinePrescription() throws ServletException, IOException, SQLException {
        Mockito.when(factory.createPrescriptionDao()).thenReturn(prescriptionDAO);
        Mockito.when(factory.createSingleTransaction()).thenReturn(singleTransaction);
        Mockito.when(request.getParameter("id")).thenReturn(String.valueOf(id));
        Mockito.when(request.getParameter("patientId")).thenReturn(String.valueOf(patientId));
        Mockito.when(prescriptionDAO.findById(id)).thenReturn(prescription1);
        Mockito.when(prescription1.getPrescriptionClass()).thenReturn(medicinePrecr);
        when(request.getServletContext()).thenReturn(context);
        when(context.getRequestDispatcher("/doctorPage")).thenReturn(dispatcher);
        doPrescriptionCommand.execute(request,response);
        verify(singleTransaction, times(1)).transfer(id,patientId);
        verify(context, times(1)).getRequestDispatcher("/doctorPage");
    }
    /**
     * Testing of correct command execution when prescription is procedure or operations
     * @throws ServletException
     * @throws IOException
     */
    @Test
    public void executeOtherPrescription() throws ServletException, IOException {
        Mockito.when(factory.createPrescriptionDao()).thenReturn(prescriptionDAO);
        Mockito.when(request.getParameter("id")).thenReturn(String.valueOf(id));
        Mockito.when(request.getParameter("patientId")).thenReturn(String.valueOf(patientId));
        Mockito.when(prescriptionDAO.findById(id)).thenReturn(prescription1);
        Mockito.when(prescription1.getPrescriptionClass()).thenReturn(prescription);
        when(request.getServletContext()).thenReturn(context);
        when(context.getRequestDispatcher("/doctorPage")).thenReturn(dispatcher);
        doPrescriptionCommand.execute(request,response);
        verify(prescriptionDAO, times(1)).delete(id,patientId);
        verify(context, times(1)).getRequestDispatcher("/doctorPage");
    }
}