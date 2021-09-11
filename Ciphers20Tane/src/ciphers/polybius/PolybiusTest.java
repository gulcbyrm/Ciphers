package ciphers.polybius;

import ciphers.des.sabitler.Mode;
import org.junit.Assert;
import org.junit.Test;


public class PolybiusTest {
    private final Polybius polybius = new Polybius();

    @Test
    public void testGetKeyMatris() throws Exception {
       // int[][] result = polybius.getKeyMatris();
       // Assert.assertArrayEquals(new int[]{0}, result);
    }

    @Test
    public void testSifreleSifreCoz() throws Exception {
        String result = polybius.sifreleSifreCoz("islenecekMetin", Mode.Encript);
        Assert.assertEquals("replaceMeWithExpectedResult", result);
    }



    @Test
    public void testRandomKeyUret() throws Exception {
        System.out.println(polybius.randomKeyUret());
    }

    @Test
    public void testKeyUygunMu() throws Exception {
        boolean result = polybius.keyUygunMu();
        Assert.assertEquals(true, result);
    }
}

