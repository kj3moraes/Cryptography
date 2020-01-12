import java.util.Scanner;
class CesearCipher {
  public static void main(String[] args){
     Scanner txt = new Scanner(System.in), num = new Scanner(System.in);
     System.out.println("Enter your choice \n\t
        [1] Encrypt \n\t [2] Decrypt \n\t [X] Exit");
     int choice = num.nextInt();
     String plainText, encryptedText;
     int shift;
     switch (choice) {
       case 1:
          System.out.println("Enter the text that you want encrypted");
          plainText = txt.nextLine();
          System.out.print("\t How much do you want the shift to be? : ");
          shift = num.nextInt();
          

     }
  }//end of void main(String[])
}//end of class
