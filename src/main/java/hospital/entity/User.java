package hospital.entity;

import hospital.dao.impl.RoleDAOImpl;
import hospital.utils.PasswordEncryptorSHA256;
import hospital.utils.UserDataManipulations;

import java.security.NoSuchAlgorithmException;

public class User {

    public static final String TABLE_NAME = "app_users";
    public static final String ID_COLUMN = "id";
    public static final String NAME_COLUMN = "name";
    public static final String SURNAME_COLUMN = "surname";
    public static final String EMAIL_COLUMN = "email";
    public static final String PASSWORD_COLUMN = "password";
    public static final String ROLE_COLUMN = "role";

    private int id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private Role role;

    UserDataManipulations userDataManipulations;

    public User (int id, String name, String surname, String email, String password, int role) {
        userDataManipulations = new UserDataManipulations();
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.role = userDataManipulations.detectRole(role);
    }

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


    public int getId() {
        return id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
