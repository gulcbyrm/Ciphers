package ciphers.hill;

import ciphers.Convert;
import ciphers.des.sabitler.Mode;
import entity.FormEntity;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class HillTest {

    Hill hill = new Hill();

    @Test
    public void testSifreleSifreCoz() throws Exception {
        hill.randomKeyUret();
        String result = hill.sifreleSifreCoz("deneme", Mode.Encript);

        System.out.println(result);
    }
    public static String[] split(String src, int len) {
        String[] result = new String[(int)Math.ceil((double)src.length()/(double)len)];
        for (int i=0; i<result.length; i++)
            result[i] = src.substring(i*len, Math.min(src.length(), (i+1)*len));
        return result;
    }

    @Test
    public void testGetThisFormEntity() throws Exception {
        FormEntity result = hill.getThisFormEntity();
        Assert.assertEquals(null, result);
    }

    @Test
    public void testRandomKeyUret() throws Exception {
        String result = hill.randomKeyUret();
        matrisiConsolaYaz(Convert.strToMatris(result));
    }

    @Test
    public void testKeyUygunMu() throws Exception {
        boolean result = hill.keyUygunMu();
        Assert.assertEquals(true, result);
    }


    public static void matrisiConsolaYaz(char[][] charMatris) {
        for (int sat = 0; sat < charMatris.length; sat++) {
            for (int sut = 0; sut < charMatris[0].length; sut++) {
                System.out.print(charMatris[sat][sut]+"\t");
            }
            System.out.println();
        }
    }
//    @Test
//    public void testGetKey() throws Exception {
//        char[] result = hill.getKey();
//        Assert.assertArrayEquals(new char[]{'a'}, result);
//    }
}