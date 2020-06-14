package hospital.utils;

import hospital.utils.commands.doctorcommands.*;
import hospital.utils.doctorcommands.*;
import hospital.utils.nursecommands.NurseDoPrescrCommand;
import hospital.utils.nursecommands.NurseFindPatientsCommand;
import hospital.utils.nursecommands.NursePageCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommandFactory {
    public static ServletCommand createCommand(String url, HttpServletRequest request, HttpServletResponse response)
    {
        ServletCommand command = null;
        switch (url) {
            case ("http://localhost:8000/nursePage"):
                command = new NursePageCommand();
                break;

            case ("http://localhost:8000/nursePage/doPrescriptionNurse"):
                command = new NurseDoPrescrCommand();
                break;

            case ("http://localhost:8000/nursePage/createNursePatients"):
                command = new NurseFindPatientsCommand();
                break;
            case ("http://localhost:8000/doctorPage"):
                command = new DoctorPageCommand();
                break;
            case ("http://localhost:8000/doctorPage/createPatientDiagnosis"):
                command = new CreateDiagnosisCommand();
                break;
            case ("http://localhost:8000/doctorPage/createPatientPrescriptions"):
                command = new CreatePrescriptionsCommand();
                break;
            case ("http://localhost:8000/doctorPage/doPrescriptionDoctor"):
                command = new DoPrescriptionCommand();
                break;
            case ("http://localhost:8000/doctorPage/releasePatient"):
                command = new ReleasePatientCommand();
                break;
            case ("http://localhost:8000/doctorPage/createPatients"):
                command = new CreatePatientsCommand();
                break;
        }
        return command;
    }
}
