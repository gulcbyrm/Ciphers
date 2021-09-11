package des;


import sabitler.KeySabitleri;
import exceptions.KeyLengthUygunDeil;



public class Key extends Abstract {


    public String[] keyUret(final String privateStrKey) throws KeyLengthUygunDeil {
        if (privateStrKey.length() != 8)
            throw new KeyLengthUygunDeil("Key 8 karakter olmalı...");

        final Araclar desT = new Araclar();
        final KeySabitleri sbt = new KeySabitleri();
        final String binariyeCevrilmisMetin = desT.toBinary(privateStrKey);
        final String pc1TablosuylaKaristirilmisBinary = sbt.karistir(sbt.getPC1_Tablosu(), binariyeCevrilmisMetin); //56 bite indirgendi
        final String C0 = getLeft(pc1TablosuylaKaristirilmisBinary);//28 bit C0
        final String D0 = getRight(pc1TablosuylaKaristirilmisBinary);//28 bit D0
        final String[] diziCn = nDizisiniOlustur(C0); //C0 dan 16 tane Cn üretir (Kaydırma tablosunu kullanarak) diziye yükler
        final String[] diziDn = nDizisiniOlustur(D0); //D0 dan 16 tane Dn üretir
        final String[] geciciKeyDizisi = birlestirCnDn(diziCn, diziDn); //Cn ve Dn dizileri birleştirilir ve geçiciKn (key) dizisi elde edilir
        final String[] key = new String[16];

        for (int i = 0; i < 16; i++) {
            key[i] = sbt.karistir(sbt.getPC2(), geciciKeyDizisi[i]); //Geçici key dizisini PC2 tablosuyla 48 bite indirger
        }

        return key;
    }



    /**
     * Bu fonksiyon herhangi bir metni belirlenen miktar kadar sola kaydırır.
     *
     * @param metin  kaydırılacak metin
     * @param miktar kaydırma miktarı
     * @return kaydırılmış sonuç metni
     */
    private String solaKaydir(final String metin, final int miktar) {
        return metin.substring(miktar) + metin.substring(0, miktar);
    }



    /**
     * 16 elemanlı  key dizisini Cn ve Dn birleştirerek oluşturur
     *
     * @param Cn key dizisinin C kısmını oluşturacak binary dizi
     * @param Dn key dizisinin D kısmını oluşturacak binary dizi
     */
    private String[] birlestirCnDn(final String[] Cn, final String[] Dn) {
        final String[] keyCnArtiDn = new String[16];
        for (int i = 0; i < 16; i++) {
            keyCnArtiDn[i] = Cn[i + 1] + Dn[i + 1];
        }
        return keyCnArtiDn;
    }


    /**
     * kendisine gelen binary stringi kaydırma tablosundaki sayı kadar sola kaydırır.
     * @param metin kaydırılacak binary string bu fonksiyona sadece Cn ve Dn dizileri gelmektedir
     * @return 16 elemanıda tabloya göre kaydırılmış dizi
     */
    private String[] nDizisiniOlustur(final String metin) {
        final int[] kaydirmaMiktarTablosu = {1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1};
        final String[] dizi = new String[17];
        dizi[0] = metin;
        for (int i = 1; i < 17; i++) {
            dizi[i] = solaKaydir(dizi[i - 1], kaydirmaMiktarTablosu[i - 1]);
        }
        return dizi;
    }
}
