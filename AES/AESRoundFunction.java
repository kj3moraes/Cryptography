import java.util.Scanner;
public class AESRoundFunction {
    private static final Scanner num = new Scanner(System.in), txt = new Scanner(System.in);
    private int keySize;
    private int[][] matrix = new int[4][4];
    public AESRoundFunction(){
        keySize = 256;
    }//end of public RoundFunction()

    public AESRoundFunction(int keySize){
        this.keySize = keySize;
    }//end of public RoundFunction(int)

    protected void subBytes(int[][] matrix){

    }//end of void subBytes(int[][])

    protected void shiftRows(int[][] matrix){
        int[] tempArray = new int[4];
        for (int n = 0; n < 4; n++) {
            for (int i = 0; i < 4; i++) tempArray[i] = matrix[n][(i+n)%n];
            for (int j = 0; j < 4; j++) matrix[n][j] = tempArray[j];
        }//for loop - n
    }// end of void shiftRows(int[][])

    protected void mixColumns(int[][] matrix){
        int[] tempArray = new int[4];
        int[][] multMatrix = { {2,3,1,1},
                               {1,2,3,1},
                               {1,1,2,3},
                               {3,1,1,2} };
        int multValue;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; i++) 
                tempArray[i] += multMatrix[i][j]*matrix[j][i]; 
            for (int k = 0; k < 4; k++) 
                matrix[i][k] = tempArray[k];          
        }//for loop - i
    }// end of void mixColumns(int[][])

    protected void addRoundKey(int[][] matrix) {

    }// end of void addRoundKey(int[][])
}//end of class