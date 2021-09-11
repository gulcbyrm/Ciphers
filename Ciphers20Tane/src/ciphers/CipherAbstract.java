package ciphers;

import arayuzler.CipherI;

import java.security.SecureRandom;


public abstract class CipherAbstract extends CipherI {

    public static final char[] stdAlfabe = {'a', 'b', 'c', 'ç', 'd', 'e', 'f', 'g', 'ğ', 'h', 'ı', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'ö', 'p', 'r', 's', 'ş', 't', 'u', 'ü', 'v', 'y', 'z'};
    public static int stdAlfabeLength =stdAlfabe.length ;

    protected SecureRandom secureRandom=new SecureRandom();


    /**
     * Bu fonksiyon herhangi bir harfin alfabedeki INDEX'ini döndürür
     *
     * @param aranan indexine ulaşmak istediğimiz karakter
     * @return karaktteri indexi döner
     */
    protected int getIndex(char[] alfabeDizisi, final char aranan) {
        for (int i = 0; i < alfabeDizisi.length; i++)
            if (aranan == alfabeDizisi[i])
                return i;
        return -1;
    }


    /**
     * Bu fonksiyon herhangi bir int sayının alfabede karşılık geldiği harfi dndürür
     * ör:-100 sayısı için 'n' döner
     *
     * @param aranan harfin int türündn değeri istenen herhangi bir int değer olabilir
     * @return bulunan char karşılığı
     */
    protected char getChar(char[] alfabeDizisi, int aranan) {
        aranan %= alfabeDizisi.length;
        return alfabeDizisi[aranan < 0 ? aranan + alfabeDizisi.length : aranan];
    }
}


//final char ingilzHarfler[] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
