package ciphers.polybius;

import arayuzler.OneKey;
import ciphers.Convert;
import entity.FormEntity;
import tools.GetRandom;
import tools.Listeler;
import tools.Tool;
import ciphers.des.sabitler.Mode;


public class Polybius extends OneKey<char[][]> {
    private char[][] keyMatris; //5x6 matris kullanacağız 5 satır 6 sütun
    public  final char[] alfabe = {'a', 'b', 'c', 'ç', 'd', 'e', 'f', 'g', 'ğ', 'h', 'ı', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'ö', 'p', 'r', 's', 'ş', 't', 'u', 'ü', 'v', 'y', 'z',' '};



    public Polybius() {
       // keyMatris=Convert.strToMatris(key);
    }



    @Override
    public char[][] getKey() {
        if(keyMatris==null)
            keyMatris=Convert.strToMatris(key);
        return keyMatris;
    }



    @Override
    public String sifreleSifreCoz(String islenecekMetin, Mode islemModu) {
        if (islemModu == Mode.Encript) return sifrele(islenecekMetin);
        else return sifreCoz(islenecekMetin);
    }


    public String sifrele(String islenecekMetin) {
        Listeler.addListDetay("\nMetin: " + islenecekMetin);
        Listeler.addListDetay("Yapılacak İşlem: Düz Metni Şifreleme (encript)\n");
        StringBuilder encript = new StringBuilder();
        for (int i = 0; i < islenecekMetin.length(); i++) {
            final char islenecekKarakter = islenecekMetin.charAt(i);
            final String matrisSatSut = Convert.isContain(getKey(), islenecekKarakter);
            if (matrisSatSut != null) {
                encript.append(matrisSatSut).append(" ");
                Listeler.addListDetay("Karakter: " + islenecekKarakter + "\tcipher karşılığı : " + matrisSatSut+" ");
            }
        }
        Listeler.addListDetay("Encript fonksiyonu bitti sonuç: " + encript);
        //buraya ekle
        return encript.toString();
    }



    public String sifreCoz(String sifreliMetin) {
        Listeler.addListDetay("\nMetin: " + sifreliMetin);
        Listeler.addListDetay("İşlem: Şifreli Metni Çözme (Decript)");
        StringBuilder decript = new StringBuilder();
        String[] cipherDizi=sifreliMetin.split(" ");
        for (String s : cipherDizi) {
            final int satir = Integer.parseInt(String.valueOf(s.charAt(0))) - 1;
            final int sutun = Integer.parseInt(String.valueOf(s.charAt(1))) - 1;
            decript.append(getKey()[satir][sutun]);
            Listeler.addListDetay("Şifreli Sayı : " + s + " yani satır:" + (satir + 1) + "\tsütun:" + (sutun + 1) + " dama tahtasındaki karşılığı : " + keyMatris[satir][sutun] + " ");
        }
        Listeler.addListDetay("Decript fonksiyonu bitti sonuç: " + decript+"\n");
        return decript.toString();
    }



    @Override
    public FormEntity getThisFormEntity() {
        return new FormEntity.FormEntityBuilder()
                .setFormBasligi("Polybius ( Tarihçi Polybius MÖ 230 - 120 )")
                .setAciklama("Polybius key olarak dama tahtası yani Matris kullanır.\n" +
                             "Ör: 3x5 matris için key girdisi örneklenecek olursa:  ertyu,ıopğü,asdfg,zcvbn  gibi olmalıdır.\n"+
                        "programda 5*6=30 karakter yani 29 türkçe harf ve 1 boşluk karakteri kullanmaktayız")
                .setBtnHakkindaBasligi("Polybius Hakkında").build();
    }



    @Override
    public String randomKeyUret() {
        final int satir = 5;
        final int sutun = 6;
        keyMatris = new char[satir][sutun];
        Listeler.getListDetay().clear();
        Listeler.addListDetay("Key Random olarak " + satir + "x" + sutun + " boyutunda oluşturuldu (Boşluk karakteri de ekli)...");
        Listeler.addListDetay("\t1\t2\t3\t4\t5\t6");
        if(alfabe.length<(satir*sutun)){
            Listeler.addListDetay("dizinin boyutu ("+satir*sutun+") alfabe boyutundan ("+alfabe.length+") büyük olduğundan tekrarsız dizi oluşturulamadı");
        }else{
            for (int sat = 0; sat < satir; sat++) {
                StringBuilder satirElemanlari = new StringBuilder(sat + 1 + "\t");
                for (int sut = 0; sut < sutun; sut++) {
                    int rSayi = new GetRandom().asInt(alfabe.length);
                    final char ch = Tool.getChar(alfabe,rSayi);
                    if (Convert.isContain(keyMatris, ch) == null) {
                        keyMatris[sat][sut] = ch;
                        satirElemanlari.append(ch).append("\t");
                    } else {
                        --sut;
                    }
                }
                Listeler.addListDetay(satirElemanlari.toString());
            }
        }
        return Convert.matrisToStr(keyMatris);
    }




    @Override
    public boolean keyUygunMu() {
        return true;
    }
}