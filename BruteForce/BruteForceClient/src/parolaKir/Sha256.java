package parolaKir;

import entities.CoreGorev;
import entities.Gorev;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import lombok.Getter;
import lombok.Setter;
import utility.Listeler;

public class Sha256 {

    @Getter @Setter private static boolean threadlerCalismayiDurdursun;
    
    public void parolayiKir(Gorev gelenGorev, int clientNo) {
        final int toplamCoreSayisi = Runtime.getRuntime().availableProcessors();
        final FutureTask[] returnStringi = new FutureTask[toplamCoreSayisi];
        
        Listeler.arkaPlandaEkle("PC de bulunan core sayısı  : " + toplamCoreSayisi);
        Listeler.arkaPlandaEkle("Tüm kombinasyonlar         :" + tumKombinasyonlar(gelenGorev.getPermutasyonDizisi()));
        
        int clientGorevBaslangici = gelenGorev.getClientGorevDizi()[clientNo].getBaslangic();
        int clientGorevBitisi = gelenGorev.getClientGorevDizi()[clientNo].getBitis();

        final CoreGorev[] cg = coreGorevDizisiOlustur(clientGorevBitisi-clientGorevBaslangici, toplamCoreSayisi);
        
        for (int i = 0; i < toplamCoreSayisi; i++) {
            //sırası gelen threade görev verilip yeni üretilen Thread submit edilerek başlatılıyor ve elde edilen sonuçlar future nesnelerini tutan listede saklanıyor
            Callable callable = new ParolaKirParallel(gelenGorev, cg[i], clientNo, i);
            returnStringi[i] = new FutureTask(callable);   
            Thread t = new Thread(returnStringi[i]); 
            t.setDaemon(true);
            t.start();
            
            //threadenin döndürdüğü sonuç listeye aktarılıyor
            Listeler.arkaPlandaEkle(i + " nolu core görevlendirildi.");
            //addParalelSonuclarListesi(returnStringi[i]);
           // Listeler.arkaPlandaEkle("Parola Kırıldı, Sonuç:", returnStringi[i]);
        }
    }


    
    
    //parolanın kırılabileceği en kötü durumu hesaplar
    private long tumKombinasyonlar(long[] permutasyonDizisi) {
        long tplK = 0;
        for (long pd : permutasyonDizisi) {
            tplK += pd;
        }
        return tplK;
    }

    
    
    
    private CoreGorev[] coreGorevDizisiOlustur(int clientKarakterSetiLength, int coreSayisi) {
        final CoreGorev[] coreGorevDizi = new CoreGorev[coreSayisi];
        int gorevAraligi = 0;
        int baslangic = 0, bitis = 0;
        if (clientKarakterSetiLength > coreSayisi) {
            int sayac = 0;
            while (clientKarakterSetiLength % coreSayisi != 0) {
                clientKarakterSetiLength--;
                sayac++;
            }
            gorevAraligi = (int) (clientKarakterSetiLength / coreSayisi);

            for (int i = 0; i < coreSayisi; i++) {
                bitis = baslangic + gorevAraligi;
                if (sayac != 0) {
                    sayac--;
                    bitis++;
                }
                coreGorevDizi[i] = new CoreGorev(baslangic, bitis);
                baslangic = coreGorevDizi[i].getBitis();
                Listeler.arkaPlandaEkle(i+" nolu corenin görev aralığı :"+coreGorevDizi[i].getBaslangic()+" - "+coreGorevDizi[i].getBitis());
            }
        } else {
            Listeler.arkaPlandaEkle("Core görev dizisi oluşturulamadı");
            return null;//hata durumunda aşağıdaki görev oluşturulmayacaktır
        }
        return coreGorevDizi;
    }

    
    
    
    
    
    //    public int gorevBaslangic_Recursive(int[] gorevDizi, int clientNo) {
//        if (clientNo == 1) {
//            return 0;
//        }
//        return gorevDizi[--clientNo - 1] + gorevBaslangic_Recursive(gorevDizi, clientNo);
//    }
}

//public class GorevIsle {
//
//    //Clientlere Görev Başlangıç Aralığı Hesaplama   Seri,Recursive ve Paralel kodları
//    public int gorevBitis_Seri(int[] gorevDizi, int clientNo) {
//        int gBitis = 0;
//        for (int i = 0; i <= clientNo; i++) { //client no 0 iken de for'un çalışması sağlandı
//            gBitis += gorevDizi[i];
//        }
//        return gBitis;
//    }
//
//    public int gorevBaslangic_Recursive(int[] gorevDizi, int clientNo) {
//        if (clientNo == 1) {
//            return 0;
//        }
//        return gorevDizi[--clientNo - 1] + gorevBaslangic_Recursive(gorevDizi, clientNo);
//    }
//
//
//
//    //Clientlere Görev Bitiş Aralığı Hesaplama   Seri,Recursive ve Paralel kodları
//    public int gorevBaslangic_Seri(int[] gorevDizi, int clientNo) {
//        if (clientNo == 0) {
//            return 0; //dögüde -1 kullanılacağından sıfır olayı garantilendi
//        }
//        int gBaslangic = 0;
//        for (int i = 1; i <= clientNo; i++) { //client no 0 iken de for'un çalışması sağlandı
//            gBaslangic += gorevDizi[i - 1];
//        }
//        return gBaslangic;
//    }
//
//    public int gorevBitis_Recursive(int[] gorevDizi, int clientNo) {
//        if (clientNo == 1) {
//            return gorevDizi[0];
//        }
//        return gorevDizi[--clientNo] + gorevBitis_Recursive(gorevDizi, clientNo);
//    }
//
//}
///BAŞKA bir versiyon
//    private final int[] gorevDizi;
//    private final int clientNo;
//    private int gBaslangic;
//
//    public BaslangicBelirleParalel(int[] gorevDizi, int clientNo) {
//        this.gorevDizi = gorevDizi;
//        this.clientNo = clientNo;
//        gBaslangic=0;
//    }
//
//
//    @Override
//    public Integer call() throws Exception {
//       if (clientNo == 0) return 0; 
//        for (int i = 1; i <= clientNo; i++) { 
//            gBaslangic += gorevDizi[i - 1];
//        }
//        return gBaslangic;
//    }

//    
//    public void parolayiKir(Gorev gelenGorev, int clientNo) {
//        final int toplamCoreSayisi = Runtime.getRuntime().availableProcessors();
//        final List<Future<String>> paralelSonuclar = new ArrayList<>();               //threadlerden geri dönen değerleri toplayacak liste
//        final ExecutorService exec = Executors.newFixedThreadPool(toplamCoreSayisi);  //paralel havuz oluşturuldu
//
//        Listeler.arkaPlandaEkle("PC de bulunan core sayısı  : " + toplamCoreSayisi);
//        Listeler.arkaPlandaEkle("Tüm kombinasyonlar         :" + tumKombinasyonlar(gelenGorev.getPermutasyonDizisi()));
//        int clientGorevBaslangici = gelenGorev.getClientGorevDizi()[clientNo].getBaslangic();
//        int clientGorevBitisi = gelenGorev.getClientGorevDizi()[clientNo].getBitis();
//
//        final CoreGorev[] cg = coreGorevDizisiOlustur(clientGorevBitisi-clientGorevBaslangici, toplamCoreSayisi);
//        for (int i = 0; i < toplamCoreSayisi; i++) {
//            //sırası gelen threade görev verilip yeni üretilen Thread submit edilerek başlatılıyor ve elde edilen sonuçlar future nesnelerini tutan listede saklanıyor
//            final Future<String> returnStringi = exec.submit(new ParolaKirParallel(gelenGorev, cg[i], clientNo, i));
//            Listeler.arkaPlandaEkle(i + 1 + ".inci core görevlendirildi.");
//
//            //threadenin döndürdüğü sonuç listeye aktarılıyor
//            paralelSonuclar.add(returnStringi);
//            Listeler.arkaPlandaEkle("Parola Kırıldı, Sonuç:", returnStringi);
//        }
//    }