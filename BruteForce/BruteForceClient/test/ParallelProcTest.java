
import entities.Gorev;
import entities.GorevDizi;
import org.junit.Test;
import parolaKir.Sha256;
import utility.Listeler;

public class ParallelProcTest {

    
    
    
    private Sha256 sha256 = new Sha256();



    //SERİ DENEMELERİN BİR KISMININ SONUÇLARI
    //a.e5z    5 karakter --> kırma süresi 33 saniye---> tüm kombinasyonlar:2.706.784.157--- bulunma denemesi:31.078.769
    //b.e5z    5 karakter --> kırma süresi 70 saniye---> tüm kombinasyonlar:2.706.784.157--- bulunma denemesi:66.231.810
    //a....    5 karakter --> kırma süresi 33 saniye---> tüm kombinasyonlar:2.706.784.157--- bulunma denemesi:31.452.721
    //c....    5 karakter --> kırma süresi 105 saniye---> tüm kombinasyonlar:2.706.784.157--- bulunma denemesi:101.758.803--yeni kodlar 
    //g....    5 karakter --> kırma süresi 302 saniye---> tüm kombinasyonlar:2.706.784.157--- bulunma denemesi:277.524.008
    @Test
    public void testSHA256KoduKirma() {

        int min = 5;
        int max = 5;
        int clientSayisi = 2;
        String karakterSeti = "abcçdefgğhıijklmnoöprsştuüvyzABCÇDEFGĞHIİJKLMNOÖPRSŞTUÜVYZ0123456789.,*-+!?/_";
        System.out.println("Karakter seti length: " + karakterSeti.length());
        String hashKod = "a575235367068ae450c4b587a7d47fbc5fb4126647b2cfeb79a5a67e09c43853"; //kelimesinin sha256 kodu
        GorevDizi[] clientGorevDizi = {new GorevDizi(5, 15)};
        long[] permustasyonDizisi = {0l, 77l, 5929l};

        Gorev gorev = new Gorev.GorevBuilder()
                .setKarakterSeti(karakterSeti)
                .setMinUzunluk(min)
                .setMaxUzunluk(max)
                .setHashKod(hashKod)
                .setClientGorevDizi(clientGorevDizi)
                .setPermutasyonDizisi(permustasyonDizisi).build();

        yaz(gorev);
        yaz("Tüm kombinasyonlar:" + tumKombinasyonlar(permustasyonDizisi));
        //sha256.parolayiKir(gorev, 0);
    }

    private <N> void yaz(String ekMsg, N n) {
        System.out.println(ekMsg + n);
    }

    private long tumKombinasyonlar(long[] permutasyonDizisi) {
        long tplK = 0;
        for (long l : permutasyonDizisi) {
            tplK += l;
        }
        Listeler.addListGenel("Tüm kombinasyonlar:" + tplK);
        return tplK;
    }

    private void yaz(Object obj) {
        System.out.println(obj.toString());
    }

}
