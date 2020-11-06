package Polybius;
import java.util.Scanner;
class Playfair {
    public static void main(String[] args) {
        final Scanner txt = new Scanner(System.in), num = new Scanner(System.in);
        System.out.println("Enter your choice \n\t [1] Encrypt \n\t [2] Decrypt \n\t [X] Exit");
        final char choice = num.next().toUpperCase().charAt(0);
        String plainText, encryptedText, key;
        switch (choice) {
            case '1':
                System.out.print("\t PLAIN TEXT : ");
                plainText = txt.nextLine();
                System.out.print("\t KEY (every character of the key must be unique) : ");
                key = txt.nextLine().toUpperCase();
                encryptedText = encrypt(plainText, key);
                System.out.println("\n\t\t INPUTED PLAIN TEXT : " + plainText);
                System.out.println("\t\t GENERATED ENCRYPTION : " + encryptedText);
                break;

            case '2':
                System.out.print("\t ENCRYPTED TEXT : ");
                encryptedText = txt.nextLine();
                plainText = decrypt(encryptedText);
                System.out.println("\n\t\t INPUTED ENCRYPTED TEXT : " + plainText);
                System.out.println("\t\t GENERATED DECRYPTION : " + encryptedText);
                break;

            case 'X':
                System.exit(0);
                break;

            default:
                System.out.println("KINDLY ENTER A NUMBER (1-2) or (X) to EXIT ");
        }// switch statement(int)
        main(new String[] {});
    }// end of void main(String[])

    private static String encrypt(String plainText, String key) {
        String result = "";
        key = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        plainText += " ";
        int rowF, columnF, rowL, columnL;
        for (int i = 0; i < plainText.length() - 1; i += 2) {
            String extract = plainText.substring(i, i + 2);

            if (key.indexOf(extract.charAt(0)) == -1 || key.indexOf(extract.charAt(1)) == -1) {
                result += extract;
                continue;
            } // if statement - NOT IN KEY MATRIX

            rowF = key.indexOf(extract.charAt(0)) / 5;
            rowL = key.indexOf(extract.charAt(1)) / 5;
            columnF = key.indexOf(extract.charAt(0)) % 5;
            columnF = key.indexOf(extract.charAt(1)) % 5;

        } // for loop - i
        return result;
    }

    private static String decrypt(String encryptedText) {
        String result = "";
        return result;
    }
}// end of class
