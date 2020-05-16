package Feistel;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Feistel {
    private static final Scanner txt = new Scanner(System.in), num = new Scanner(System.in);

    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println("\nEnter your choice " + "\n\t [1] Encode \n\t [2] Decode \n\t [X] Exit");
        char choice = num.next().toUpperCase().charAt(0);
        String plainText, encodedText;
        int noOfRounds = 1;
        switch (choice) {
            // ENCODING
            case '1':
                System.out.print("\t PLAIN TEXT : ");
                plainText = txt.nextLine();
                System.out.println("\t NO. OF ROUNDS : ");
                noOfRounds = num.nextInt();
                encodedText = encrypt(plainText, noOfRounds);
                System.out.println("\n\t\t INPUTED PLAIN TEXT : " + plainText);
                System.out.println("\t\t NO OF ROUNDS : " + noOfRounds);
                System.out.println("\t\t GENERATED ENCODING : " + encodedText);

                break;

            // DECODING
            case '2':
                System.out.print("\t ENCRYPTED TEXT : ");
                encodedText = txt.nextLine();
                plainText = decrypt(encodedText, noOfRounds);
                System.out.println("\n\t\t INPUTED ENCRYPTED TEXT : " + encodedText);
                System.out.println("\t\t GENERATED DECRYPTION : " + plainText);
                break;

            // EXIT
            case 'X':
                System.exit(0);
                break;

            default:
                System.out.println("KINDLY ENTER A NUMBER (1-2) or (X)");
        }// switch statement
        main(new String[] {});
    }// end of void main(String[])

    private static String encrypt(String plainText, int noOfRounds) throws NoSuchAlgorithmException {
        String result = "";
        System.out.println("\t\t HMAC will be used as the round function. "
                + "\n\t\t PREFERRED HASH FUNCTION (type ~ for default : SHA256)");
        String hashFunction = txt.next().trim().toUpperCase();
        if (hashFunction.equals("~")) {
            RoundFunction F = new RoundFunction();
        } // if statement
        else {
            try {
                RoundFunction F = new RoundFunction(hashFunction);
            } catch (NoSuchAlgorithmException ex) {
                System.out.println("NO SUCH HASH ALGORITHM EXISTS, EXITING...");
                System.exit(0);
            } // try-catch block
        } // else statement
        for (int i = 1; i <= noOfRounds; i++) {

        }
        return result;
    }// end of String encrypt(String)

    private static String decrypt(String encryptedText, int noOfRounds) {
        String result = "";

        return result;
    }// end of String decrypt(String)
}// end of class
