package Trifid;

import java.util.Scanner;

class Trifid extends Polybius {
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
                plainText = txt.nextLine().trim().toUpperCase();
                System.out.print("\t GROUPING SIZE : ");
                groupingSize = num.nextInt();
                System.out.print("\t KEY/SEED (leave blank for default) : ");
                key = txt.nextLine().toUpperCase().trim();
                key = generateCustomKey(key.replaceAll(" ", ""),
                        "ABCDEFGHIJKLMNOPQRSTUVWXYZ") + "+";

                if (groupingSize == 0 || groupingSize > 10) groupingSize = 5;
                encryptedText = encrypt(plainText, groupingSize, key);
                System.out.println("\n\t\t INPUTED PLAIN TEXT : " + plainText);
                System.out.println("\t\t GROUPED IN : " + groupingSize);
                System.out.println("\t\t KEY/SEED : " + key);
                System.out.println("\t\t GENERATED ENCRYPTION : " + encryptedText);
                break;

            //DECRYPTION
            case '2':
                System.out.print("\t ENCRYPTED TEXT : ");
                encryptedText = txt.nextLine().toUpperCase().trim();
                System.out.print("\t GROUPING SIZE : ");
                groupingSize = num.nextInt();
                System.out.print("\t KEY/SEED (same one used for encryption : if default, leave blank) : ");
                key = txt.nextLine().toUpperCase();
                key = generateCustomKey(key.replaceAll(" ", ""),
                        "ABCDEFGHIJKLMNOPQRSTUVWXYZ") + "+";

                plainText = decrypt(encryptedText, groupingSize, key);
                System.out.println("\n\t\t INPUTED ENCRYPTED TEXT : " + encryptedText);
                System.out.println("\t\t GROUPED IN : " + groupingSize);
                System.out.println("\t\t KEY/SEED : " + key);
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
     * This function takes 3 - parameters - plain text, size of the groups and key for
     * encryption. It outputs the encryption of the plaintext using the Trifid encryption
     * algorithm.
     * @param plainText - the user input that is to be encrypted
     * @param groupingSize - the size of the group that will act as blocks
     * @param key - the key that will encrypt the numbers
     * @return the encrypted text
     */
    private static String encrypt(String plainText, int groupingSize, String key){
        String result = "";

        // S1 : CONVERT THE PLAINTEXT INTO THEIR RESPECTIVE ENCODED EQUIVALENT.
        String squareNum = "", rowNum = "", columnNum = "";
        for (int j = 0; j < plainText.length(); j++) {
            if (plainText.charAt(j) < 65 || plainText.charAt(j) > 90) continue;
            int coordinates =  getTrigramCoordinates(plainText.charAt(j), key);
            squareNum += coordinates / 100;
            rowNum += (coordinates / 10) % 10;
            columnNum += coordinates % 10;
        }//for loop - j

        // S2 : NORMALIZE THE COORDINATES
        int toGo = (groupingSize - plainText.length() % groupingSize) % groupingSize;
        for (int i = 1; i <= toGo; i++) {
            squareNum += " ";
            rowNum += " ";
            columnNum += " ";
        }
        squareNum += rowNum + columnNum + " ";

        // S3: CONVERSION OF NUMBER ENCODING BACK INTO LETTER THROUGH GROUPING.
        int lineLength = rowNum.length();
        for (int m = 0; m <  lineLength/groupingSize; m++) {
            String letterBlock = squareNum.substring(m*groupingSize, groupingSize*(m+1)) +
                    squareNum.substring(lineLength+m*groupingSize, lineLength+groupingSize*(m+1)) +
                    squareNum.substring(2*lineLength+m*groupingSize, 2*lineLength+groupingSize*(m+1)) + " ";
            letterBlock = letterBlock.replaceAll(" ", "");

            for (int n = 0; n < letterBlock.length() - 1; n += 3) {
                int encryptingNumber = Integer.parseInt(letterBlock.substring(n,n+3));
                int letterTranscribe = (encryptingNumber / 100 - 1)*9 +
                        ((encryptingNumber / 10)%10 - 1)*3 + encryptingNumber%10 - 1;
                result += n % groupingSize == 0 ? " " + key.charAt(letterTranscribe) : key.charAt(letterTranscribe);
            }//for loop - n
        }//for loop - m
        return result;
    }//end of String encrypt(String, int, String)

    /**
     * TRIFID - DECRYPT
     * This function takes 3 - parameters - encrypted text, size of the groups and key for
     * encryption. It outputs the decryption of the cipher text using the Trifid decryption
     * algorithm.
     * @param encryptedText - the user input that is to be decrypted
     * @param groupingSize - the size of the group that will act as blocks
     * @param key - the key that will decrypt the numbers
     * @return the encrypted text
     */
    private static String decrypt(String encryptedText, int groupingSize, String key) {
        String result = "";
        String[] trigramCoord = {"", "", ""};

        // S1 : NORMALIZE THE ENCRYPTED TEXT WITH SPACES
        int toGO = (groupingSize - (encryptedText.length() - encryptedText.lastIndexOf(' ')) + 1) % groupingSize;
        for (int i = 1; i <= toGO ; i++)
            encryptedText += " ";
        encryptedText += " ";

        // S2 : CONVERT THE CIPHER TEXT LETTER INTO THE 3 COORDINATES GROUP WISE
        for (int i = 0; i < encryptedText.length() ; i += groupingSize + 1) {
            String extract = encryptedText.substring(i, i + groupingSize).replaceAll(" ", "");
            String coordinateConvert = "";
            for (int j = 0; j < extract.length(); j++)
                coordinateConvert += getTrigramCoordinates(extract.charAt(j), key);

            int lengthOfConvert = coordinateConvert.length();
            trigramCoord[0] += coordinateConvert.substring(0, lengthOfConvert / 3);
            trigramCoord[1] += coordinateConvert.substring(lengthOfConvert / 3, 2 * lengthOfConvert / 3);
            trigramCoord[2] += coordinateConvert.substring(2 * lengthOfConvert / 3);
        }// for loop - i

        // S3 : USE THE COORDINATES TO EXTRACT THE LETTERS
        for (int i = 0; i < trigramCoord[0].length(); i++) {
            String coordinate = trigramCoord[0].charAt(i) +"" + trigramCoord[1].charAt(i) + "" +
                    trigramCoord[2].charAt(i);
            int position = Integer.parseInt(coordinate);

            int squareNum = position/100;
            int rowNum = (position / 10) % 10;
            int columnNum = position % 10;
            result += key.charAt(--squareNum*9 + --rowNum*3 + --columnNum);
        }// for loop - i
        return result;
    }//end of String decrypt(String, String)

    /**
     * TRIFID - GET TRIGRAM COORDINATES
     * This function takes 2 parameters - character and a key. It outputs the coordinates
     * of the character in the key using the Trigram scheme employed in the Trifid encryption
     * algorithm. A requirement is that the character must be located SOMEWHERE in the key
     *
     * @param character - the character whose coordinates need to be found
     * @param key - the key where the character is located.
     * @return the trigram coordinates of the character text
     */
    private static int getTrigramCoordinates (char character, String key) {
        int keyNumber =  key.indexOf(character);
        int squareLayer =   keyNumber / 9 + 1;
        int rowNumber = (keyNumber%9) / 3 + 1 ;
        int columnNumber = keyNumber%3 + 1;
        return 100 * squareLayer + 10 * rowNumber + columnNumber;
    }//end of int getTrigramCoordinates(char, String)
}//end of class