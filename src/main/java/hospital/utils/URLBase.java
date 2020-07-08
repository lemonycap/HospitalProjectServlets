package hospital.utils;

public enum URLBase {
    NURSE_PAGE ("http://localhost:8000/nursePage"),
    DO_PRESCR_NURSE ("http://localhost:8000/nursePage/doPrescriptionNurse"),
    CREATE_NURSE_PATIENTS ("http://localhost:8000/nursePage/createNursePatients"),
    DOCTOR_PAGE ("http://localhost:8000/doctorPage"),
    CREATE_DIAGNOSIS ("http://localhost:8000/doctorPage/createPatientDiagnosis"),
    CREATE_PRESCR ("http://localhost:8000/doctorPage/createPatientPrescriptions"),
    DO_PRESCR_DOCTOR ("http://localhost:8000/doctorPage/doPrescriptionDoctor"),
    RELEASE_PATIENT ("http://localhost:8000/doctorPage/releasePatient"),
    CREATEPATIENTSDOC("http://localhost:8000/doctorPage/createPatients");

    private final String url;
    URLBase(String url){
        this.url = url;
    }
    public String getUrl(){ return url;}

    public static URLBase getValue(String value) {
        for(URLBase e: URLBase.values()) {
            if(e.url.equals(value)) {
                return e;
            }
        }
        return null;
    }
}
