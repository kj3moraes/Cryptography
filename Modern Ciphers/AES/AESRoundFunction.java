package AES;

public class AESRoundFunction {
    private static SBox S = new SBox();

    protected static void subBytes(int[][] matrix){        
        for (int r = 0; r < matrix.length; r++)
            for (int c = 0; c < matrix[r].length; c++)
                matrix[r][c] = S.performSubstitution(matrix[r][c], 'e');
    }//end of void subBytes(int[][])

    protected static void shiftRows(int[][] matrix){
        int[] tempArray;
        for (int r = 0; r < 4; r++) {
            tempArray = new int[4];
            for (int c = 0; c < 4; c++)
                tempArray[c] = matrix[r][(c+r)%4];
            matrix[r] = tempArray;
        }//for loop - n
    }// end of void shiftRows(int[][])

    protected static void mixColumns(int[][] matrix){
        int[] tempArray = new int[4];
        for (int c = 0; c < 4; c++) {
            tempArray[0] = dotProduct(2, matrix[0][c]) ^ dotProduct(3, matrix[1][c]) ^ matrix[2][c] ^ matrix[3][c];

            tempArray[1] = matrix[0][c] ^ dotProduct(2, matrix[1][c]) ^ dotProduct(3, matrix[2][c]) ^ matrix[3][c];

            tempArray[2] = matrix[0][c] ^ matrix[1][c] ^ dotProduct(2, matrix[2][c]) ^ dotProduct(3, matrix[3][c]);

            tempArray[3] = dotProduct(3, matrix[0][c]) ^ matrix[1][c] ^ matrix[2][c] ^ dotProduct(2, matrix[3][c]);

            for(int r = 0 ; r < 4 ; r++)
                matrix[r][c] = tempArray[r];
        }//for loop - c
    }// end of void mixColumns(int[][])

    protected static void addRoundKey(int[][] keyMatrix, int[][] matrix) {
        for (int c = 0; c < 4; c++){
            matrix[0][c] ^= keyMatrix[0][c];
            matrix[1][c] ^= keyMatrix[1][c];
            matrix[2][c] ^= keyMatrix[2][c];
            matrix[3][c] ^= keyMatrix[3][c];
        }//for loop - columns
    }// end of void addRoundKey(int[][])

    protected static void invSubBytes(int[][] matrix){
        for (int r = 0; r < matrix.length; r++)
            for (int c = 0 ; c < matrix[r].length ; c++)
                matrix[r][c] = S.performSubstitution(matrix[r][c], 'd');
    }// end of void invSubBytes(int[][])

    protected static void invShiftRows(int[][] matrix){
        int[] tempArray;
        for (int r = 0; r < 4; r++) {
            tempArray = new int[4];
            for (int c = 0; c < 4; c++)
                tempArray[c] = matrix[r][(4+c-r)%4];
            matrix[r] = tempArray;
        }//for loop - n
    }// end of void invShiftRows(int[][])

    protected static void invMixColumns(int[][] matrix){
        int[] tempArray = new int[4];
        for (int c = 0; c < 4; c++) {
            tempArray[0] = dotProduct(14, matrix[0][c]) ^ dotProduct(11, matrix[1][c]) ^ dotProduct(13, matrix[2][c]) ^ dotProduct(9, matrix[3][c]);

            tempArray[1] = dotProduct(9, matrix[0][c]) ^ dotProduct(14, matrix[1][c]) ^ dotProduct(11, matrix[2][c]) ^ dotProduct(13, matrix[3][c]);

            tempArray[2] = dotProduct(13, matrix[0][c]) ^ dotProduct(9, matrix[1][c]) ^ dotProduct(14, matrix[2][c]) ^ dotProduct(11, matrix[3][c]);

            tempArray[3] = dotProduct(11, matrix[0][c]) ^ dotProduct(13, matrix[1][c]) ^ dotProduct(9, matrix[2][c]) ^ dotProduct(14, matrix[3][c]);

            for(int r = 0 ; r < 4 ; r++)
                matrix[r][c] = tempArray[r];
        }//for loop - c
    }// end of void invMixColumns(int[][])

    private static int dotProduct(int mulValue, int number){
        int result = 0;
        switch (mulValue) {
            case 2:
                result = number << 1 >= 256 ? (number << 1) ^ 256 : number << 1;
                result = number >= 128 ? result ^ 27 : result;
                break;

            case 3:
                result = dotProduct(2, number) ^ number;
                break;

            case 9:
                result = dotProduct(2, dotProduct(2, dotProduct(2, number))) ^ number;
                break;

            case 11:
                result = dotProduct(2, dotProduct(2, dotProduct(2, number)) ^ number) ^ number;
                break;

            case 13:
                result = dotProduct(2, dotProduct(2, dotProduct(3, number))) ^ number;
                break;

            case 14:
                result = dotProduct(2, dotProduct(2, dotProduct(3, number)) ^ number);
                break;
        }//switch statement
        return result;
    }//end of int dotProduct(int, int)
}//end of class
