package Feistel;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class RoundFunction {

    private static Mac HMAC;

    public RoundFunction() throws NoSuchAlgorithmException {
        HMAC = Mac.getInstance("HmacSHA256");
    }// end of public RoundFunction()

    public RoundFunction(String hashAlgorithm) throws NoSuchAlgorithmException {
        HMAC = Mac.getInstance(hashAlgorithm);
    }// end of public RoundFunction(String)

    /**
     * ROUND FUNCTION - GENERATE MAC TAG
     *
     *
     * @param plainText - accepts the plaintext message as a array of bytes
     * @param key       - acts as the seed for the PKDF function to generate a
     *                  pseudorandom key.
     * @return - the tag of the plaintext using the secret key from the
     *         SecretKeySpec class
     * @throws InvalidKeyException - SECRET KEY IS INVALID
     */
    protected byte[] generateMACTag(byte[] plainText, String key) throws InvalidKeyException {
        byte[] result;

        // STEP 1 - INITIALIZING THE HMAC ALGORITHM WITH A SECRET KEY
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), HMAC.getAlgorithm());
        HMAC.init(secretKey);

        // STEP 2 - USE THE KEY TO GENERATE A MAC TAG
        result = HMAC.doFinal(plainText);

        return result;
    }// end of static String generateMAC()

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException {
        RoundFunction F = new RoundFunction("HmacSHA256");
        F.generateMACTag("hwfbf".getBytes(), "wrt getge ge");
    }
}// end of class
