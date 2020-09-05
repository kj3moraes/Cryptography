package AES;

import java.util.Random;
import java.util.Scanner;

public class AES {
    private static final Scanner num = new Scanner(System.in), txt = new Scanner(System.in);
    private static int noOfRounds;
    private static String salt;

    public static void main(String[] args) {
        System.out.println("\nEnter your choice " + "\n\t [1] Encrypt \n\t [2] Decrypt \n\t [X] Exit");
        final char choice = num.next().toUpperCase().charAt(0);
        String plainText, encryptedText, seed;
        switch (choice) {
            // ENCRYPTION
            case '1':
                System.out.print("\t PLAIN TEXT :");
                plainText = txt.nextLine().replaceAll(" ","").trim();
                System.out.print("\t SEED (for secret key) : ");
                seed = txt.nextLine().toUpperCase().trim();
                System.out.print("\t SALT (16 character long string | leave blank if you want a pseudorandom one generated) : ");
                salt = txt.nextLine().toUpperCase().trim();
                encryptedText = encrypt(plainText, seed);
                System.out.println("\n\t\t INPUTED PLAIN TEXT : " + plainText);
                System.out.println("\t\t KEY (seed for secret key): " + seed);
                System.out.println("\t\t SALT (salt for key generation): " + salt);
                System.out.println("\t\t GENERATED ENCRYPTION : " + encryptedText);
                break;

            // DECRYPTION
            case '2':
                System.out.print("\t ENCRYPTED TEXT : ");
                encryptedText = txt.nextLine();
                System.out.print("\t KEY : ");
                seed = txt.nextLine().toUpperCase().trim();
                System.out.print("\t SALT (the same 16 character string that was used for encryption) : ");
                salt = txt.nextLine().toUpperCase().trim();
                plainText = decrypt(encryptedText, seed);
                System.out.println("\n\t\t INPUTED ENCRYPTED TEXT : " + encryptedText);
                System.out.println("\t\t KEY (seed for secret key): " + seed);
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

    public static String encrypt(String plaintext, String seed) {
        String result = "";
        System.out.println("\t SUBMIT YOUR AES VERSION: \n\t\t [1] AES-128 \n\t\t [2] AES-192 \n\t\t [3] AES-256");
        final char choice = txt.next().trim().charAt(0);

        // STEP 1 : INITIALIZE AND SET UP EVERYTHING WE NEED
        Keys K;
        if (salt.length() < 16) {
            Random rand = new Random(); // salt = "";
            while (16 - salt.length() > 0)
                salt += Integer.toHexString(rand.nextInt(0XF));
        } // if statement - generate a RANDOM salt.

        switch (choice) {
            case '1':
                noOfRounds = 10;
                K = new Keys(seed, salt, 128);
                break;

            case '2':
                noOfRounds = 12;
                K = new Keys(seed, salt, 192);
                break;

            default:
                noOfRounds = 14;
                K = new Keys(seed, salt, 256);
        }// switch statement - algorithm choice

        int[][] matrix = new int[4][4];

        // STEP 2 : PUT THE NECESSARY DATA INTO OUR VARIABLES
        for (int index = 0 ; index < plaintext.length(); index++)
            matrix[index%4][Math.floorDiv(index,4)] = plaintext.charAt(index);           

        // STEP 3 : USE THE DATA AND THE VARIABLES TO PERFORM THE ENCRYPTION
            // STEP 3.1 - PERFORM THE KEY0 XOR
            AESRoundFunction.addRoundKey(K.generateKeyMatrix(0), matrix);

            // STEP 3.2 : ITERATE UNTIL THE SECOND LAST ROUND
            for (int i = 1; i < noOfRounds; i++) {
                AESRoundFunction.subBytes(matrix);
                AESRoundFunction.shiftRows(matrix);
                AESRoundFunction.mixColumns(matrix);
                AESRoundFunction.addRoundKey(K.generateKeyMatrix(i), matrix);
            } // for loop - i

            // STEP 3.3 : PERFORM THE UNIQUE LAST ROUND FUNCTION
            AESRoundFunction.subBytes(matrix);
            AESRoundFunction.shiftRows(matrix);
            AESRoundFunction.addRoundKey(K.generateKeyMatrix(noOfRounds), matrix);

        // STEP 4 : PUT THE RESULTANT MATRIX INTO A HEX STRING
        for (int c = 0 ; c < 4 ; c++)
            for (int r = 0 ; r < 4 ; r++){
                result += Integer.toHexString(matrix[r][c]).length() < 2 ? "0" + Integer.toHexString(matrix[r][c]) : Integer.toHexString(matrix[r][c]);
            }
        return result;
    }// end of String encrypt(String, String)
    

    public static String decrypt(String encryptedText, String seed) {
        String result = "";
        System.out.println("\t SUBMIT YOUR AES VERSION: \n\t\t [1] AES-128 \n\t\t [2] AES-192 \n\t\t [3] AES-256");
        final char choice = txt.next().trim().charAt(0);

        // STEP 1 : INITIALIZE AND SET UP EVERYTHING WE NEED
        Keys K;

        switch (choice) {
            case '1':
                noOfRounds = 10;
                K = new Keys(seed, salt, 128);
                break;

            case '2':
                noOfRounds = 12;
                K = new Keys(seed, salt, 192);
                break;

            default:
                noOfRounds = 14;
                K = new Keys(seed, salt, 256);
        }// switch statement

        int[][] matrix = new int[4][4];

        // STEP 2 : PUT THE NECESSARY DATA INTO OUR VARIABLES        
        for (int index = 0 ; index < encryptedText.length(); index+=2)
            matrix[((index-1)/2)%4][Math.floorDiv(((index-1)/2),4)] = Integer.parseInt(encryptedText.substring(index, index+2), 16);
            
        // STEP 3 : USE THE DATA AND THE VARIABLES TO PERFORM THE ENCRYPTION
            // STEP 3.1 - PERFORM THE LAST KEY XOR (KEY 10, 12 0R 14 DEPENDING ON AES VERSION)
            AESRoundFunction.addRoundKey(K.generateInvKeyMatrix(noOfRounds), matrix);            

            // STEP 3.2 : ITERATE BACKWARD FROM (noOfRounds-1) TO 1

            //TEMPORARY CODE
            System.out.println("\n\nRound 10: \n");

            for (int round = noOfRounds-1 ; round > 0 ; round--) {


                //TEMPORARY CODE
                System.out.println("\n\nRound " + round + ": \n");


                AESRoundFunction.invShiftRows(matrix);
                AESRoundFunction.invSubBytes(matrix);                
                AESRoundFunction.addRoundKey(K.generateInvKeyMatrix(round), matrix);            
                AESRoundFunction.invMixColumns(matrix);
                              
            } // for loop - round

            // STEP 3.3 : PERFORM THE UNIQUE FIRST ROUND FUNCTION

            //TEMPORARY CODE
            System.out.println("\n\nRound 0: \n");

            AESRoundFunction.invShiftRows(matrix);
            AESRoundFunction.invSubBytes(matrix);            
            AESRoundFunction.addRoundKey(K.generateInvKeyMatrix(0), matrix);

        // STEP 4 : PUT THE RESULTANT MATRIX INTO A HEX STRING
        String hex = "";
        for (int c = 0 ; c < 4 ; c++)
            for (int r = 0 ; r < 4 ; r++)
                hex += Integer.toHexString(matrix[r][c]).length() < 2 ? "0" + Integer.toHexString(matrix[r][c]) : Integer.toHexString(matrix[r][c]);
        
        // STEP 5 : CONVERT HEX VALUE TO STRING
        hex += " ";
        String number;//stores 2 character hex values
        for(int i = 0 ; i < hex.length()-1 ; i+=2){
            number = hex.substring(i, i+2);
            result += (char)(Integer.parseInt(number, 16));
        }

        return result;
    }// end of String decrypt(String, String)
}// end of class
