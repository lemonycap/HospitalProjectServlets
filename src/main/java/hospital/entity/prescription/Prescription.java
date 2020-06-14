package hospital.entity.prescription;

public  class Prescription {

    public static final String TABLE_NAME = "prescription_table";
    public static final String ID_COLUMN = "id_prescription";
    public static final String NAME_COLUMN = "prescription_name";
    public static final String CLASS_COLUMN = "prescription_class";

    public static final String CONNECTED_TABLE_HISTORY = "previous_prescriptions";
    public static final String CONNECTED_TABLE_HISTORY_ID = "id";
    public static final String CONNECTED_TABLE_HISTORY_PATIENT = "patient_id";
    public static final String CONNECTED_TABLE_HISTORY_PRESCR= "prescription_id";

    public static final String CONNECTED_TABLE_NEW = "new_prescriptions";
    public static final String CONNECTED_TABLE_NEW_ID = "id";
    public static final String CONNECTED_TABLE_NEW_PATIENT = "patient_id";
    public static final String CONNECTED_TABLE_NEW_PRESCR= "prescription_id";

    public static final String MED_TABLE = "medicine_list";
    public static final String MED_TABLE_NAME = "medicine_name";
    public static final String MED_TABLE_AMOUNT = "medicine_amount";


    private int id;
    private String name;
    private String prescriptionClass;
    private int amountOfMedicine;


    public Prescription (int id, String name, String prescriptionClass) {
        this.id = id;
        this.name = name;
        this.prescriptionClass = prescriptionClass;
    }

    public Prescription (String name, int amount) {
        this.name = name;
        this.amountOfMedicine = amount;
    }

    public int getAmountOfMedicine() {
        return amountOfMedicine;
    }

    public void setAmountOfMedicine(int amountOfMedicine) {
        this.amountOfMedicine = amountOfMedicine;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrescriptionClass() {
        return prescriptionClass;
    }

    public void setPrescriptionClass(String prescriptionClass) {
        this.prescriptionClass = prescriptionClass;
    }
}
