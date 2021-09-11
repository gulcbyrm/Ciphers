package ciphers.hill;

import arayuzler.OneKey;
import ciphers.Convert;
import ciphers.des.sabitler.Mode;
import entity.FormEntity;
import tools.GetRandom;
import tools.Listeler;
import tools.Tool;

import java.util.Arrays;

public class Hill extends OneKey<char[][]> {
    private char[][] keyMatris;

    @Override
    public String sifreleSifreCoz(String islenecekMetin, Mode islemModu) {
        addListDetayBaslangic(islenecekMetin, islemModu);
        //şifreleme
        final String[] blokDizi = Convert.split(islenecekMetin, getKey().length);
        Listeler.addListDetay("Adım 1. Metin matris boyutunda bloklara ayrılır: " + Arrays.toString(blokDizi));
        Listeler.addListDetay("Adım 2. Metin blok blok şifrelenir");
        final StringBuilder islemSonucu = new StringBuilder();
        for (String blok : blokDizi) {
            final char[] vektor = Convert.strToVektor(blok);
            islemSonucu.append(blokSifrele(vektor));
        }
        Listeler.addListDetay("Adım 3. şifreli bloklar birleştirilerek şifreli metin elde edilmiş olur");
        addListDetayBitis(islemSonucu, islemModu);
        return islemSonucu.toString();
    }




    private String blokSifrele(char[] msgVektor) {
        char[] sifreliVektor = new char[keyMatris.length];
        for (int i = 0; i < msgVektor.length; i++) {
            int eleman = 0;
            for (int j = 0; j < 1; j++) {
                eleman += Tool.getIndex(keyMatris[i][j]) * Tool.getIndex(msgVektor[j]);
            }
            sifreliVektor[i] = Tool.getChar(eleman % stdAlfabeLength);
        }
        Listeler.addListDetay("blok: " + Arrays.toString(msgVektor) + "\t-->\t" + Arrays.toString(sifreliVektor));
        return Convert.matrisToStr(sifreliVektor);
    }




    @Override
    public FormEntity getThisFormEntity() {
        return new FormEntity.FormEntityBuilder()
                .setFormBasligi("Hill Cipher (Lester S.Hill - 1929)")
                .setAciklama("key olarak tersi alınabilir kare matris zorunludur. \"Ör: 2x2 matris için key örneklenecek olursa:  ed,dc şeklinde yazınız.")
                .setBtnHakkindaBasligi("Hill Hakkında").build();
    }




    @Override
    public String randomKeyUret() {
        keyMatris = new char[3][3];
        int k = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                final int rSayi = new GetRandom().asInt(stdAlfabeLength);
                final char rKarekter = Tool.getChar(stdAlfabe, rSayi);
                if (Convert.isContain(keyMatris, rKarekter) == null) {//değeri içermiyorsa
                    keyMatris[i][j] = Tool.getChar(stdAlfabe, rSayi);
                } else j--;
            }
        }
        Listeler.addListDetay("Key Matris Random olarak 3x3 boyutunda oluşturuldu");
        Convert.matrisiListDetayaYaz(keyMatris);
        return Convert.matrisToStr(keyMatris);
    }


    @Override
    public boolean keyUygunMu() {
        return true;
    }



    @Override
    public char[][] getKey() {
        if (keyMatris == null) {
            keyMatris = Convert.strToMatris(key);
            Listeler.addListDetay("Key Matris oluşturuldu" + Arrays.toString(keyMatris));
        }
        return keyMatris;
    }
}
