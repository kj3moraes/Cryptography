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

    /**
     * POLYBIUS - ENCRYPT 
     * This function takes 2 parameters - plain text and the
     * shift value and outputs the encrpytion of the plain text via a Polybius cipher. 
     * @param plainText - the user input that is to be encrypted
     * @param key       - the text to encrypt the plain text.
     * @return - the encrypted text
     */
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
            columnL = key.indexOf(extract.charAt(1)) % 5;

            int encRowF, encRowL, encColumnF, encColumnL;
            if (rowF == rowL) {
                encColumnF = (columnF + 1) % 5;
                encColumnL = (columnL + 1) % 5;
                encRowF = encRowL = rowL;
            } else if (columnF == columnL) {
                encRowF = (rowF + 1) % 5;
                encRowL = (rowL + 1) % 5;
                encColumnF = encColumnL = columnF;
            }//else if statement

        } // for loop - i
        return result;
    }//end of String encrypt(String, String)

    /**
     * POLYBIUS - DECRYPT
     *  This function takes 2 parameters - plain text and the
     * shift value and outputs the decryption of the encrypted text     * 
     * @param encryptedText - the user input that is to be encrypted
     * @param key       - the text to encrypt the plain text.
     * @return - the decrypted text
     */
    private static String decrypt(String encryptedText){
        String result = "";
        encryptedText = encryptedText.trim()+ " ";
        for(int i = 0; i<encryptedText.length(); i+=3){
            int letterNumber =  Integer.parseInt(encryptedText.substring(i,i+2));
            char letter;
            if(letterNumber/10>2 || (letterNumber/10==2 && letterNumber%10==5))
                letter = (char)(65 + (letterNumber/10-1)*5 + (letterNumber%10));
            else letter = (char)(65 + (letterNumber/10-1)*5 + (letterNumber%10-1));
            result+=letter;
        }//for loop - i
        return result;
    }//end of String decrypt(String)
}//end of class
