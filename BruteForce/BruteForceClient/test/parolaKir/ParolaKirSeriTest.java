
package parolaKir;

import entities.Gorev;
import entities.GorevDizi;
import org.junit.Test;


public class ParolaKirSeriTest {
    
    public ParolaKirSeriTest() {
    }


    @Test
    public void testSha256Kir() {
        System.out.println("sha256Kir");
        Gorev grv = createGorev();
        ParolaKirSeri instance = new ParolaKirSeri();
        instance.sha256Kir(grv);
    }
    
    
    
    public Gorev createGorev() {
        int min = 2;
        int max = 3;
        int clientSayisi = 1;
        String karakterSeti = "abcçdefgğhıijklmnoöprsştuüvyzABCÇDEFGĞHIİJKLMNOÖPRSŞTUÜVYZ01236789.,*-+!?/";
        String hashKod = "ba7816bf8f01cfea414140de5dae2223b00361a396177a9cb410ff61f20015ad"; //k karfinin sha256 kodu
        GorevDizi[] clientGorevDizi = {new GorevDizi(0, karakterSeti.length())};
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
