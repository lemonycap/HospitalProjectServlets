package hospital.utils;

/**
 * Class, which used to random number
 * @author Yelyzaveta Onyshchenko
 * @version 1.01
 */
public class RandomNumber {
    /**
     * Method which generates random number
     * @param min min number
     * @param max max number
     * @return generated number
     */
    public static int randNumber(int min, int max) {
        return (min + (int) (Math.random() * max));
    }
}
    