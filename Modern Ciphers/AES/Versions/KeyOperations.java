package AES.Versions;

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
    public abstract int[][] generateKeyMatrix();
}
