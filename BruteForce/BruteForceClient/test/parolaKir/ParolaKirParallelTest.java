/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parolaKir;

import entities.CoreGorev;
import entities.Gorev;
import entities.GorevDizi;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import org.junit.Test;
import utility.Listeler;




public class ParolaKirParallelTest {
    
    
    
    public ParolaKirParallelTest() {
    }



    
    
    @Test
    public void testSha256Kir() {
        System.out.println("sha256Kir");
        int clientNo=0;
        Gorev gelenGorev = createGorev();
        
        final int toplamCoreSayisi = Runtime.getRuntime().availableProcessors();
        final FutureTask[] returnStringi = new FutureTask[toplamCoreSayisi];
        
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

        
        ParolaKirParallel instance = new ParolaKirParallel(gelenGorev,new CoreGorev(0, 16),1,0);
        instance.sha256Kir();;
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
}
