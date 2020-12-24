package Polybius;
import java.util.Scanner;
public class Polybius {
    public static void main(String[] args) {
        final Scanner txt = new Scanner(System.in), num = new Scanner(System.in);
        System.out.println("\nEnter your choice \n\t [1] Encrypt \n\t [2] Decrypt \n\t [X] Exit");
        final char choice = num.next().toUpperCase().charAt(0);
        String plainText, encryptedText;
        switch (choice) {
            // ENCRYPTION
            case '1':
                System.out.print("\t PLAIN TEXT : ");
                plainText = txt.nextLine().trim().toUpperCase();
                encryptedText = encrypt(plainText);
                System.out.println("\n\t\t INPUTED PLAIN TEXT : " + plainText);
                System.out.println("\t\t GENERATED ENCRYPTION : " + encryptedText);
                break;

            // DECRYPTION
            case '2':
                System.out.print("\t ENCRYPTED TEXT : ");
                encryptedText = txt.nextLine();
                plainText = decrypt(encryptedText);
                System.out.println("\n\t\t INPUTED ENCRYPTED TEXT : " + encryptedText);
                System.out.println("\t\t SHIFT : ");
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
     * POLYBIUS - ENCRYPT 
     * This function takes 2 parameters - plain text and the
     * shift value and outputs the encryption of the plain text via a Polybius
     * cipher.
     * 
     * @param plainText - the user input that is to be encrypted
     * @param key       - the text to encrypt the plain text.
     * @return - the encrypted text
     */
    private static String encrypt(String plainText) {
        String result = "";
        for (int i = 0; i < plainText.length(); i++) {
            int letterNumber;
            if ((plainText.charAt(i) < 65 || plainText.charAt(i) > 90)
                    && (plainText.charAt(i) < 97 || plainText.charAt(i) > 122)) {
                result = result.concat(plainText.charAt(i) + "");
                continue;
            } // if statement - special characters

            if (plainText.charAt(i) >= 'J')
                letterNumber = plainText.charAt(i) - 66;
            else
                letterNumber = plainText.charAt(i) - 65;
            int rowNumber = (int)(Math.floor(letterNumber / 5) + 1);
            int columnNumber = letterNumber % 5 + 1;
            result += (rowNumber + "") + (columnNumber + "") + " ";
        } // for loop - i
        return result;
    }// end of String encrypt(String)

    /**
     * POLYBIUS - DECRYPT 
     * This function takes 2 parameters - encrypted text and the
     * shift value and outputs the decryption of the encrypted text
     * 
     * @param encryptedText - the user input that is to be encrypted
     * @param key           - the text to encrypt the plain text.
     * @return - the decrypted text
     */
    private static String decrypt(String encryptedText) {
        String result = "";
        encryptedText = encryptedText.trim() + " ";
        for (int i = 0; i < encryptedText.length(); i += 3) {
            int letterNumber = Integer.parseInt(encryptedText.substring(i, i + 2));
            int rowNumber = letterNumber/10, columnNumber = letterNumber%10;
            char letter;
            if (rowNumber > 2 || (rowNumber == 2 && columnNumber == 5))
                letter = (char) (65 + (rowNumber - 1) * 5 + columnNumber);
            else
                letter = (char) (65 + (rowNumber - 1) * 5 + (columnNumber - 1));
            result += letter;
        } // for loop - i
        return result;
    }// end of String decrypt(String)
}// end of class
