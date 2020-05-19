package Feistel;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class RoundFunction {

    private static Mac HMAC;

    public RoundFunction() throws NoSuchAlgorithmException {
        HMAC = Mac.getInstance("HmacSHA256");
    }// end of public RoundFunction()

    public RoundFunction(String hashAlgorithm) throws NoSuchAlgorithmException {
        HMAC = Mac.getInstance(hashAlgorithm);
    }// end of public RoundFunction(String)

    protected String generateMAC(String plainText) throws NoSuchAlgorithmException, InvalidKeyException {
        String result = "";

        // IGNORE THE FOLLOWING LINES - TREAT THE HMAC ALGORITHM AS A BLACK BOX
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256);
        SecretKey secretKey = keyGen.generateKey();
        HMAC.init(secretKey);
        return result;
    }// end of static String generateMAC()

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException {
        RoundFunction rf = new RoundFunction();
        rf.generateMAC("jwjrgeg");
    }
}// end of class
