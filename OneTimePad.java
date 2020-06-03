import java.util.Scanner;
public class OneTimePad {
    public static void main(String[] args) {
        final Scanner txt = new Scanner(System.in), num = new Scanner(System.in);
        System.out.println("Enter your choice \n\t [1] Encrypt \n\t [2] Decrypt \n\t [X] Exit");
        final char choice = num.next().toUpperCase().charAt(0);
        String plainText = "", encryptedText= "", key = "";
        switch(choice) {
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
                encryptedText = txt.nextLine();
                System.out.print("\t KEY : ");
                key = num.next().toUpperCase().trim();
                plainText = computeBitwiseXOR(HexTo8BitBinary(encryptedText), key, 'd');
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
    }// end of void main(String[])

    /**
     * OTP - COMPUTE BITWISE XOR
     *      This function takes 2 parameters - plain text and the key.
     *      The function will compute the XOR of 2 Strings as follows
     *          String --> ASCII --> 8-bit binary --> XOR --> Decimal -- Hex
     *      The reason we can't output Strings isbecause we cannot guarantee that
     *      the XOR will be a ASCII printable character (i.e "H"(72) XORed with "W"
     *      (87) gives me an ASCII of (31) which is not an ASCII Printable Character.
     *      So we must print our output in Hexadecimal.
     * @param plainText - the user input that is to be encrypted
     * @param key - the text XOR with the plain text.
     * @return - the encrypted text in Hex
     */
    private static String computeBitwiseXOR(String plainText, String key, char mode) {
        String result = "", resultInBits = "";
        String plainTextToBits = "", keyToBits = "";
        switch(mode){
            case 'e':
                //STEP 1 : CONVERT THE PLAINTEXT AND THE KEY TO 8-BIT ASCII STRINGS
                for (int i = 0; i < plainText.length(); i++) {
                    int character = plainText.charAt(i);
                    plainTextToBits = plainTextToBits.concat(Decimalto8BitBinary(character));
                }//for loop - i | PLAIN TEXT TO BITS
                for (int j = 0; j < key.length(); j++) {
                    int character = key.charAt(j);
                    keyToBits = key.concat(Decimalto8BitBinary(character));
                }//for loop - j | KEY TO BITS
                break;

            case 'd':
				plainTextToBits = plainText;
				for (int j = 0; j < key.length(); j++) {
					int character = key.charAt(j);
					keyToBits = key.concat(Decimalto8BitBinary(character));
				} // for loop - j | KEY TO BITS
        }//switch case - mode

        //STEP 2 : XOR THEM TOGETHER (WRAPPING THE KEY IF NEED BE)
        for(int k = 0; k<plainTextToBits.length(); k++){
            int plainTextBit = plainTextToBits.charAt(k) - 48;
            int keyBit = keyToBits.charAt(k%keyToBits.length()) - 48;
            resultInBits = result.concat((plainTextBit+keyBit)%2+"");
        }//for loop - k

		switch (mode){
			case 'e':
				for (int m = 0; m < resultInBits.length(); m += 8)
					result = result
					.concat(Integer.toHexString(Integer.parseInt(resultInBits.substring(m, m + 8), 2)) + "");
					break;

			case 'd':

		}//switch case - mode

        //STEP 3 : CONVERTING 8-BIT BINARY TO HEXADECIMAL
        for (int m = 0; m < resultInBits.length(); m+=8)
            result=  result.concat(Integer.toHexString(Integer.parseInt(resultInBits.substring(m,m+8),2))+"");

        return result;
    }// end of String computeBitwiseXOR(String, String)

    /**
     * OTP - ASCII TO 8 BIT BINARY
     *      This function takes 1 parameter - a character's ASCII value
     *      It outputs its equivalent 8 bit binary string.
     * @param charValue - the character to be converted
     * @return the 8 bit string of the character
     */
    private static String Decimalto8BitBinary(int charValue){
        String result = Integer.toBinaryString(charValue);
        for (int i = result.length() ; i < 8; i++) result = "0".concat(result);
        return result;
    }//end of String ASCIIto8BitBinary(int)

    /**
     * OTP - HEXADECIMAL TO 8 BIT BINARY
     *      This function takes a hex string and for each character will generate a 8-bit
     *      binary output. This must be 8 bit because that was how it was encrypted.
     * @param hexString- inputed hex string
     * @return - the equivalent binary string.
     */
    private static String HexTo8BitBinary(String hexString){
        String result = "";
        for (int i = 0; i < hexString.length(); i++) {
				int num = Integer.parseInt(hexString.charAt(i)+"");
				result = result.concat(Decimalto8BitBinary(num));
        }//for loop - i
        return result;
    }//end of static String HexTo8BitBinary(String)
}//end of class
