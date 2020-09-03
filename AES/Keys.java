package AES;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public class Keys {
    private int[][] currentKeyMatrix;
    protected String initialKeyState;
    protected int algorithm, maxColumnNo;
    private final int[] RCON = { 1, 2, 4, 8, 16, 32, 64, 128, 27, 54 };
    private SBox S = new SBox('e');

    public String getInitialKeyState() {
        return initialKeyState;
    }// end of String getInitKeyState()

    public Keys(String seed, String salt, int algorithm) {
        SecretKey key = null;
        try {
            KeySpec keyGen = new PBEKeySpec(seed.toCharArray(), salt.getBytes(), 65536, algorithm);
            key = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1").generateSecret(keyGen);
        } // try block
        catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            System.out.println("ERROR");
            System.exit(0);
        } // catch block
        initialKeyState = ByteArrayToHexadecimal(key.getEncoded());

        // STEP 2 : PROPERLY INITIALIZE THE ALGORITHM AND KEY MATRIX
        switch (algorithm) {
            case 128:
                currentKeyMatrix = new int[4][4];
                this.algorithm = 128;
                maxColumnNo = 3;
                break;

            case 192:
                currentKeyMatrix = new int[4][6];
                this.algorithm = 192;
                maxColumnNo = 5;
                break;

            case 256:
                currentKeyMatrix = new int[4][8];
                this.algorithm = 256;
                maxColumnNo = 7;
                break;
        }// switch statement
    }// end of public Keys(String, String, int)

    protected int[][] generateKeyMatrix(int roundNo) {
        int[][] outputMatrix = new int[4][4];
        // STEP 1 : TAKE CARE OF THE KEY0 STATE FOR ALL POSSIBLE KEY LENGTHS
        if (roundNo == 0) {
            for (int i = 0; i < initialKeyState.length(); i+=2)
                currentKeyMatrix[(i/2)%4][Math.floorDiv((i/2),4)] = Integer.parseInt(initialKeyState.substring(i,i+2),16);
            for (int i = 0; i < 4; i++)
                System.arraycopy(currentKeyMatrix[i], 0, outputMatrix[i], 0, 4);
            return outputMatrix;
        } // if statement - INITIAL ROUND

        // STEP 2: HANDLE THE KEY GENERATION PER ALGORITHM
        int[] column = new int[4];
        switch (algorithm) {
            // STEP 2.1 - 128 BIT VARIANT
            case 128:
                // STEP 2.1.1 : ASSIGN THE LAST COLUMN (COLUMN 3) TO A TEMPORARY VARIABLE.
                for (int i = 0; i < 4; i++)
                    column[i] = currentKeyMatrix[i][3];

                // STEP 2.1.2 : GENERATE THE FIRST COLUMN OF THE NEXT KEY MATRIX
                column = functionF(column, roundNo-1);
                for(int i = 0 ; i < 4 ; i++)
                    currentKeyMatrix[i][0] ^= column[i];

                // STEP 2.1.3 : USE THE FIRST COLUMN TO GENERATE THE NEXT COLUMNS
                for (int c = 1; c < 4; c++)
                    for(int r = 0 ; r < 4 ; r++)
                        currentKeyMatrix[r][c] ^= currentKeyMatrix[r][c - 1];
                break;

            // STEP 2.2 - 192 BIT VARIANT
            case 192:
                switch (roundNo % 3) {
                    case 0:
                        // STEP 2.2.0.1 : ASSIGN THE LAST COLUMN (COLUMN 5) TO A TEMPORARY VARIABLE.
                        for (int i = 0; i < 4; i++)
                            column[i] = currentKeyMatrix[i][5];

                        // STEP 2.2.0.2 : GENERATE THE FIRST COLUMN OF THE NEXT 4X6 KEY MATRIX
                        column = functionF(column, roundNo - Math.floorDiv(roundNo,3) - 1);
                        for(int r = 0 ; r < 4 ; r++)
                            currentKeyMatrix[r][0] ^= column[r];

                        // STEP 2.2.0.3 : USE THE 0th COLUMN TO GENERATE COLUMNS THE NEXT 3 COLUMNS
                        for (int c = 1 ; c < 4 ; c++)
                            for(int r = 0 ; r < 4 ; r++)
                                currentKeyMatrix[r][c] ^= currentKeyMatrix[r][c - 1];
                        break;

                    case 1:
                        // STEP 2.2.1.1 : GENERATE THE COLUMNS 4 & 5
                        if (roundNo != 1) {
                            for (int c = 4; c < 6; c++)
                                for(int r = 0 ; r < 4 ; r++)
                                    currentKeyMatrix[r][c] ^= currentKeyMatrix[r][c - 1];
                        }//if statement - ROUND 1 EXCEPTION

                        // STEP 2.2.1.2 : ASSIGN THE LAST COLUMN TO A TEMPORARY VARIABLE.
                        for (int i = 0; i < 4; i++)
                            column[i] = currentKeyMatrix[i][5];

                        // STEP 2.2.1.3 : GENERATE THE FIRST COLUMN OF THE NEXT KEY 4X6 MATRIX
                        column = functionF(column, roundNo - Math.floorDiv(roundNo,3) - 1);
                        for(int r = 0 ; r < 4 ; r++)
                            currentKeyMatrix[r][0] ^= column[r];

                        // STEP 2.2.1.4 : USE THE 0th COLUMN TO GENERATE COLUMN 1
                        for(int r = 0 ; r < 4 ; r++)
                            currentKeyMatrix[r][1] ^= currentKeyMatrix[r][0];

                        // STEP 2.2.1.5 : ISOLATE THE COLUMNS 4,5,0,1 INTO A 4x4 PART TO RETURN
                        for (int i = 4; i <= 7; i++)
                            for (int j = 0; j < 4; j++)
                                outputMatrix[j][i-4] = currentKeyMatrix[j][i % 6];
                        return outputMatrix;

                    case 2:
                        // STEP 2.2.2.1 : GENERATE COLUMNS 2,3,4,5
                        for (int c = 2; c <= 5; c++)
                            for(int r = 0 ; r < 4 ; r++)
                                currentKeyMatrix[r][c] ^= currentKeyMatrix[r][c - 1];

                        // STEP 2.2.2.2 : ISOLATE COLUMNS 2,3,4,5 INTO A 4x4 PART
                        for (int i = 2; i <= 5; i++)
                            for (int j = 0; j < 4; j++)
                                outputMatrix[j][i - 2] = currentKeyMatrix[j][i];
                        return outputMatrix;
                }// switch statement - ROUND NO.
                break;

            // STEP 2.3 - 256 BIT VARIANT
            case 256:
                switch (roundNo % 2) {
                    case 0:
                        // STEP 2.3.0.1 : ASSIGN THE LAST COLUMN (COLUMN 7) TO A TEMPORARY VARIABLE.
                        for (int i = 0; i < 4; i++)
                            column[i] = currentKeyMatrix[i][7];

                        // STEP 2.3.0.2 : GENERATE THE FIRST COLUMN OF THE NEXT KEY MATRIX
                        column = functionF(column, (roundNo/2) - 1);

                        for(int r = 0 ; r < 4 ; r++)
                            currentKeyMatrix[r][0] ^= column[r];

                        // STEP 2.3.0.3 : USE THE FIRST COLUMN TO GENERATE THE NEXT COLUMNS
                        for (int c = 1 ; c < 4 ; c++)
                            for(int r = 0 ; r < 4 ; r++)
                                currentKeyMatrix[r][c] ^= currentKeyMatrix[r][c - 1];
                        break;

                    case 1:
                        // STEP 2.3.1.1 : ASSIGN COLUMN 3 TO A TEMPORARY VARIABLE.
                        for (int i = 0; i < 4; i++)
                            column[i] = currentKeyMatrix[i][3];

                        // STEP 2.3.1.2 : GENERATE COLUMN 4 USING COLUMN 3.
                        if (roundNo != 1) {
                            for(int r = 0 ; r < 4 ; r++)
                                currentKeyMatrix[r][4] ^= S.performSubstitution(currentKeyMatrix[r][3]);

                            // STEP 2.3.1.3 : USE THE COLUMN 4 TO GENERATE COLUMNS 5,6,7
                            for (int c = 5; c <= 7; c++)
                                for(int r = 0 ; r < 4 ; r++)
                                    currentKeyMatrix[r][c] ^= currentKeyMatrix[r][c - 1];
                        }//if statement - ROUND 1 EXCEPTION

                        // STEP 2.3.1.4 : ISOLATE THE COLUMNS 4,5,0,1 INTO A 4x4 PART TO RETURN
                        for (int i = 4; i <= 7; i++)
                            for (int j = 0; j < 4; j++)
                                outputMatrix[j][i - 4] = currentKeyMatrix[j][i];
                        return outputMatrix;
                }// switch statement - ROUND NO.
                break;
        }// switch statement - KEY SIZE

        // STEP 3 : ISOLATE COLUMNS 0,1,2,3 INTO A 4x4 TO RETURN
        for (int i = 0; i < 4; i++)
            System.arraycopy(currentKeyMatrix[i], 0, outputMatrix[i], 0, 4);
        return outputMatrix;
    }// end of int[][] getKeyMatrix(int)

    private int[] functionF(int[] column, int rconNo) {
        int[] resultantKeyColumn = new int[4];

        // STEP 1: ROTWORD
        resultantKeyColumn[3] = column[0];
        System.arraycopy(column, 1, resultantKeyColumn, 0, 3);

        // STEP 2 : SUBBYTES
        for (int i = 0; i < 4; i++)
            resultantKeyColumn[i] = S.performSubstitution(resultantKeyColumn[i]);
        // STEP 3 : RCON
        resultantKeyColumn[0] ^= RCON[rconNo];
        return resultantKeyColumn;
    }// end of int[] functionF(int[], int)

    private String ByteArrayToHexadecimal(byte[] input) {
        String result = "";
        for (byte b : input)
            result += String.format("%02X", b);
        return result;
    }// end of String ByteArrayToHexadecimal(byte[])
}// end of class
