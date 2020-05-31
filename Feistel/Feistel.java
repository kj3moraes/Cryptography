package Feistel;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Feistel {
    private static final Scanner txt = new Scanner(System.in), num = new Scanner(System.in);
    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println("\nEnter your choice " + "\n\t [1] Encode \n\t [2] Decode \n\t [X] Exit");
        char choice = num.next().toUpperCase().charAt(0);
        String plainText, encodedText, seed;
        int noOfRounds = 1;
        switch (choice) {
            // ENCODING
            case '1':
                System.out.print("\t PLAIN TEXT : ");
                plainText = txt.nextLine().trim();
                System.out.print("\t KEY : ");
                seed = txt.nextLine().toUpperCase().trim();
                System.out.print("\t NO. OF ROUNDS : ");
                noOfRounds = num.nextInt();
                encodedText = encrypt(plainText, seed, noOfRounds);
                System.out.println("\n\t\t INPUTED PLAIN TEXT : " + plainText);
                System.out.println("\t\t KEY (seed for Secret Key): " + seed);
                System.out.println("\t\t NO OF ROUNDS : " + noOfRounds);
                System.out.println("\t\t GENERATED ENCODING : " + encodedText);
                break;

            // DECODING
            case '2':
                System.out.print("\t ENCRYPTED TEXT : ");
                encodedText = txt.nextLine();
                plainText = decrypt(encodedText, noOfRounds);
                System.out.println("\n\t\t INPUTED ENCRYPTED TEXT : " + encodedText);
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
    }// end of void main(String[])

    /**
     * FEISTEL - ENCODE
     * This function takes 3 parameters - plaintext, seed and the no. of rounds and
     *      outputs the 'encoded' text. (The reason we use encoded in this context and
     *      not encrypted is because our Round Function is HMAC algorithm which is an
     *      uninvertible function that only 'tags' the message and does not provide
     *      confidentiality).
     *      Each round the RHS of the generated text is MACed and XORed with LHS. This
     *      is then assigned to the RHS for the next round. The LHS of the next round is
     *      only the RHS of the previous round.
     *
     * @param plainText - the user input that is to be tagged
     * @param seed - the user input used to generate a secret key
     * @param noOfRounds - the no. of rounds of the Network
     * @return - the resultant
     * @throws NoSuchAlgorithmException - HASH ALGORITHM IS INVALID
     */
    private static String encrypt(String plainText, String seed, int noOfRounds) throws NoSuchAlgorithmException {
        String result = "";

        //STEP 1 : CHOOSING THE HASH FUNCTION
        System.out.println("\t\t HMAC will be used as the round function. "
                + "\n\t\t Choose your preferred HASH FUNCTION (default set to SHA256):");
        System.out.println("\t\t\t [1] SHA-1 \n\t\t\t [2] SHA256\n\t\t\t [3] SHA384");
        System.out.println("\t\t\t [4] SHA512\n\t\t\t [5] MD5");
        char hashFunction = txt.next().trim().charAt(0);
        RoundFunction F;
        int ptSizeCap;
        switch(hashFunction){
            case '1' :
                F = new RoundFunction("HmacSHA1");
                ptSizeCap = 40;
                break;

            case '3':
                F = new RoundFunction("HmacSHA384");
                ptSizeCap = 48;
                break;

            case '4':
                F = new RoundFunction("HmacSHA512");
                ptSizeCap = 128;
                break;

            case '5':
                F = new RoundFunction("HmacMD5");
                ptSizeCap = 32;
                break;
                
            default:
                F = new RoundFunction();
                ptSizeCap = 64;
                break;
        }//switch statement

        //STEP 2 : TRUNCATING/PAD THE INPUT TO DESIRED INPUT SIZE
        if (plainText.length()>ptSizeCap)
            plainText = plainText.substring(0,ptSizeCap);
        else{
            String padNumber = (ptSizeCap-plainText.length()+"").charAt(0)+"";
            for (int j = plainText.length(); j < ptSizeCap; j++)
                plainText+=padNumber;
        }//else statement
        System.out.println(plainText);
        System.out.println(plainText.length());

        //STEP 3 : RUNNING THE FEISTEL NETWORK UPTO 'N' ROUNDS
        byte[] RHStemp;
        byte[] RHS  = plainText.substring(0,ptSizeCap/2).getBytes();
        byte[] LHS = plainText.substring(ptSizeCap/2).getBytes();
        // The RHStemp variable simply stores the value of the RHS before the MAC so it
        // can later be assigned to LHS

        for (int i = 1; i <= noOfRounds; i++) {
            try {
                RHStemp = RHS;
//                for (int j = 0; j < RHS.length; j++) {
//                    System.out.println("RHS ELEMENT : " + RHS[j] + " RHS TEMP ELEMENT" + RHStemp[j]);
//                }
                RHS = F.generateMACTag(plainText.getBytes(),"ROUND NO."+i+""+seed);
                for (int j = 0; j < LHS.length; j++) {
                    RHS[j] = (byte) (RHS[j]^LHS[j]);
                    LHS[j] = RHStemp[j];
                }//for loop - i
//                System.out.println("\n\n\n AFTER MACING \n\n\n ");
//                for (int j = 0; j < RHS.length; j++) {
//                    System.out.println("RHS ELEMENT : " + RHS[j] + " RHS TEMP ELEMENT : " + RHStemp[j] + "  LHS ELEMENT : " + LHS[j]);
//                }
                //System.out.println(HexBin.encode(RHS) +  " LENGTH : " + HexBin.encode(RHS).length());
            } catch (InvalidKeyException e) {
                System.out.println("INVALID KEY - TRY AGAIN");
                main(new String[] {});
            }//try-catch block
//            System.out.println("\n\n\n\n --- END OF ROUND --- \n\n\n\n");
        }//for loop - i
        result = F.ByteToHex(RHS).concat(F.ByteToHex(LHS));
        return result;
    }// end of String encrypt(String)

    private static String decrypt(String encryptedText, int noOfRounds) {   
        String result = "";

        return result;
    }// end of String decrypt(String)
}// end of class
