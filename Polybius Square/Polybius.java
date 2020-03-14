import java.util.Scanner;
public class Polybius {
    public static void main(String[] args) {
        Scanner txt = new Scanner(System.in), num = new Scanner(System.in);
        System.out.println("\nEnter your choice \n\t [1] Encrypt \n\t [2] Decrypt \n\t [X] Exit");
        char choice = num.next().toUpperCase().charAt(0);
        String plainText = "", encryptedText = "";
        switch (choice) {
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
                System.out.println("\t\t SHIFT : ");
                System.out.println("\t\t GENERATED DECRYPTION : " +  plainText);
                break;

            //EXIT
            case 'X':
                System.exit(0);
                break;

            default:
                System.out.println("KINDLY ENTER A NUMBER (1-2) or (X) to EXIT ");
        }//switch statement
        main(new String[]{});
    }//end of void main(String[])

    private static String encrypt(String plainText) {
        String result = "";
        for (int i = 0; i < plainText.length(); i++) {
            int letterNumber;
            if(Character.isLowerCase(plainText.charAt(i))) {
                if(plainText.charAt(i)>='j')
                    letterNumber = plainText.charAt(i) - 98;
                else
                    letterNumber = plainText.charAt(i) - 97;
            }//if statement - lowercase
            else if(Character.isUpperCase(plainText.charAt(i))) {
                if(plainText.charAt(i)>='J')
                    letterNumber = plainText.charAt(i) - 66;
                else
                    letterNumber = plainText.charAt(i) - 65;
            }//if statement - uppercase
            else {
				result+=plainText.charAt(i);
				continue;
			}//else statement
            int rowNumber = letterNumber/5 + 1;
            int columnNumber = letterNumber%5 + 1;
            result += (rowNumber + "") + (columnNumber+"") + " ";
        }//for loop - i
        return result;
    }//end of String encrypt(String)

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
