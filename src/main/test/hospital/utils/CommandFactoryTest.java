package hospital.utils;

import hospital.utils.doctorcommands.*;
import hospital.utils.nursecommands.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.*;

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

    @Test
    public void createCommandNursePage() {
        String url = "http://localhost:8000/nursePage";
        command = CommandFactory.createCommand(url);
        assertEquals(command.getClass(), NursePageCommand.class);
    }
    @Test
    public void createCommandNurseDoPrescr() {
        String url = "http://localhost:8000/nursePage/doPrescriptionNurse";
        command = CommandFactory.createCommand(url);
        assertEquals(command.getClass(), NurseDoPrescrCommand.class);
    }

    @Test
    public void createCommandNurseCreatePatients() {
        String url = "http://localhost:8000/nursePage/createNursePatients";
        command = CommandFactory.createCommand(url);
        assertEquals(command.getClass(), NurseFindPatientsCommand.class);
    }
    @Test
    public void createCommandDoctorPage() {
        String url = "http://localhost:8000/doctorPage";
        command = CommandFactory.createCommand(url);
        assertEquals(command.getClass(), DoctorPageCommand.class);
    }
    @Test
    public void createCommandDiagnosis() {
        String url = "http://localhost:8000/doctorPage/createPatientDiagnosis";
        command = CommandFactory.createCommand(url);
        assertEquals(command.getClass(), CreateDiagnosisCommand.class);
    }
    @Test
    public void createCommandMakePrescr() {
        String url = "http://localhost:8000/doctorPage/createPatientPrescriptions";
        command = CommandFactory.createCommand(url);
        assertEquals(command.getClass(), CreatePrescriptionsCommand.class);
    }
    @Test
    public void createCommandDoPrescrDoc() {
        String url = "http://localhost:8000/doctorPage/doPrescriptionDoctor";
        command = CommandFactory.createCommand(url);
        assertEquals(command.getClass(), DoPrescriptionCommand.class);
    }
    @Test
    public void createCommandReleasePatient() {
        String url = "http://localhost:8000/doctorPage/releasePatient";
        command = CommandFactory.createCommand(url);
        assertEquals(command.getClass(), ReleasePatientCommand.class);
    }

    @Test
    public void createCommandPatientsDoc() {
        String url = "http://localhost:8000/doctorPage/createPatients";
        command = CommandFactory.createCommand(url);
        assertEquals(command.getClass(), CreatePatientsCommand.class);
    }

}