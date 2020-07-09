package hospital.utils;


/**
 * Class for checking input data
 * @author Yelyzaveta Onyshchenko
 * @version 1.01
 */
public class InputDataCheck {
    /**
     * Method which compares input string with regular expressin
     * @param input input string
     * @param regex regular expression
     * @return result of string comparison
     */
    public static boolean inputCheck(String input, String regex)  {
            if (input.matches(regex))
                return true;
            else
                return false;
    }
}
