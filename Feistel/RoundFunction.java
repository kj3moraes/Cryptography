package Feistel;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import java.math.BigInteger;
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
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256);
        SecretKey secretKey = keyGen.generateKey();
        HMAC.init(secretKey);
        byte[] plainTextStream = plainText.getBytes();
        byte[] encryptedTextStream = HMAC.doFinal(plainTextStream);
        BigInteger bigInteger = new BigInteger(1, encryptedTextStream);
        System.out.println(bigInteger.toString(16) + "\t length" + bigInteger.toString(16).length());
        return bigInteger.toString(16);
    }// end of static String generateMAC()
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException {
        RoundFunction rf = new RoundFunction();
        rf.generateMAC("othtadwrg");
    }//end of void main(String[])
}// end of class