import java.util.Arrays;
import java.util.Scanner;
import javax.crypto.spec.SecretKeySpec;
public class AESRoundFunction {
    //private static final Scanner num = new Scanner(System.in), txt = new Scanner(System.in);
    private int keySize;
    private String key;
    private int[][] matrix = new int[4][4];

    public AESRoundFunction(int matrix[][]){
        this.matrix = matrix;
        keySize = 256;
    }//end of public RoundFunction()

    public AESRoundFunction(int matrix[][], int keySize) {
        this.matrix = matrix;
        this.keySize = keySize;
    }// end of public RoundFunction(int)

    private void generateSecretKey(String seed) {
        SecretKeySpec keyGenerator = new SecretKeySpec(seed.getBytes(),"AES_".concat(keySize+""));
        key = Arrays.toString(keyGenerator.getEncoded());
    }//end of void generateSecretKey(String)

    protected void subBytes(int[][] matrix){
        SBox S = new SBox('e');
    }//end of void subBytes(int[][])

    protected void shiftRows(int[][] matrix) {
        int[] tempArray;
        for (int n = 0; n < 4; n++) {
            tempArray = new int[4];
            for (int i = 0; i < 4; i++)
                tempArray[i] = matrix[n][(i + n) % 4];
            matrix[n] = tempArray;
        } // for loop - n
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

    private void keyGenerate(String seed){
        
    }//end of void keyGenerate(String)
}//end of class