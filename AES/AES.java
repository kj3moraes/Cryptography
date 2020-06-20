import java.util.Scanner;
class AES {
    private static final Scanner num = new Scanner(System.in), txt = new Scanner(System.in);
    public static void main(String[] args){
        System.out.println("\nEnter your choice " + "\n\t [1] Encode \n\t [2] Decode \n\t [X] Exit");
        final char choice = num.next().toUpperCase().charAt(0);
        String plainText, encryptedText, seed;
        switch (choice) {
            // ENCRYPTION
            case '1':
                System.out.print("\t PLAIN TEXT : ");
                plainText = txt.nextLine().trim();
                System.out.print("\t KEY : ");
                seed = txt.nextLine().toUpperCase().trim();
                encryptedText = encrypt(plainText, seed);
                System.out.println("\n\t\t INPUTED PLAIN TEXT : " + plainText);
                System.out.println("\t\t KEY (seed for Secret Key): " + seed); 
                System.out.println("\t\t GENERATED ENCODING : " + encryptedText);
                break;

            // DECRYPTION
            case '2':
                System.out.print("\t ENCRYPTED TEXT : ");
                encryptedText = txt.nextLine();
                System.out.print("\t KEY : ");
                seed = txt.nextLine().toUpperCase().trim();
                plainText = decrypt(encryptedText, seed);
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
    }//end of void main(String[])

    public static String encrypt(String plaintext, String seed){
        String result = "";
        System.out.println("SUBMIT YOUR AES VERSION: [1] AES-128, [2] AES-192, [3] AES-256");
        final char choice = txt.next().trim().charAt(0);
        switch(choice){
            case '1':
            break;
            
            case '2':
            break;

            case '3':
            break;

            default:
            break;
        }// switch statement
        return result;
    }//end of String encrpyt(String, String)

    public static String decrypt(String encryptedText, String seed) {
        String result = "";
        return result;
    }//end of String decrypt(String, String)
}//end of class
