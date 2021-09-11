package arayuzler;

public abstract class TwoKey<P> extends CipherI {

    protected String key1;
    protected String key2;

    public abstract P getKey1() ;
    public abstract P getKey2() ;

    public void setKey1(String key1) {
        this.key1 = key1;
    }

    public void setKey2(String key2) {
        this.key2 = key2;
    }
}