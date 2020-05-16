package Feistel;
import javax.crypto.Mac;
import java.security.NoSuchAlgorithmException;

class RoundFunction {

    private static Mac HMAC;

    public RoundFunction() throws NoSuchAlgorithmException {
        HMAC = Mac.getInstance("SHA256");
    }// end of public RoundFunction()

    public RoundFunction(String hashAlgorithm) throws NoSuchAlgorithmException {
        HMAC = Mac.getInstance(hashAlgorithm);
    }// end of public RoundFunction(String)

    protected static String generateMAC() {
        String result = "";
        
        return result;
    }// end of static String generateMAC()

}// end of class
