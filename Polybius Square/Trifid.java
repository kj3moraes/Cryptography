import java.util.*;
class Trifid {
    public static void main(String[] args) {
        Scanner txt = new Scanner(System.in), num = new Scanner(System.in);
        System.out.println("Enter your choice \n\t [1] Encrypt \n\t [2] Decrypt \n\t [X] Exit");
        int choice = num.nextInt();
        String plainText = "", encryptedText = "";
        switch (choice) {
            case 1:
                System.out.print("\t PLAIN TEXT : ");
                plainText = txt.nextLine();
                encryptedText = encrypt(plainText);
                System.out.println("\n\t\t INPUTED PLAIN TEXT : " + plainText);
                System.out.println("\t\t GENERATED ENCRYPTION : " +  encryptedText);
                break;

            case 2:
                System.out.print("\t ENCRYPTED TEXT : ");
                encryptedText = txt.nextLine();
                plainText = decrypt(encryptedText);
                System.out.println("\n\t\t INPUTED ENCRYPTED TEXT : " + plainText);
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

    private static String encrypt(String plainText){
        String result = "";
        return result;
    }//end of String encrypt(String)

    private static String decrypt(String encryptedText){
        String result = "";
        return result;
    }//end of String decrypt(String)
}//end of class