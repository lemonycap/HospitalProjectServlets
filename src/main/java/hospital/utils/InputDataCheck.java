package hospital.utils;

public class InputDataCheck {
    public static boolean inputCheck(String input, String regex)  {
            if (input.matches(regex))
                return true;
            else
                return false;
    }
}
