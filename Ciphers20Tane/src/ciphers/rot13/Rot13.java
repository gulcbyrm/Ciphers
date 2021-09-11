package ciphers.rot13;

import ciphers.CipherAbstract;
import ciphers.des.sabitler.Mode;
import entity.FormEntity;

public class Rot13 extends CipherAbstract {
    @Override
    public String sifreleSifreCoz(String islenecekMetin, Mode islemModu) {
        return null;
    }


    @Override
    public FormEntity getThisFormEntity() {
        return new FormEntity.FormEntityBuilder()
                .setBtnHakkindaBasligi("Rot13 Hakkında").build();

    }



    @Override
    public String randomKeyUret() {
        return null;
    }

    @Override
    public boolean keyUygunMu() {
        return false;
    }
}