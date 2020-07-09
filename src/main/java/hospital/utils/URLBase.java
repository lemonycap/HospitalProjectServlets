package hospital.utils;
/**
 * Enum which stores URLs of project pages
 * @author Yelyzaveta Onyshchenko
 * @version 1.01
 */
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

    /**
     * URL of page
     */
    private final String url;

    /**
     * Constructor
     * @param url url of page
     */
    URLBase(String url){
        this.url = url;
    }

    /**
     * Returns url
     * @return page url
     */
    public String getUrl(){ return url;}

    /**
     * Retrieves value
     * @param value url, which used to find value
     * @return  value
     */
    public static URLBase getValue(String value) {
        for(URLBase e: URLBase.values()) {
            if(e.url.equals(value)) {
                return e;
            }
        }
        return null;
    }
}
