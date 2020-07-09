package hospital.utils.factories;

import hospital.utils.URLBase;
import hospital.utils.doctorcommands.*;
import hospital.utils.nursecommands.NurseDoPrescrCommand;
import hospital.utils.nursecommands.NurseFindPatientsCommand;
import hospital.utils.nursecommands.NursePageCommand;

public class CommandFactory {
    public static ServletCommand createCommand(String url)
    {
        ServletCommand command = null;

        switch (URLBase.getValue(url)) {
            case NURSE_PAGE:
                command = new NursePageCommand();
                break;
            case DO_PRESCR_NURSE:
                command = new NurseDoPrescrCommand();
                break;

            case CREATE_NURSE_PATIENTS:
                command = new NurseFindPatientsCommand();
                break;
            case DOCTOR_PAGE:
                command = new DoctorPageCommand();
                break;
            case CREATE_DIAGNOSIS:
                command = new CreateDiagnosisCommand();
                break;
            case CREATE_PRESCR:
                command = new CreatePrescriptionsCommand();
                break;
            case DO_PRESCR_DOCTOR:
                command = new DoPrescriptionCommand();
                break;
            case RELEASE_PATIENT:
                command = new ReleasePatientCommand();
                break;
            case CREATEPATIENTSDOC:
                command = new CreatePatientsCommand();
                break;
        }
        return command;
    }
}
