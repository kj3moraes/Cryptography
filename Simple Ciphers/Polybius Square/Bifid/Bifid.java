package Bifid;

import java.util.Scanner;

import Polybius.Polybius;

public class Bifid extends Polybius {
    public static void main(String[] args) {
        final Scanner txt = new Scanner(System.in), num = new Scanner(System.in);
        System.out.println("\nEnter your choice \n\t [1] Encrypt \n\t [2] Decrypt \n\t [X] Exit");
        final char choice = num.next().toUpperCase().charAt(0);
        String plainText, encryptedText, key;
        switch (choice) {
            // ENCRYPTION
            case '1':
                System.out.print("\t PLAIN TEXT : ");
                plainText = txt.nextLine().toUpperCase().trim();
                System.out.print("\t KEY/SEED (leave blank for default) : ");
                key = txt.nextLine().toUpperCase().trim();
                encryptedText = encrypt(plainText, key);
                System.out.println("\n\t\t INPUTED PLAIN TEXT : " + plainText);
                System.out.println("\t\t GENERATED ENCRYPTION : " + encryptedText);
                break;

            // DECRYPTION
            case '2':
                System.out.print("\t ENCRYPTED TEXT : ");
                encryptedText = txt.nextLine().trim();
                System.out.print("\t KEY/SEED (same one used for encryption : if default, leave blank) : ");
                key = txt.nextLine().toUpperCase();
                plainText = decrypt(encryptedText, key);
                System.out.println("\n\t\t INPUTED ENCRYPTED TEXT : " + encryptedText);
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
     * BIFID - ENCRYPT This function takes a single parameter - plain text. It
     * ouputs the encryption of the plaintext using the Bifid encryption algorithm.
     *
     * @param plainText - the user input that is to be encrypted
     * @param key       - the custom key to fill the Polybius Square
     * @return - the encrypted text
     */
    protected static String encrypt(String plainText, String key) {
        String result = "", rowNumbers = "", columnNumbers = "";
        key = generateCustomKey(key, "ABCDEFGHIKLMNOPQRSTUVWXYZ");

        // S1 : ENCODING LETTERS INTO POLYBIUS SQUARE COORDINATES
        for (int i = 0; i < plainText.length(); i++) {
            char character = plainText.charAt(i);
            if (character < 65 || character > 90)
                continue;
            if (character == 'J')
                character = 'I';
            int letterNumber = key.indexOf(character);
            rowNumbers += (letterNumber / 5 + 1) + "";
            columnNumbers += (letterNumber % 5 + 1) + "";
        } // for loop - i

        // S2 : CONCATENATING THE ROW AND COLUMN COORDINATES
        rowNumbers += columnNumbers;
        System.out.println(rowNumbers);
        // S3 : CONVERTING COORDINATES BACK INTO LETTERS
        result = Polybius.decrypt(rowNumbers, key);
        return result;
    }// end of String encrypt(String, String)

    /**
     * BIFID - DECRYPT This function takes a single parameter - the encrypted text.
     * It ouputs the decryption of the encrypted text via the Bifid decryption
     * algorithm.
     *
     * @param encryptedText - the user input that is to be decrypted
     * @param key           - the custom key to fill the Polybius square
     * @return - the decrypted text
     */
    protected static String decrypt(String encryptedText, String key) {

        // S1 : CONVERSION OF ENCRYPTED TEXT INTO COORDINATES
        String result = "", intermediateLetterNumbers = "";
        intermediateLetterNumbers = Polybius.encrypt(encryptedText, key);
        key = generateCustomKey(key, "ABCDEFGHIKLMNOPQRSTUVWXYZ");

        // S2 : USING ROW AND COLUMN COORDINATES TO CONVERT TO LETTER
        int midPoint = intermediateLetterNumbers.length() / 2;
        for (int i = 0; i < midPoint; i++) {
            int letterNumber = Integer.parseInt(
                    intermediateLetterNumbers.charAt(i) + "" + intermediateLetterNumbers.charAt(midPoint + i));
            int rowNumber = letterNumber / 10, columnNumber = letterNumber % 10;
            result += key.charAt(--rowNumber * 5 + --columnNumber);
        } // for loop - i
        return result;
    }// end of String decrypt(String, String)
}// end of class
