package hospital.utils;

/**
 * Basic interface for regular expressions
 * @author Yelyzaveta Onyshchenko
 * @version 1.01
 */
public interface RegexContainer {
    /**
     * Regular expression for name and surname
     */
    String REGEX_NAME_ENG="^[A-Z][a-z]{3,29}$";
    /**
     * Regular expression for email
     */
    String REGEX_EMAIL = "([a-z0-9_-]+\\.)*[a-z0-9_-]+\\@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";
    /**
     * Regular expression for password
     */
    String REGEX_PASSWORD = "^[A-Za-z0-9_-]{3,30}$";
}
