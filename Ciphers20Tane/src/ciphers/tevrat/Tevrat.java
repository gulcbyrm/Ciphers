package ciphers.tevrat;

import arayuzler.OneIntKey;
import arayuzler.OneKey;
import ciphers.CipherAbstract;
import ciphers.des.sabitler.Mode;
import entity.FormEntity;
import tools.GetRandom;
import tools.Listeler;
import tools.Tool;

import javax.swing.*;

public class Tevrat extends OneIntKey {


    @Override
    public String sifreleSifreCoz(String islenecekMetin, Mode islemModu) {
        addListDetayBaslangic(islenecekMetin, islemModu);
        StringBuilder sonucMetni = new StringBuilder();
        if (islemModu == Mode.Encript) {
            for (int i = 0; i < islenecekMetin.length(); i++) {
                for (int j = 0; j < getKey(); j++)
                    sonucMetni.append(Tool.getChar(new GetRandom().asInt(stdAlfabeLength)));
                Listeler.addListDetay(sonucMetni.substring(sonucMetni.length() - getKey()) + "\t+\t" + islenecekMetin.charAt(i));
                sonucMetni.append(islenecekMetin.charAt(i));
            }
            for (int j = 0; j < getKey(); j++) sonucMetni.append(Tool.getChar(new GetRandom().asInt(stdAlfabeLength)));
            Listeler.addListDetay(" + " + sonucMetni.substring(sonucMetni.length() - getKey()));
        }else{
            for (int i =getKey(); i < islenecekMetin.length(); i+=(getKey()+1)) {
                sonucMetni.append(islenecekMetin.charAt(i));
               // Listeler.addListDetay(sonucMetni.substring(sonucMetni.length() - getKey()) + "\t+\t" + islenecekMetin.charAt(i));
            }
        }
        addListDetayBitis(sonucMetni, islemModu);
        return sonucMetni.toString();
    }

    @Override
    public FormEntity getThisFormEntity() {
        return new FormEntity.FormEntityBuilder()
                .setFormBasligi("Tevrat (TROaH) Cipher 1940")
                .setAciklama("key olarak sayı girilmelidir, random key üret butonu alfabeden den küçük bir sayı üretecektir\ndüz metinde boşluk ve noktalama işaretleri kullanmayınız")
                .setBtnHakkindaBasligi("Tevrat Hakkında").build();
    }




    @Override
    public String randomKeyUret() {
        return new GetRandom().asStr(stdAlfabeLength);
    }


    @Override
    public boolean keyUygunMu() {
        Integer tmpKey = getKey();
        if (tmpKey != null && tmpKey < 0) {
            JOptionPane.showMessageDialog(null, "Key alfabe boyutunda olmak zorunda.");
            return false;
        }
        return true;
    }
}