package AES;

import java.util.Random;
import java.util.Scanner;

public class AES {
    private static Scanner num = new Scanner(System.in), txt = new Scanner(System.in);
    private static int noOfRounds;
    private static String salt;

    public static void main(String[] args) {
        System.out.println("\nEnter your choice " + "\n\t [1] Encrypt \n\t [2] Decrypt \n\t [X] Exit");
        char choice = num.next().toUpperCase().charAt(0);
        String plainText, encryptedText, seed;
        switch (choice) {
            // ENCRYPTION
            case '1':
                System.out.print("\t PLAIN TEXT : ");
                plainText = txt.nextLine().trim();
                System.out.print("\t SEED (for secret key) : ");
                seed = txt.nextLine().toUpperCase().trim();
                System.out.print(
                        "\t SALT (16 character long string | leave blank if you want a pseudorandom one generated) : ");
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
                encryptedText = txt.nextLine().trim();
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

    /**
     * AES - ENCRYPT 
     * This function takes 2 parameters - plain text and the seed and
     * outputs the encryption of the plain text via the Rjindael Encryption
     * algorithm.
     *
     * @param plainText - the user input that is to be encrypted
     * @param seed      - the text used to generate a key to encrypt the plain text.
     * @return - the encrypted text
     */
    public static String encrypt(String plainText, String seed) {
        String result = "";
        System.out.println("\t SUBMIT YOUR AES VERSION: \n\t\t [1] AES-128 \n\t\t [2] AES-192 \n\t\t [3] AES-256");
        char choice = txt.next().trim().charAt(0);

        // STEP 1 : INITIALIZE AND SET UP EVERYTHING WE NEED
        Keys E;
        if (salt.length() < 16) {
            Random rand = new Random(); // salt = "";
            while (16 - salt.length() > 0)
                salt += Integer.toHexString(rand.nextInt(0XF));
        } // if statement - generate a RANDOM salt.

        switch (choice) {
            case '1':
                noOfRounds = 10;
                E = new Keys(seed, salt, 128);
                break;

            case '2':
                noOfRounds = 12;
                E = new Keys(seed, salt, 192);
                break;

            default:
                noOfRounds = 14;
                E = new Keys(seed, salt, 256);
                break;
        }// switch statement - algorithm choice

        int[][] matrix = new int[4][4];

        // STEP 2 : PUT THE NECESSARY DATA INTO OUR VARIABLES
        for (int index = 0; index < plainText.length(); index++)
            matrix[index % 4][Math.floorDiv(index, 4)] = plainText.charAt(index);

        // STEP 3 : USE THE DATA AND THE VARIABLES TO PERFORM THE ENCRYPTION
        // STEP 3.1 - PERFORM THE KEY0 XOR
        AESRoundFunction.addRoundKey(E.generateKeyMatrix(0), matrix);

        // STEP 3.2 : ITERATE UNTIL THE SECOND LAST ROUND
        for (int i = 1; i < noOfRounds; i++) {
            AESRoundFunction.subBytes(matrix);
            AESRoundFunction.shiftRows(matrix);
            AESRoundFunction.mixColumns(matrix);
            AESRoundFunction.addRoundKey(E.generateKeyMatrix(i), matrix);
        } // for loop - i

        // STEP 3.3 : PERFORM THE UNIQUE LAST ROUND FUNCTION
        AESRoundFunction.subBytes(matrix);
        AESRoundFunction.shiftRows(matrix);
        AESRoundFunction.addRoundKey(E.generateKeyMatrix(noOfRounds), matrix);

        // STEP 4 : PUT THE RESULTANT MATRIX INTO A HEX STRING
        for (int c = 0; c < 4; c++) {
            for (int r = 0; r < 4; r++) {
                if (Integer.toHexString(matrix[r][c]).length() < 2)
                    result += "0" + Integer.toHexString(matrix[r][c]);
                else
                    result += Integer.toHexString(matrix[r][c]);
            } // for loop - rows
        } // for loop - columns

        return result;
    }// end of String encrypt(String, String)

    /**
     * AES - DECRYPT 
     * This function takes 2 parameters - encrypted text and the seed
     * and outputs the plain text via the Rjindael Decryption algorithm.
     *
     * @param encryptedText - the user input that is to be encrypted
     * @param seed          - the text used to generate the key to decrypt the
     *                      encrypted text.
     * @return - the decrypted text
     */
    public static String decrypt(String encryptedText, String seed) {
        String result = "";
        System.out.println("\t SUBMIT YOUR AES VERSION: \n\t\t [1] AES-128 \n\t\t [2] AES-192 \n\t\t [3] AES-256");
        char choice = txt.next().trim().charAt(0);

        // STEP 1 : INITIALIZE AND SET UP EVERYTHING WE NEED
        Keys D;
        switch (choice) {
            case '1':
                noOfRounds = 10;
                D = new Keys(seed, salt, 128);
                break;

            case '2':
                noOfRounds = 12;
                D = new Keys(seed, salt, 192);
                break;

            default:
                noOfRounds = 14;
                D = new Keys(seed, salt, 256);
                break;
        }// switch statement

        D.generateInverseKeyMatrices(noOfRounds);
        int[][] matrix = new int[4][4];

        // STEP 2 : PUT THE NECESSARY DATA INTO OUR VARIABLES
        encryptedText += " ";
        for (int i = 0; i < encryptedText.length() - 1; i += 2)
            matrix[(i / 2) % 4][Math.floorDiv((i / 2), 4)] = Integer.parseInt(encryptedText.substring(i, i + 2), 16);

        // STEP 3 : USE THE DATA AND THE VARIABLES TO PERFORM THE ENCRYPTION
            // STEP 3.1 - PERFORM THE LAST KEY XOR (KEY 10, 12 0R 14 DEPENDING ON AES
            // VERSION)
            AESRoundFunction.addRoundKey(D.getKeyMatrix(noOfRounds), matrix);
            AESRoundFunction.invShiftRows(matrix);
            AESRoundFunction.invSubBytes(matrix);

            // STEP 3.2 : ITERATE BACKWARD FROM (noOfRounds-1) TO 1
            for (int round = noOfRounds - 1; round > 0; round--) {
                AESRoundFunction.addRoundKey(D.getKeyMatrix(round), matrix);
                AESRoundFunction.invMixColumns(matrix);
                AESRoundFunction.invShiftRows(matrix);
                AESRoundFunction.invSubBytes(matrix);
            } // for loop - round

            // STEP 3.3 : PERFORM THE UNIQUE FIRST ROUND FUNCTION
            AESRoundFunction.addRoundKey(D.getKeyMatrix(0), matrix);

        // STEP 4 : PUT THE RESULTANT MATRIX INTO A HEX STRING
        String hex = "";
        for (int c = 0; c < 4; c++)
            for (int r = 0; r < 4; r++)
                hex += Integer.toHexString(matrix[r][c]).length() < 2 ? "0" + Integer.toHexString(matrix[r][c])
                        : Integer.toHexString(matrix[r][c]);

        // STEP 5 : CONVERT HEX VALUE TO STRING
        hex += " ";
        for (int i = 0; i < hex.length() - 1; i += 2)
            result += (char) (Integer.parseInt(hex.substring(i, i + 2), 16));

        return result;
    }// end of String decrypt(String, String)
}// end of class
