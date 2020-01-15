import java.util.Scanner;
class CaesarCipher {
  public static void main(String[] args){
     Scanner txt = new Scanner(System.in), num = new Scanner(System.in);
     System.out.println("Enter your choice \n\t [1] Encrypt \n\t [2] Decrypt \n\t [X] Exit");
     int choice = num.nextInt();
     String plainText = "", encryptedText = "";
     int shift;
	  System.out.print("PLAIN TEXT :");
     switch (choice) {
       case 1:
		 	System.out.print("PLAIN TEXT :");
			plainText = txt.nextLine();
			System.out.print("\t SHIFT : ");
			shift = num.nextInt();
			for (int i = 0;i<plainText.length();i++) {
				 if(plainText.charAt(i)>=65 && plainText.charAt(i)<=90)
					encryptedText = encryptedText.concat((char)((plainText.charAt(i)-65+shift)%25+65)+"");
				 else if(plainText.charAt(i)>=97 && plainText.charAt(i)<=122)
					encryptedText = encryptedText.concat((char)((plainText.charAt(i)-97+shift)%25+97)+"");
			}//for loop - i
			break;

       case 2:
		 	System.out.print("ENCRYPTED TEXT : ");


     }//switch statement(int)
  }//end of void main(String[])
}//end of class
