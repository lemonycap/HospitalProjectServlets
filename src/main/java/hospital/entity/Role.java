package hospital.entity;

/**
 * Class, which represents possible role in system.
 * @author Yelyzaveta Onyshchenko
 * @version 1.01
 */
public class Role {
    /**
     * The corresponding name of table in database.
     */
    public static final String TABLE_NAME = "user_roles";
    /**
     * The corresponding name of id column in table.
     */
    public static final String ID_COLUMN = "role_id";
    /**
     * The corresponding name of name column in table.
     */
    public static final String NAME_COLUMN = "name";

    /**
     * Id of the role
     */
    private int id;
    /**
     * Name of the role
     */
    private String name;

    /**
     * Constructor for creating new object.
     * @param id  corresponding id
     * @param name  corresponding name of the role
     */
    public Role (int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Gets name of the role
     * @return name of the role
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name of the role
     * @param name name of the role to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets id of the role
     * @return id of the role
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id of the role
     * @param id id of the role to set
     */
    public void setId(int id) {
        this.id = id;
    }
}
