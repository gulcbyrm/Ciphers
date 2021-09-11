package unitTestleri;

import ciphers.des.Araclar;

public class toHexMetotTest {

    Araclar dES = new Araclar();

/*
    @Test
    public void toHEX_Turkçe_Tek_Harf_Testi() throws Exception {
        String result = dES.toHex("İ");
        Assert.assertEquals("130", result);
        String result1 = dES.toHex("Ş");
        Assert.assertEquals("15e", result1);
        String result2 = dES.toHex("Ü");
        Assert.assertEquals("dc", result2);
        String result3 = dES.toHex("U");
        Assert.assertEquals("55", result3);
        String result4 = dES.toHex("ç");
        Assert.assertEquals("e7", result4);
    }

    @Test
    public void toHEX_OzelKarakterlerin_Testi() throws Exception {
        String result = dES.toHex("!");
        Assert.assertEquals("21", result);
        String result1 = dES.toHex(" ");
        Assert.assertEquals("20", result1);
        String result2 = dES.toHex(".");
        Assert.assertEquals("2e", result2);
        String result3 = dES.toHex("?");
        Assert.assertEquals("3f", result3);
    }

    @Test
    public void toHEX_Kelime_Testi() throws Exception {
        String result0 = dES.toHex("İnönü");
        Assert.assertEquals("1306ef66efc", result0);
        String result = dES.toHex("Deneme");
        Assert.assertEquals("44656e656d65", result);
        String result2 = dES.toHex("İnönü Üniversitesi Bilgisayar Mühendisliği");
        Assert.assertEquals("1306ef66efc20dc6e697665727369746573692042696c67697361796172204dfc68656e6469736c6911f69", result2);
    }

    @Test
    public void toHEX_UzunVeKarisikMetinTesti() throws Exception {
        String metin = "İnönü Üniversitesi Bilgisayar Mühendisliği. Çeşitli, !";
        String result = dES.toHex(metin);

        Assert.assertEquals(
                "1306ef66efc20dc6e697665727369746573692042696c6769736" +
                        "1796172204dfc68656e6469736c6911f692e20c76515f69746c692c2021", result);
    }*/
}

