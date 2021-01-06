package Playfair;

import java.util.Scanner;

import Polybius.Polybius;
class Wheatstone_Playfair extends Polybius {
    public static void main(String[] args) {
        final Scanner txt = new Scanner(System.in), num = new Scanner(System.in);
        System.out.println("\nEnter your choice \n\t [1] Encrypt \n\t [2] Decrypt \n\t [X] Exit");
        final char choice = num.next().toUpperCase().charAt(0);
        String plainText, encryptedText, key;
        switch (choice) {
            case '1':
                System.out.print("\t PLAIN TEXT : ");
                plainText = txt.nextLine().toUpperCase().trim();
                System.out.print("\t KEY/SEED : ");
                key = txt.nextLine().toUpperCase();
                encryptedText = encrypt(plainText, key);
                System.out.println("\n\t\t INPUTED PLAIN TEXT : " + plainText);
                System.out.println("\n\t\t KEY/SEED USED : " + key);
                System.out.println("\t\t GENERATED ENCRYPTION : " + encryptedText);
                break;

            case '2':
                System.out.print("\t ENCRYPTED TEXT : ");
                encryptedText = txt.nextLine().toUpperCase().trim();
                System.out.print("\t KEY / SEED (the same one used for encryption) : ");
                key = txt.nextLine().toUpperCase();
                plainText = decrypt(encryptedText, key);
                System.out.println("\n\t\t INPUTED ENCRYPTED TEXT : " + encryptedText);
                System.out.println("\t\t GENERATED DECRYPTION : " + plainText);
                break;

            case 'X':
                System.exit(0);
                break;

            default:
                System.out.println("KINDLY ENTER A NUMBER (1-2) or (X) to EXIT ");
        }// switch statement(int)
        main(new String[] {});
    }// end of void main(String[])

    /**
     * PLAYFAIR - ENCRYPT This function takes 2 parameters - plain text and the key
     * and outputs the encrypted text via the Wheatstone-Playfair encryption
     * algorithm.
     *
     * @param plainText - the user input that is to be encrypted
     * @param key       - the text to encrypt the plain text.
     * @return - the encrypted text
     */
    protected static String encrypt(String plainText, String key) {
        String result = "";
        plainText = plainText.replaceAll(" ", "");
        plainText += plainText.length() % 2 != 0 ? "X" : "";
        key = generateCustomKey(key, "ABCDEFGHIKLMNOPQRSTUVWXYZ");

        int rowF, columnF, rowL, columnL;
        for (int i = 0; i < plainText.length(); i += 2) {
            String extract = plainText.substring(i, i + 2);

            // STEP 1 : WEED OUT THE 'EXTRAS'
            if (key.indexOf(extract.charAt(0)) == -1) {
                result += extract.charAt(0) + "";
                continue;
            } else if (key.indexOf(extract.charAt(1)) == -1) {
                result += extract.charAt(1) + "";
                continue;
            } // if statement - NOT IN KEY MATRIX

            // STEP 2 : EXTRACT THE ROWS AND COLUMNS OF BOTH THE CHARACTERS
            rowF = key.indexOf(extract.charAt(0)) / 5;
            rowL = key.indexOf(extract.charAt(1)) / 5;
            columnF = key.indexOf(extract.charAt(0)) % 5;
            columnL = key.indexOf(extract.charAt(1)) % 5;

            // STEP 3 : ENCRYPT BASED ON THE POSITIONS IN THE SQUARE
            int encRowF, encRowL, encColumnF, encColumnL;
            if (rowF == rowL) {
                encColumnF = (columnF + 1) % 5;
                encColumnL = (columnL + 1) % 5;
                encRowF = encRowL = rowL;
            } // if statement - same ROW
            else if (columnF == columnL) {
                encRowF = (rowF + 1) % 5;
                encRowL = (rowL + 1) % 5;
                encColumnF = encColumnL = columnF;
            } // if statement - same COLUMN
            else {
                encRowF = rowF;
                encColumnF = columnL;
                encRowL = rowL;
                encColumnL = columnF;
            } // else - REST OF THE CASES
            result += key.charAt(encRowF * 5 + encColumnF) + "" + key.charAt(encRowL * 5 + encColumnL) + " ";
        } // for loop - i
        return result;
    }// end of String encrypt(String, String)

    /**
     * PLAYFAIR - DECRYPT This function takes 2 parameters - encrypted text and the
     * key and outputs the decrypted via the Wheatstone-Playfair dectyption
     * algorithm.
     *
     * @param encryptedText - the user input that is to be encrypted
     * @param key           - the text to encrypt the plain text.
     * @return - the decrypted text
     */
    protected static String decrypt(String encryptedText, String key) {
        String result = "";
        encryptedText = encryptedText.replaceAll(" ", "");
        encryptedText += encryptedText.length() % 2 != 0 ? "X" : "";
        key = generateCustomKey(key, "ABCDEFGHIKLMNOPQRSTUVWXYZ");

        int rowF, columnF, rowL, columnL;
        for (int i = 0; i < encryptedText.length(); i += 2) {
            String extract = encryptedText.substring(i, i + 2);

            // STEP 1 : WEED OUT THE 'EXTRAS'
            if (key.indexOf(extract.charAt(0)) == -1) {
                result += extract.charAt(0) + "";
                continue;
            } else if (key.indexOf(extract.charAt(1)) == -1) {
                result += extract.charAt(1) + "";
                continue;
            } // if statement - NOT IN KEY MATRIX

            /* STEP 2 : EXTRACT THE ROWS AND COLUMNS OF BOTH THE CHARACTERS */
            rowF = key.indexOf(extract.charAt(0)) / 5;
            rowL = key.indexOf(extract.charAt(1)) / 5;
            columnF = key.indexOf(extract.charAt(0)) % 5;
            columnL = key.indexOf(extract.charAt(1)) % 5;

            // STEP 3 : DECRYPT BASED ON THE POSITIONS IN THE SQUARE
            int decRowF, decRowL, decColumnF, decColumnL;
            if (rowF == rowL) {
                decColumnF = ((columnF - 1) % 5 + 5) % 5;
                decColumnL = ((columnL - 1) % 5 + 5) % 5;
                decRowF = decRowL = rowL;
            } // if statement - same ROW
            else if (columnF == columnL) {
                decRowF = ((rowF - 1) % 5 + 5) % 5;
                decRowL = ((rowL - 1) % 5 + 5) % 5;
                decColumnF = decColumnL = columnF;
            } // if statement - same COLUMN
            else {
                decRowF = rowF;
                decColumnF = columnL;
                decRowL = rowL;
                decColumnL = columnF;
            } // else - REST OF THE CASES
            result += key.charAt(decRowF * 5 + decColumnF) + "" + key.charAt(decRowL * 5 + decColumnL);
        } // for loop - i
        return result;
    }// end of String decrypt(String, String)
}// end of class
