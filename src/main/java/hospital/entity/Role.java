package hospital.entity;

public class Role {
    public static final String TABLE_NAME = "user_roles";
    public static final String ID_COLUMN = "role_id";
    public static final String NAME_COLUMN = "name";

    private int id;
    private String name;

    public Role (int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
