package ciphers.affine;


import arayuzler.TwoIntKey;
import ciphers.CipherAbstract;
import entity.FormEntity;
import tools.GetRandom;
import tools.Listeler;
import tools.Tool;
import ciphers.des.sabitler.Mode;
import javax.swing.*;
import java.util.Arrays;

public class Affine extends TwoIntKey {

    private int getA_Invert(int a) {
        for (int i = 0; i < stdAlfabeLength; i++) {
            if (a * i % stdAlfabeLength == 1)
                return i;
        }
        return 0;
    }


    @Override
    public String sifreleSifreCoz(String metin, Mode mod) {
        addListDetayBaslangic(metin,mod);
        StringBuilder sonuc = new StringBuilder();
        for (int i = 0; i < metin.length(); i++) {
            final int index = Tool.getIndex(stdAlfabe, metin.charAt(i));

            if (index == -1) {
                sonuc.append(metin.charAt(i));
                Listeler.addListDetay(metin.charAt(i)+" alfabenin bir karakteri olmadığından şifrelenmedi" );
            }else if (mod == Mode.Decript) {//decript kısmı
                sonuc.append(Tool.getChar(stdAlfabe, ((index - getKey2()) * getA_Invert(getKey1()) % stdAlfabeLength)));
                Listeler.addListDetay(metin.charAt(i)+"\tindex: "+index+"\t(x-b): "+(index - getKey2())+ "\ta invert: "+getA_Invert(getKey1())+"\tformül:Dx=(a invert) (x-b) mod m ");
            } else {//encript kısmı
                sonuc.append(Tool.getChar(stdAlfabe, ((getKey1() * index + getKey2()) % stdAlfabeLength)));
                Listeler.addListDetay(metin.charAt(i)+"\tindex: "+index+"\t(ax+b) yani key1*index+key2: "+(getKey1() * index +
                        getKey2())+"\tmod m: "+((getKey1() * index + getKey2()) % stdAlfabeLength)+"\tformül:Ex=(ax+b) mod m ");
            }
        }
        addListDetayBitis(sonuc,mod);
        return sonuc.toString();
    }


    @Override
    public FormEntity getThisFormEntity() {
        return new FormEntity.FormEntityBuilder()
                .setFormBasligi("AFFINE (Doğrusal) şifreleme")
                .setAciklama("Programda a=0, b=1, c=2,..., z=28 şeklinde standart tablo kullandık.\n" +
                        "Key B yi Max alfabe boyutu kadar belirleyiniz. " +
                        "Key A ise alfabe boyutuyla aralarında asal olmalı alfabe boyutu 29 dur")
                .setBtnHakkindaBasligi("Affine Hakkında").build();

    }




    @Override
    public String randomKeyUret() {
        Listeler.addListDetay("Kullanılacak Alfabe   : " + Arrays.toString(CipherAbstract.stdAlfabe));
        do{ setKey2(new GetRandom().asInt(stdAlfabeLength));} while (getKey2() == 0);
        do{ setKey1(new GetRandom().asInt(stdAlfabeLength - 1) + 1);} while (getKey1() == 0 || stdAlfabeLength % getKey1() == 0);
        return getKey1() + " " + getKey2();
    }

    @Override
    public boolean keyUygunMu() {

        if(getKey2()==null||getKey1()==null){
            JOptionPane.showMessageDialog(null, "Key sayı olmak zorunda");
            return false;
        }

        if (getKey2() > stdAlfabeLength) {
            JOptionPane.showMessageDialog(null, "b_key i alfabe karakterlerinden fazla girdiniz.\nMod alma yapılacağından " + getKey2() % stdAlfabeLength+"'i kullanınız.");
            return false;
        }
        return true;
    }
}
