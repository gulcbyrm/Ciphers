package ciphers.caesar;


import ciphers.des.sabitler.Mode;
import org.junit.Assert;
import org.junit.Test;

public class CeaserTest {

    Caesar ceaser = new Caesar();

    @Test
    public void testSifrele() throws Exception {
        ceaser.setKey("0");
        String result = ceaser.sifreleSifreCoz("a", Mode.Encript);
        Assert.assertEquals("a", result);
    }


    @Test
    public void testSifrele1() throws Exception {
        ceaser.setKey("3");
        String result = ceaser.sifreleSifreCoz("a", Mode.Encript);
        Assert.assertEquals("d", result);
    }


    @Test
    public void testSifrele2() throws Exception {
        ceaser.setKey("3");
        String result = ceaser.sifreleSifreCoz("z", Mode.Encript);
        Assert.assertEquals("c", result);
    }


    @Test
    public void testSifrele3() throws Exception {
        ceaser.setKey("29");
        String result = ceaser.sifreleSifreCoz("deneme", Mode.Encript);
        System.out.println(result);
        Assert.assertEquals("deneme", result);
    }


    @Test
    public void testSifrele4() throws Exception {
        ceaser.setKey("28");
        String result = ceaser.sifreleSifreCoz("deneme", Mode.Encript);
        System.out.println(result);
        Assert.assertEquals("fgpgog", result);
    }

    @Test
    public void testSifrele5() throws Exception {
        ceaser.setKey("58");
        String result = ceaser.sifreleSifreCoz("deneme", Mode.Encript);
        System.out.println(result);
        Assert.assertEquals("jktksk", result);
    }

    @Test
    public void testSifreCoz() throws Exception {
        ceaser.setKey("0");
        String result = ceaser.sifreleSifreCoz("encriptedet", Mode.Encript);
        Assert.assertEquals("encriptedet", result);
    }


    @Test
    public void testSifreleCoz() throws Exception {
        ceaser.setKey("21");
        String encripted =ceaser.sifreleSifreCoz("metindenemezzzaaaxx", Mode.Encript);
        String result = ceaser.sifreleSifreCoz(encripted, Mode.Decript);
        Assert.assertEquals("metindenemezzzaaaxx", result);
    }
}
