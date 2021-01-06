package Polybius;

import java.util.Scanner;

public class Polybius {
    public static void main(String[] args) {
        final Scanner txt = new Scanner(System.in), num = new Scanner(System.in);
        System.out.println("\nEnter your choice \n\t [1] Encrypt \n\t [2] Decrypt \n\t [X] Exit");
        final char choice = num.next().toUpperCase().charAt(0);
        String plainText, encryptedText, key;
        switch (choice) {
            // ENCRYPTION
            case '1':
                System.out.print("\t PLAIN TEXT : ");
                plainText = txt.nextLine().trim().toUpperCase();
                System.out.print("\t KEY/SEED (leave blank for default) : ");
                key = txt.nextLine().toUpperCase();
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
                System.out.println("KINDLY ENTER A NUMBER (1-2) or (X) to EXIT ");
        }// switch statement
        main(new String[] {});
    }// end of void main(String[])

    /**
     * POLYBIUS - ENCRYPT This function takes a single parameter - the plain text
     * and outputs the encrypted text encrypted via a polybius square.
     *
     * @param plainText - the user input that is to be decrypted
     * @param key       - the custom key to fill the Polybius square
     * @return - the encrypted text
     */
    protected static String encrypt(String plainText, String key) {
        String result = "";
        key = generateCustomKey(key.replaceAll(" ", ""), "ABCDEFGHIKLMNOPQRSTUVWXYZ");

        for (int i = 0; i < plainText.length(); i++) {
            char character = plainText.charAt(i);
            if (character < 65 || character > 90)
                continue;
            if (character == 'J')
                character = 'I';

            int letterNumber = key.indexOf(character);
            int rowNumber = (int) (Math.floor((float) letterNumber / 5) + 1);
            int columnNumber = letterNumber % 5 + 1;
            result += rowNumber + "" + columnNumber;
        } // for loop - i
        return result;
    }// end of String encrypt(String)

    /**
     * POLYBIUS - GENERATE CUSTOM KEY 
     * This function takes 2 parameters - a seed and  an alphabet. 
     * It outputs a unique key generated from the seed with letters
     * from the custom alphabet appended after.
     *
     * @param seed           - the text used to generate a key
     * @param customAlphabet - the custom alphabet to build key from
     * @return - the unique key based on the seed and custom alphabet
     */
    protected static String generateCustomKey(String seed, String customAlphabet) {
        String generatedKey = "";

        // S1 : ENSURE THAT ONLY UNIQUE CHARACTERS GET ADDED TO generatedKey
        for (int i = 0; i < seed.length(); i++)
            if (!generatedKey.contains(seed.charAt(i) + ""))
                generatedKey += seed.charAt(i);

        // S2 : ATTACH THE REST OF THE LETTERS IN customAlphabet
        for (int i = 0; i < customAlphabet.length(); i++)
            if (generatedKey.indexOf(customAlphabet.charAt(i)) == -1)
                generatedKey = generatedKey.concat(customAlphabet.charAt(i) + "");

        return generatedKey;
    }// end of String generateCustomKey(String, String)

    /**
     * POLYBIUS - DECRYPT This function takes a single parameter - encrypted text.
     * It outputs the decrypted text via a Polybius square.
     *
     * @param encryptedText - the user input that is to be decrypted
     * @param key           - the custom key to fill the Polybius square
     * @return - the decrypted text
     */
    protected static String decrypt(String encryptedText, String key) {
        String result = "";
        key = generateCustomKey(key.replaceAll(" ", ""), "ABCDEFGHIKLMNOPQRSTUVWXYZ");

        for (int i = 0; i < encryptedText.length(); i += 2) {
            int letterNumber = Integer.parseInt(encryptedText.substring(i, i + 2));
            int rowNumber = letterNumber / 10, columnNumber = letterNumber % 10;
            result += key.charAt(--rowNumber * 5 + --columnNumber);
        } // for loop - i
        return result;
    }// end of String decrypt(String)
}// end of class