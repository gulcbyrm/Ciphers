package arayuzler;

public abstract class OneKey<P> extends CipherI {

    protected String key;

    public abstract P getKey() ;

    public void setKey(String key) {
        this.key = key;
    }
}