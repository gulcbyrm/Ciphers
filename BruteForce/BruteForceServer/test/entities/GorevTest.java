/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Arrays;
import network.ParallelProc;
import static org.junit.Assert.*;
import org.junit.Test;

public class GorevTest {

    
    
    private Gorev instance;

    
    
    public GorevTest() {
        instance = createGorev();
    }

    
    
    @Test
    public void testGetPermutasyonDizisi() {
        System.out.println("getPermutasyonDizisi");
        long[] expResult = new ParallelProc().permutasyonDizisiOlustur(instance.getKarakterSeti().length(), instance.getMinUzunluk(), instance.getMaxUzunluk());
        long[] result = instance.getPermutasyonDizisi();
        System.out.println(Arrays.toString(expResult));
        System.out.println(Arrays.toString(result));
        assertArrayEquals(expResult, result);
    }

    
    
    
    @Test
    public void testGetClientGorevDizi() {
        System.out.println("getClientGorevDizi");
        GorevDizi[] expResult =new ParallelProc().clientGorevDizisiOlustur(instance.getKarakterSeti().length(), 1);
        GorevDizi[] result = instance.getClientGorevDizi();
        System.out.println(Arrays.toString(expResult));
        System.out.println(Arrays.toString(result));
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
    public void testGorevToString() {
        System.out.println("toString");
        String result = instance.toString();
        Gorev g = createGorev();
        System.out.println(g.toString());
    }

    
    
    
    @Test
    public void testGetKarakterSeti() {
        System.out.println("getKarakterSeti");
        String expResult = "abcçdefgğhıijklmnoöprsştuüvyzABCÇDEFGĞHIİJKLMNOÖPRSŞTUÜVYZ01236789.,*-+!?/";
        String result = instance.getKarakterSeti();
        assertEquals(expResult, result);
    }


    
    
    @Test
    public void testGetMinUzunluk() {
        System.out.println("getMinUzunluk");
        int expResult = 3;
        int result = instance.getMinUzunluk();
        assertEquals(expResult, result);
    }


    
    
    @Test
    public void testGetMaxUzunluk() {
        System.out.println("getMaxUzunluk");
        int expResult = 8;
        int result = instance.getMaxUzunluk();
        assertEquals(expResult, result);
    }


    

    
    
    public Gorev createGorev() {
        int min = 3;
        int max = 8;
        int clientSayisi = 1;
        String karakterSeti = "abcçdefgğhıijklmnoöprsştuüvyzABCÇDEFGĞHIİJKLMNOÖPRSŞTUÜVYZ01236789.,*-+!?/";
        String hashKod = "36bbe50ed96841d10443bcb670d6554f0a34b761be67ec9c4a8ad2c0c44ca42c"; //k karfinin sha256 kodu
        GorevDizi[] clientGorevDizi = {new GorevDizi(0, 74)};
        long[] permustasyonDizisi = {0l, 0l, 405224l, 29986576l, 2219006624l, 164206490176l, 12151280273024l, 899194740203776l};

        return new Gorev.GorevBuilder()
                .setKarakterSeti(karakterSeti)
                .setMinUzunluk(min)
                .setMaxUzunluk(max)
                .setHashKod(hashKod)
                .setClientGorevDizi(clientGorevDizi)
                .setPermutasyonDizisi(permustasyonDizisi).build();
    }
}
