package ciphers.des;


import arayuzler.CipherI;
import ciphers.des.sabitler.EncriptDecriptSabitleri;
import ciphers.des.exceptions.KeyLengthUygunDeil;
import ciphers.des.sabitler.Mode;
import entity.FormEntity;
import tools.GetRandom;
import tools.Listeler;
import tools.Tool;

import javax.swing.*;
import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

/**
 * des.txt ADIMLARI
 * 1) metni veya HEX ifadeyi al
 * 2) metni 8in tam katı değilse boşluk ekleyerek 8in tam katı olarak tamamla
 * 3) metni 8li bloklara ayır, 8li blokları listeye koy
 * 4) burada döngü açılır listedeki eleman sayısı kadarlık bir döngü
 * 5) 8li string bloğu 64 bit binary sayıya çevir
 * 6) 64 bit işleme girmeyeceğinden indirgemek lazım initial permütation tablosuyla 56 bite indirgenir.
 * 7) 56 bit binary olan sayıyı L0 ve R0 adlarıyla ikiye ayır (28 bit L ve 28 bit R)
 * 8) kullanıcının verdiği keyi K0 kabul et ve ilki K1 sonuncusu K16 olmak üzere 16 tane key türet
 * 9) ve burada 16 round başlar
 * 10) 28 bit olan R0 'ı E tablosuna tabi tutarak 48 bite yükselt. yeni durumun adı ER0 oldu
 * key 48 bite indirgenmiş olduğundan R0'ı da 48 bite çıkardık ki XOR yapılabilsin
 * 11) ER0 ile key1 i XOR la çıkan sonuca F_Fonksiyon_Adım1 dersek
 * 12) F_Fonksiyon_Adım1'i 8 eşit parçaya ayır. çünkü S tablolarıyla işleme tabi tutulacaktır
 * 13) burada 8 li döngü başlar. elimizde 6 bitten oluşan 8 tane binary sayı var
 * 14) S tablolarına tabi tutulunca 32 bite indirgenir S tablo iş az karmaşıktır
 * Örneğin 010011 sayısı olsun 0 1001 1 diye parçalanır kenarda duran 01 sayısı satırı
 * 1001 yani decimal 9 sayısı ise sütunu gösterir. S tablosunun ilgili satır sütundaki elemanı alınır.
 * ve S tablosundan çıkmış 32 bite indirgenmiş yeni bir R0 var elimizde buna SR0 dersek
 * 15)SR0 bir kez daha InitPermütasyona tabi tutulur sonuca  sonR0 dersek
 * 16) sonR0 ise L0 ile XOR işlemine tabi tutulur. ve buradan çıkan sonuç R1 olur
 * 17) L1 'i elde etmek ise basittir. R0 direk L1 olarak alınır. ve booylecek R1,L1 elde edilip 1 round bitirilmiş olur
 * 18) bu şekilde her biri bir öncekinden türeyen 16 round tamamlanır. en sondaki L16 ve R16 birbirine terster eklenerek yani önce R16 artı L16 alınır.
 * 19) ve son bir inv_permütasyon tablosuna tabi tutulur.
 * 20) şifreli 64 bit hazırdır. stringe çevrilir
 */


public class Des extends Abstract {

    public String key;


    /**
     * kendisine gelen şifreleme ve şifre çözme taleplerini yerine getirir.
     * <p>
     * param mod            Encript, decript seçeneklerinden biri
     * param cipher         verinin hangi cipher ile şifreleneceği ör:Des,caesar.txt,Affine vs..
     * param islenecekMetin şifrelenecek veya şifresi çözülecek metin
     * param key            şifreleme ve çözmede kullanılacak keyi ifade eder
     * return şifreleme sonucu veya şifresi çözülmüş düz metin
     *//*
    String sifreleCoz(Mode mod, Ciphers cipher, String islenecekMetin, String key) {

        try {
            switch (cipher) {
                case des.txt:
                    return desSifrele(mod, islenecekMetin, key);
                case caesar.txt:
            }
        } catch (KeyLengthUygunDeil e) {
            JOptionPane.showMessageDialog(null, "Key 8 karakter olmak zorunda");
        } catch (UnsupportedEncodingException ex) {
            JOptionPane.showMessageDialog(null, "Encode tipi desteklenmiyor");
        }
        return null;
    }*/


/*    //şifreleme işini gerçekleştiren metod
    public String encript(final String plainText) throws UnsupportedEncodingException, KeyLengthUygunDeil {
        return sifrele(plainText, key, Mode.Encript);
    }


    //şifre çözen metod
    public String decript(final String encriptedText) throws UnsupportedEncodingException, KeyLengthUygunDeil {
        final Araclar araclar = new Araclar();
        //String binary= araclar.hexToBinary(encriptedText);//gelen hexi binarye çevir   //eklenen1
        StringBuilder binary = new StringBuilder();
        araclar.hexToBinary(binary, encriptedText);
        String str = araclar.toStr(binary.toString());//eklenen2
        return sifrele(str, key, Mode.Decript);
    }*/


    @Override
    public String sifreleSifreCoz(String islenecekMetin, Mode islemModu) {
        try {
            return sifrele(islenecekMetin, key, islemModu);
        } catch (UnsupportedEncodingException | KeyLengthUygunDeil e) {
            e.printStackTrace();
            return null;
        }
    }


    //Türkçe karaktelerinde encrip decriptini yapabilmektedir.
    private String sifrele(final String plainText, String key, Mode mod) throws UnsupportedEncodingException, KeyLengthUygunDeil {
        final EncriptDecriptSabitleri sbt = new EncriptDecriptSabitleri();                  // sabit tabloların bulunduğu class
        final Araclar araclar = new Araclar();                                              // ciphers.des.ciphers.des yardımcı araçlarının olduğu class tobinary vs..
        final String sekizinKatinaTamamlanmisPlainText = sekizinKatinaTamamla(plainText);   //eğer metin 8in tam katı değilse 8in katına tamamlar
        final List<String> plaintTextList = new ArrayList<>();                              //metin 8 li bloklara bölünüp plaintTextBlocks doldurulacaktır
        parcalaPlainTexti(plaintTextList, sekizinKatinaTamamlanmisPlainText);               //8 karakter string den oluşan liste
        StringBuilder encriptedString = new StringBuilder();                                //şifrelenmiş metin doldurulacak
        for (String blockText : plaintTextList) {                                           //her 8 li block alınıp şifrelenmeye gönderiliyor
            final String biteCevrilmisHali = Tool.toBinary(blockText);  //64BİT          //bloklar bite çevriliyor toplam:64 bit
            Listeler.addListDetay("--------------------------------------------------------------------------------------------------------------------");
            Listeler.addListDetay("şifrelenecek blok [String]: " + blockText);
            Listeler.addListDetay("şifrelenecek blok [Binary]: " + biteCevrilmisHali);
            String initPermSonrasiBit = sbt.karistir(sbt.getInitialPermTable(), biteCevrilmisHali); //64 bite KARIŞTIRILDI
            //FEİSTEL BAŞLIYOR
            String R_arti_L = onAltiRounduIsle(initPermSonrasiBit, key, mod);

            final String R16SonKaristirma = sbt.karistir(sbt.getInitialPerm_inv(), R_arti_L);

            //final String encriptedBlock =araclar.toStr(R16SonKaristirma);//*****
            final String encriptedBlock = mod == Mode.Encript ? araclar.toHex64BitBinary(R16SonKaristirma) : araclar.toStr(R16SonKaristirma);//*****
            encriptedString.append(encriptedBlock);
            Listeler.addListDetay("L16+R16 AfterPerm\t: " + R_arti_L);
            //Listeler.addListDetay("R+L[16] P tablosuyla Permütasyon: "+ R16SonKaristirma);
            Listeler.addListDetay("Şifrelenmiş Block\t: " + encriptedBlock);
        }

        Listeler.addListDetay("Şifrelenmiş Metin\t: " + encriptedString.toString());
        Listeler.addListDetay("--------------------------------------------------------------------------------------------------------------------");
        return encriptedString.toString();
    }


    /**
     * des.txt şifrelerken 56 bite indirgenmiş olan binary veriyi sol ve sağ diye ikiye böler daha sonra
     * sağ bloğu E tablosuyla 48 bite genişletir.
     * kullanıcıdan alınan key ile 16 adet key türetilir. key üretme işi Key sınıfına bırakılmıştır.
     * çıkan sonuç ile sırasındaki key fonksiyona tabi tutulur fonksiyon 3 aşamalı olup ayrı bir metod olarak yazıldı
     * fonksiyondan çıkan sonuç ile Left taraf XOR işlemine tabi tutulur
     * bu şekilde 16 round işlenen veri L16+R16 olarak return edilir
     * özetle formülü şu şekildedir: Rn=Ln-1+f(Rn-1,Kn)
     *
     * @param initPermSonrasiBit stringin bloklara parçalanıp bitlere dönüştürüldükten sonra 56 bite indirgenen formu
     * @param mod                encript veya decript seçeneklerinden biri
     * @return 16 round sonucundaki L16 ve R16 nın toplamı
     * @throws KeyLengthUygunDeil bizim yazdığımız throw keyin 8 haneli olmasını zorunlu kılar
     */
    private String onAltiRounduIsle(String initPermSonrasiBit, String islenmisKey, Mode mod) throws KeyLengthUygunDeil {
        final EncriptDecriptSabitleri sbt = new EncriptDecriptSabitleri();                  //tabloların bulunduğu class
        final String[] subKeys = subKeyUret(islenmisKey);                                           //subKeyUret fonksiyonu 16 adet 48 bite indirgenmiş key üretir
        String[] L = new String[17];                                                    //sol:28 bit için dizi
        String[] R = new String[17];                                                    //sağ:28 bit için dizi
        //sol ve sağ  ilk 0ınci değeri veriliyor ancak bu kullanılmayacak bunlardan türetilecek L1..L16 ve R1..R16 aralığı kullanılacak
        L[0] = getLeft(initPermSonrasiBit);//32
        R[0] = getRight(initPermSonrasiBit);//32

        //16 ROUND BAŞLADI
        //Rn=Ln-1+f(Rn-1,Kn) işlemi yapılacak
        for (int i = 1; i <= 16; i++) {
            L[i] = R[i - 1];                                                //Formül Ln=Rn-1
            final String ERn = sbt.karistir(sbt.getE_Tablosu(), R[i - 1]);  //28 bit 48 bite yükseltildi
            final String f_fonks_degeri = f(ERn, mod == Mode.Encript ? subKeys[i - 1] : subKeys[16 - i]);         //f(Rn-1,Kn) // sonuç 32 bit çıkar
            R[i] = XOR(L[i - 1], f_fonks_degeri);                           //Rn=Ln-1+f(Rn-1,Kn)
            Listeler.addListDetay("Round " + i + "\t\tLeft: " + L[i] + "\t\tRight: " + R[i]);
        }
        return R[16] + L[16];
    }


    /**
     * kullanıcıdan gelen 8 haneli stringden key dizisi üretir
     *
     * @param keyStringi 8 haneli gizli key olacak string
     * @return Kn dizisi
     * @throws KeyLengthUygunDeil key stringi 8 haneli değilse bu execption fırlatılır
     */
    public String[] subKeyUret(final String keyStringi) throws KeyLengthUygunDeil {
        final Key anahtar = new Key();
        return anahtar.keyUret(keyStringi);
    }


    /**
     * f fonksiyonu 3 adımda hesaplanır. for döngüsü içinde 16 defa çağrılacaktır
     *
     * @param fonksion_E_48bit E tablosuyla yükseltilmiş 48 bit
     * @param key              XOR işlemine tabi tutulacak keydir ve her round için yeni bir key fonksiyona gelir
     * @return f fonksiyonu sonucu
     */
    private String f(final String fonksion_E_48bit, final String key) {
        final EncriptDecriptSabitleri sbt = new EncriptDecriptSabitleri();
        final Araclar araclar = new Araclar();

        final String fonksiyon_F = XOR(fonksion_E_48bit, key);       //Kn ile XORlanmış fonksiyon_E
        final String[] B = new String[8]; //6 bitlik bloklar toplam 8 tane olacak
        StringBuilder tmpR = new StringBuilder();
        for (int j = 0; j < 8; j = j + 1) {   //48 bitlik dizi 8 eşit parçaya bölünüyor her parça 6 bit
            B[j] = fonksiyon_F.substring(j * 6, (j + 1) * 6);
            int satir = araclar.binaryToDecimal("" +
                    toInt(B[j].charAt(0)) +
                    toInt(B[j].charAt(5)));
            int sutun = araclar.binaryToDecimal("" +
                    toInt(B[j].charAt(1)) +
                    toInt(B[j].charAt(2)) +
                    toInt(B[j].charAt(3)) +
                    toInt(B[j].charAt(4)));
            int sayi = sbt.S_tablolari[j][satir][sutun];
            tmpR.append(araclar.decimalToBinary(sayi));     // 10 --1010
        }
        return sbt.karistir(sbt.getP(), tmpR.toString());
    }


    /**
     * Bu metod **RECURSIVE yazıldı. gelen stringin eksik bitlerini tamamlar
     *
     * @param plainText eksikse tamamlanması istenen string
     * @return 8'in katları şeklinde string
     */
    private String sekizinKatinaTamamla(final String plainText) {
        final int fark = plainText.length() % 8;
        if (fark == 0) return plainText;
        return sekizinKatinaTamamla(plainText + " ");
    }


    /**
     * XOR işlemi
     *
     * @param str1 string 1  1 ve 2 eşit olmak zorunda
     * @param str2 string 2
     * @return XORlanmış string
     */
    private String XOR(String str1, String str2) {
        StringBuilder xor_Sonucu = new StringBuilder();
        for (int i = 0; i < str1.length(); i++) {
            xor_Sonucu.append(str1.charAt(i) ^ str2.charAt(i));
        }
        return xor_Sonucu.toString();
    }


    /**
     * Bu metod RECURSIVE yazıldı.
     * Bu metod stringi 8'er li bloklara ayırarak listeye doldurur.
     * gelen metin 8in tam katı uzunlukta olması garantilenmiştir.
     *
     * @param sekizinKatiPlainText parçalanacak metin.
     * @param list                 parçalanmış düz metin bu listeye doldurulur.
     */

    private void parcalaPlainTexti(List<String> list, final String sekizinKatiPlainText) {
        final int plainTextBoyutu = sekizinKatiPlainText.length();
        if (plainTextBoyutu == 0) return;
        list.add(sekizinKatiPlainText.substring(0, 8));
        parcalaPlainTexti(list, sekizinKatiPlainText.substring(8));
    }


    //S tabloları için kullanılmaktadır
    private int toInt(char ch) {
        return ch == '0' ? 0 : 1;
    }



    @Override
    public FormEntity getThisFormEntity() {
        return new FormEntity.FormEntityBuilder()
                .setFormBasligi("DES (Data Encription Standart) Implementation")
                .setAciklama("1997 de şifreleme standardı olarak kabul edildi ve 2000 de yerini AES'e bıraktı")
                .setBtnHakkindaBasligi("DES Hakkında").build();

    }


    @Override
    public String randomKeyUret() {
        StringBuilder key = new StringBuilder();
        for (int i = 0; i < 8; i++)
            key.append(new GetRandom().asInt(9));
        return key.toString();
    }



    @Override
    public boolean keyUygunMu() {
        if (key.length() != 8) {
            JOptionPane.showMessageDialog(null, "Key 8 karakter olmak zorunda.");
            return false;
        } else return true;
    }
}
