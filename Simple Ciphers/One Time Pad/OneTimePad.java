import java.util.Scanner;
public class OneTimePad {
    public static void main(String[] args) {
        final Scanner txt = new Scanner(System.in), num = new Scanner(System.in);
        System.out.println("\nEnter your choice \n\t [1] Encrypt \n\t [2] Decrypt \n\t [X] Exit");
        final char choice = num.next().toUpperCase().charAt(0);
        String plainText, encryptedText, key;
        switch (choice) {
            //ENCRYPTION
            case '1':
                System.out.print("\t PLAIN TEXT : ");
                plainText = txt.nextLine().trim();
                System.out.print("\t KEY (to achieve Perfect Secrecy the key must be as long the plaintext) : ");
                key = txt.nextLine().trim();
                encryptedText = computeBitwiseXOR(plainText, key, 'e');
                System.out.println("\n\t\t INPUTED PLAIN TEXT : " + plainText);
                System.out.println("\t\t KEY : " + key);
                System.out.println("\t\t GENERATED ENCRYPTION : " + encryptedText);
                break;

            //DECRYPTION
            case '2':
                System.out.print("\t ENCRYPTED TEXT : ");
                encryptedText = txt.nextLine().trim();
                System.out.print("\t KEY : ");
                key = txt.nextLine().toUpperCase().trim();
                plainText = computeBitwiseXOR(encryptedText, key, 'd');
                System.out.println("\n\t\t INPUTED ENCRYPTED TEXT : " + encryptedText);
                System.out.println("\t\t KEY : " + key);
                System.out.println("\t\t GENERATED DECRYPTION : " + plainText);
                break;

            //EXIT
            case 'X':
                System.exit(0);
                break;

            default:
                System.out.println("KINDLY ENTER A NUMBER (1-2) or (X) to EXIT ");
        }//switch statement
        main(new String[]{});
    }// end of void main(String[])

    /**
     * OTP - COMPUTE BITWISE XOR
     *      This function takes 2 parameters - given text, a key and a mode.
     *      The function will compute the XOR of 2 Strings as follows
     *          ENCRYTPION
     *          String --> ASCII --> 8-bit binary --> XOR --> Decimal --> Hex
     *
     *          DECRYTPION
     *          Hex --> 8-bit binary --> XOR --> Decimal (ASCII) --> String
     *
     *      The (e)ncryption and (d)ecryption algorithms both take 3 steps.
     * @param text - the user input that is to be encrypted
     * @param key - the text XOR with the plain text.
     * @param mode - represents either (e)ncryption or (d)ecryption
     * @return - the encrypted text in Hex
     */
    private static String computeBitwiseXOR(String text, String key, char mode) {
        String result = "", resultInBits = "";
        String textIn8BitBin = "", keyIn8BitBin = "";

        //STEP 1 : CONVERT THE GIVEN TEXT INTO 8 BIT BINARY [SPACED]
        switch (mode) {
            //ENCRYPTION
            case 'e':
                //PLAIN TEXT TO 8 BIT BINARY
                for (int i = 0; i < text.length(); i++) {
                    char character = text.charAt(i);
                    if (character==' ') continue;
                    textIn8BitBin += DecimalTo8BitBinary(character) + " ";
                }//for loop - i
                break;

            //DECRYPTION
            case 'd':
                //ENCRYPTED TEXT TO 8 BIT BINARY
                if(text.charAt(text.length()-1)!=' ') text+=" ";
                textIn8BitBin = HexTo8BitBinary(text);
                break;
        }//switch statement - mode

         // KEY TO 8 BIT BINARY
        for (int j = 0; j < key.length(); j++) {
            char character = key.charAt(j);
            if (character == ' ')
                continue;
            keyIn8BitBin += DecimalTo8BitBinary(character) + " ";
        } // for loop - j

        //STEP 2 : XOR THE TEXT AND THE KEY (WRAPPING THE KEY IF NEED BE)
        for(int k = 0; k<textIn8BitBin.length(); k++) {
            if (textIn8BitBin.charAt(k)==' ') {
                resultInBits += " ";
                continue;
            }//if statement - spaces
            int textBit = textIn8BitBin.charAt(k) - 48;
            int keyBit = keyIn8BitBin.charAt(k%keyIn8BitBin.length()) - 48;
            resultInBits = resultInBits.concat((textBit+keyBit)%2+"");
        }//for loop - k

        //STEP 3 : CONVERTING INTO THE DESIRED OUTPUT FORMAT
        switch (mode){
            case 'e':
                for (int m = 0, n; m < resultInBits.length();) {
                    n = resultInBits.indexOf(' ',m);
                    result += Integer.toHexString(Integer.parseInt(resultInBits.substring(m,n),2)) + " ";
                    m = n+1;
                }//for loop - m,n
                break;

            case 'd':
                for (int m = 0, n; m < resultInBits.length();) {
                    n = resultInBits.indexOf(' ',m);
                    result += (char)(Integer.parseInt(resultInBits.substring(m,n),2));
                    m = n+1;
                }//for loop - m,n
                break;
        }//switch statement
        return result;
    }//end of String computeBitwiseXOR(String, String, int)

    /**
     * OTP - DECIMAL TO 8 BIT BINARY
     *      This function takes 1 parameter - a decimal (int)
     *      It outputs its equivalent 8 bit binary string.
     * @param intValue - the character to be converted
     * @return the 8 bit string of the character
     */
    private static String DecimalTo8BitBinary(int intValue) {
        String result = Integer.toBinaryString(intValue);
        for (int i = result.length() ; i < 8; i++) result = "0" + result;
        return result;
    }//end of String DecimalTo8BitBinary(int)

    /**
     * OTP - HEXADECIMAL TO 8 BIT BINARY
     *      This function takes a hex string and for each character will generate a 8-bit
     *      binary output. This must be 8 bit because that was how it was encrypted.
     * @param hexString- inputed hex string
     * @return - the equivalent binary string.
     */
    private static String HexTo8BitBinary(String hex){
        String result = "";
        for (int i = 0, j; i < hex.length();) {
            j = hex.indexOf(' ',i);
            result += DecimalTo8BitBinary(Integer.parseInt(hex.substring(i,j),16)) + " ";
            i = j+1;
        }//for loop - i,j
        return result;
    }//end of static String HexTo8BitBinary(String)
}//end of class
