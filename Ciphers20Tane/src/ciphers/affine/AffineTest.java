package ciphers.affine;

import ciphers.des.sabitler.Mode;
import org.junit.Test;

public class AffineTest {


    @Test
    public void testGetIndex() throws Exception {
        Affine affine = new Affine();
        affine.setKey1(37);
        affine.setKey2(11);
        String metin="gldeneme";
        System.out.println("PlainText: "+metin);
        String sif= affine.sifreleSifreCoz(metin, Mode.Encript);
        System.out.println("Encripted: "+sif);
        System.out.println("Decripted: "+ affine.sifreleSifreCoz(sif,Mode.Decript));
    }
}

