package hospital.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Class, which represents password encryptor using algorithm SHA-256
 * @author Yelyzaveta Onyshchenko
 * @version 1.01
 */
public class PasswordEncryptorSHA256 {
    /**
     * Method that encrypts string using SHA-256
     * @param input input string
     * @return encrypted string
     * @throws NoSuchAlgorithmException no such algorithm exception
     */
    public static String encryptPasswordWithSHA256(String input) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] result = messageDigest.digest(input.getBytes());
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < result.length; ++i) {
            stringBuffer.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }
        return stringBuffer.toString();
    }
}
