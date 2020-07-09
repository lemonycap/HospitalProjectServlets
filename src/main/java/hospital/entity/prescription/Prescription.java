package hospital.entity.prescription;
/**
 * Class, which represents prescription which can be set to the patient.
 * @author Yelyzaveta Onyshchenko
 * @version 1.01
 */

public  class Prescription {
    /**
     * The corresponding name of table in database.
     */
    public static final String TABLE_NAME = "prescription_table";
    /**
     * The corresponding name of id column in table.
     */
    public static final String ID_COLUMN = "id_prescription";
    /**
     * The corresponding name of prescription name in table.
     */
    public static final String NAME_COLUMN = "prescription_name";
    /**
     * The corresponding name of prescription class column in table.
     */
    public static final String CLASS_COLUMN = "prescription_class";


    /**
     * The corresponding name of previous prescriptions table in database.
     */
    public static final String CONNECTED_TABLE_HISTORY = "previous_prescriptions";
    /**
     * The corresponding name of id column in table.
     */
    public static final String CONNECTED_TABLE_HISTORY_ID = "id";
    /**
     * The corresponding name of patient id column in table.
     */
    public static final String CONNECTED_TABLE_HISTORY_PATIENT = "patient_id";
    /**
     * The corresponding name of prescription id column in table.
     */
    public static final String CONNECTED_TABLE_HISTORY_PRESCR= "prescription_id";


    /**
     * The corresponding name of new prescriptions table in database.
     */
    public static final String CONNECTED_TABLE_NEW = "new_prescriptions";
    /**
     * The corresponding name of id column in table.
     */
    public static final String CONNECTED_TABLE_NEW_ID = "id";
    /**
     * The corresponding name of patient id column in table.
     */
    public static final String CONNECTED_TABLE_NEW_PATIENT = "patient_id";
    /**
     * The corresponding name of prescription id column in table.
     */
    public static final String CONNECTED_TABLE_NEW_PRESCR= "prescription_id";


    /**
     * The corresponding name of medicine list table in database.
     */
    public static final String MED_TABLE = "medicine_list";
    /**
     * The corresponding name of medicine name column in table.
     */
    public static final String MED_TABLE_NAME = "medicine_name";
    /**
     * The corresponding name of medicine amount column in table.
     */
    public static final String MED_TABLE_AMOUNT = "medicine_amount";

    /**
     * Prescription id
     */
    private int id;
    /**
     * Prescription name
     */
    private String name;
    /**
     * Prescription class
     */
    private String prescriptionClass;
    /**
     * Available amount of medicine
     */
    private int amountOfMedicine;

    /**
     * Constructor for creating new object.
     * @param id corresponding id of the prescription
     * @param name coresponding name of the prescription
     * @param prescriptionClass coresponding class of the prescription
     */
    public Prescription (int id, String name, String prescriptionClass) {
        this.id = id;
        this.name = name;
        this.prescriptionClass = prescriptionClass;
    }

    /**
     * Constructor for creating new object of medical prescription.
     * @param name coresponding name of the prescription
     * @param amount coresponding amount of the prescription
     */
    public Prescription (String name, int amount) {
        this.name = name;
        this.amountOfMedicine = amount;
    }

    /**
     * Method, which returns amount of medicine
     * @return amount of medicine
     */
    public int getAmountOfMedicine() {
        return amountOfMedicine;
    }

    /**
     * Method, which sets amount of medicine
     * @param amountOfMedicine amount of medicine
     */
    public void setAmountOfMedicine(int amountOfMedicine) {
        this.amountOfMedicine = amountOfMedicine;
    }
    /**
     * Method, which returns id of prescription
     * @return id of prescription
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id of prescription
     * @param id id of prescription
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * Method, which returns name of prescription
     * @return name of prescription
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name of prescription
     * @param name name of prescription
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Method, which returns prescription class
     * @return class of prescription
     */
    public String getPrescriptionClass() {
        return prescriptionClass;
    }

    /**
     * Sets class of prescription
     * @param prescriptionClass prescription class to set
     */
    public void setPrescriptionClass(String prescriptionClass) {
        this.prescriptionClass = prescriptionClass;
    }
}
