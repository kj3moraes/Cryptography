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

package AES.Versions;;
import AES.Resources.SBox;
public abstract class KeyOperations {
    protected int algoVariant, maxColumnNo;
    private final int[] RCON = { 1, 2, 4, 8, 16, 32, 64, 128, 27, 54 };
    private int[][][] keyMatrix;

    public int getAlgoVariant() {
        return algoVariant;
    }

    /**
     * generateKeyMatrix() --------------------------------------------------------------
     * Returns the key matrix in integer encoded form
     * @return -
     */
    public abstract int[][] generateKeyMatrix(int roundNo);

    /**
     * functionF(int[], int) ------------------------------------------------------------
     *
     * @param column
     * @param rconNo
     * @return - returns the ...
     */
    private int[] functionF(int[] column, int rconNo) {
        int[] resultantKeyColumn = new int[4];

        // STEP 1: ROTWORD
        resultantKeyColumn[3] = column[0];
        System.arraycopy(column, 1, resultantKeyColumn, 0, 3);

        // STEP 2 : SUBBYTES
        for (int i = 0; i < 4; i++)
            resultantKeyColumn[i] = SBox.performSubstitution(resultantKeyColumn[i], 'e');

        // STEP 3 : RCON
        resultantKeyColumn[0] ^= RCON[rconNo];
        return resultantKeyColumn;
    }// end of int[] functionF(int[], int)

    /**
     * KEYS - BYTE ARRAY TO HEX
     * This function takes a single parameters - a byte
     * array and outputs the hex value of each of the elements of the array
     * concatenated together.
     *
     * @param input - the byte array
     * @return - the concatenated hex value
     */
    protected String ByteArrayToHexadecimal(byte[] input) {
        String result = "";
        for (byte b : input)
            result += String.format("%02X", b);
        return result;
    }// end of String ByteArrayToHexadecimal(byte[])
}
