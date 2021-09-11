package ciphers.des.exceptions;

/**
 * bu sınıf hata fırlatmaktadır.
 * girilen key 8 karakter değilse Exception sınıfına extend eden  bu sınıf kullanılacaktır
 */

public class KeyLengthUygunDeil extends Exception {

    public KeyLengthUygunDeil() {

    }

    public KeyLengthUygunDeil(String message) {
        super(message);
    }
}
