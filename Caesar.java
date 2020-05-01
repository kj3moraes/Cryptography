import java.util.Scanner;
class Caesar {
    public static void main(String[] args) {
        final Scanner txt = new Scanner(System.in), num = new Scanner(System.in);
        System.out.println("\nEnter your choice \n\t [1] Encrypt \n\t [2] Decrypt \n\t [X] Exit");
        final char choice = num.next().toUpperCase().charAt(0);
        String plainText, encryptedText;
        int shift;
        switch (choice) {
            // ENCRYPTION
            case '1':
                System.out.print("\t PLAIN TEXT : ");
                plainText = txt.nextLine();
                System.out.print("\t SHIFT : ");
                shift = num.nextInt();
                encryptedText = encrypt(plainText, shift);
                System.out.println("\n\t\t INPUTED PLAIN TEXT : " + plainText);
                System.out.println("\t\t SHIFT : " + shift);
                System.out.println("\t\t GENERATED ENCRYPTION : " + encryptedText);
                break;

            // DECRYPTION
            case '2':
                System.out.print("\t ENCRYPTED TEXT : ");
                encryptedText = txt.nextLine();
                System.out.print("\t SHIFT : ");
                shift = num.nextInt();
                plainText = decrypt(encryptedText, shift);
                System.out.println("\n\t\t INPUTED ENCRYPTED TEXT : " + encryptedText);
                System.out.println("\t\t SHIFT : " + shift);
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
     * CAESAR - ENCRYPT
     * This function takes 2 parameters - plain text and the shift
     * value and outputs the encrypted text. In a Caesar cipher, the shift is
     * constant and we add it to the plaintext
     * 
     * @param plainText - the user input that is to be encrypted
     * @param shift     - the shift value necessary to encrypt the data
     * @return - the encrypted text
     */
    private static String encrypt(final String plainText, final int shift) {
        String encryption = "";
        for (int i = 0; i < plainText.length(); i++) {
            if (plainText.charAt(i) >= 65 && plainText.charAt(i) <= 90)
                encryption = encryption.concat((char) ((plainText.charAt(i) - 65 + shift) % 26 + 65) + "");
            else if (plainText.charAt(i) >= 97 && plainText.charAt(i) <= 122)
                encryption = encryption.concat((char) ((plainText.charAt(i) - 97 + shift) % 26 + 97) + "");
        } // for loop - i
        return encryption;
    }// end of void encrypt(String, int)

    /**
     * CAESAR - DECRYPT
     * This function takes 2 parameters - encrypted text and the
     * shift value and outputs the plain text. In Caesar cipher, we just subtract
     * the encrypted text with the shift to decrypt it.
     * 
     * @param encryptedText - the user input that is to be decrypted
     * @param shift - the shift value necessary to decrypt the data
     * @return - the decrypted text
     */
    private static String decrypt(final String encryptedText, final int shift) {
        String decryption = "";
        for (int i = 0; i<encryptedText.length(); i++) {
            if (encryptedText.charAt(i) >= 65 && encryptedText.charAt(i) <= 90)
                decryption = decryption.concat((char) (((encryptedText.charAt(i) - 65 - shift)%26 + 26)%26 + 65)+"");
            else if (decryption.charAt(i) >= 97 && decryption.charAt(i) <= 122)
                decryption = decryption.concat((char) (((encryptedText.charAt(i) - 97 - shift)%26 + 26)%26 + 97)+"");
        }//for loop - i
        return decryption;
    }//end of String decrypt(String, int)
}//end of class
