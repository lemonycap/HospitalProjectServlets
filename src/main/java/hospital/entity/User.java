package hospital.entity;

import hospital.utils.PasswordEncryptorSHA256;
import hospital.utils.utilsForDBData.PatientDataManipulations;
import hospital.utils.utilsForDBData.UserDataManipulations;

import java.security.NoSuchAlgorithmException;
/**
 * Class, which represents app user.
 * @author Yelyzaveta Onyshchenko
 * @version 1.01
 */
public class User {
    /**
     * The corresponding name of table in database.
     */
    public static final String TABLE_NAME = "app_users";
    /**
     * The corresponding name of id column in table.
     */
    public static final String ID_COLUMN = "id";
    /**
     * The corresponding name of name column in table.
     */
    public static final String NAME_COLUMN = "name";
    /**
     * The corresponding name of surname column in table.
     */
    public static final String SURNAME_COLUMN = "surname";
    /**
     * The corresponding name of email column in table.
     */
    public static final String EMAIL_COLUMN = "email";
    /**
     * The corresponding name of password column in table.
     */
    public static final String PASSWORD_COLUMN = "password";
    /**
     * The corresponding name of role column in table.
     */
    public static final String ROLE_COLUMN = "role";

    /**
     * User id
     */
    private int id;
    /**
     * User name
     */
    private String name;
    /**
     * User surname
     */
    private String surname;
    /**
     * User email
     */
    private String email;
    /**
     * User password
     */
    private String password;
    /**
     * User role
     */
    private Role role;
    /**
     * Instance of the class used for operations with data
     */
     UserDataManipulations userDataManipulations;

    /**
     * Constructor for creating new object.
     * @param id  corresponding user id
     * @param name  corresponding name
     * @param surname corresponding surname
     * @param email corresponding email
     * @param password corresponding password
     * @param role corresponding role
     * @see User(int,String,String,String,String,int,UserDataManipulations)
     */
    public User (int id, String name, String surname, String email, String password, int role) {
        userDataManipulations = new UserDataManipulations();
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.role = userDataManipulations.detectRole(role);
    }
    /**
     * Constructor for creating new object.
     * @param name  corresponding name
     * @param surname corresponding surname
     * @param email corresponding email
     * @param password corresponding password
     * @param role corresponding role
     * @see User(String,String,String,String,int,UserDataManipulations)
     */
    public User (String name, String surname, String email, String password, int role) {
        userDataManipulations = new UserDataManipulations();
        this.name = name;
        this.surname = surname;
        this.email = email;
        try {
            this.password = PasswordEncryptorSHA256.encryptPasswordWithSHA256(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        this.role = userDataManipulations.detectRole(role);
    }

    /**
     * Constructor for creating new object,used for testing
     * @param id  corresponding user id
     * @param name  corresponding name
     * @param surname corresponding surname
     * @param email corresponding email
     * @param password corresponding password
     * @param role corresponding role
     * @param userDataManipulations1 instance for operations with data
     * @see User(int,String,String,String,String,int)
     */
    public User (int id, String name, String surname, String email, String password, int role,UserDataManipulations userDataManipulations1) {
        userDataManipulations = userDataManipulations1;
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.role = userDataManipulations.detectRole(role);
    }
    /**
     * Constructor for creating new object,used for testing
     * @param name  corresponding name
     * @param surname corresponding surname
     * @param email corresponding email
     * @param password corresponding password
     * @param role corresponding role
     * @param userDataManipulations1 instance for operations with data
     * @see User(int,String,String,String,String,int)
     */
    public User (String name, String surname, String email, String password, int role,UserDataManipulations userDataManipulations1) {
        userDataManipulations = userDataManipulations1;
        this.name = name;
        this.surname = surname;
        this.email = email;
        try {
            this.password = PasswordEncryptorSHA256.encryptPasswordWithSHA256(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        this.role = userDataManipulations.detectRole(role);
    }

    /**
     * Gets user id
     * @return user id
     */
    public int getId() {
        return id;
    }
    /**
     * Gets user role
     * @return user role
     */
    public Role getRole() {
        return role;
    }

    /**
     * Sets user role
     * @param role user role to set
     */
    public void setRole(Role role) {
        this.role = role;
    }
    /**
     * Sets user id
     * @param id user id to set
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * Gets user name
     * @return user name
     */
    public String getName() {
        return name;
    }
    /**
     * Sets user name
     * @param name user name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Gets user surname
     * @return user surname
     */
    public String getSurname() {
        return surname;
    }
    /**
     * Sets user surname
     * @param surname user surname to set
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }
    /**
     * Gets user email
     * @return user id
     */
    public String getEmail() {
        return email;
    }
    /**
     * Sets user email
     * @param email user email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * Gets user password
     * @return user id
     */
    public String getPassword() {
        return password;
    }
    /**
     * Sets user password
     * @param password user password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
