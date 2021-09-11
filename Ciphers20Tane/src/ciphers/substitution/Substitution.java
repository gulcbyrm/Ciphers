package ciphers.substitution;


import arayuzler.OneKey;
import ciphers.Convert;
import ciphers.des.sabitler.Mode;
import entity.FormEntity;
import tools.GetRandom;
import tools.Listeler;
import javax.swing.*;
import static tools.Tool.getChar;
import static tools.Tool.getIndex;

public class Substitution extends OneKey<char[]> {
    public char keyDizi[]; //= {'k', 'l', 'm', 'n', 'o', 'ö', 'p', 'r', 's', 'ş', 't', 'u', 'ü', 'v', 'y', 'z', 'a', 'b', 'c', 'ç', 'd', 'e', 'f', 'g', 'ğ', 'h', 'ı', 'i', 'j'};


protected char getSonEleman(StringBuilder str){
    final int sonIndex=str.length()-1;
    return str.charAt(sonIndex);
}
    @Override
    public String sifreleSifreCoz(String islenecekMetin, Mode islemModu) {
        StringBuilder islemSonucu = new StringBuilder();
        addListDetayBaslangic(islenecekMetin,islemModu);
        for (int i = 0; i < islenecekMetin.length(); i++) {
            //Şifrele
            if(islemModu==Mode.Encript){
                islemSonucu.append(getChar(keyDizi, getIndex(stdAlfabe,islenecekMetin.charAt(i))));
                Listeler.addListDetay(islenecekMetin.charAt(i)+"\tkey dizideki karşılığı: "+getSonEleman(islemSonucu));
            }
            //Şifre Çöz
            else{
                islemSonucu.append(getChar(stdAlfabe, getIndex(keyDizi, islenecekMetin.charAt(i))));
                Listeler.addListDetay(islenecekMetin.charAt(i)+"\talfabe dizisindeki karşılığı: "+getSonEleman(islemSonucu));
            }
        }
        addListDetayBitis(islemSonucu, islemModu);
        return islemSonucu.toString();
    }




    @Override
    public FormEntity getThisFormEntity() {
        return new FormEntity.FormEntityBuilder()
                .setFormBasligi("Substitution (Yerine Koyma Şifrelemesi)")
                .setAciklama("Key olarak rastgele karıştırılmış harf dizisi kullanır.\nKeyi karakter katarı giriniz, program diziye çevirecektir Örnek Key: socyğediünhuaıpjökrlgşbvmtzçf")
                .setBtnHakkindaBasligi("Substitution Hakkında").build();
    }




    @Override
    public String randomKeyUret() {
        keyDizi = new char[stdAlfabe.length];
            for (int sat = 0; sat < stdAlfabe.length; sat++) {
                StringBuilder satirElemanlari = new StringBuilder(sat + 1 + "\t");
                    int rSayi = new GetRandom().asInt(stdAlfabe.length);
                    final char ch = getChar(stdAlfabe, rSayi);
                    if (Convert.isContain(keyDizi, ch) == null) {
                        keyDizi[sat] = ch;
                        satirElemanlari.append(ch).append("\t");
                    } else {
                        --sat;
                    }
            }
        return Convert.matrisToStr(keyDizi);
    }




    @Override
    public boolean keyUygunMu() {
        if(keyDizi.length<stdAlfabeLength){
                JOptionPane.showMessageDialog(null, "belirlemiş olduğunuz dizinin eleman sayısı yetersiz. standart alfabeden az karakter var\n"+
                        "şifreleme esnasında boşta kalan elemanlar olacağından, geçersizdir.\nLütfen geçerli bir key giriniz");
                return false;
            }
        return true;
    }




    @Override
    public char[] getKey() {
        return keyDizi;
    }
}
