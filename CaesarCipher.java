import java.util.Scanner;
class CesearCipher {
  public static void main(String[] args){
     Scanner txt = new Scanner(System.in), num = new Scanner(System.in);
     System.out.println("Enter your choice \n\t
        [1] Encrypt \n\t [2] Decrypt \n\t [X] Exit");
     int choice = num.nextInt();
     switch (choice) {
       case 1:
          System.out.println("Enter the text that you want encrypted");
          String plainText = txt.nextLine().

     }
  }//end of void main(String[])
}//end of class
