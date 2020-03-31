/**  
 * if main Key is fkmcpdxyehbigqrosazlutjnwv
 * and Number Key is 0249685731
 * and Space Numbers are 4, 3
 * then:

                     | 0  2  4  9  6  8  5  7  3  1   --> Numeric Key
                  ---|------------------------------
                     | f  k     m  c  p  d  x     y   --> Alphabets are filled into the matrix according to the order of the Alphabetic Key
                   4 | e  h  b  i  g  q  r  o  s  a
Space Numbers -->  3 | z  l  u  t  j  n  w  v  
 **/
import java.util.Scanner;
class Checkerboard
{
    static Scanner sc = new Scanner(System.in);
    static byte Nkey[];
    static byte space_nums[];
    static char board[][];
    public static void main()
    {
        initialize();
        sc = new Scanner(System.in);//reinitializing Scanner sc
        System.out.println("\nEncrypt(E) or Decrypt(D) Message?");
        switch(sc.next().toUpperCase().trim())//finding the choice enetered by the user
        {
            case "E":
            System.out.println(encrypt());
            break;

            case "D":
            System.out.println(decrypt());
            break;

            default:
            System.out.println("Invalid Input.");
            System.exit(0);
        }//switch case block
    }//main()

    private static void initialize()//initializes the value of Nkey, space_nums and boards[][]
    {
        sc = new Scanner(System.in);
        System.out.print("\nEnter Alphabet Key: ");
        String key = sc.nextLine().toLowerCase().trim().replaceAll("\\s", "");

        System.out.print("\nEnter Number Key: ");
        Nkey = convertIntoArray(sc.nextLine().trim().replaceAll("\\s", ""));        

        System.out.print("\nEnter Space Numbers: ");
        space_nums = convertIntoArray(sc.nextLine().trim().replaceAll("\\s", ""));

        board = new char[space_nums.length+1][10];//creating matrix to store characters

        int index = 0;//keeps track of the index of the String
        for(byte col = 0 ; col < 10 ; col++)//loop to fill first row, leaving spaces at space number positions
        {
            if(!belongsTo(Nkey[col], space_nums))
                board[0][col] =  key.charAt(index++);
        }//loop to run through the first row

        for(byte row = 1 ; row < board.length && index < key.length() ; row++)//loop to fill remaining positions in matrix
        {
            for(byte col = 0 ; col < 10 && index < key.length() ; col++)
            {
                board[row][col] = key.charAt(index++);
            }//inner loop
        }//outer loop
    }//initialize() method

    private static String encrypt()
    {       
        sc = new Scanner(System.in);//reinitializing Scanner sc
        System.out.println("\nEnter message to be encrypted: ");
        String plaintxt = sc.nextLine().trim().toLowerCase().replaceAll("\\s", "");

        int len = plaintxt.length();//stores the length of the plaintxt
        char ch;
        boolean flag;//indicates when the letter is found in the board so that the loops can be exited
        String ciphertxt = "";//stores the encrypted txt
        for(int inc = 0 ; inc < len ; inc++)//for loop to encrypt the plaintxt
        {
            flag = false;
            ch = plaintxt.charAt(inc);
            for(byte r = 0 ; r < board.length && !flag ; r++)//nested loop to traverse through board
                for(byte c = 0 ; c < 10 && !flag ; c++)
                    if(ch == board[r][c])
                    {
                        switch(r)
                        {
                            case 0:
                            ciphertxt = ciphertxt + Nkey[c];
                            break;

                            default:
                            ciphertxt = ciphertxt + space_nums[r-1] + Nkey[c];
                            break;
                        } 
                        flag = true;//exiting nested loop
                        break;
                    }
        }//encryption loop 
        return ciphertxt;
    }//encrypt() method

    private static String decrypt()
    {
        sc = new Scanner(System.in);//reinitializing Scanner sc
        System.out.println("\nEnter Ciphertext: ");
        String ciphertxt = sc.nextLine().trim();

        String plaintxt = "";
        byte num;
        int len = ciphertxt.length();
        for(int inc = 0 ; inc < len ; inc++)
        {
            num = Byte.parseByte("" + ciphertxt.charAt(inc));
            if(belongsTo(num, space_nums))
            {
                byte r;
                for(r = 0 ; r < space_nums.length ; r++)
                {
                    if(num == space_nums[r])
                    {
                        break;
                    }          
                }
                num = Byte.parseByte("" + ciphertxt.charAt(++inc));
                for(int c = 0 ; c < Nkey.length ; c++)
                {
                    if(num == Nkey[c])
                    {
                        plaintxt = plaintxt + board[r+1][c];
                        break;   
                    }
                }
            }
            else
            {
                for(int c = 0 ; c < Nkey.length ; c++)
                {
                    if(num == Nkey[c])
                    {
                        plaintxt = plaintxt + board[0][c];
                        break;   
                    }
                }
            }
        }
        return plaintxt;
    }//decrypt() method

    private static byte[] convertIntoArray(String str)//converts String data into an array
    {
        byte op[] = new byte[str.length()];
        for(byte inc = 0 ; inc < str.length() ; inc++)
            op[inc] = Byte.parseByte("" + str.charAt(inc));
        return op;
    }//convertIntoArray() method

    private static boolean belongsTo(byte val, byte arr[])//method to check if a byte value 'val' exists in the array arr[]
    {
        boolean flag = false;
        for(byte inc = 0 ; inc < arr.length ; inc++)
            if(val == arr[inc])
            { flag = true;
                break;}
        return flag;
    }//belongsTo() method
}//class Checkerboard
