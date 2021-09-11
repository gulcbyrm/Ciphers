package ciphers.vigenera;

import arayuzler.OneKey;
import entity.FormEntity;
import tools.GetRandom;
import tools.Listeler;
import tools.Tool;
import ciphers.des.sabitler.Mode;
import javax.swing.*;
import java.security.SecureRandom;

public class Vigenera extends OneKey<String> {



    @Override
    public String sifreleSifreCoz(String metin, Mode talep) {
        StringBuilder sonucMetni = new StringBuilder();
        final String lowCaseEncText = metin.toLowerCase().trim();
        for (int i = 0; i < lowCaseEncText.length(); i++) {
            final char chDuzMetin = lowCaseEncText.charAt(i);
            final char chKey = getKey().charAt(i % getKey().length());
            if (Tool.getIndex(stdAlfabe,chDuzMetin) == -1) {              //karakter mi?
                sonucMetni.append(chDuzMetin);
                Listeler.addListDetay("alfabe dışı karakter, işlenmeden eklendi: "+chDuzMetin);
            } else {
                sonucMetni.append(Tool.getChar(stdAlfabe,talep == Mode.Encript ? Tool.getIndex(stdAlfabe,chDuzMetin) + Tool.getIndex(stdAlfabe,chKey) : Tool.getIndex(stdAlfabe,chDuzMetin) - Tool.getIndex(stdAlfabe,chKey)));
                Listeler.addListDetay("DüzMetin: "+chDuzMetin+"\t\tKey: "+chKey+"\t\tişlem sonucu: "+sonucMetni.charAt(i));
            }
        }
        return sonucMetni.toString();
    }



    @Override
    public String randomKeyUret() {
        int keyUzunlugu = 0;
        try {
            keyUzunlugu = Integer.parseInt(JOptionPane.showInputDialog(null, "Key uzunluğu belirleyiniz.", null));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Uzunluk olarak sayı girilmeli..");
        }
        StringBuilder randomKey = new StringBuilder();
        while (randomKey.length() < keyUzunlugu) {
            randomKey.append(Tool.getChar(stdAlfabe,new GetRandom().asInt(stdAlfabeLength)));
        }
        return randomKey.toString();
    }



    /**
     * stringdeki her karaktere bakılır eğer alfabedeki harfler ise sorun yok
     */
    @Override
    public boolean keyUygunMu() {
        if (getKey().isEmpty()) return keyFalseMesaji();
        for (char ch : getKey().toCharArray()) {
            if (!Character.isAlphabetic(ch)) return keyFalseMesaji();
        }
        return true;
    }


    private boolean keyFalseMesaji() {
        JOptionPane.showMessageDialog(null, "Key uygun değil. Key string olmak zorunda");
        return false;
    }


    @Override
    public FormEntity getThisFormEntity() {
        return new FormEntity.FormEntityBuilder()
                .setFormBasligi("VIGENERA (Fransız şifrecisi Blaise de Vigenere)")
                .setAciklama("Programın Kullandığı Alfabe: 'a', 'b', 'c', 'ç', 'd', 'e', 'f', 'g', 'ğ', 'h', 'ı', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'ö', 'p', 'r', 's', 'ş', 't', 'u', 'ü', 'v', 'y', 'z' dir.")
                .setBtnHakkindaBasligi("Vigenere Hakkında").build();
    }



    @Override
    public String getKey() {
        return key;
    }
}
