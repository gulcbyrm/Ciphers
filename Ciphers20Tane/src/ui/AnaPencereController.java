package ui;

import arayuzler.CipherI;
import arayuzler.OneKey;
import arayuzler.TwoKey;
import ciphers.*;
import ciphers.des.Des;
import ciphers.des.sabitler.Mode;
import entity.FormEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import tools.Listeler;

import javax.swing.*;


public class AnaPencereController extends BaseView implements Initializable {

    private CipherI cipherInterface;
    public static SelectedCipher secilenCipher;


    //bu fonksiyon cipher sınıflarını üretir
    //cipher clasının üretimi için Factory Pattern Design kullanılmaktadır.
    public CipherI getCipherInterface() {
        return cipherInterface;
    }


    //secilenSifrelemeTekniginin null gönderilmeme durumu garanti edilmek zorunda
    public void setCipherInterface() {
        this.cipherInterface = new CipherFactory().createCipher(secilenCipher);
        Listeler.getListDetay().clear();
    }


    //des.txt clicked
    @FXML
    void sifrelemeSec(ActionEvent event) {
        final String btnText = ((Button) event.getSource()).getText().toUpperCase();
        secilenCipher = SelectedCipher.valueOf(btnText);

        formuSecilenSifrelemeyeGoreDizaynEt();
        setCipherInterface(); //her butona tıklatıldığında yenisi üretilmektedir

        FormEntity entity=getCipherInterface().getThisFormEntity();
        txtBaslik.setText(entity.getFormBasligi());
        btnHakkinda.setText(entity.getBtnHakkindaBasligi());
        txtAciklama.setText(entity.getAciklama());
    }


    //seçilen şifrelemeye göre ekran ayarlarını yap
    private void formuSecilenSifrelemeyeGoreDizaynEt() {
        paneGiris.setManaged(false);
        paneGiris.setVisible(false);
        txtKey.setText(null);
        txtKey2.setText(null);
        txtKey.prefWidthProperty().set(200);
        lblKey.setText("Key");
        setKey2Visible(false);
        switch (secilenCipher) {
            case AFFİNE:
                lblKey.setText("Key A - Key B");
                setKey2Visible(true);
                break;
            case SUBSTİTUTİON:
            case POLYBİUS:
                txtKey.prefWidthProperty().set(450);
                break;
            case VERNAM:
                txtKey.prefWidthProperty().set(600);
                break;

        }
    }


    private boolean setKeyVariableInCipherClass() {
        if(secilenCipher==SelectedCipher.VERNAM) return true;
        if(txtKey.getText()==null||txtKey.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Key alanı boş olamaz.\n\nKey Giriniz.");
            return false;
        }

        final String key1=txtKey.getText().trim();

        //tek keye ihtiyaç duyan sınıfların keyi set ediliyor
        if (getCipherInterface() instanceof OneKey)
            ((OneKey) getCipherInterface()).setKey(key1);

        //iki keyle çalışacak sınıfların keyi set ediliyor
        if (getCipherInterface() instanceof TwoKey) {
            final String key2=txtKey2.getText().trim();
            ((TwoKey) getCipherInterface()).setKey1(key1);
            ((TwoKey) getCipherInterface()).setKey2(key2);
        }

        if (getCipherInterface() instanceof Des)
            ((Des) getCipherInterface()).key = txtKey.getText().trim();
        return true;
    }

    /**
     * Listviewdeki veriyi şifreler veya şifreli ise şifresini çözer
     */
    @FXML
    void sifreleSifreyiCoz() {
        final int acilacakListViewIndexi = getSelectedTabIndex() == 0 ? 1 : 0;
        //Parametreler oluşturuluyor
        final String listViewMetni = listeyiTekSatirlikMetneCevir(getSelectedList()).toLowerCase().trim();
        //keyiKontrolEt();
        if(secilenCipher!=SelectedCipher.PLAYFAİR)if(!setKeyVariableInCipherClass())return;//keyi set ederken sorun yaşarsa return
        if (getCipherInterface().keyUygunMu()) {
            String sonucStringi = getCipherInterface().sifreleSifreCoz(listViewMetni, acilacakListViewIndexi == 0 ? Mode.Decript : Mode.Encript);
            if (sonucStringi != null) {
                Listeler.addList(getAnotherList(),sonucStringi);
                //viewi öne getirir
                tabPane.getSelectionModel().select(acilacakListViewIndexi);
  /*
                viewiDoldur(listViewDetay, 2, Des.logDetay, null);
                viewiDoldur(acilacakListViewIndexi == 0 ? listViewDuzMetin : listViewSifreliMetin, acilacakListViewIndexi, null, sonucStringi);*/
            }
        }
    }


    @FXML
        // Hakkında adlı fxml penceresini görüntüler
    void showHakkinda() {
        new OpenFXML().showFxmlAsDialog("/ui/hakkinda.fxml");
    }


    /**
     * Formda ki RandomKeyÜret düğmesi ile ilişkilendirilmiş metotdur.
     * keyi otomatik üretecek metotdur.
     * Gerçek Random kullanır
     */
    @FXML
    void randomKeyUret() {
        if(secilenCipher!=SelectedCipher.PLAYFAİR)if (!keyOlusturulsunMu()) return;
        switch (secilenCipher) {
            case AFFİNE:
                final String keyler = getCipherInterface().randomKeyUret();
                txtKey.setText(keyler.split(" ")[0]);
                txtKey2.setText(keyler.split(" ")[1]);
                break;
            case PLAYFAİR:if(!setKeyVariableInCipherClass())return;
            default:
                txtKey.setText(getCipherInterface().randomKeyUret());
        }
       // viewiDoldur(listViewDetay, 2, CipherI.logDetay, null);
    }


    /**
     * Key zaten var mı kontrol eder varsa kullanıcıyı uyarır eğer kullanıcı hayır derse key oluşturma işi iptal edilir.
     *
     * @return kişi hayır derse false diğer durumlarda true döner
     */
    private boolean keyOlusturulsunMu() {
        if (txtKey.getText() == null) return true;
        if (!txtKey.getText().isEmpty()) {
            final String message = "Key mevcut.\nMevcut keyi kaybetmeniz durumunda şifrelenmiş verileri eski haline döndüremeyebilirsiniz. \nYeni key oluşturulsun mu? ";
            final int result = JOptionPane.showConfirmDialog(null, message, "Uyarı", JOptionPane.YES_NO_OPTION);
            return result != JOptionPane.NO_OPTION;//hayır derse false gönderiliyor
        }
        return true;
    }

}
