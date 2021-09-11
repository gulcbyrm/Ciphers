package ciphers.caesar;


import arayuzler.OneIntKey;
import entity.FormEntity;
import tools.GetRandom;
import tools.Listeler;
import tools.Tool;
import ciphers.des.sabitler.Mode;

import javax.swing.*;


public class Caesar extends OneIntKey {


    /**
     * Dn(x) = (e-n) mod 26 formülünü kullanır
     * En(x) = (x+n) mod 26 formülünü kullanır
     *
     * @param islenecekMetin şifrelenecek veya şifresi çözülecek metin
     * @param islemModu      yapılacak işlem türü Encript veya Decript
     * @return sonuç metni
     */
    @Override
    public String sifreleSifreCoz(String islenecekMetin, Mode islemModu) {
        addListDetayBaslangic(islenecekMetin,islemModu);
        StringBuilder sonucMetni = new StringBuilder();
        for (int i = 0; i < islenecekMetin.length(); i++) {
            if (Tool.getIndex(stdAlfabe, islenecekMetin.charAt(i)) != -1) {
                final int chIndex = Tool.getIndex(stdAlfabe, islenecekMetin.charAt(i));//karakterin alfabedeki index nosu
                final int indexeKeyEkleVeyaCikar = islemModu == Mode.Encript ? (chIndex + getKey()) : (chIndex - getKey());
                final char encriptKarakter = Tool.getChar(stdAlfabe, indexeKeyEkleVeyaCikar % stdAlfabeLength);
                sonucMetni.append(encriptKarakter);
                Listeler.addListDetay(i + ")\tkarakter: " + islenecekMetin.charAt(i) +
                        "\talfabe indexi: " + chIndex + "\t Key " + key + (islemModu == Mode.Encript ? "\ttoplamı: " : " \tçıkar: ") + indexeKeyEkleVeyaCikar + "\tsonuc: " + encriptKarakter);
            } else sonucMetni.append(islenecekMetin.charAt(i));
        }
        addListDetayBitis(sonucMetni,islemModu);
        return sonucMetni.toString();
    }




    @Override
    public FormEntity getThisFormEntity() {
        return new FormEntity.FormEntityBuilder()
                .setFormBasligi("Caesar (Transposition And Substitution Cipher)")
                .setAciklama("Tarihteki İlk Şifreleme Türü. Diktatör Julius Caesar kullandığından Caesar(SEZAR) adıyla bilinmektedir.\n" +
                        "key olarak sezar her zaman 3 kullanmışsa da. Türkçe için key olarak 1-29 arası bir sayı kullanabilirsiniz.")
                .setBtnHakkindaBasligi("Caesar Hakkında").build();

    }


    @Override
    public String randomKeyUret() {
        return new GetRandom().asStr(stdAlfabeLength);
    }


    @Override
    public boolean keyUygunMu() {
        Integer tmpKey = getKey();
        if (tmpKey != null && tmpKey < 0 || tmpKey > stdAlfabeLength) {
            JOptionPane.showMessageDialog(null, "Key alfabe boyutunda olmak zorunda.");
            return false;
        }
        return true;
    }
}
