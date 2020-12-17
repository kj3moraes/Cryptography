package Polyalphabetic;
import java.util.Scanner;
public class Vigenere {
    public static void main(String[] args) {
        final Scanner txt = new Scanner(System.in), num = new Scanner(System.in);
        System.out.println("\nEnter your choice \n\t [1] Encrypt \n\t [2] Decrypt \n\t [X] Exit");
        final char choice = num.next().toUpperCase().charAt(0);
        String plainText, encryptedText, key;
        switch (choice) {
            //ENCRYPTION
            case '1':
                System.out.print("\t PLAIN TEXT : ");
                plainText = txt.nextLine();
                System.out.print("\t KEY : ");
                key = num.next().toUpperCase().trim();
                encryptedText = encrypt(plainText, key);
                System.out.println("\n\t\t INPUTED PLAIN TEXT : " + plainText);
                System.out.println("\t\t KEY : " + key);
                System.out.println("\t\t GENERATED ENCRYPTION : " + encryptedText);
                break;

            //DECRYPTION 
            case '2':
                System.out.print("\t ENCRYPTED TEXT : ");
                encryptedText = txt.nextLine();
                System.out.print("\t KEY : ");
                key = num.next().toUpperCase().trim();
                plainText = decrypt(encryptedText, key);
                System.out.println("\n\t\t INPUTED ENCRYPTED TEXT : " + encryptedText);
                System.out.println("\t\t KEY : " + key);
                System.out.println("\t\t GENERATED DECRYPTION : " + plainText);
                break;

            //EXIT
            case 'X':
                System.exit(0);
                break;

                default:
                System.out.println("KINDLY ENTER A NUMBER (1-2) or (X)");
        }//switch statement
        main(new String[]{});
    }//end of void main(String[])

    /**
     * VIGENÈRE - ENCRYPT
     * This function takes 2 parameters - plain text and the shift value and outputs the encrypted text.     *
     * @param plainText - the user input that is to be encrypted
     * @param key - the text to encrypt the plain text.
     * @return - the encrypted text
     */
    private static String encrypt(String plainText, String key){
        String result = "";
        /*'intermediate' is a helper variable that prevents the lines
        from being excessively long to read.
        It just stores the (plaintext+shift).*/
        int intermediate;
        for(int i = 0, j = 0; i<plainText.length(); i++) {
            int plainTextLetter = plainText.charAt(i);
            if ((plainTextLetter < 65 || plainTextLetter > 90) &&
                    (plainTextLetter < 97 || plainTextLetter > 122)) {
                result = result.concat((char)plainTextLetter + "");
                continue;
            }//if statement - special characters
            if(plainTextLetter<=90){
                intermediate = (plainTextLetter - 65) + (key.charAt(j) - 65);
                result = result.concat((char) (intermediate % 26 + 65) + "");
            }//UPPERCASE
            else {
                intermediate = (plainTextLetter - 97) + (key.charAt(j) - 65);
                result = result.concat((char) (intermediate % 26 + 97) + "");
            }//LOWERCASE
            j = ++j%key.length();
        }//for loop - i
        return result;
    }//end of String encrypt(String, String)
 
    /**
     * VIGENÈRE - DECRYPT
     * This function takes 2 parameters - plain text and the shift value and outputs the encrypted text.
     * @param encryptedText - the user input that is to be decrypted
     * @param key - the text to decrypt the plain text
     * @return - the decrypted text
     */
    private static String decrypt(String encryptedText, String key){
        String result = "";
        int intermediate;
        for(int i = 0, j = 0; i<encryptedText.length(); i++) {
            int encryptedTextLetter = encryptedText.charAt(i);
            if ((encryptedTextLetter < 65 || encryptedTextLetter > 90) &&
                    (encryptedTextLetter < 97 || encryptedTextLetter > 122)) {
                result = result.concat((char)encryptedTextLetter + "");
                continue;
            }//if statement - special characters
            if(encryptedTextLetter<=90){
                intermediate = (encryptedText.charAt(i) - 65) - (key.charAt(j) - 65);
                result = result.concat((char) (((intermediate % 26) + 26) % 26 + 65) + "");
            }//UPPERCASE
            else {
                intermediate = (encryptedText.charAt(i) - 97) - (key.charAt(j) - 65);
                result = result.concat((char) (((intermediate % 26) + 26) % 26 + 65) + "");
            }//LOWERCASE
            j = ++j%key.length();
        }//for loop - i
        return result;
    }//end of String decrypt(String, String)
}//end of class
