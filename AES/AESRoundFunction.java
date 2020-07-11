package AES;
public class AESRoundFunction {
    private int keySize;
    protected String seed;

    public AESRoundFunction(String seed) {
        this.seed = seed;
        keySize = 256;
    }// end of public RoundFunction()

    public AESRoundFunction(int keySize, String seed) {
        this.seed = seed;
        this.keySize = keySize;
    }// end of public RoundFunction(int)


    protected void subBytes(int[][] matrix) {
        SBox S = new SBox('e');
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[i].length; j++)
                matrix[i][j] = S.performSubstitution(matrix[i][j]);
    }// end of void subBytes(int[][])

    protected void shiftRows(int[][] matrix){
        int[] tempArray;
        for (int n = 0; n < 4; n++) {
            tempArray = new int[4];
            for (int i = 0; i < 4; i++)
                tempArray[i] = matrix[n][(i+n)%4];
            matrix[n]= tempArray;
        }//for loop - n
    }// end of void shiftRows(int[][])

    protected void mixColumns(int[][] matrix) {
        for (int i = 0; i < 4; i++) {
            matrix[0][i] = dotProduct(2, matrix[0][i]) ^ dotProduct(3, matrix[1][i]) ^ matrix[2][i] ^ matrix[3][i];

            matrix[1][i] = matrix[0][i] ^ dotProduct(2, matrix[1][i]) ^ dotProduct(3, matrix[2][i]) ^ matrix[3][i];

            matrix[2][i] = matrix[0][i] ^ matrix[1][i] ^ dotProduct(2, matrix[2][i]) ^ dotProduct(3, matrix[3][i]);

            matrix[3][i] = dotProduct(3, matrix[0][i]) ^ matrix[1][i] ^ matrix[2][i] ^ dotProduct(2, matrix[3][i]);

        }
    }// end of void mixColumns(int[][])

    private int dotProduct(int a, int b) {
        int result = 0;
        switch (a) {
            case 2:
                result = b << 1 >= 256 ? (b << 1) ^ 256 : b << 1;
                result = b >= 128 ? result ^ 27 : result;
                break;

            case 3:
                result = dotProduct(2, b) ^ b;
        }// switch statement
        return result;
    }// end of int dotProduct(int, int)

    protected void addRoundKey(int keyMatrix[][], int[][] matrix) {
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                matrix[i][j] ^= keyMatrix[i][j];
    }// end of void addRoundKey(int[][])
}// end of class
