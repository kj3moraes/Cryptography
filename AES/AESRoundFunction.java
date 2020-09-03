package ModernCiphers.AES;

public class AESRoundFunction {

    protected static void subBytes(int[][] matrix){
        SBox S = new SBox('e');
        for (int c = 0; c < matrix.length; c++)
            for (int j = 0; j < matrix[c].length; j++)
                matrix[c][j] = S.performSubstitution(matrix[c][j]);
    }//end of void subBytes(int[][])

    protected static void shiftRows(int[][] matrix){
        int[] tempArray;
        for (int n = 0; n < 4; n++) {
            tempArray = new int[4];
            for (int c = 0; c < 4; c++)
                tempArray[c] = matrix[n][(c+n)%4];
            matrix[n]= tempArray;
        }//for loop - n
    }// end of void shiftRows(int[][])

    protected static void mixColumns(int[][] matrix){
        int[] tempArray = new int[4];
        for (int c = 0; c < 4; c++) {
            tempArray[0] = dotProduct(2, matrix[0][c]) ^ dotProduct(3, matrix[1][c]) ^ matrix[2][c] ^ matrix[3][c];

            tempArray[1]= matrix[0][c] ^ dotProduct(2, matrix[1][c]) ^ dotProduct(3, matrix[2][c]) ^ matrix[3][c];

            tempArray[2] = matrix[0][c] ^ matrix[1][c] ^ dotProduct(2, matrix[2][c]) ^ dotProduct(3, matrix[3][c]);

            tempArray[3] = dotProduct(3, matrix[0][c]) ^ matrix[1][c] ^ matrix[2][c] ^ dotProduct(2, matrix[3][c]);

            for(int r = 0 ; r < 4 ; r++)
                matrix[r][c] = tempArray[r];
        }//for loop - c
    }// end of void mixColumns(int[][])

    private static int dotProduct(int mulValue, int number){
        int result = 0;
        switch (mulValue) {
            case 2:
                result = number << 1 >= 256 ? (number << 1) ^ 256 : number << 1;
                result = number >= 128 ? result ^ 27 : result;
                break;

            case 3:
                result = dotProduct(2, number) ^ number;
        }//switch statement
        return result;
    }//end of int dotProduct(int, int)

    protected static void addRoundKey(int[][] keyMatrix, int[][] matrix) {
        for (int c = 0; c < 4; c++){
            matrix[0][c] ^= keyMatrix[0][c];
            matrix[1][c] ^= keyMatrix[1][c];
            matrix[2][c] ^= keyMatrix[2][c];
            matrix[3][c] ^= keyMatrix[3][c];
        }//for loop - columns
    }// end of void addRoundKey(int[][])
}//end of class
