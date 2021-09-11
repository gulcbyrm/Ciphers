package des;

public abstract class Abstract {

    protected String getLeft(final String bitStr) {
        return bitStr.substring(0, metninOrtasi(bitStr));
    }
    protected String getRight(final String bitStr) {
        return bitStr.substring(metninOrtasi(bitStr));
    }
    private  int metninOrtasi(final String bitStr) {
        return bitStr.length() / 2;
    }
}
