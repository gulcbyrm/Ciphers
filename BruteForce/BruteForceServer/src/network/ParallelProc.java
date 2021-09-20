package network;

import entities.GorevDizi;
import utility.Listeler;


public class ParallelProc {
    

    public long[] permutasyonDizisiOlustur(int karakterSetiUzunlugu, int sifreMinLength, int sifreMaxLength) {
        long[] permutasyonDizisi = new long[sifreMaxLength];
        long tplOlasilik = 0;
        for (int i = permutasyonDizisi.length - 1; i >= 0; i--) {
            long usSonuc = 1;
            
            for (int j = 1; j <= i + 1; j++) {//ÜS ALMA 
                usSonuc *= karakterSetiUzunlugu;
            }

            if (usSonuc != 1) tplOlasilik += usSonuc;
            if ((i + 1) >= sifreMinLength) {
                permutasyonDizisi[i] = usSonuc;
            } else {
                permutasyonDizisi[i] = 0;
            }
        }
        Listeler.addList(Listeler.getListGenel(), "Hesaplanması gereken toplam kombinasyon : " + tplOlasilik);

        return permutasyonDizisi;
    }

    
    
    
    
    
    public GorevDizi[] clientGorevDizisiOlustur(int karakterSetiLength, int clientSayisi) {
        final GorevDizi[] clientGorevDizisi = new GorevDizi[clientSayisi];
        int gorevAraligi = 0;
        int baslangic=0, bitis=0;
        if (karakterSetiLength > clientSayisi) {
            int sayac = 0;
            while (karakterSetiLength % clientSayisi != 0) {
                karakterSetiLength--;
                sayac++;
            }

            gorevAraligi = (int) (karakterSetiLength / clientSayisi);

            for (int i = 0; i < clientSayisi; i++) {
                bitis=baslangic+gorevAraligi;
                if (sayac != 0) {
                    sayac--;
                    bitis++;
                }
                clientGorevDizisi[i]=new GorevDizi(baslangic,bitis);
                baslangic=clientGorevDizisi[i].getBitis();
            }
        } else {
            
            Listeler.addList(Listeler.getListGenel(), "desteklenen maximum client sayısı aşıldı"  );
            return null;//hata durumunda aşağıdaki görev oluşturulmayacaktır
        }
        return clientGorevDizisi;
    }
}
