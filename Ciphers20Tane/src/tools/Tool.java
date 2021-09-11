package tools;

import java.io.UnsupportedEncodingException;

public class Tool {
    public static char[] turkceAlfabe ={'a', 'b', 'c', 'ç', 'd', 'e', 'f', 'g', 'ğ', 'h', 'ı', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'ö', 'p', 'r', 's', 'ş', 't', 'u', 'ü', 'v', 'y', 'z'};


    //Bu fonksiyon herhangi bir harfin alfabedeki INDEX'ini döndürür
    public static int getIndex(char[] alfabeDizisi, final char aranan) {
        for (int i = 0; i < alfabeDizisi.length; i++)
            if (aranan == alfabeDizisi[i])
                return i;
        return -1;
    }
    //Bu fonksiyon herhangi bir harfin alfabedeki INDEX'ini döndürür
    public static int getIndex(final char aranan) {
        for (int i = 0; i < turkceAlfabe.length; i++)
            if (aranan == turkceAlfabe[i])
                return i;
        return -1;
    }



    //Bu fonksiyon herhangi bir int sayının alfabede karşılık geldiği harfi dndürür
    public  static char getChar(char[] alfabeDizisi, int aranan) {
        aranan %= alfabeDizisi.length;
        return alfabeDizisi[aranan < 0 ? aranan + alfabeDizisi.length : aranan];
    }

    //Bu fonksiyon herhangi bir int sayının alfabede karşılık geldiği harfi dndürür
    public  static char getChar(int aranan) {
        aranan %= turkceAlfabe.length;
        return turkceAlfabe[aranan < 0 ? aranan + turkceAlfabe.length : aranan];
    }


    //Sayının numaric olup olmadığını döndürür
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static String toBinary(String metin) {
        StringBuilder binaryStringi = new StringBuilder();
        for (char ch : metin.toCharArray()) {                              //gelen stringi parçalıyoruz
            binaryStringi.append(toBinary(ch));               //tek tek bit değerlerini elde ediyoruz
        }
        return binaryStringi.toString();
    }



    public static String toBinary(char numeric) {
/*        switch (numeric) {
            case 'İ':
                return "10110100";
            case 'ı':
                return "10110011";
            case 'Ğ':
                return "10110010";
            case 'ğ':
                return "10110001";
            case 'Ş':
                return "10110000";
            case 'ş':
                return "01111111";
        }*/
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            final int s = (numeric & 128) == 0 ? 0 : 1;
            sb.append(s);
            numeric <<= 1;
        }
        return sb.toString();
    }



    public static String binaryToStr(String binaryStr) throws UnsupportedEncodingException {
        StringBuilder metin = new StringBuilder();
        for (int i = 0; i < binaryStr.length(); i = i + 8) {
            String tmp = binaryStr.substring(i, i + 8);
            final char karakter = binaryToTekKarakterStr(tmp);
            metin.append(karakter);
        }
        return metin.toString();
    }

    // toStr metodundan gelen string türünden binari 8lisini char'a çevirir
    public static char binaryToTekKarakterStr(String binary8lisi)  {
/*        switch (binary8lisi) {
            case "10110100":
                return 'İ';
            case "10110011":
                return 'ı';
            case "10110010":
                return 'Ğ';
            case "10110001":
                return 'ğ';
            case "10110000":
                return 'Ş';
            case "01111111":
                return 'ş';
        }*/
        int num = Integer.parseInt(binary8lisi, 2);
        return (char) num;
    }



    public static String XOR(String str1, String str2) {
        StringBuilder xor_Sonucu = new StringBuilder();
        for (int i = 0; i < str1.length(); i++) {
            xor_Sonucu.append(str1.charAt(i) ^ str2.charAt(i));
        }
        return xor_Sonucu.toString();
    }

}
