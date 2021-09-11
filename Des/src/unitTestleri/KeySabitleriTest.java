package unitTestleri;

import des.Key;
import exceptions.KeyLengthUygunDeil;
import org.junit.Assert;
import org.junit.Test;
import sabitler.KeySabitleri;


public class KeySabitleriTest {

    KeySabitleri sabitler = new KeySabitleri();
    Key key = new Key();

    public KeySabitleriTest() throws KeyLengthUygunDeil {
    }

    @Test
    public void testKaristir() throws Exception {
        String result = sabitler.karistir(sabitler.getPC1_Tablosu(), "0001001100110100010101110111100110011011101111001101111111110001");
        Assert.assertEquals("11110000110011001010101011110101010101100110011110001111", result);
    }

    @Test
    public void test_KEYUret() throws Exception {
        key.keyUret("4Wy¼ßñ");
        key.keyUret("12345678");
    }
  /*  @Test
    public void testGetLeft() throws Exception {
        String result = key.getLeft("11110000110011001010101011110101010101100110011110001111");
        Assert.assertEquals("1111000011001100101010101111", result);
    }

    @Test
    public void testGetRight() throws Exception {
        String result = key.getRight("11110000110011001010101011110101010101100110011110001111");
        Assert.assertEquals("0101010101100110011110001111", result);
    }*/


   /* @Test
    public void tabloyaGoreKaydir_CnUretimi() throws Exception {
        String[] expected={
                "1111000011001100101010101111",
                "1110000110011001010101011111",
                "1100001100110010101010111111",
                "0000110011001010101011111111",
                "0011001100101010101111111100",
                "1100110010101010111111110000",
                "0011001010101011111111000011",
                "1100101010101111111100001100",
                "0010101010111111110000110011",
                "0101010101111111100001100110",
                "0101010111111110000110011001",
                "0101011111111000011001100101",
                "0101111111100001100110010101",
                "0111111110000110011001010101",
                "1111111000011001100101010101",
                "1111100001100110010101010111",
                "1111000011001100101010101111",};
        String[] result=key.CnDizisiniOlustur("1111000011001100101010101111");
       // System.out.println(Arrays.toString(sabitler.CnDizisiniOlustur("1111000011001100101010101111")));
        Assert.assertEquals(expected,result);
    }

    @Test
    public void tabloyaGoreKaydir_DnUretimi() throws Exception {
        System.out.println(Arrays.toString(key.CnDizisiniOlustur("1111000011001100101010101111")));
    }*/

/*
    @Test
    public void solaKaydir_miktarSifir() throws Exception {
        String result = key.solaKaydir("kaydirilacakMetin", 0);
        Assert.assertEquals("kaydirilacakMetin", result);
    }

    @Test
    public void solaKaydir_miktarIki() throws Exception {
        String result = key.solaKaydir("kaydirilacakMetin", 2);
        Assert.assertEquals("ydirilacakMetinka", result);
    }

    @Test
    public void solaKaydir_UcSinirTesti() throws Exception {
        String result = key.solaKaydir("kaydirilacakMetin", 17);
        Assert.assertEquals("kaydirilacakMetin", result);
    }

    //EXCEPTION Test
    @Test(expected = IndexBelirlenenSinirlarinDisindaException.class)
    public void solaKaydir_TasmaTesti() throws Exception {
        String result = key.solaKaydir("kaydirilacakMetin", 18);
    }

    @Test
    public void test_Uret() throws Exception {
        String[] tmpKey=key.birlestirCnDn(key.nDizisiniOlustur("1111000011001100101010101111"), key.nDizisiniOlustur("0101010101100110011110001111"));
        System.out.println(Arrays.toString(tmpKey));
    }*/


   /* @Test
    public void keySon() throws Exception {
        String[] tmpKey=key.birlestirCnDn(key.nDizisiniOlustur("1111000011001100101010101111"), key.nDizisiniOlustur("0101010101100110011110001111"));
        final int[] pc2Tablosu = sabitler.getPC2();
        final String[] kullanilacakKey = new String[16];
        for(int i=0;i<16;i++){
            final String siradakiKey = tmpKey[i];
            kullanilacakKey[i]=sabitler.karistir(pc2Tablosu, siradakiKey);
        }
        System.out.println(Arrays.toString(kullanilacakKey));
    }
*/


//KEY DİZİ
//[11100001100110010101010111111010101011001100111100011110,
// 11000011001100101010101111110101010110011001111000111101,
// 00001100110010101010111111110101011001100111100011110101,
// 00110011001010101011111111000101100110011110001111010101,
// 11001100101010101111111100000110011001111000111101010101,
// 00110010101010111111110000111001100111100011110101010101,
// 11001010101011111111000011000110011110001111010101010110,
// 00101010101111111100001100111001111000111101010101011001,
// 01010101011111111000011001100011110001111010101010110011,
// 01010101111111100001100110011111000111101010101011001100,
// 01010111111110000110011001011100011110101010101100110011,
// 01011111111000011001100101010001111010101010110011001111,
// 01111111100001100110010101010111101010101011001100111100,
// 11111110000110011001010101011110101010101100110011110001,
// 11111000011001100101010101111010101010110011001111000111,
// 11110000110011001010101011110101010101100110011110001111]
}
