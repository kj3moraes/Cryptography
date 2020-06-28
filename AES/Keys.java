package AES;

import javax.crypto.spec.SecretKeySpec;

public class Keys {
    /** AES-128 has 10+1 rounds, AES-192 has 12+1 rounds, AES-256 has 14+1 rounds */
    public String key[] = new String[14];
    private byte[][] keyState = new byte[4][4];
    private byte noOfRounds;
    private byte[] rcon = {};

    Keys(final String seed, final byte noOfRounds) {
        this.noOfRounds = noOfRounds;
        generateKeys();
    }//end of public Keys(String, byte)

    private void generateKeys() {
    }//end of void generateKeys(String)

    private void generateKeyState(String seed) {
        byte[] key0; byte inc = 0;
        final SecretKeySpec keyGenerator = new SecretKeySpec(seed.getBytes(), "AES_128");
        key0 = keyGenerator.getEncoded();

        for(byte c = 0 ; c < 4 ; c++){//loop to convert key0 into 4x4 matrix as keyState
            for(byte r = 0 ; r < 4 ; r++){
                keyState[r][c] = key0[inc++];
            }//inner for loop
        }//outer for loop

    }//end of void generateSecretKey(String)
