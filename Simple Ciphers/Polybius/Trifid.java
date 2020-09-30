package Polybius;
import java.util.Scanner;
class Trifid {
    public static void main(String[] args) {
        final Scanner txt = new Scanner(System.in), num = new Scanner(System.in);
        System.out.println("Enter your choice \n\t [1] Encrypt \n\t [2] Decrypt \n\t [X] Exit");
        final char choice = num.next().toUpperCase().charAt(0);
        String plainText, encryptedText, key;
        int groupingSize;
        switch (choice) {
            //ENCRYPTION
            case '1':
                System.out.print("\t PLAIN TEXT : ");
                plainText = txt.nextLine().trim ().toUpperCase();
                System.out.print("\t GROUPING SIZE : ");
                groupingSize = num.nextInt();
                if (groupingSize == 0 || groupingSize > 10)
                    groupingSize = 7;
                System.out.print("\t KEY : ");
                key = txt.nextLine().toUpperCase().replaceAll(" ", "");
                encryptedText = encrypt(plainText, groupingSize, key);
                System.out.println("\n\t\t INPUTED PLAIN TEXT : " + plainText);
                System.out.println("\t\t GROUPED IN : " + groupingSize);
                System.out.println("\t\t KEY : " + key);
                System.out.println("\t\t GENERATED ENCRYPTION : " + encryptedText);
                break;

            //DECRYPTION
            case '2':
                System.out.print("\t ENCRYPTED TEXT : ");
                encryptedText = txt.nextLine();
                System.out.print("\t GROUPING SIZE : ");
                groupingSize = num.nextInt();
                System.out.print("\t KEY : ");
                key = num.next().toUpperCase().trim();
                plainText = decrypt(encryptedText, groupingSize, key);
                System.out.println("\n\t\t INPUTED ENCRYPTED TEXT : " + encryptedText);
                System.out.println("\t\t GROUPED IN : " + groupingSize);
                System.out.println("\t\t KEY : " + key);
                System.out.println("\t\t GENERATED DECRYPTION : " + plainText);
                break;

            // EXIT
            case 'X':
                System.exit(0);
                break;

            default:
                System.out.println("KINDLY ENTER A NUMBER (1-2) or (X)");
        }//switch statement(int)
        main(new String[]{});
    }//end of void main(String[])

    /**
     * TRIFID - ENCRYPT
     *      This function takes 3 - parameters - plain text, size of the groups and key for
     *      encryption. The encryption scheme occurs in 3 steps. However, Step 1 can be
     *      omitted if the user enters a key with unique letters.
     *      Step 1 : Generating a unique key 27 characters long. 'Unique' means no 
     *              repeated characters. The 27th character is a '+' symbol.
     *      Step 2 : Converting the plain text into the trifid number equivalent.
     *               We will call these the 'encodings'.
     *      Step 3 : By partitioning the encodings into groups whose size is 
     *               determined by the 'groupingSize' variable. The encodings 
     *               are then grouped in 3s and converted back into letters.
     *               
     * @param plainText - the user input that is to be encrypted
     * @param groupingSize - the size of the group that will act as blocks for S3
     * @param key - the key that will encrypt the numbers
     * @return the encrypted text
     */
    private static String encrypt(String plainText, int groupingSize, String key){
        String result = "", alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        key = key.toUpperCase().trim();

        // S1 : GENERATE A UNIQUE KEY.
        for (int i = 0; i < key.length(); i++) {
            char keyCharacter = key.charAt(i);
            alphabet = alphabet.replaceAll(keyCharacter + "","");
            key = key.substring(0,i+1)+key.substring(i).replaceAll(keyCharacter+"","");
        }//for loop - i
        key += alphabet+"+";

        // S2 : CONVERT THE PLAINTEXT INTO THEIR RESPECTIVE ENCODED EQUIVALENT.
        String coordinateTranscribe = "", rowTranscribe = "", columnTranscribe = "";
        for (int j = 0; j < plainText.length(); j++) {
            if(plainText.charAt(j)<65 || plainText.charAt(j)>90) continue;
            int keyNumber =  key.indexOf(plainText.charAt(j));
            coordinateTranscribe += keyNumber/9 + 1;
            rowTranscribe += (keyNumber%9)/3 + 1 ;
            columnTranscribe += keyNumber%3 + 1;
        }//for loop - j
        coordinateTranscribe += rowTranscribe + columnTranscribe + " ";

        // S3: CONVERSION OF NUMBER ENCODING BACK INTO LETTER THROUGH GROUPING.
        int lineLength = rowTranscribe.length();
        for (int m = 0; m < lineLength/groupingSize; m++) {
            String letterBlock = coordinateTranscribe.substring(m*groupingSize, groupingSize*(m+1))+
                    coordinateTranscribe.substring(lineLength+m*groupingSize, lineLength+groupingSize*(m+1))+
                    coordinateTranscribe.substring(2*lineLength+m*groupingSize, 2*lineLength+groupingSize*(m+1))+" ";
            for (int n = 0; n < letterBlock.length() - 1; n+=3) {
                int encryptingNumber = Integer.parseInt(letterBlock.substring(n,n+3));
                int letterTranscribe = (encryptingNumber/100-1)*9 +
                        ((encryptingNumber/10)%10-1)*3 + encryptingNumber%10- 1;
                result += n%groupingSize == 0?" " + key.charAt(letterTranscribe) : key.charAt(letterTranscribe);
            }//for loop - n
        }//for loop - m
        return result;
    }//end of String encrypt(String, int, String)
    
    private static String decrypt(String encryptedText, int groupingSize, String key) {
        String result = "";
        return result;
    }// end of String decrypt(String, String)
}//end of class
