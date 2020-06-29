package AES;

import javax.crypto.spec.SecretKeySpec;

public class Keys {
    /** AES-128 has 10+1 rounds, AES-192 has 12+1 rounds, AES-256 has 14+1 rounds */
    public int[][] key[] = new int[15][4][4]; // array of matrices, 15 matrices, size of matrices are 4x4
    private byte noOfRounds;
    private int[][] rcon = { { 1, 2, 4, 8, 16, 32, 64, 128, 27, 54 }, 
                             { 0, 0, 0, 0,  0,  0,  0,   0,  0,  0 },
                             { 0, 0, 0, 0,  0,  0,  0,   0,  0,  0 }, 
                             { 0, 0, 0, 0,  0,  0,  0,   0,  0,  0 } };

    private SBox SB = new SBox('e');

    Keys(final String seed, final byte noOfRounds) {
        this.noOfRounds = noOfRounds;
        generateKeyZero(seed);
        generateKeys();
    }// end of public Keys(String, byte)

    private void generateKeys() {
        int[][] tmp_matrix;
        for (byte keyNumber = 1; keyNumber <= noOfRounds; keyNumber++) {

            tmp_matrix = key[keyNumber - 1];
            rotWordAndSubBytesLastColumn(tmp_matrix);

            // calculating first column of key[keyNumber]
            for (byte r = 0; r < 4; r++) {
                key[keyNumber][r][0] = tmp_matrix[r][0] ^ tmp_matrix[r][3] ^ rcon[r][keyNumber - 1];
            } // r

            for (byte c = 1; c < 4; c++) {
                for (byte r = 0; r < 4; r++) {
                    key[keyNumber][r][c] = key[keyNumber-1][r][c] ^ key[keyNumber][r][c-1];
                } // r
            } // c

        } // keyNumber

    }// end of void generateKeys(String)

    private void generateKeyZero(String seed) {
        byte[] keyZero;
        byte inc = 0;
        final SecretKeySpec keyGenerator = new SecretKeySpec(seed.getBytes(), "AES_128");
        keyZero = keyGenerator.getEncoded();

        for (byte c = 0; c < 4; c++) {// loop to convert keyZero into 4x4 matrix and storing in key[0]
            for (byte r = 0; r < 4; r++) {
                key[0][r][c] = (int) keyZero[inc++];
            } // inner for loop
        } // outer for loop

    }// end of void generateKeyZero(String)

    private void rotWordAndSubBytesLastColumn(int[][] matrix) {
        int tmp;
        tmp = matrix[0][3]; // first element of the last column

        for (int r = 1; r < 4; r++)
            matrix[r - 1][3] = SB.performSubstitution(matrix[r][3]);
        matrix[3][3] = SB.performSubstitution(tmp); // placing last element

    }// end of void rotWordAndSubBytesLastColumn()

} // end of Key class
