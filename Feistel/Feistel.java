package Feistel;

import java.math.BigInteger;
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
                System.out.print("\t NO. OF ROUNDS : ");
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

        // STEP 1 : CHOOSING THE HASH FUNCTION
        System.out.println("\t\t HMAC will be used as the round function. "
                + "\n\t\t Choose your preferred HASH FUNCTION (default set to SHA256):");
        System.out.println("\t\t\t [1] SHA-1 \n\t\t\t [2] SHA256\n\t\t\t [3] SHA384");
        System.out.println("\t\t\t [4] SHA512\n\t\t\t [5] MD5");
        char hashFunction = txt.next().trim().charAt(0);
        RoundFunction F;
        switch (hashFunction) {
            case '1':
                F = new RoundFunction("HmacSHA1");
                break;

            case '3':
                F = new RoundFunction("HmacSHA384");
                break;

            case '4':
                F = new RoundFunction("HmacSHA512");
                break;

            case '5':
                F = new RoundFunction("HmacMD5");
                break;

            case '6':
                F = new RoundFunction("HmacWHIRLPOOL");
                break;

            default:
                F = new RoundFunction();
                break;
        }// switch statement

        // STEP 2 : TRUNCATING/PAD THE INPUT TO DESIRED INPUT BLOCK SIZE(S)
        String plainTextStream = new BigInteger(plainText.getBytes()).toString(2);
        System.out.println("As plainTextStream: " + plainTextStream);

        // STEP 3 : RUNNING THE FEISTEL NETWORK UPTO 'N' ROUNDS
        for (int i = 1; i < noOfRounds; i++) {
            
        } // for loop - i
        return result;
    }// end of String encrypt(String)

    private static String decrypt(String encryptedText, int noOfRounds) {
        String result = "";
        
        return result;
    }// end of String decrypt(String)
}// end of class
