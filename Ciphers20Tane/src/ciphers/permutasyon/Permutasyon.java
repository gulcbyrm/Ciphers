package ciphers.permutasyon;

import ciphers.CipherAbstract;
import ciphers.des.sabitler.Mode;
import entity.FormEntity;

public class Permutasyon  extends CipherAbstract {
    @Override
    public String sifreleSifreCoz(String islenecekMetin, Mode islemModu) {
        return null;
    }


    @Override
    public FormEntity getThisFormEntity() {
        return new FormEntity.FormEntityBuilder()
                .setBtnHakkindaBasligi("Permütasyon Hakkında").build();

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
