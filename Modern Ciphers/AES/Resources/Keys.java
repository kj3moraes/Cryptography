package AES.Resources;

public class Keys {
    protected int algoVariant, maxColumnNo;
    private final int[] RCON = { 1, 2, 4, 8, 16, 32, 64, 128, 27, 54 };
    private int[][][] keyMatrix;

    public int getAlgoVariant() {
        return algoVariant;
    }

    public Keys() {
        this(128);
    }

    public Keys(int algoVariant) {

    }

    public Keys(String seed, String salt, int algoVariant) {

    }

}//end of class
