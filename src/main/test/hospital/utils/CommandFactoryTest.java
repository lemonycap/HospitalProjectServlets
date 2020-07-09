package hospital.utils;

import hospital.utils.doctorcommands.*;
import hospital.utils.factories.CommandFactory;
import hospital.utils.factories.ServletCommand;
import hospital.utils.nursecommands.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
/**
 * Class for testing of command factory
 * @author Yelyzaveta Onyshchenko
 * @version 1.01
 */
public class CommandFactoryTest {
    ServletCommand command;

    @Before
    public void setUp() {
        command = null;
    }
    @After
    public void tearDown() {
        command = null;
    }

    /**
     * Testing of successful creation of command for nurse page
     */
    @Test
    public void createCommandNursePage() {
        String url = "http://localhost:8000/nursePage";
        command = CommandFactory.createCommand(url);
        assertEquals(command.getClass(), NursePageCommand.class);
    }

    /**
     * Testing of successful creation of command for performing prescription (nurse)
     */
    @Test
    public void createCommandNurseDoPrescr() {
        String url = "http://localhost:8000/nursePage/doPrescriptionNurse";
        command = CommandFactory.createCommand(url);
        assertEquals(command.getClass(), NurseDoPrescrCommand.class);
    }
    /**
     * Testing of successful creation of command for creating patients for nurse
     */
    @Test
    public void createCommandNurseCreatePatients() {
        String url = "http://localhost:8000/nursePage/createNursePatients";
        command = CommandFactory.createCommand(url);
        assertEquals(command.getClass(), NurseFindPatientsCommand.class);
    }
    /**
     * Testing of successful creation of command for doctor page
     */
    @Test
    public void createCommandDoctorPage() {
        String url = "http://localhost:8000/doctorPage";
        command = CommandFactory.createCommand(url);
        assertEquals(command.getClass(), DoctorPageCommand.class);
    }
    /**
     * Testing of successful creation of command for creating diagnosis for patient
     */
    @Test
    public void createCommandDiagnosis() {
        String url = "http://localhost:8000/doctorPage/createPatientDiagnosis";
        command = CommandFactory.createCommand(url);
        assertEquals(command.getClass(), CreateDiagnosisCommand.class);
    }
    /**
     * Testing of successful creation of command for creating prescriptions for patient
     */
    @Test
    public void createCommandMakePrescr() {
        String url = "http://localhost:8000/doctorPage/createPatientPrescriptions";
        command = CommandFactory.createCommand(url);
        assertEquals(command.getClass(), CreatePrescriptionsCommand.class);
    }
    /**
     * Testing of successful creation of command for performing prescriptions
     */
    @Test
    public void createCommandDoPrescrDoc() {
        String url = "http://localhost:8000/doctorPage/doPrescriptionDoctor";
        command = CommandFactory.createCommand(url);
        assertEquals(command.getClass(), DoPrescriptionCommand.class);
    }
    /**
     * Testing of successful creation of command for performing release of the patient
     */
    @Test
    public void createCommandReleasePatient() {
        String url = "http://localhost:8000/doctorPage/releasePatient";
        command = CommandFactory.createCommand(url);
        assertEquals(command.getClass(), ReleasePatientCommand.class);
    }
    /**
     * Testing of successful creation of command for creating patients for doctor
     */
    @Test
    public void createCommandPatientsDoc() {
        String url = "http://localhost:8000/doctorPage/createPatients";
        command = CommandFactory.createCommand(url);
        assertEquals(command.getClass(), CreatePatientsCommand.class);
    }

}