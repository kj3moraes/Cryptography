package AES;

import java.util.Scanner;

public class AES {
    private static final Scanner num = new Scanner(System.in), txt = new Scanner(System.in);
    private static int noOfRounds;
    private static String salt;

    public static void main(String[] args) {
        System.out.println("\nEnter your choice " + "\n\t [1] Encode \n\t [2] Decode \n\t [X] Exit");
        final char choice = num.next().toUpperCase().charAt(0);
        String plainText, encryptedText, seed;
        switch (choice) {
            // ENCRYPTION
            case '1':
                System.out.print("\t PLAIN TEXT : ");
                plainText = txt.nextLine().trim();
                System.out.print("\t SEED (for secret key) : ");
                seed = txt.nextLine().toUpperCase().trim();
                System.out.print(
                        "\t SALT (16 character long string | type 00 if you want a pseudo random one generated) : ");
                salt = txt.nextLine().toUpperCase().trim();
                encryptedText = encrypt(plainText, seed, salt);
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

    public static String encrypt(String plaintext, String seed, String salt) {
        String result = "";
        System.out.println("\t SUBMIT YOUR AES VERSION: \n\t\t [1] AES-128 \n\t\t [2] AES-192 \n\t\t [3] AES-256");
        char choice = txt.next().trim().charAt(0);

        // STEP 1 : INITIALIZE AND SET UP EVERYTHING WE NEED
        Keys K;
        if (salt == "00")
            choice = (char) (choice + 3);
        else if (salt.length() >= 16)
            salt = salt.substring(0, 16);
        else
            for (int i = salt.length(); i < 16; i++)
                salt = salt.concat("a");

        switch (choice) {
            case '1':
                noOfRounds = 10;
                K = new Keys(seed, salt, 128);
                break;

            case '4':
                noOfRounds = 10;
                K = new Keys(seed, 128);
                break;

            case '2':
                noOfRounds = 12;
                K = new Keys(seed, salt, 192);
                break;

            case '5':
                noOfRounds = 12;
                K = new Keys(seed, 192);
                break;

            case '3':
                noOfRounds = 14;
                K = new Keys(seed, salt, 256);
                break;

            case '6':
                noOfRounds = 14;
                K = new Keys(seed, 256);
        }// switch statement
        String initialKey;
        int[][] matrix = new int[4][4];

        // STEP 2 : PUT THE NECESSARY DATA INTO OUR VARIABLES
        for (int i = 0; i < 16; i++)
            matrix[i / 4][i % 4] = plaintext.charAt(i);
        salt = K.getSalt();
        initialKey = K.getInitialKeyState();

        // STEP 3 : USE THE DATA AND THE VARIABLES TO PERFORM THE ENCRYPTION
        // STEP 3.1 - PERFORM THE KEY0 XOR
        AESRoundFunction.addRoundKey(K.generateKeyMatrix(0), matrix);

        // STEP 3.2 - ITERATE UNTIL THE SECOND LAST ROUND
        for (int i = 1; i < noOfRounds; i++) {
            System.out.println("ROUND NO : " + i);
            AESRoundFunction.subBytes(matrix);
            AESRoundFunction.shiftRows(matrix);
            AESRoundFunction.mixColumns(matrix);
            AESRoundFunction.addRoundKey(K.generateKeyMatrix(i), matrix);
        } // for loop - i

        // STEP 3.3 - PERFORM THE UNIQUE LAST ROUND FUNCTION
        AESRoundFunction.subBytes(matrix);
        AESRoundFunction.shiftRows(matrix);
        AESRoundFunction.addRoundKey(K.generateKeyMatrix(noOfRounds), matrix);

        for (int row[] : matrix)
            for (int element : row)
                result += Integer.toHexString(element);
        return result;
    }// end of String encrypt(String, String)

    public static String decrypt(String encryptedText, String seed) {
        String result = "";
        return result;
    }// end of String decrypt(String, String)
}// end of class