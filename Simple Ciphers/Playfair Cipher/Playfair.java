package Polybius;
import java.util.Scanner;
class Playfair{
    public static void main(String[] args) {
        final Scanner txt = new Scanner(System.in), num = new Scanner(System.in);
        System.out.println("Enter your choice \n\t [1] Encrypt \n\t [2] Decrypt \n\t [X] Exit");
        final char choice = num.next().toUpperCase().charAt(0);
        String plainText, encryptedText,key;
        switch (choice) {
            case '1':
                System.out.print("\t PLAIN TEXT : ");
                plainText = txt.nextLine().toUpperCase().trim();
                System.out.print("\t KEY (every character of the key must be unique) : ");
                key = txt.nextLine().toUpperCase();
                encryptedText = encrypt(plainText, key);
                System.out.println("\n\t\t INPUTED PLAIN TEXT : " + plainText);
                System.out.println("\t\t GENERATED ENCRYPTION : " +  encryptedText);
                break;

            case '2':
                System.out.print("\t ENCRYPTED TEXT : ");
                encryptedText = txt.nextLine();
                plainText = decrypt(encryptedText);
                System.out.println("\n\t\t INPUTED ENCRYPTED TEXT : " + plainText);
                System.out.println("\t\t GENERATED DECRYPTION : " +  encryptedText);
                break;

            case 'X':
                System.exit(0);
                break;

            default:
                System.out.println("KINDLY ENTER A NUMBER (1-2) or (X) to EXIT ");
        }//switch statement(int)
        main(new String[]{});
    }//end of void main(String[])

    private static String encrypt(String plainText, String key) {
        String result = "";
        plainText += " ";
        int rowF, columnF, rowL, columnL;
        for (int i = 0; i < plainText.length()-1; i+=2) {
            String extract = plainText.substring(i,i+2);

            // STEP 1 : WEED OUT THE 'EXTRAS'
            if(key.indexOf(extract.charAt(0)) == -1 || key.indexOf(extract.charAt(1)) == -1) {
                result += extract;
                continue;
            }// if statement - NOT IN KEY MATRIX

            // STEP 2 : EXTRACT THE ROWS AND COLUMNS OF BOTH THE CHARACTERS
            rowF = key.indexOf(extract.charAt(0)) / 5;
            rowL = key.indexOf(extract.charAt(1)) / 5;
            columnF = key.indexOf(extract.charAt(0)) % 5;
            columnL = key.indexOf(extract.charAt(1)) % 5;

            // STEP 3 : ENCRYPT BASED ON THE POSITIONS IN THE SQUARE
            int encRowF = 0, encRowL = 0, encColumnF = 0, encColumnL = 0;
            if (rowF==rowL){
                encColumnF = (columnF+1)%5;
                encColumnL = (columnL+1)%5;
                encRowF = encRowL = rowL;
            }// if statement - same ROW
            else if (columnF==columnL){
                encRowF = (rowF + 1)%5;
                encRowL = (rowL + 1)%5;
                encColumnF = encColumnL = columnF;
            }// if statement - same COLUMN
            else {
                encRowF = rowF;
                encColumnF = columnL;
                encRowL = rowL;
                encColumnL = columnF;
            }// else - REST OF THE CASES
            result +=  key.charAt(encRowF*5 + encColumnF) + "" +  key.charAt(encRowL*5 + encColumnL);
        }// for loop - i
        return result;
    }//end of String encrypt(String, String)

    private static String decrypt(String encryptedText, String key){
        String result = "";
        return result;
    }//end of String decrypt(String, String)
}//end of class
