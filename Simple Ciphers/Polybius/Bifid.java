package Polybius;
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
     *      This function takes a single parameter - plain text. However the user may choose
     *      to implement a scheme with a key for additional security. The encryption scheme
     *      is implemented in 3 steps
     *      Step 1 : Convert the letters into standard Polybius Square coordinates.
     *               This is done by simple calculating the letter number and
     *               performing a ceil division by 5 and a modulo  of 5 to attain
     *               the row and column no. respectively
     *
     *      Step 2 : The row and column coordinates are concatenated
     *
     *      Step 3 : The coordinates are then converted back into letters. Once again
     *               we use a standard Polybius Square to achieve this.
     * @param plainText - the user input that is to be encrypted
     * @return - the encrypted text
     */
    private static String encrypt(String plainText){
        /*
            The 'rowNumbers' and the 'columnNumbers' simply store the
            rows and columns of the characters of the plaintext respectively.
            The row\column no. is that of the standard Polybius square.
            Finally the rowNumber and columnNumbers are concatenated into
            the rowNumbers before moving onto converting back into characters.
         */
        String result = "", rowNumbers = "", columnNumbers = "";
        plainText = (plainText+" ").toUpperCase();

        // S1 : ENCODING LETTERS INTO POLYBIUS SQUARE COORDINATES
        for (int i = 0; i < plainText.length(); i++) {
            /*
                The letterNumber variable extracts the required letter number in standard format
                of a given character. By standard format, we mean A-Z while omitting J as in a
                standard Polybius square.
                This behaves as a helper variable to improve the understanding of the reader.
             */
            int letterNumber;
            if(Character.isUpperCase(plainText.charAt(i))) {
                if(plainText.charAt(i)>='J')
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
            if(letterNumber/10>2 || (letterNumber/10==2 && letterNumber%10>=4))
                letter = (char)(65 + (letterNumber/10-1)*5 + (letterNumber%10));
            else letter = (char)(65 + (letterNumber/10-1)*5 + (letterNumber%10-1));
            result+=letter;
            j+=2;
        }while (j<rowNumbers.length());
        return result;
    }//end of String encrypt(String)

    /**
     * BIFID - DECRYPT
     *      This function takes a single parameter - the encrypted text. Decryption takes place
     *      in 2 steps. 
     *      Step 1 : Convert the letters into standard Polybius Square coordinates.
     *               This is done the same way as in the encrypt() function.
     *               
     *      Step 2 : Using the row and column coordinates to get back the plain text. By
     *               using the midpoint variable we can access both the row and column 
     *               coordinates simultaneously to convert the coordinates into letters.
     * @param encryptedText - the user input that is to be decrypted
     * @return - the plain text
     */
    private static String decrypt(String encryptedText){
        // S1 : CONVERSION OF ENCRYPTED TEXT INTO COORDINATES
        /*
            'intermediateLetterNumbers' is a variable that stores each character's
            standard Polybius square equivalent. Now we can split this variable into
            2 equivalent halves where the ith digit of the first half and the ith
            digit of the second half put together is the polybius square equivalent
            the ith character of the plaintext.
         */
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
            if(letterNumber/10>2 || (letterNumber/10==2 && letterNumber%10>=5))
                letter = (char)(65 + (letterNumber/10-1)*5 + (letterNumber%10));
            else letter = (char)(65 + (letterNumber/10-1)*5 + (letterNumber%10-1));
            result+=letter;
        }//for loop - i
        return result;
    }//end of String decrypt(String)
}//end of class
