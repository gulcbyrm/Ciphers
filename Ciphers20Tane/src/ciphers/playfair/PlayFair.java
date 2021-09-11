package ciphers.playfair;

import arayuzler.OneKey;
import ciphers.CipherAbstract;
import ciphers.Convert;
import ciphers.des.sabitler.Mode;
import entity.FormEntity;
import tools.GetRandom;
import tools.Listeler;
import tools.Tool;

import javax.swing.*;

public class PlayFair extends OneKey<char[][]> {
    private char[][] keyMatris;
    public char[] alfabe = {'a', 'b', 'c', 'ç', 'd', 'e', 'f', 'g', 'ğ', 'h', 'ı', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'ö', 'p', 'r', 's', 'ş', 't', 'u', 'ü', 'v', 'y', 'z', 'x', 'q', 'w'};

    @Override
    public String sifreleSifreCoz(String islenecekMetin, Mode islemModu) {
        addListDetayBaslangic(islenecekMetin, islemModu);
        String[] ikiliBloklarDizisi = Convert.split(islenecekMetin, 2);
        StringBuilder islemSonucu=new StringBuilder();
        for (int i = 0; i < ikiliBloklarDizisi.length; i++) {
            if(ikiliBloklarDizisi[i].length()<2){
                //son elemanın tek olarak gelme durumu oluyor böyle bir durumda matris işlemeyeceğinden son eleman şifreli gibi eklenir
                islemSonucu.append(ikiliBloklarDizisi[i]);
                Listeler.addListDetay("blok: " + ikiliBloklarDizisi[i] + "\t" +  "son blok ikili olmadığından matris uygulanamaz, cipher kendisidir-> " +  ikiliBloklarDizisi[i]);

            }
            else islemSonucu.append(blokSifrele(ikiliBloklarDizisi[i]));
        }
        addListDetayBitis(islemSonucu, islemModu);
        return islemSonucu.toString();
    }


    @Override
    public FormEntity getThisFormEntity() {
        return new FormEntity.FormEntityBuilder()
                .setFormBasligi("Playfair Cipher (1854 - Charles Wheatstone)")
                .setAciklama("key olarak normal düz metin giriniz. örnek:İnönüÜniversitesi")
                .setBtnHakkindaBasligi("PlayFair Hakkında").build();

    }

    @Override
    public char[][] getKey() {
        if (keyMatris == null)
            randomKeyUret();
        return keyMatris;
    }

    private String blokSifrele(String blok) {
        int satirCount = keyMatris.length;
        int sutunCount = keyMatris[0].length;
        int[] ilkKarakterinIndexi = Convert.findIndexInMatris(keyMatris, blok.charAt(0));
        int[] ikinciKarakterinIndexi = Convert.findIndexInMatris(keyMatris, blok.charAt(1));
        char ilkKarakterinCipherliHali;
        char ikinciKarakterinCipherliHali;

        //Kural1: alınan 2 li bloklardaki harfler matriste aynı satırda ise harflerin birer sağındaki değer alınır
        if (ilkKarakterinIndexi[0] == ikinciKarakterinIndexi[0]) { //0 da satırdaki pozisyonu 1 de ise sütündaki pozisyonu mevcut
            ilkKarakterinCipherliHali = keyMatris[ilkKarakterinIndexi[0]][(ilkKarakterinIndexi[1] + 1) % sutunCount];
            ikinciKarakterinCipherliHali = keyMatris[ikinciKarakterinIndexi[0]][(ikinciKarakterinIndexi[1] + 1) % sutunCount];
            Listeler.addListDetay("Kural1: alınan 2 li bloklardaki harfler matriste aynı satırda ise harflerin birer sağındaki değer alınır");
        }

        //Kural2- alınan 2 li bloklar aynı sütunda ise birer altındaki değer alınır.
        else if (ilkKarakterinIndexi[1] == ikinciKarakterinIndexi[1]) { //0 da satırdaki pozisyonu 1 de ise sütündaki pozisyonu mevcut
            ilkKarakterinCipherliHali = keyMatris[(ilkKarakterinIndexi[0] + 1) % satirCount][ilkKarakterinIndexi[1]];
            ikinciKarakterinCipherliHali = keyMatris[(ikinciKarakterinIndexi[0] + 1) % satirCount][ikinciKarakterinIndexi[1]];
            Listeler.addListDetay("Kural2- alınan 2 li bloklar aynı sütunda ise birer altındaki değer alınır.");
        }

        //Kural3- alınan 2 li bloklar aynı satırda ve aynı sütunda değilse dikdörtgen köşesi gibi düşünülür ve o harflerin köşelerine denk gelen ters köşeler alınır.
        else { //0 da satırdaki pozisyonu 1 de ise sütündaki pozisyonu mevcut
            ilkKarakterinCipherliHali = keyMatris[ilkKarakterinIndexi[0]][ikinciKarakterinIndexi[1]];
            ikinciKarakterinCipherliHali = keyMatris[ikinciKarakterinIndexi[0]][ilkKarakterinIndexi[1]];
            Listeler.addListDetay("Kural3- alınan 2 li bloklar aynı satırda ve aynı sütunda değilse dikdörtgen köşesi gibi düşünülür ve o harflerin köşelerine denk gelen ters köşeler alınır.");
        }
        Listeler.addListDetay("blok: " + blok + "\t" + blok.charAt(0) + " için cipher -> " + ilkKarakterinCipherliHali + "\t\t" + blok.charAt(1) + " için cipher -> " + ikinciKarakterinCipherliHali);
        Listeler.addListDetay("");
        return "" + ilkKarakterinCipherliHali + ikinciKarakterinCipherliHali;
        //1- alınan 2 li bloklar aynı harf ise arasına X yerleştirilir.

    }


    @Override
    public String randomKeyUret() {
        if (key == null || key.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Key kutusuna metin girmeniz gerekiyor. Daha sonra key matrisini girdiğiniz metne göre otomatik oluşturacağım.");
            return null;
        }
        final int satir = 8;
        final int sutun = 4;
        keyMatris = new char[satir][sutun];
        Listeler.getListDetay().clear();
        Listeler.addListDetay("Key Random olarak " + satir + "x" + sutun + " boyutunda oluşturuldu");
        Listeler.addListDetay("\t1\t2\t3\t4");

        int keySayac = 0;
        int alfabeSayac = 0;
        for (int sat = 0; sat < satir; sat++) {
            StringBuilder satirElemanlari = new StringBuilder(sat + 1 + "\t"); //List detaya yazdırmak için matris görselleştiriliyor

            for (int sut = 0; sut < sutun; sut++) {
                final char ch = keySayac == key.length() ? alfabe[alfabeSayac++] : key.charAt(keySayac++);
                if (Convert.isContain(keyMatris, ch) == null) {
                    keyMatris[sat][sut] = ch;
                } else {
                    --sut;
                    continue;
                }
            }
            //List detay için matris görselleştiriliyor
            satirElemanlari.append(keyMatris[sat][0]).append("\t").append(keyMatris[sat][1]).append("\t").append(keyMatris[sat][2]).append("\t").append(keyMatris[sat][3]).append("\t");
            Listeler.addListDetay(satirElemanlari.toString());
        }
        JOptionPane.showMessageDialog(null, "Key matrisi oluşturuldu. Detaylar listesinden kontrol edebilirsiniz.");
        return Convert.matrisToStr(keyMatris);
    }


    @Override
    public boolean keyUygunMu() {
        //uzun yada kısa girmesi sorun olmaz null olmasına bakılır.
        if (key == null || key.isEmpty()) {
            JOptionPane.showMessageDialog(null, "key metni girmediniz oluşturulacak matris key metni olmadığından standart sıralı alfabe olacaktır.\n" +
                    "bu durum ise şifreli metnin herkes tarafında kolaylıkla kırılmasına yol açacağından key metnini girmek zorundasınız");
            return false;
        }
        return true;
    }
}