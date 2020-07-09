package hospital.entity.diagnosis;

/**
 * Class, which represents diagnosis which can be established to the patient.
 * @author Yelyzaveta Onyshchenko
 * @version 1.01
 */

public class Diagnosis {

    /**
     * The corresponding name of table in database.
     */
    public static final String TABLE_NAME = "diagnosis_table";

    /**
     * The corresponding name of the "id" column in table name.
     */
    public static final String ID_COLUMN = "id_diagnosis";
    /**
     * The corresponding name of the "name" column in table name.
     */
    public static final String NAME_COLUMN = "diagnosis_name";
    /**
     * The corresponding name of the "diagnosis difficulty" column in table name.
     */
    public static final String DIFFICULTY_COLUMN = "diagnosis_difficulty";

    /**
     * Id of the diagnosis.
     */
    private int id;
    /**
     * Name of the diagnosis.
     */
    private String name;
    /**
     * Difficulty of the diagnosis.
     */
    private String difficulty;

    /**
     * Constructor for creating new object.
     * @param id corresponding id of the diagnosis
     * @param name coresponding name of the diagnosis
     * @param difficulty coresponding difficulty of the diagnosis
     */
    public Diagnosis (int id, String name, String difficulty) {
        this.id = id;
        this.name = name;
        this.difficulty = difficulty;
    }

    /**
     * Method, which returns id of the diagnosis
     * @return id of the diagnosis
     */
    public int getId() {
        return id;
    }

    /**
     * Method,which sets id of the diagnosis
     * @param id corresponding id of the diagnosis
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Method, which returns name of the diagnosis
     * @return name of the diagnosis
     */
    public String getName() {
        return name;
    }
    /**
     * Method, which sets name of the diagnosis
     * @param  name  corresponding name of the diagnosis
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Method, which returns difficulty of the diagnosis
     * @return difficulty of the diagnosis
     */
    public String getDifficulty() {
        return difficulty;
    }
    /**
     * Method, which sets difficulty of the diagnosis
     * @param difficulty corresponding difficulty of the diagnosis
     */
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
}
