package hospital.entity.diagnosis;

public class Diagnosis {
    public static final String TABLE_NAME = "diagnosis_table";
    public static final String ID_COLUMN = "id_diagnosis";
    public static final String NAME_COLUMN = "diagnosis_name";
    public static final String DIFFICULTY_COLUMN = "diagnosis_difficulty";

    private int id;
    private String name;
    private String difficulty;

    public Diagnosis (int id, String name, String difficulty) {
        this.id = id;
        this.name = name;
        this.difficulty = difficulty;
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

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
}
