class OneTimePad {
	public static void main(String[] args) {
		Scanner txt = new Scanner(System.in), num = new Scanner(System.in);
      System.out.println("Enter your choice \n\t [1] Encrypt \n\t [2] Decrypt \n\t [X] Exit");
      int choice = num.nextInt();
      String plainText = "", encryptedText= "", key = "";
      switch(choice) {
			case 1:
				System.out.println("PLAIN TEXT:");
				plainText = txt.next();
				System.out.println("KEY :");
				key = txt.next();
				encryptedText = computeBitwiseXOR(plainText, key);
				System.out.println("\t INPUTED PLAIN TEXT : ");
				System.out.println("\t KEY : ");
				System.out.println("\t\t ENCRPYTED TEXT : " + encryptedText);
				break;

			case 2:
				System.out.println("ENCRYPTED TEXT : ");
				encryptedText = txt.next();
				System.out.println("KEY :");
				key = txt.next();
				plainText = computeBitwiseXOR(encryptedText, key);
				System.out.println("\t INPUTED ENCRYPTED TEXT : ");
				System.out.println("\t KEY : ");
				System.out.println("\t\t PLAIN TEXT : " + plainTexts);

			case 88:

         case 120:
         	System.exit(0);
            break;

         default:
            System.out.println("KINDLY ENTER A NUMBER (1-2) or (X) to EXIT ");
		}//switch statement
	}//end of void main(String[])

	private static String computeBitwiseXOR(String plainText, String key){
        String result = "";
        for(int i = 0, j = 0; i<plainText.length(); i++){
            int plainTextBit = plainText.charAt(i) - 48;
            int keyBit = key.charAt(j) - 48;
            result += (plainTextBit+keyBit)%2;
            j = ++j%key.length();
        }//for loop - i
        return result;
    }//end of String computeBitwiseXOR(String, String)
}//end of class
