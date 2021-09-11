package ciphers.vernam;

import arayuzler.OneKey;
import ciphers.des.sabitler.Mode;
import entity.FormEntity;
import tools.GetRandom;
import tools.Listeler;
import javax.swing.*;
import static tools.Tool.*;

public class Vernam extends OneKey<String> {
    @Override
    public String sifreleSifreCoz(String islenecekMetin, Mode islemModu) {
        //Key oluşturma adımları (vernama özel burada oluşturuldu ve kontrol edildi)
        if(key==null||key.isEmpty()){
            setKey(runtimeKeyUret(islenecekMetin));
            Listeler.addListDetay("key üretildi: "+getKey());
            JOptionPane.showMessageDialog(null,"Key runtime da üretildi, Detaylar sekmesinden kontrol edebilirsiniz.");
        }

        if(key.length()<islenecekMetin.length()){
            JOptionPane.showMessageDialog(null,"Key Uzunluğu "+key.length()
                    +" tir. Metnin uzunluğu ise: "+islenecekMetin.length()+" tir. Key şifrelenecek metinden kısa olamaz.");
            return null;
        }

        //Şifreleme ve şifre çözme
        StringBuilder islemSonucu = new StringBuilder();
        addListDetayBaslangic(islenecekMetin,islemModu);
        for (int i = 0; i < islenecekMetin.length(); i++) {
                //Şifreleme ve çözme aynı
                final String metninBinaryKarsiligi=toBinary(islenecekMetin.charAt(i));
                final String keyinBinaryKarsiligi=toBinary(getKey().charAt(i));
                final String xorSonucu=XOR(metninBinaryKarsiligi, keyinBinaryKarsiligi);
                final String duzenlenmisXOR=islemModu==Mode.Encript?1+xorSonucu.substring(1):0+xorSonucu.substring(1);
                islemSonucu.append(binaryToTekKarakterStr(duzenlenmisXOR));
                //sonuçlar list view de gösteriliyor
                Listeler.addListDetay(
                        "metin: "+islenecekMetin.charAt(i)+"\tbinari-> "+metninBinaryKarsiligi
                        +"\tkey: "+key.charAt(i)  +"\tbinary-> "+keyinBinaryKarsiligi
                        +"\tmetin XOR key: "+xorSonucu +"\tdüzenlenmiş XOR : "+duzenlenmisXOR   +"\tsonuç: "+binaryToTekKarakterStr(xorSonucu)     );
        }
        addListDetayBitis(islemSonucu, islemModu);
        return islemSonucu.toString();
    }

    private String runtimeKeyUret(String islenecekMetin) {
        StringBuilder runTimeKey = new StringBuilder();
        for (int i = 0; i < islenecekMetin.length(); i++) {
            int rSayi = new GetRandom().asInt(stdAlfabe.length);
            final char ch = getChar(stdAlfabe, rSayi);
            runTimeKey.append(ch);
        }
        return runTimeKey.toString();
    }

    @Override
    public FormEntity getThisFormEntity() {
        return new FormEntity.FormEntityBuilder()
                .setAciklama("Vernamda şifrelenecek metin key boyutundan az olamaz bu sebeple key alanını boş bırakabilirsiniz program runtime da keyi oluşturacaktır.\n" +
                        "program Vernam için Türkçe karakter şifrelemesi henüz yapmamaktadır.")
                .setFormBasligi("Vernam Cipher ( a symmetrical stream cipher )")
                .setBtnHakkindaBasligi("Vernam Hakkında").build();
    }


    @Override
    public String randomKeyUret() {
        JOptionPane.showMessageDialog(null,"vernam da key şifrelenecek metnin uzunluğu kadar olacağından\n"+
                "Key alanını boş bırakmanız durumunda şifreleme yapılacakken key otomatik üretilecektir.");
        return null;
    }

    @Override
    public boolean keyUygunMu() {
        return true;//kullanıcı keyin otomatik üretilmesini istiyor olabilir şifreleme aşamasında bakılacak
    }

    @Override
    public String getKey() {
        return key;
    }
}