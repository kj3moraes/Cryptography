import java.util.Scanner;
class CheckerboardCipher {
    public static void main(String[] args) {
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
}//end of class
