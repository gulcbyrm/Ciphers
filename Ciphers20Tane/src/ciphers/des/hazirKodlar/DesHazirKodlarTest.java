package ciphers.des.hazirKodlar;


import org.junit.Test;
import java.util.Arrays;


public class DesHazirKodlarTest {


    @Test
    public void encriptDecript() throws Exception {
        DESHazirKodlar des = new DESHazirKodlar();
         //ciphers.des.ciphers.des.dco();
        final String plainText = "İnönü";
        final byte[] encriptedText = des.encript(plainText);
        final byte[] decriptedText = des.decript(encriptedText);

        System.out.println("Düz Metin                 :" + plainText);
        System.out.println("Düz Metin [BYTE]  :" + Arrays.toString(plainText.getBytes()));
        System.out.println("Şifrelenmiş Metin         :" + new String(encriptedText));
        System.out.println("Şifrelenmiş Metin [BYTE]  :" + Arrays.toString(encriptedText));
        System.out.println("Çözülmüş Metin            :" + new String(decriptedText));
        System.out.println("Çözülmüş Metin    [BYTE]  :" +Arrays.toString(decriptedText));
    }
}