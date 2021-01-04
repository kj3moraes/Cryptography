import java.util.Scanner;
public class Bifid {
    public static void main(String[] args) {
        final Scanner txt = new Scanner(System.in), num = new Scanner(System.in);
        System.out.println("\nEnter your choice \n\t [1] Encrypt \n\t [2] Decrypt \n\t [X] Exit");
        final char choice = num.next().toUpperCase().charAt(0);
        String plainText, encryptedText;
        switch (choice){
            //ENCRYPTION
            case '1':
                System.out.print("\t PLAIN TEXT : ");
                plainText = txt.nextLine();
                encryptedText = encrypt(plainText);
                System.out.println("\n\t\t INPUTED PLAIN TEXT : " + plainText);
                System.out.println("\t\t GENERATED ENCRYPTION : " +  encryptedText);
                break;

            //DECRYPTION
            case '2':
                System.out.print("\t ENCRYPTED TEXT : ");
                encryptedText = txt.nextLine();
                plainText = decrypt(encryptedText);
                System.out.println("\n\t\t INPUTED ENCRYPTED TEXT : " + encryptedText);
                System.out.println("\t\t GENERATED DECRYPTION : " +  plainText);
                break;

            //EXIT
            case 'X':
                System.exit(0);
                break;

            default:
                System.out.println("KINDLY ENTER A NUMBER (1-2) or (X)");
        }//switch statement
        main(new String[]{});
    }//end of void main(String[])

    /**
     * BIFID - ENCRYPT
     * This function takes a single parameter - plain text. It ouputs the encryption 
     * of the plaintext using the Bifid encryption algorithm.
     * 
     * @param plainText - the user input that is to be encrypted
     * @return - the encrypted text
     */
    private static String encrypt(String plainText){
        String result = "", rowNumbers = "", columnNumbers = "";
        plainText = (plainText+" ").toUpperCase();

        // S1 : ENCODING LETTERS INTO POLYBIUS SQUARE COORDINATES
        for (int i = 0; i < plainText.length(); i++) {
           
            int letterNumber;
            if (Character.isUpperCase(plainText.charAt(i))) {
                if (plainText.charAt(i) >= 'J')
                    letterNumber = plainText.charAt(i) - 66;
                else
                    letterNumber = plainText.charAt(i) - 65;
            }//if statement - uppercase correction
            else
                continue;
            rowNumbers += (letterNumber/5 + 1) + "";
            columnNumbers += (letterNumber%5 + 1) + "";
        }//for loop - i

        // S2 : CONCATENATING THE ROW AND COLUMN COORDINATES
        rowNumbers += columnNumbers;

        //S3 : CONVERTING COORDINATES BACK INTO LETTERS
        int j = 0;
        do {    
            int letterNumber = Integer.parseInt(rowNumbers.substring(j,j+2));
            char letter;
            if (letterNumber/10 > 2 || (letterNumber/10 == 2 && letterNumber%10 >= 4))
                letter = (char)(65 + (letterNumber/10-1)*5 + (letterNumber%10));
            else letter = (char)(65 + (letterNumber/10-1)*5 + (letterNumber%10-1));
            result+=letter;
            j+=2;
        }while (j < rowNumbers.length());
        return result;
    }//end of String encrypt(String)

    /**
     * BIFID - DECRYPT
     * This function takes a single parameter - the encrypted text. It ouputs the decryption
     * of the encrypted text via the Bifid decryption algorithm.
     * @param encryptedText - the user input that is to be decrypted
     * @return - the decrypted text
     */
    private static String decrypt(String encryptedText){
        // S1 : CONVERSION OF ENCRYPTED TEXT INTO COORDINATES
        String result = "", intermediateLetterNumbers = "";
        for (int i = 0; i < encryptedText.length(); i++) {
            int letterNumber;
                if (encryptedText.charAt(i) >= 'J')
                    letterNumber = encryptedText.charAt(i) - 66;
                else
                    letterNumber = encryptedText.charAt(i) - 65;
            intermediateLetterNumbers += (letterNumber/5+1)+""+(letterNumber%5+1);
        }//for loop - i

        // S2 : USING ROW AND COLUMN COORDINATES TO CONVERT TO LETTER
        int midPoint = intermediateLetterNumbers.length()/2;
        for(int i = 0; i<midPoint; i++){
            int letterNumber =  Integer.parseInt(intermediateLetterNumbers.charAt(i)+""+
                    intermediateLetterNumbers.charAt(midPoint+i));
            char letter;
            if (letterNumber/10 > 2 || (letterNumber/10 == 2 && letterNumber%10 >= 5))
                letter = (char)(65 + (letterNumber/10-1)*5 + (letterNumber%10));
            else letter = (char)(65 + (letterNumber/10-1)*5 + (letterNumber%10-1));
            result+=letter;
        }//for loop - i
        return result;
    }//end of String decrypt(String)
}//end of class
