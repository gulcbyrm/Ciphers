package parolaKir;

import entities.Gorev;
import org.apache.commons.codec.digest.DigestUtils;



public class ParolaKirSeri {

    
    
    private String sha256Hesapla(String plainText) {
        return DigestUtils.sha256Hex(plainText);
    }
        

    public void sha256Kir(Gorev grv) {
        final int basamakSayisi = grv.getKarakterSeti().length();
        final String kSeti = grv.getKarakterSeti();
        final String hash = grv.getHashKod();
        //final int gBaslangic=grv.getGorevDizi()[grv.getClientNo()];
        //final int gBitis=grv.getGorevDizi()[grv.getClientNo()];

        long sayac = 0;
        baslangic:
        for (int i = grv.getMinUzunluk(); i <= grv.getMaxUzunluk(); i++) {
            int aktifUzunluk = i;
            int gBaslangic = 0;

            for (int h = aktifUzunluk == 8 ? gBaslangic : 0; h < basamakSayisi; h++) {
                final StringBuilder aranan = new StringBuilder();
                if (aktifUzunluk == 8) {
                    aranan.append(kSeti.charAt(h));
                }
                for (int g = aktifUzunluk == 7 ? gBaslangic : 0; g < basamakSayisi; g++) {
                    if (aktifUzunluk >= 7) {
                        aranan.append(kSeti.charAt(g));
                    }
                    for (int f = aktifUzunluk == 6 ? gBaslangic : 0; f < basamakSayisi; f++) {
                        if (aktifUzunluk >= 6) {
                            aranan.append(kSeti.charAt(f));
                        }
                        for (int e = aktifUzunluk == 5 ? gBaslangic : 0; e < basamakSayisi; e++) {
                            if (aktifUzunluk >= 5) {
                                aranan.append(kSeti.charAt(e));
                            }
                            for (int d = aktifUzunluk == 4 ? gBaslangic : 0; d < basamakSayisi; d++) {
                                if (aktifUzunluk >= 4) {
                                    aranan.append(kSeti.charAt(d));
                                }
                                for (int c = aktifUzunluk == 3 ? gBaslangic : 0; c < basamakSayisi; c++) {
                                    if (aktifUzunluk >= 3) {
                                        aranan.append(kSeti.charAt(c));
                                    }

                                    for (int b = aktifUzunluk == 2 ? gBaslangic : 0; b < basamakSayisi; b++) {
                                        if (aktifUzunluk >= 2) {
                                            aranan.append(kSeti.charAt(b));
                                        }

                                        for (int a = aktifUzunluk == 1 ? gBaslangic : 0; a < basamakSayisi; a++) {
                                            aranan.append(kSeti.charAt(a));
                                            sayac++;
                                            if (DigestUtils.sha256Hex(aranan.toString()).equals(hash)) {
                                                System.out.println("Müjde Aranan Bulundu. Aranan:" + aranan);
                                                System.out.println("Kaç denemede bulunduğu:" + sayac);
                                                return;
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
    }
}
