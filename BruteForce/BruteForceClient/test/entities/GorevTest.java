package entities;

import java.util.Arrays;
import org.junit.Test;
import static org.junit.Assert.*;


public class GorevTest {

    private Gorev instance;

    
    public GorevTest() {
        instance = createGorev();
    }

    
    
    @Test
    public void gorevNesnesiOlusturmaTest() {
        int min = 5;
        int max = 5;
        int clientSayisi = 1;
        String karakterSeti = "abcçdefgğhıijklmnoöprsştuüvyzABCÇDEFGĞHIİJKLMNOÖPRSŞTUÜVYZ01236789.,*-+!?/";
        String hashKod = "36bbe50ed96841d10443bcb670d6554f0a34b761be67ec9c4a8ad2c0c44ca42c"; //k karfinin sha256 kodu
        GorevDizi[] clientGorevDizi = {new GorevDizi(5, 15)};
        long[] permustasyonDizisi = {0l, 77l, 5929l};

        new Gorev.GorevBuilder()
                .setKarakterSeti(karakterSeti)
                .setMinUzunluk(min)
                .setMaxUzunluk(max)
                .setHashKod(hashKod)
                .setClientGorevDizi(clientGorevDizi)
                .setPermutasyonDizisi(permustasyonDizisi).build();

        System.out.println("gorev");
    }




    @Test
    public void testGetKarakterSeti() {
        System.out.println("getKarakterSeti");
        String expResult = "abcçdefgğhıijklmnoöprsştuüvyzABCÇDEFGĞHIİJKLMNOÖPRSŞTUÜVYZ01236789.,*-+!?/";
        String result = instance.getKarakterSeti();
        assertEquals(expResult, result);
    }

    
    
    
    @Test
    public void testGetPermutasyonDizisi() {
        System.out.println("getPermutasyonDizisi");
        long[] expResult = {0l, 77l, 5929l};
        long[] result = instance.getPermutasyonDizisi();
        assertArrayEquals(expResult, result);
    }


    @Test
    public void testGetMinUzunluk() {
        System.out.println("getMinUzunluk");
        int expResult = 2;
        int result = instance.getMinUzunluk();
        assertEquals(expResult, result);
    }


    @Test
    public void testGetMaxUzunluk() {
        System.out.println("getMaxUzunluk");
        int expResult = 6;
        int result = instance.getMaxUzunluk();
        assertEquals(expResult, result);

    }


    @Test
    public void testGetClientGorevDizi() {
        System.out.println("getClientGorevDizi");
        GorevDizi[] result = instance.getClientGorevDizi();
        System.out.println(Arrays.toString(result));
    }
    
    

    public Gorev createGorev() {
        int min = 2;
        int max = 6;
        int clientSayisi = 1;
        String karakterSeti = "abcçdefgğhıijklmnoöprsştuüvyzABCÇDEFGĞHIİJKLMNOÖPRSŞTUÜVYZ01236789.,*-+!?/";
        String hashKod = "36bbe50ed96841d10443bcb670d6554f0a34b761be67ec9c4a8ad2c0c44ca42c"; //k karfinin sha256 kodu
        GorevDizi[] clientGorevDizi = {new GorevDizi(5, 15)};
        long[] permustasyonDizisi = {0l, 77l, 5929l};

        return new Gorev.GorevBuilder()
                .setKarakterSeti(karakterSeti)
                .setMinUzunluk(min)
                .setMaxUzunluk(max)
                .setHashKod(hashKod)
                .setClientGorevDizi(clientGorevDizi)
                .setPermutasyonDizisi(permustasyonDizisi).build();
    }

}
