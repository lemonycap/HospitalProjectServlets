package hospital.utils.doctorcommands;

import hospital.dao.impl.PatientDataImpl;
import hospital.dao.impl.PrescriptionDAOImpl;
import hospital.entity.PatientData;
import hospital.entity.diagnosis.Diagnosis;
import hospital.entity.prescription.Prescription;
import hospital.utils.factories.DAOFactory;
import hospital.utils.utilsForDBData.PatientDataManipulations;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

public class CreatePrescriptionsCommandTest {
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
    PrescriptionDAOImpl prescriptionDAO;
    @Mock
    DAOFactory daoFactory;

    PatientDataManipulations patientDataManipulations;
    List<Prescription> medicine;
    List<Prescription> procedure;
    List<Prescription> operations;
    Diagnosis diagnosis;
    List<Integer> history;
    PatientData patient;
    Prescription prescription;
    @InjectMocks
    CreatePrescriptionsCommand createPrescriptionsCommand;

    @Before
    public void setUp() throws Exception {
        createPrescriptionsCommand = new CreatePrescriptionsCommand(daoFactory,patientDataManipulations);
        patient = new PatientData(1,2,3,4,1);
        diagnosis = new Diagnosis(1,"2","low");
        patient.setDiagnosis(diagnosis);
        prescription = new Prescription(5,"5","1");
        medicine = new ArrayList<>();
        procedure = new ArrayList<>();
        operations = new ArrayList<>();
        history = new ArrayList<>();
        medicine.add(new Prescription(1,"1","medicine"));
        medicine.add(new Prescription(2,"2","medicine"));
        procedure.add(new Prescription(3,"3","procedures"));
        operations.add(new Prescription(4,"4","operations"));

    }

    @After
    public void tearDown() throws Exception {
        request = null;
        response = null;
        context = null;
        dispatcher = null;
    }

    @Test
    public void execute() {
        Mockito.when(daoFactory.createPatientDataDao()).thenReturn(patientData);
        Mockito.when(daoFactory.createPrescriptionDao()).thenReturn(prescriptionDAO);
        Mockito.when(prescriptionDAO.findByClass("medicine")).thenReturn(medicine);
        Mockito.when(prescriptionDAO.findByClass("procedures")).thenReturn(procedure);
        Mockito.when(prescriptionDAO.findByClass("operations")).thenReturn(operations);
        Mockito.when(prescriptionDAO.findByPatientHistory(patient.getPatient().getId())).thenReturn(history);
        Mockito.when(prescriptionDAO.findById(any(Integer.class))).thenReturn(prescription);
       // doNothing.when(prescriptionDAO.insertNewPrescriptions(any(Integer.class),any(Integer.class)))
    }
}