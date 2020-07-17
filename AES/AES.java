package AES;
import java.util.Scanner;
public class AES {
    private static final Scanner num = new Scanner(System.in), txt = new Scanner(System.in);
    private static int noOfRounds;
    public static void main(String[] args){
        System.out.println("\nEnter your choice " + "\n\t [1] Encode \n\t [2] Decode \n\t [X] Exit");
        final char choice = num.next().toUpperCase().charAt(0);
        String plainText, encryptedText, seed;
        switch (choice) {
            // ENCRYPTION
            case '1':
                System.out.print("\t PLAIN TEXT : ");
                plainText = txt.nextLine().trim();
                System.out.print("\t KEY : ");
                seed = txt.nextLine().toUpperCase().trim();
                encryptedText = encrypt(plainText, seed);
                System.out.println("\n\t\t INPUTED PLAIN TEXT : " + plainText);
                System.out.println("\t\t KEY (seed for Secret Key): " + seed); 
                System.out.println("\t\t GENERATED ENCODING : " + encryptedText);
                break;

            // DECRYPTION
            case '2':
                System.out.print("\t ENCRYPTED TEXT : ");
                encryptedText = txt.nextLine();
                System.out.print("\t KEY : ");
                seed = txt.nextLine().toUpperCase().trim();
                plainText = decrypt(encryptedText, seed);
                System.out.println("\n\t\t INPUTED ENCRYPTED TEXT : " + encryptedText);
                System.out.println("\t\t GENERATED DECRYPTION : " + plainText);
                break;

            // EXIT
            case 'X':
                System.exit(0);
                break;

            default:
                System.out.println("KINDLY ENTER A NUMBER (1-2) or (X)");
        }// switch statement
        main(new String[] {});
    }//end of void main(String[])

    public static String encrypt(String plaintext, String seed){
        String result = "";
        System.out.println("\t SUBMIT YOUR AES VERSION: \n\t\t [1] AES-128 \n\t\t [2] AES-192 \n\t\t [3] AES-256");
        final char choice = txt.next().trim().charAt(0);
        
        //STEP 1 : INITIALIZE AND SET UP EVERYTHING WE NEED
        Keys K;
        switch(choice){
            case '1':
                noOfRounds = 10;
                K = new Keys(seed,128);
                break;

            case '2':
                noOfRounds = 12;
                K  = new Keys(seed, 192);
                break;

            default:
                noOfRounds = 14;
                K = new Keys(seed, 256);
        }// switch statement
        String initialKey, salt;
        int[][] matrix = new int[4][4];
        
        //STEP 2 : PUT THE NECESSARY DATA INTO OUR VARIABLES
        for (int i = 0; i < 16; i++)
            matrix[i/4][i%4] = plaintext.charAt(i);
        salt = K.getSalt(); initialKey = K.getInitialKeyState();

        //STEP 3 : USE THE DATA AND THE VARIABLES TO PERFORM THE ENCRYPTION
        AESRoundFunction.addRoundKey(K.generateKeyMatrix(0), matrix);
        for (int row[]:
                matrix) {
            for (int element :
                    row) {
                System.out.print(Integer.toHexString(element));
            }
        }
        for (int i = 1; i <= noOfRounds ; i++) {

        }
        return result;
        
    }//end of String encrypt(String, String)

    public static String decrypt(String encryptedText, String seed) {
        String result = "";
        return result;
    }//end of String decrypt(String, String)
}//end of class
