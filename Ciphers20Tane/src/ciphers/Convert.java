package ciphers;

import tools.Listeler;

import javax.swing.*;

public class Convert {
    private static final String regex=",";

    public static String matrisToStr(char[][] charMatris) {
        StringBuilder str = new StringBuilder();
        for (int sat = 0; sat < charMatris.length; sat++) {
            for (int sut = 0; sut < charMatris[0].length; sut++) {
                str.append(charMatris[sat][sut]);
            }
            str.append(regex);//alt+157 ascii kodu
        }
        return str.toString();
    }




    public static String matrisToStr(char[] charMatris) {
        StringBuilder str = new StringBuilder();
        for (char matris : charMatris) {
            str.append(matris);
        }
        return str.toString();
    }




    public static void matrisiListDetayaYaz(char[][] charMatris) {
        for (int sat = 0; sat < charMatris.length; sat++) {
            StringBuilder sb=new StringBuilder();
            for (int sut = 0; sut < charMatris[0].length; sut++) {
                sb.append(charMatris[sat][sut]+"\t");
            }
            Listeler.addListDetay(sb.toString());
        }
        Listeler.addListDetay(" ");
    }





    public static char[] strToVektor(String str) {
        try {
            char[] chArray=new char[str.length()];
                for (int sut = 0; sut < chArray.length; sut++) {
                    chArray[sut]=str.charAt(sut);
                }
            return chArray;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Girdiğiniz dizi kurallara uygun değil...");
            return null;
        }
    }





    public static String[] split(String src, int len) {
        String[] result = new String[(int) Math.ceil((double) src.length() / (double) len)];
        for (int i = 0; i < result.length; i++)
            result[i] = src.substring(i * len, Math.min(src.length(), (i + 1) * len));
        return result;
    }





    public static char[][] strToMatris(String str) {
        try {
            String[] strDizi=str.split(regex);
            char[][] chArray=new char[strDizi.length][strDizi[0].length()];
            for (int sat = 0; sat < chArray.length; sat++) {
                for (int sut = 0; sut < chArray[0].length; sut++) {
                    chArray[sat][sut]=strDizi[sat].charAt(sut);
                }
            }
            return chArray;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Girdiğiniz dizi kurallara uygun değil...");
            return null;
        }
    }



    //random key üret için kullanılmakta. karakterin matrise ekli olup olmadığını döndürür
    //java8 ile birlikte gelen stream anyMatch fonksiyonu char'ı desteklemediğinden char için kendi metodumu yazdım
    public static String isContain(char[][] matrisDizi, char aranan) {
        for (int sat = 0; sat < matrisDizi.length; sat++) {  //getKeyMatris özellikle getli çağrıldı matris yoksa yeniden oluşturulsun
            for (int sut = 0; sut < matrisDizi[0].length; sut++) {//keyMatris özellikle böyle çağrıldı pc yi yormamak için get ile çağrılması
                if(aranan==matrisDizi[sat][sut]) return (sat+1)+""+(sut+1); //0 dan değil de 1 den başlatmak için +1 eklendi
            }
        }
        return null;
    }


    public static int[] findIndexInMatris(char[][] matris, char aranan){
        final int satirCount=matris.length;
        final int sutunCount=matris[0].length;

        for(int sat=0;sat<satirCount;sat++)
            for (int sut = 0; sut < sutunCount; sut++)
                if (matris[sat][sut] == aranan) return new int[]{sat, sut};
                return new int[]{-1, -1};
    }

    public static String isContain(char[] matrisDizi, char aranan) {
        for (int sat = 0; sat < matrisDizi.length; sat++) {  //getKeyMatris özellikle getli çağrıldı matris yoksa yeniden oluşturulsun
                if(aranan==matrisDizi[sat]) return (sat+1)+""; //0 dan değil de 1 den başlatmak için +1 eklendi
        }
        return null;
    }
}
