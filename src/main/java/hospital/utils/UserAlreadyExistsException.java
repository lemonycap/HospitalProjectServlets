package hospital.utils;

/**
 * Class, which represents exception, which means that user already exists in database.
 * Used in registration process in CreateUserServlet.
 * @author Yelyzaveta Onyshchenko
 * @version 1.01
 */
public class UserAlreadyExistsException extends Exception {

    public UserAlreadyExistsException() {
    }

    public UserAlreadyExistsException(String message) {
        super(message);
    }

    public UserAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserAlreadyExistsException(Throwable cause) {
        super(cause);
    }

}
