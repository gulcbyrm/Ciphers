package unitTestleri;

import ciphers.des.Des;
import ciphers.des.exceptions.KeyLengthUygunDeil;
import ciphers.des.sabitler.Mode;
import org.junit.Test;
import java.io.UnsupportedEncodingException;


public class EncriptTest {

    Des des = new Des();


    @Test
    public void test_sifrele_Coz() {
        Des des = new Des();
            //String encriptedText=ciphers.des.ciphers.des.sifrele("#Eg«Íï","4Wy¼ßñ");
            des.key = "12345678";
            String plainText = "İnönü Üniversitesi Bilgisayar Mühendisliği";
            System.out.println("Şifrelenecek Metin  :" + plainText);
            String encriptedText = des.sifreleSifreCoz(plainText, Mode.Encript);
            System.out.println("Şifreli Metin       :" + encriptedText);
            String decriptedText = des.sifreleSifreCoz(encriptedText,Mode.Decript);
            System.out.println("decriptedText       :" + decriptedText);
    }
}
