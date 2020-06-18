package AES;
import java.util.Scanner;
class AES {
    public static void main(String[] args){
        Scanner num = new Scanner(System.in), txt = new Scanner(System.in);
        System.out.println("\nEnter your choice \n\t [1] Encrypt \n\t [2] Decrypt \n\t [X] Exit");
        final char choice = num.next().toUpperCase().charAt(0);
        String plainText, encryptedText, seed;
        switch (choice) {
            // ENCODING
            case '1':
                System.out.print("\t PLAIN TEXT : ");
                plainText = txt.nextLine().trim();
                System.out.print("\t KEY : ");
                seed = txt.nextLine().toUpperCase().trim();
                System.out.println("\n\t\t INPUTED PLAIN TEXT : " + plainText);
                System.out.println("\t\t KEY (seed for Secret Key): " + seed);
                System.out.println("\t\t GENERATED ENCRYPTION : " + encryptedText);
                break;

            // DECODING
            case '2':
                System.out.print("\t ENCRYPTED TEXT : ");
                encryptedText = txt.nextLine();
                System.out.print("\t KEY : ");
                seed = txt.nextLine().toUpperCase().trim();
                System.out.println("\n\t\t INPUTED ENCRYPTED TEXT : " + encryptedText);
                System.out.println("\t\t KEY (seed for Secret Key): " + seed);
                System.out.println("\t\t GENERATED DECRYPTION : " + plainText);
                break;

            // EXIT
            case 'X':
                System.exit(0);
                break;

            default:
                System.out.println("KINDLY ENTER A NUMBER (1-2) or (X) to EXIT ");
        }//switch statement
        main(new String[]{});
    }//end of void main(String[])

    public static String encrpyt(String plaintext, String seed, int type){

    }//end of String encrypt(String, String, int)

    public static String decrypt(String encryptedText, String seed, int type) {
        
    }//end of String decrypt(String, String, int)
}//end of class