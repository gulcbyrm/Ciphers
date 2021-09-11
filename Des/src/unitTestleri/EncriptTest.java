package unitTestleri;

import des.Des;
import exceptions.KeyLengthUygunDeil;
import org.junit.Assert;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

public class EncriptTest {

    Des des = new Des();


    @Test
    public void testSifrele() throws Exception {
        String result = des.encript("plainText", "key");
        Assert.assertEquals("replaceMeWithExpectedResult", result);
    }



    @Test
    public void test_sifrele_Coz() {
        Des des = new Des();
        try {
            //String encriptedText=des.sifrele("#Eg«Íï","4Wy¼ßñ");

            String plainText = "İnönü Üniversitesi Bilgisayar Mühendisliği";
            System.out.println("Şifrelenecek Metin  :" +plainText);
            String encriptedText = des.encript(plainText, "12345678");
            System.out.println("Şifreli Metin       :" + encriptedText);
            String decriptedText = des.decript(encriptedText, "12345678");
            System.out.println("decriptedText       :" + decriptedText);
        } catch (KeyLengthUygunDeil | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
