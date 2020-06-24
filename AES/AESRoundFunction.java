import java.util.Scanner;
public class AESRoundFunction {
    private static final Scanner num = new Scanner(System.in), txt = new Scanner(System.in);
    private int keySize;

    public AESRoundFunction(){
        keySize = 256;
    }//end of public RoundFunction()

    public AESRoundFunction(int keySize){
        this.keySize = keySize;
    }//end of public RoundFunction(int)

    protected void subBytes(int[][] matrix){

    }//end of void subBytes(int[][])

    protected void shiftRows(int[][] matrix){

    }// end of void shiftRows(int[][])

    protected void mixColumns(int[][] matrix){

    }// end of void mixColumns(int[][])

    protected void addRoundKey(int[][] matrix) {

    }// end of void addRoundKey(int[][])
}//end of class