import java.util.Scanner;
class OneTimePad {
	public static void main(String[] args) {
		final Scanner txt = new Scanner(System.in), num = new Scanner(System.in);
      	System.out.println("Enter your choice \n\t [1] Encrypt \n\t [2] Decrypt \n\t [X] Exit");
		final char choice = num.next().toUpperCase().charAt(0);
		String plainText = "", encryptedText= "", key = "";
		switch(choice) {
			case '1':
				System.out.println("PLAIN TEXT :");
				plainText = txt.nextLine().trim();
				System.out.println("KEY (to acheive Perfect Secrecy the key must be as long the plaintext) :");
				key = txt.nextLine().trim();
				encryptedText = computeBitwiseXOR(plainText, key);
				System.out.println("\t INPUTED PLAIN TEXT : ");
				System.out.println("\t KEY : ");
				System.out.println("\t\t ENCRPYTED TEXT : " + encryptedText);
				break;

			case '2':
				System.out.println("ENCRYPTED TEXT : ");
				encryptedText = txt.next();
				System.out.println("KEY :");
				key = txt.next();
				plainText = computeBitwiseXOR(encryptedText, key);
				System.out.println("\t INPUTED ENCRYPTED TEXT : ");
				System.out.println("\t KEY : ");
				System.out.println("\t\t PLAIN TEXT : " + plainText);
				break;

			case 'X':
				System.exit(0);
				break;

			default:
					System.out.println("KINDLY ENTER A NUMBER (1-2) or (X) to EXIT ");
		}//switch statement
	}// end of void main(String[])

	private static String computeBitwiseXOR(String plainText, String key) {
		String result = "";
		byte[] plainTextStream = plainText.getBytes();
		byte[] keyStream = key.getBytes();
		// int smallerLength = Math.min(plainTextStream.length, keyStream.length);
		byte b = keyStream[8];
		System.out.println("b = " + b);
		return result;
	}// end of String computeBitwiseXOR(String, String)
}// end of class
