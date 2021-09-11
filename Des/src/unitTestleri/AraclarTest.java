package unitTestleri;

import des.Araclar;
import org.junit.Assert;
import org.junit.Test;

public class AraclarTest {
    Araclar araclar = new Araclar();

    @Test
    public void testToBinary() throws Exception {
        String result = araclar.toBinary("metin");
        Assert.assertEquals("replaceMeWithExpectedResult", result);
    }

    @Test
    public void hexToBinary() throws Exception {
        StringBuilder result = new StringBuilder();
        araclar.hexToBinary(result,"5C515");
        Assert.assertEquals("01011100010100010101", result);
    }
    @Test
    public void hexToBinary1() throws Exception {
        StringBuilder result = new StringBuilder();
        araclar.hexToBinary(result,"AA1FD8A0A0619D02");
        Assert.assertEquals("1010101000011111110110001010000010100000011000011001110100000010", result);
    }

    @Test
    public void testToHex64BitBinary() throws Exception {
        String result = araclar.toHex64BitBinary("01011100010100010101");
        Assert.assertEquals("5C515", result);
    }
    @Test
    public void testToHex64BitBinary1() throws Exception {
        String result = araclar.toHex64BitBinary("1010101000011111110110001010000010100000011000011001110100000010");
        Assert.assertEquals("AA1FD8A0A0619D02", result);
    }
}

