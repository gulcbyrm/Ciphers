package arayuzler;


import ciphers.des.sabitler.Mode;
import entity.FormEntity;
import tools.Listeler;
import tools.Tool;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;


public abstract class CipherI {
    protected char[] stdAlfabe = Tool.turkceAlfabe;
    protected int stdAlfabeLength = stdAlfabe.length;

    public abstract String sifreleSifreCoz(String islenecekMetin, Mode islemModu);
    public abstract String randomKeyUret();
    public abstract FormEntity getThisFormEntity();
    public abstract boolean keyUygunMu();

    protected void addListDetayBaslangic(String islenecekMetin, Mode islemModu) {
        Listeler.addListDetay("İŞLEM: " + (islemModu == Mode.Encript ? "Şifreleme" : "Şifre Çözme") );
        Listeler.addListDetay("METİN: " + islenecekMetin);
        Listeler.addListDetay("------------------------------------------------------------------------------");

    }


    protected void addListDetayBitis(StringBuilder sonucMetni, Mode islemModu) {
        Listeler.addListDetay((islemModu == Mode.Encript ? "ENCRIPT METIN: " : "DECRIPT METIN: ") + sonucMetni);
        Listeler.addListDetay("------------------------------------------------------------------------------");
    }
}