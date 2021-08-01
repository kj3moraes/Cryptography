/*
 * Copyright (c) 2021 Keane Jonathan Moraes
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package AES.Resources;;

public final class RoundFunction {
    /**
     * subBytes() -----------------------------------------------------------------------
     * This function takes a single parameter - a 4x4 integer matrix and replaces
     * each element with its corresponding forward SBox lookup equivalent
     *
     * @param matrix - the 4x4 int matrix
     */
    public static void subBytes(int[][] matrix){
        for (int r = 0; r < matrix.length; r++)
            for (int c = 0; c < matrix[r].length; c++)
                matrix[r][c] = SBox.performSubstitution(matrix[r][c], 'e');
    }//end of void subBytes(int[][])

    /**
     * AESRF - SHIFT ROWS
     * This function takes a single parameter - a 4x4 integer matrix and shifts 
     * each row according by its respective index. 
     *
     * @param matrix - the 4x4 int matrix
     * @return - the 4x4 resultant matrix.
     */
    public static void shiftRows(int[][] matrix){
        int[] tempArray;
        for (int r = 0; r < 4; r++) {
            tempArray = new int[4];
            for (int c = 0; c < 4; c++)
                tempArray[c] = matrix[r][(c+r)%4];
            matrix[r] = tempArray;
        }//for loop - n
    }// end of void shiftRows(int[][])

    /**
     * AESRF - MIX COLUMNS
     * This function takes a single parameter - a 4x4 integer matrix and replaces
     * each column with its dotProduct equivalent.
     * equivalent
     *
     * @param matrix - the 4x4 int matrix
     * @return - the 4x4 resultant matrix.
     */
    public static void mixColumns(int[][] matrix){
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

    /**
     * AESRF - ADD ROUND KEY
     * This function takes 2 single parameters - a 4x4 integer matrix that
     * represents the key matrix and a 4x4 integer matrix that represents
     * the state matrix. It XORes each column of the state matrix with 
     * the corresponding column of the key matrix and stores the value in 
     * the state matrix.
     *
     * @param keyMatrix - the 4x4 int key matrix
     * @param matrix - the 4x4 int state matrix
     * @return - the 4x4 resultant matrix.
     */
    public static void addRoundKey(int[][] keyMatrix, int[][] matrix) {
        for (int c = 0; c < 4; c++){
            matrix[0][c] ^= keyMatrix[0][c];
            matrix[1][c] ^= keyMatrix[1][c];
            matrix[2][c] ^= keyMatrix[2][c];
            matrix[3][c] ^= keyMatrix[3][c];
        }//for loop - columns
    }// end of void addRoundKey(int[][])

    /**
     * AESRF - INVERSE SUBSTITUTE BYTES 
     * This function takes a single parameter - a 4x4 integer matrix and 
     * replaces each element with its corresponding reverse SBox lookup
     * equivalent 
     *
     * @param matrix - the 4x4 int matrix
     * @return - the 4x4 resultant matrix.
     */
    public static void invSubBytes(int[][] matrix){
        for (int r = 0; r < matrix.length; r++)
            for (int c = 0 ; c < matrix[r].length ; c++)
                matrix[r][c] = SBox.performSubstitution(matrix[r][c], 'd');
    }// end of void invSubBytes(int[][])

    /**
     * AESRF - INVERSE SHIFT ROWS 
     * This function takes a single parameter - a 4x4 integer matrix
     * and shifts each row backwards according by its respective index.
     *
     * @param matrix - the 4x4 int matrix
     * @return - the 4x4 resultant matrix.
     */
    public static void invShiftRows(int[][] matrix){
        int[] tempArray;
        for (int r = 0; r < 4; r++) {
            tempArray = new int[4];
            for (int c = 0; c < 4; c++)
                tempArray[c] = matrix[r][(4+c-r)%4];
            matrix[r] = tempArray;
        }//for loop - n
    }// end of void invShiftRows(int[][])

    /**
     * AESRF - INVERSE MIX COLUMNS 
     * This function takes a single parameter - a 4x4 integer matrix and replaces
     * each column with its inverse dotProduct equivalent.
     *
     * @param matrix - the 4x4 int matrix
     * @return - the 4x4 resultant matrix.
     */
    public static void invMixColumns(int[][] matrix){
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

    /**
     * AESRF - DOT PRODUCT
     * This function takes 2 parameters - an integer that correspods to the 
     * operation to take place on the 2nd input parameter - the number. Outputs
     * the result of the calculation.
     *
     * @param mulValue - integer that indicates which operation
     * @param number - the number to be operated on
     * @return - the result of the operation on number
     */
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
