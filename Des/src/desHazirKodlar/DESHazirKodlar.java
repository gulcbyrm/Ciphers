package desHazirKodlar;


import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * TEST AMAÇLI YAZILMIŞ BİR SINIFTIR. Projemizle alakası yoktur.
 * hazır java kodlarıyla DES'in implementasyonudur
 * Amaç DES şifrelemenin ayrıca hazır java kodlarıylada nasıl çalıştığını görmek
 */

public class DESHazirKodlar {

    //Variables
    private Cipher desCipher;
    SecretKey myDesKey;



    //Constructure
    public DESHazirKodlar() {
        try {
            this.myDesKey = KeyGenerator.getInstance("DES").generateKey();
            desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
    }




    //Encript
    public byte[] encript(String plainText) throws BadPaddingException, IllegalBlockSizeException, InvalidKeyException {
        desCipher.init(Cipher.ENCRYPT_MODE, myDesKey);
        return desCipher.doFinal(plainText.getBytes());
    }




    //Decript
    public byte[] decript(byte[] enciptedText) throws BadPaddingException, IllegalBlockSizeException, InvalidKeyException {
        desCipher.init(Cipher.DECRYPT_MODE, myDesKey);
        return desCipher.doFinal(enciptedText);
    }
}
