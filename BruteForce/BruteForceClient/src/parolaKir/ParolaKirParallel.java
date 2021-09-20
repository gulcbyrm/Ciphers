
package parolaKir;

import entities.Gorev;
import entities.CoreGorev;
import java.util.concurrent.Callable;
import org.apache.commons.codec.digest.DigestUtils;
import ui.ClientFXMLController;
import utility.Listeler;


public class ParolaKirParallel implements Callable<String> {

    private final Gorev grv;
    private final CoreGorev coreGrv;
    private final int clientNo;
    private final int coreNo; //core no sadece kullanıcıya bilgi vermek amacıyla kullanılıyor. bir işlevi yok

    final String hash;
    private long sayac;  //geçici

    public ParolaKirParallel(Gorev grv, CoreGorev coreGrv, int clientNo, int coreNo) {
        this.grv=grv;
        this.coreGrv=coreGrv;
        this.hash = grv.getHashKod();
        this.clientNo=clientNo;
        this.coreNo=coreNo;
    }

    @Override
    public String call() throws Exception {
        return sha256Kir();
    }

    
    public String sha256Kir() {
        final String kSeti=grv.getKarakterSeti();
        final int gbitis = grv.getClientGorevDizi()[clientNo].getBaslangic()+coreGrv.getBitis();//77
        final int gBaslangic =grv.getClientGorevDizi()[clientNo].getBaslangic()+ coreGrv.getBaslangic();
        final int tumKarakterUzunlugu=grv.getKarakterSeti().length();
        baslangic:
        for (int i = grv.getMinUzunluk(); i <= grv.getMaxUzunluk(); i++) {
            int aktifUzunluk = i;
            

            for (int h = aktifUzunluk == 8 ? gBaslangic : 0; h <  (aktifUzunluk == 8 ? gbitis : tumKarakterUzunlugu); h++) {
                final StringBuilder aranan = new StringBuilder();
                if (aktifUzunluk == 8) {
                    aranan.append(kSeti.charAt(h));
                }
                for (int g = aktifUzunluk == 7 ? gBaslangic : 0; g <  (aktifUzunluk == 7 ? gbitis : tumKarakterUzunlugu); g++) {
                    if (aktifUzunluk >= 7) {
                        aranan.append(kSeti.charAt(g));
                    }
                    for (int f = aktifUzunluk == 6 ? gBaslangic : 0; f <  (aktifUzunluk == 6 ? gbitis : tumKarakterUzunlugu); f++) {
                        if (aktifUzunluk >= 6) {
                            aranan.append(kSeti.charAt(f));
                        }
                        for (int e = aktifUzunluk == 5 ? gBaslangic : 0; e < (aktifUzunluk == 5 ? gbitis : tumKarakterUzunlugu); e++) {
                            if (aktifUzunluk >= 5) {
                                aranan.append(kSeti.charAt(e));
                            }
                            for (int d = aktifUzunluk == 4 ? gBaslangic : 0; d <  (aktifUzunluk == 4 ? gbitis : tumKarakterUzunlugu); d++) {
                                if (aktifUzunluk >= 4) {
                                    aranan.append(kSeti.charAt(d));
                                }
                                for (int c = aktifUzunluk == 3 ? gBaslangic : 0; c <  (aktifUzunluk == 3 ? gbitis : tumKarakterUzunlugu); c++) {
                                    if (aktifUzunluk >= 3) {
                                        aranan.append(kSeti.charAt(c));
                                    }
                                    
                                    if(Sha256.isThreadlerCalismayiDurdursun()) {
                                        Listeler.arkaPlandaEkle("CoreNo: "+coreNo+" Thread durdurma talimatı aldığından durduruldu ");
                                        return null;
                                    }  

                                    for (int b = aktifUzunluk == 2 ? gBaslangic : 0; b <  (aktifUzunluk == 2 ? gbitis : tumKarakterUzunlugu); b++) {
                                        if (aktifUzunluk >= 2) {
                                            aranan.append(kSeti.charAt(b));
                                        }

                                        
                                        
                                        for (int a = aktifUzunluk == 1 ? gBaslangic : 0; a < tumKarakterUzunlugu; a++) {
                                            //aranan oluştuuldu Sha256 koduna çevrilerek mevcut hash kod ile karşılaştırılacak
                                            aranan.append(kSeti.charAt(a));
                                            sayac++;
                                            //hashkodlar eşit ise parola kırıldı
                                            if (DigestUtils.sha256Hex(aranan.toString()).equals(hash)) {
                                                //serveri bilgilendir
                                                Listeler.arkaPlandaEkle("Parola Kırıldı:"+aranan);
                                                Listeler.arkaPlandaEkle("Parola "+coreNo+" nolu core tarafından "+sayac+"'ıncı denemede kırıldı.");
                                                //parola kırıldığından diğer threadlerin kendini durduracağı işareti bırak
                                                Sha256.setThreadlerCalismayiDurdursun(true);

                                                ClientFXMLController.getClient().sendObj("Parola Kırıldı SONUÇ: "+aranan);
//                                                try {
//                                                    Thread.sleep(2000);
//                                                } catch (InterruptedException ex) {
//                                                    System.out.println(ex.getMessage());
//                                                }
                                                return aranan.toString();
                                            }
                                            aranan.setLength(aranan.length() - 1);//son karakteri silmede deleteCharAt(length-1) den çok başarılı
                                        }
                                        if (aktifUzunluk == 1) {
                                            continue baslangic;
                                        }
                                        if (aranan.length() > 0) {
                                            aranan.setLength(aranan.length() - 1);
                                        }
                                    }
                                    if (aktifUzunluk == 2) {
                                        continue baslangic;
                                    }
                                    if (aranan.length() > 0) {
                                        aranan.setLength(aranan.length() - 1);
                                    }
                                }
                                if (aktifUzunluk == 3) {
                                    continue baslangic;
                                }

                                if (aranan.length() > 0) {
                                    aranan.setLength(aranan.length() - 1);
                                }
                            }
                            if (aktifUzunluk == 4) {
                                continue baslangic;
                            }
                            if (aranan.length() > 0) {
                                aranan.setLength(aranan.length() - 1);
                            }
                        }
                        if (aktifUzunluk == 5) {
                            continue baslangic;
                        }
                        if (aranan.length() > 0) {
                            aranan.setLength(aranan.length() - 1);
                        }
                    }
                    if (aktifUzunluk == 6) {
                        continue baslangic;
                    }
                    if (aranan.length() > 0) {
                        aranan.setLength(aranan.length() - 1);
                    }
                }
                if (aktifUzunluk == 7) {
                    continue baslangic;
                }
                if (aranan.length() > 0) {
                    aranan.setLength(aranan.length() - 1);
                }
            }
        }
        return null;
    }

}
