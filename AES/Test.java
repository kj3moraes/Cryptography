package AES;

public class Test {
    public static void main(String[] args){
        int noOfRounds = 0;
        int algo = 256;
        
        switch(algo){
            case 128:
            noOfRounds = 10;
            break;

            case 192:
            noOfRounds = 12;
            break;

            case 256:
            noOfRounds = 14;
            break;
        }
        
        String text = "HitCoordinates74";

        ///////////////////////////////////////////////////////////////////////////////////////////////
        //ENCRYPTION STARTS HERE
        ///////////////////////////////////////////////////////////////////////////////////////////////

        Keys E = new Keys("KEANEJONATHANMORAES", "HASTALAVISTABABY", algo);
        int[][] matrix = new int[4][4];

        // STEP 2 : PUT THE NECESSARY DATA INTO OUR VARIABLES
        for (int index = 0 ; index < text.length(); index++)
            matrix[index%4][Math.floorDiv(index,4)] = text.charAt(index);           

        text = "";

        // STEP 3 : USE THE DATA AND THE VARIABLES TO PERFORM THE ENCRYPTION
            // STEP 3.1 - PERFORM THE KEY0 XOR
            AESRoundFunction.addRoundKey(E.generateKeyMatrix(0), matrix);

            // STEP 3.2 : ITERATE UNTIL THE SECOND LAST ROUND
            for (int i = 1 ; i < noOfRounds ; i++) {
                AESRoundFunction.subBytes(matrix);
                AESRoundFunction.shiftRows(matrix);
                AESRoundFunction.mixColumns(matrix);
                AESRoundFunction.addRoundKey(E.generateKeyMatrix(i), matrix);
            } // for loop - i

            // STEP 3.3 : PERFORM THE UNIQUE LAST ROUND FUNCTION
            AESRoundFunction.subBytes(matrix);
            AESRoundFunction.shiftRows(matrix);
            AESRoundFunction.addRoundKey(E.generateKeyMatrix(noOfRounds), matrix);

        // STEP 4 : PUT THE RESULTANT MATRIX INTO A HEX STRING
        text = "";
        for (int c = 0 ; c < 4 ; c++)
            for (int r = 0 ; r < 4 ; r++){
                text += Integer.toHexString(matrix[r][c]).length() < 2 ? "0" + Integer.toHexString(matrix[r][c]) : Integer.toHexString(matrix[r][c]);
            }
    
        System.out.println("Encrypted Text : " + text);


        ///////////////////////////////////////////////////////////////////////////////////////////////
        //DECRYPTION STARTS HERE
        ///////////////////////////////////////////////////////////////////////////////////////////////


        Keys D = new Keys("KEANEJONATHANMORAES", "HASTALAVISTABABY", algo);
        D.generateInverseKeyMatrices(noOfRounds);

        matrix = new int[4][4];

        // STEP 2 : PUT THE NECESSARY DATA INTO OUR VARIABLES
        text += " ";      
        for (int i = 0 ; i < text.length()-1 ; i+=2)
                matrix[(i/2)%4][Math.floorDiv((i/2),4)] = Integer.parseInt(text.substring(i,i+2),16);
        
        // STEP 3 : USE THE DATA AND THE VARIABLES TO PERFORM THE ENCRYPTION
            // STEP 3.1 - PERFORM THE LAST KEY XOR (KEY 10, 12 0R 14 DEPENDING ON AES VERSION)
            AESRoundFunction.addRoundKey(D.getKeyMatrix(noOfRounds), matrix);
            AESRoundFunction.invShiftRows(matrix);
            AESRoundFunction.invSubBytes(matrix);         

            // STEP 3.2 : ITERATE BACKWARD FROM (noOfRounds-1) TO 1
            for (int i = noOfRounds - 1 ; i > 0 ; i--) {
                AESRoundFunction.addRoundKey(D.getKeyMatrix(i), matrix);            
                AESRoundFunction.invMixColumns(matrix);
                AESRoundFunction.invShiftRows(matrix);
                AESRoundFunction.invSubBytes(matrix);                
                                          
            } // for loop - round

            // STEP 3.3 : PERFORM THE UNIQUE FIRST ROUND FUNCTION                        
            AESRoundFunction.addRoundKey(D.getKeyMatrix(0), matrix);

        // STEP 4 : PUT THE RESULTANT MATRIX INTO A HEX STRING
        String hex = "";
        for (int c = 0 ; c < 4 ; c++)
            for (int r = 0 ; r < 4 ; r++)
                hex += Integer.toHexString(matrix[r][c]).length() < 2 ? "0" + Integer.toHexString(matrix[r][c]) : Integer.toHexString(matrix[r][c]);
        
        // STEP 5 : CONVERT HEX VALUE TO STRING
        text = "";
        hex += " ";
        for(int i = 0 ; i < hex.length()-1 ; i+=2)
            text += (char)(Integer.parseInt(hex.substring(i, i+2), 16));

        System.out.println("Plain Text : " + text);
    }// end of main()


    public static void displayMatrix(int[][] matrix){
        for(int r = 0 ; r < matrix.length ; r++){
            for(int c = 0 ; c < matrix[0].length ; c++){
                System.out.print(Integer.toHexString(matrix[r][c]) + ", ");
            }
            System.out.println();
        }
    }// end of displayMatrix()
    
}// end of class Test
