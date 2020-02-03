package SimpleCiphers.Caesar;
import java.util.Scanner;
public class Caesar {
    public static void main(String[] args){
        Scanner txt = new Scanner(System.in), num = new Scanner(System.in);
        System.out.println("Enter your choice \n\t [1] Encrypt \n\t [2] Decrypt \n\t [X] Exit");
        int choice = num.nextInt();
        String plainText = "", encryptedText = "";
        int shift;
        switch (choice) {
            case 1:
                System.out.print("\t PLAIN TEXT : ");
                plainText = txt.nextLine();
                System.out.print("\t SHIFT : ");
                shift = num.nextInt();
                encryptedText = encrypt(plainText, shift);
                System.out.println("\n\t\t INPUTED PLAIN TEXT : " + plainText);
                System.out.println("\t\t SHIFT : " + shift);
                System.out.println("\t\t GENERATED ENCRYPTION : " +  encryptedText);
                break;

            case 2:
                System.out.print("\t ENCRYPTED TEXT : ");
                encryptedText = txt.nextLine();
                System.out.print("\t SHIFT : ");
                shift = num.nextInt();
                plainText = decrypt(encryptedText, shift);
                System.out.println("\n\t\t INPUTED ENCRYPTED TEXT : " + plainText);
                System.out.println("\t\t SHIFT : " + shift);
                System.out.println("\t\t GENERATED DECRYPTION : " +  encryptedText);
                break;

            case 88:

            case 120:
                System.exit(0);
                break;

            default:
                System.out.println("KINDLY ENTER A NUMBER (1-2) or (X) to EXIT ");
        }//switch statement(int)
        main(new String[]{});
    }//end of void main(String[])

    /**
     * CAESAR - ENCRYPT
     * This function takes 2 parameters - plain text and the shift value and outputs the encrypted text.
     * @param plainText - the user input that he/she wants encrypted
     * @param shift - the shift value necessary to encrypt the data
     * @return - the encrypted text
     */
    private static String encrypt(String plainText, int shift){
        String encryption = "";
        for (int i = 0;i<plainText.length();i++) {
            if(plainText.charAt(i)>=65 && plainText.charAt(i)<=90)
                encryption = encryption.concat((char)((plainText.charAt(i) - 65 + shift)%26 + 65)+"");
            else if(plainText.charAt(i)>=97 && plainText.charAt(i)<=122)
                encryption = encryption.concat((char)((plainText.charAt(i) - 97 + shift)%26 + 97)+"");
        }//for loop - i
        return encryption;
    }//end of void encrypt(String, int)

    /**
     * CAESAR - DECRYPT
     * This function takes 2 parameters - encrypted text and the shift value and outputs
     * the plain text.
     * @param encryptedText - the user input that he/she wants encrypted
     * @param shift - the shift value necessary to encrypt the data
     * @return - the encrypted text
     */
    private static String decrypt(String encryptedText, int shift){
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
