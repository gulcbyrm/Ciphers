package unitTestleri;

import ciphers.des.Araclar;
import org.junit.Assert;
import org.junit.Test;
import tools.Tool;

public class ToolsTest {

    Araclar dES = new Araclar();

    @Test
    public void toStr_Test() throws Exception {
        Assert.assertEquals("Ş", dES.toStr("10110000"));
        Assert.assertEquals("Ğ", dES.toStr("10110010"));
    }
    @Test
    public void toStr_Test_kelime() throws Exception {
        Assert.assertEquals("ŞĞ", dES.toStr("1011000010110010"));
        Assert.assertEquals("denemeD?.*=", dES.toStr("0110010001100101011011100110010101101101011001010100010000111111001011100010101000111101"));
        Assert.assertEquals("çdenemeD?.*=İ", dES.toStr("11100111011001000110010101101110011001010110110101100101010001000011111100101110001010100011110110110100"));
    }
    @Test
    public void toStr_Test_keldsdime() throws Exception {
        Assert.assertEquals("1011000010110010", Tool.toBinary("ŞĞ"));
        Assert.assertEquals("0110010001100101011011100110010101101101011001010100010000111111001011100010101000111101", Tool.toBinary("denemeD?.*="));
        Assert.assertEquals("11100111011001000110010101101110011001010110110101100101010001000011111100101110001010100011110110110100", Tool.toBinary("çdenemeD?.*=İ"));
    }
    @Test
    public void toBinary_Test() throws Exception {
        //Tek harf
        Assert.assertEquals("00101010", Tool.toBinary("*"));
        Assert.assertEquals("01100001", Tool.toBinary("a"));
        Assert.assertEquals("11100111", Tool.toBinary("ç"));
        Assert.assertEquals("11000111", Tool.toBinary("Ç"));
        Assert.assertEquals("01011110", Tool.toBinary("Ş"));
        Assert.assertEquals(
                "11110110" + //hatasız ö
                        "11010110" + //hatasız Ö
                        "11111100" + //hatasız ü
                        "11011100" + //hatasız Ü
                        "11100111" + //hatasız ç
                        "11000111" + //hatasız Ç
                        "10110100" + //hata  YENİ DURUM: İ--> 10110100
                        "10110011" + //hata  YENİ DURUM: ı--> 10110011
                        "10110010" + //hata  YENİ DURUM: Ğ--> 10110010
                        "10110001" + //hata  YENİ DURUM: ğ--> 10110001
                        "10110000" + //hata  YENİ DURUM: Ş--> 10110000
                        "01111111",  //hata  YENİ DURUM: ş--> 01111111
                Tool.toBinary("öÖüÜçÇİıĞğŞş"));
        // Assert.assertEquals("111001110011111100100111001000011100011111010110101011110011010010010111000101100100011110001010100010101100101111001101101001100000010000010011000101001001110111001111110000101010001011110010110100101011", dES.toBinary("ç?'!ÇÖŞi.,Ğ*+/6İ ıIÜü*/-+"));
        //Kelime
   /*     Assert.assertEquals("0110000101100100011000010110111001100001", dES.toBinary("adana".getBytes()));
        //KüçükBüyükHarf karışık
        Assert.assertEquals("010000010110111001101011011000010111001001100001", dES.toBinary("Ankara".getBytes()));
        //Küçük Büyük Harf özel karakter karışık
        Assert.assertEquals("01000001010110100101010000111010001011100011111101100001001011000111011101110001", dES.toBinary("AZT:.?a,wq".getBytes()));
        //Boşluk
        Assert.assertEquals("00100000", dES.toBinary(" ".getBytes()));
        //Türkçe Karakter
        String metin="ç";
        System.out.println(Arrays.toString(metin.getBytes()));
        Assert.assertEquals("11100111", dES.toBinary(metin.getBytes()));*/

        // Assert.assertEquals("100110000", dES.toBinary("İ".getBytes()));
    }

}

