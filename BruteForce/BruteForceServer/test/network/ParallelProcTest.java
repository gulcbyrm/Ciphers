package network;

import entities.GorevDizi;
import java.util.Arrays;
import static org.junit.Assert.assertArrayEquals;
import org.junit.Test;





public class ParallelProcTest {
    
    ParallelProc instance = new ParallelProc();
    private String karakterSeti1 = "abcçdefgğhıijklmnoöprsştuüvyzABCÇDEFGĞHIİJKLMNOÖPRSŞTUÜVYZ01236789.,*-+!?/";


    
    
    @Test
    public void testPermutasyonDizisiOlustur() {
        System.out.println("permutasyonDizisiOlustur");
        int karakterSetiUzunlugu = 70;
        int sifreMinLength = 2;
        int sifreMaxLength = 2;
        long[] expResult = new long[]{0,4900};
        long[] result = instance.permutasyonDizisiOlustur(karakterSetiUzunlugu, sifreMinLength, sifreMaxLength);
        System.out.println("result: "+Arrays.toString(result));
        assertArrayEquals(expResult, result);
    }


    
    
    
    @Test
    public void testClientGorevDizisiOlustur() {
        System.out.println("clientGorevDizisiOlustur");
        int karakterSetiLength =77;
        int clientSayisi =3;
        GorevDizi[] result = instance.clientGorevDizisiOlustur(karakterSetiLength, clientSayisi);
        System.out.println(Arrays.toString(result));
    }
    
    
    
    
    
    
    @Test
    public void test2PermutasyonDizisiOlustur() {
        System.out.println("permutasyonDizisiOlustur");
        int karakterSetiUzunlugu = 77;
        int sifreMinLength = 1;
        int sifreMaxLength = 4;
        long[] expResult = new long[]{77,5929,456533,35153041};
        long[] result = instance.permutasyonDizisiOlustur(karakterSetiUzunlugu, sifreMinLength, sifreMaxLength);
        System.out.println(Arrays.toString(result));
        assertArrayEquals(expResult, result);
    }
    
    
    
    @Test
    public void test2ClientGorevDizisiOlustur() {
        int karakterSetiLeng=77;
        System.out.println("clientGorevDizisiOlustur");
        long[] permutasyonDizisi = new long[]{77,5929,456533,35153041};
        int clientSayisi = 1;
        int[] expResult = null;
        GorevDizi[] result = instance.clientGorevDizisiOlustur(karakterSetiLeng,clientSayisi);
        System.out.println(Arrays.toString(result));
    }
    

    //GÖZLEME DAYALI TESTLER
    @Test
    public void permutasyonDizisiniDoldurTest() {
        long[] permustasyonDizisi = instance.permutasyonDizisiOlustur(karakterSeti1.length(), 1, 2);
        GorevDizi[] clientGorevDizisi = instance.clientGorevDizisiOlustur(karakterSeti1.length(), 1);
        System.out.println("Görev Dizi: "+Arrays.toString(clientGorevDizisi));
        System.out.println("Permütasyon Dizi: "+Arrays.toString(clientGorevDizisi));
    }
    
    
    
    @Test
    public void clienteGorevHesaplaTest() {
        long[] permustasyonDizisi = instance.permutasyonDizisiOlustur(76, 1, 2);
        GorevDizi[] clientGorevDizisi = instance.clientGorevDizisiOlustur(karakterSeti1.length(), 7);
        System.out.println("Görev Dizi: "+Arrays.toString(clientGorevDizisi));
        System.out.println("Permütasyon Dizi: "+Arrays.toString(clientGorevDizisi));
    }

    
    

    @Test
    public void clienteGorevHesaplaTest2() {
        long[] permustasyonDizisi = instance.permutasyonDizisiOlustur(76, 1, 2);
        GorevDizi[] clientGorevDizisi = instance.clientGorevDizisiOlustur(karakterSeti1.length(), 33);
        System.out.println("Görev Dizi: "+Arrays.toString(clientGorevDizisi));
        System.out.println("Permütasyon Dizi: "+Arrays.toString(clientGorevDizisi));
    }


    
    
    
    @Test
    public void clienteGorevHesaplaTest3() {//Hata vermeli
        long[] permustasyonDizisi = instance.permutasyonDizisiOlustur(76, 1, 2);
        GorevDizi[] clientGorevDizisi = instance.clientGorevDizisiOlustur(karakterSeti1.length(), 75);
        System.out.println("Görev Dizi: "+Arrays.toString(clientGorevDizisi));
        System.out.println("Permütasyon Dizi: "+Arrays.toString(clientGorevDizisi));
    }


    
    
    @Test
    public void clienteGorevHesaplaTest4() {//Hata vermeli
        long[] permustasyonDizisi = instance.permutasyonDizisiOlustur(76, 3, 5);
        GorevDizi[] clientGorevDizisi = instance.clientGorevDizisiOlustur(karakterSeti1.length(), 7);
        System.out.println("Görev Dizi: "+Arrays.toString(clientGorevDizisi));
        System.out.println("Permütasyon Dizi: "+Arrays.toString(clientGorevDizisi));
    }

    
}
