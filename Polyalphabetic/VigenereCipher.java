import java.util.Scanner;
public class Vigenere {
    public static void main(String[] args) {
        Scanner txt = new Scanner(System.in), num = new Scanner(System.in);
        System.out.println("\nEnter your choice \n\t [1] Encrypt \n\t [2] Decrypt \n\t [X] Exit");
        int choice = num.nextInt();
        String plainText = "", encryptedText = "", key = "";
        switch (choice) {
            //ENCRYPTION
            case 1:
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
            case 2:
                System.out.print("\t ENCRYPTED TEXT : ");
                encryptedText = txt.nextLine();
                System.out.print("\t KEY : ");
                key = num.next().toUpperCase().trim();
                plainText = decrypt(encryptedText, key);
                System.out.println("\n\t\t INPUTED ENCRYPTED TEXT : " + encryptedText);
                System.out.println("\t\t SHIFT : " + key);
                System.out.println("\t\t GENERATED DECRYPTION : " + plainText);
                break;

            //EXIT
            case 88:
                System.exit(0);
                break;

                default:
                System.out.println("KINDLY ENTER A NUMBER (1-2) or (X) to EXIT ");
        }//switch statement
        main(new String[]{});
    }//end of void main(String[])

    /**
     * VIGENÈRE - ENCRYPT
     * This function takes 2 parameters - plain text and the shift value and outputs the encrypted text.
     *       In the Vigenère cipher, the shift is dependant on the absolute value of the letter
     *       of the key at that position. We take this as  the shift and we add it to the plaintext.
     * @param plainText - the user input that he/she wants encrypted
     * @param key - the shift value necessary to encrypt the data
     * @return - the encrypted text
     */
    private static String encrypt(String plainText, String key){
        String result = "";
        /*'intermediate' is a helper variable that prevents the lines
        from being excessively long to read.
        It just stores the (plaintext+shift).*/
        int intermediate;
        for(int i = 0, j = 0; i<plainText.length(); i++) {
            if (plainText.charAt(i) < 65 || plainText.charAt(i) > 90) {
                result = result.concat(plainText.charAt(i) + "");
                continue;
            }//if statement - special characters
            intermediate = (plainText.charAt(i) - 65) + (key.charAt(j) - 65);
            result = result.concat((char) (intermediate % 26 + 65) + "");
            j = ++j%key.length();
        }//for loop - i
        return result;
    }//end of String encrypt(String, String)

    /**
     * VIGENÈRE - DECRYPT
     * This function takes 2 parameters - plain text and the shift value and outputs the encrypted text.
     *       We take the absolute value of the key at the position and subtract it from the encrypted text.
     * @param encryptedText - the user input that he/she wants decrypted
     * @param key - the shift value necessary to decrypt the data
     * @return - the decrypted text
     */
    private static String decrypt(String encryptedText, String key){
        String result = "";
        int intermediate;
        for(int i = 0, j = 0; i<encryptedText.length(); i++) {
            if (encryptedText.charAt(i) < 65 || encryptedText.charAt(i) > 90) {
                result = result.concat(encryptedText.charAt(i) + "");
                continue;
            }//if statement - special characters
            intermediate = (encryptedText.charAt(i) - 65) - (key.charAt(j) - 65);
            result = result.concat((char) (((intermediate % 26) + 26) % 26 + 65) + "");
            j = ++j%key.length();
        }//for loop - i
        return result;
    }//end of String decrypt(String, String)
}//end of class
