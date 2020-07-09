package AES;
public class AESRoundFunction {
    private int keySize;

    public AESRoundFunction(){
        keySize = 256;
    }//end of public RoundFunction()

    public AESRoundFunction(int keySize){
        this.keySize = keySize;
    }//end of public RoundFunction(int)

    protected void subBytes(int[][] matrix){
        SBox S = new SBox('e');
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[i].length; j++)
                matrix[i][j] = S.performSubstitution(matrix[i][j]);
    }//end of void subBytes(int[][])

    protected void shiftRows(int[][] matrix){
        int[] tempArray;
        for (int n = 0; n < 4; n++) {
            tempArray = new int[4];
            for (int i = 0; i < 4; i++)
                tempArray[i] = matrix[n][(i+n)%4];
            matrix[n]= tempArray;
        }//for loop - n
    }// end of void shiftRows(int[][])

    protected void mixColumns(){
    }// end of void mixColumns(int[][])

    protected void addRoundKey(int[][] matrix) {

    }// end of void addRoundKey(int[][])

    private int dotProduct(int a, int b){
        int result = 0;
        switch (a){
            case 2:
                result = b<<1>=256?(b<<1)^256:b<<1;
                result = b>=128?result^27:result;
                break;

            case 3:
                result = dotProduct(2,b)^b;
        }//switch statement
        return result;
    }//end of int dotProduct(int, int)
}//end of class