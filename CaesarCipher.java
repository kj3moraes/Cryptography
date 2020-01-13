import java.util.Scanner;
class CesearCipher {
  public static void main(String[] args){
     Scanner txt = new Scanner(System.in), num = new Scanner(System.in);
     System.out.println("Enter your choice \n\t
        [1] Encrypt \n\t [2] Decrypt \n\t [X] Exit");
     int choice = num.nextInt();
     String plainText = "", encryptedText = "";
     int shift;
     switch (choice) {
       case 1:
		 	System.out.println("Enter the text that you want encrypted");
			plainText = txt.nextLine();
			System.out.print("\t How much do you want to shift? : ");
			shift = num.nextInt();
			for (int i = 0;i<plainText.length();i++) {
				 if(plainText.charAt(i)>=65 && plainText.charAt<=90)
					encryptedText =
					encryptedText.concat((char)(plainText.charAt(i)-65+shift)%25+65);
				 else if(plainText.charAt(i)>=97 && plainText.charAt<=122))
					encryptedText =
					encryptedText.concat((char)(plainText.charAt(i)-97+shift)%25+97);
			}//for loop - i
			break;

       case 2:
		 	System.out.println("Enter the text that you want decrypted");
			encryptedText = txt.nextLine();
			System.out.print("\t How much was the text shifted by? : ");
			shift = num.nextInt();
				for (int i = 0;i<encryptedText.length();i++) {
					 if(encryptedText.charAt(i)>=65 && encryptedText.charAt<=90)
						plainText =
						plainText.concat((char)(plainText.charAt(i)-65)%25+65);
					 else if(encryptedText.charAt(i)>=97 && encryptedText.charAt<=122))
						plainText =
						plainText.concat((char)(plainText.charAt(i)-97)%25+97);
				}//for loop - i
				break;

     }//switch statement(int)
  }//end of void main(String[])
}//end of class
