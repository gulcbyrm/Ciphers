

import entities.CoreGorev;
import entities.Gorev;
import entities.GorevDizi;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.junit.Test;
import parolaKir.Sha256;

public class Sha256KirParallelTest {

    //SERİ DENEMELER
//a.e5z    5 karakter --> kırma süresi 33 saniye---> tüm kombinasyonlar:2.706.784.157--- bulunma denemesi:31.078.769
//b.e5z    5 karakter --> kırma süresi 70 saniye---> tüm kombinasyonlar:2.706.784.157--- bulunma denemesi:66.231.810
//a....    5 karakter --> kırma süresi 33 saniye---> tüm kombinasyonlar:2.706.784.157--- bulunma denemesi:31.452.721
//c....    5 karakter --> kırma süresi 105 saniye---> tüm kombinasyonlar:2.706.784.157--- bulunma denemesi:101.758.803--yeni kodlar 
//g....    5 karakter --> kırma süresi 302 saniye---> tüm kombinasyonlar:2.706.784.157--- bulunma denemesi:277.524.008

    //PARALEL DENEMELER
//a.e5z   5 karakter --> 
//b.e5z    5 karakter --> 
//a....    5 karakter --> 
//c....    5 karakter --> 
//g....    5 karakter --> 
   
   
    //callable ve future arabirimini kullanarak paralel hesaplama
//    @Test
//    public void testCall() throws Exception {
//        //değişkenlere test amaçlı ilk değerler atanıyor
//        final String karakterSeti="abcçdefgğhıijklmnoöprsştuüvyzABCÇDEFGĞHIİJKLMNOÖPRSŞTUÜVYZ0123456789.,*-+!?/_";
//        final int min=5;
//        final int max=5;
//
//        final Gorev gorev = new Gorev.GorevBuilder()
//                .setKarakterSeti(karakterSeti)
//                .setMinUzunluk(min)
//                .setMaxUzunluk(max)
//                .setHashKod("808a20f19352883fdf07c5bc41cb5b38dbfe43d657d2185285ca3d8f8f39c5b7")
//                .setClientGorevDizi(new GorevDizi[]{new GorevDizi(10, karakterSeti.length())})
//                .setPermutasyonDizisi(new long[]{0,0,0,0,2554126}).build();
//
//
//        final int coreSayisi = Runtime.getRuntime().availableProcessors();      //core sayısı elde ediliyor
//        final ExecutorService exec = Executors.newFixedThreadPool(coreSayisi);  //paralel havuz oluşturuldu
//        final List<Future<String>> paralelSonuclar = new ArrayList<>();         //threadlerden geri dönen değerleri toplayacak liste
//
//
//        yaz("Core sayısı        : ", coreSayisi);
//
//
//
//        //paralel çalıştırma başlıyor
//        System.out.println("BAŞLADI");
//        for (int i = 0; i < coreSayisi; i++) {
//            final CoreGorev cg=goreviCoreyeBolustur(gorev,coreSayisi,i);
//            yaz(i+1+".inci corenin görev başlangıcı: ",cg.getBaslangic());
//            //sırası gelen threade görev verilip yeni üretilen Thread submit edilerek başlatılıyor ve elde edilen sonuçlar future nesnelerini tutan listede saklanıyor
//            final Future<String> returnStringi = exec.submit(new Sha256().parolayiKir(gorev, 0));
//            //threadenin döndürdüğü sonuç listeye aktarılıyor
//            paralelSonuclar.add(returnStringi);
//        }
//
//        //threadlerden dönen sonuçlara future nesnesinin get metodu sayesinde erişilerek tek tek yazdırılıyor
//        paralelSonuclar.forEach((ps) -> {
//            try {
//                System.out.println(ps.get());
//                if(ps.get()!=null){
//                    System.out.println("aranan bulunduğundan diğer coreler durduruluyor");
//                    exec.shutdown();
//                }
//                // 
//            } catch (InterruptedException | ExecutionException e) {
//                e.printStackTrace();
//            } finally {
//                //exec.awaitTermination(10, TimeUnit.SECONDS)
//                exec.shutdown();
//            }
//        });
//        //while (!exec.isTerminated());
//        System.out.println("BİTTİ");
//    }
//
//
//
//
//    public CoreGorev goreviCoreyeBolustur(Gorev gorev,int toplamCore, int coreID){
//        final int pay=gorev.getClientGorevDizi()[0]/toplamCore;
//        return new CoreGorev(pay*coreID, pay*(coreID+1));
//    }
//
//    // Generic ve overload yaz metodu
//    private <N> void yaz(String ekMsg, N n) {
//        System.out.println(ekMsg + n);
//    }
//
//    private void yaz(Object obj) {
//        System.out.println(obj.toString());
//    }
}
