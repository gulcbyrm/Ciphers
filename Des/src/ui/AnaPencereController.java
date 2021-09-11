package ui;

import des.Des;
import des.OpenFXML;
import exceptions.KeyLengthUygunDeil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldListCell;
import txt.Txt;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.SecureRandom;
import java.util.List;
import java.util.ResourceBundle;

public class AnaPencereController implements Initializable {
    @FXML
    private Button btnListeyiSifrele;
    @FXML
    private Button btnListedekiSifreyiCoz;
    @FXML
    private Button btnMetniListeyeAktar;
    @FXML
    private TextField txtKey;
    @FXML
    private TabPane tabPane;
    @FXML
    private TextField txtMesaj;
    @FXML
    private Button btnKaydetDuz;
    @FXML
    private Button btnKaydetSifreli;
    @FXML
    private ListView<String> listViewDetay;
    @FXML
    private ListView<String> listViewSifreliMetin;
    @FXML
    private ListView<String> listViewDuzMetin;



    //VERİLERİ TXT DOSYASINA KAYDET

    @FXML
    void DuzmetinKaydet() {
        listViewDatakaydet(listViewDuzMetin);
    }

    @FXML
    void sifreliMetinViewKaydet() {
        listViewDatakaydet(listViewSifreliMetin);
    }

    /**
     * Bu fonksiyon kendisine parametre olarak gelen list viewdeki verileri txt dosyasına kaydeder
     *
     * @param listView
     */
    private void listViewDatakaydet(ListView<String> listView) {
        Txt txt = new Txt();
        File file = txt.keydetDialog();
        if(file==null)return;
        txt.insertTXT(file, listView.getItems());
    }




    // TEMİZLE 3 METOD
    @FXML
    void sifreleViewTemizle() {
        temizle(listViewSifreliMetin, 1);
    }

    @FXML
    void duzMetinViewTemizle() {
        temizle(listViewDuzMetin, 0);
    }

    @FXML
    void viewDetayTemizle() {
        temizle(listViewDetay, 2);
    }


    /**
     * listViewdeki verileri temizler
     *
     * @param listView      temizlenecek list view
     * @param listViewIndex temizlenecek listviewin tabpanedeki indexi
     */
    public void temizle(ListView<String> listView, int listViewIndex) {
        listView.getItems().clear();
        listView.refresh();
        btnDisabledEnabled(listViewIndex);
    }




    //ŞİFRELEME ŞİFRE ÇÖZME ALTTAKİ 3 METOD

    @FXML
    void listViewSifreyiCoz() {
        sifreleCoz(listViewSifreliMetin, 1);
    }

    @FXML
    void listeyiSifrele() {
        sifreleCoz(listViewDuzMetin, 0);
    }


    /**
     * kendisine gelen şifreleme ve şifre çözme taleplerini yerine getirir.
     * gelen metnin şifrelenecek mi yada şifresi mi çözülecek; bunu listViewIndex parametresiyle anlamaktadır
     * eğer listViewIndex=0 ise kişi düz metin ekranındadır ve şifreleme yapmak istemektedir
     * eğer listViewIndex=1 ise kişi şifreli metin ekranında dolayısıyla yapacağı işlem şifre çözme olacaktır
     *
     * @param listView      gönderilen list view seçenekler ise (listViewDetay,listViewSifreliMetin,listViewDuzMetin)
     * @param listViewIndex listviewin tab pane numarasıdır. listViewDuzMetin için 0, listViewSifreliMetin=1,listViewDetay için 2 dir
     */
    private void sifreleCoz(ListView<String> listView, int listViewIndex) {
        String metin = listeyiTekSatirlikMetneCevir(listView.getItems());
        Des des = new Des();
        final String sifreliMetin, duzMetin;
        try {
            if (listViewIndex == 0) {
                sifreliMetin = des.encript(metin, txtKey.getText());
                viewiDoldur(listViewDetay, 2, Des.islemDetayList, null);
                viewiDoldur(listViewSifreliMetin, 1, null, sifreliMetin);
            } else {
                duzMetin = des.decript(metin, txtKey.getText());
                viewiDoldur(listViewDetay, 2, Des.islemDetayList, null);
                viewiDoldur(listViewDuzMetin, 0, null, duzMetin);
            }
        } catch (UnsupportedEncodingException | KeyLengthUygunDeil e) {
            JOptionPane.showMessageDialog(null, "Geçersiz Key. " + e.getMessage());
        }
    }



    // VİEWLERE VERİ DOLDURMA İŞLERİ ALTTAKİ 6 METOD

    /**
     * txtMesaj kutusunun içeriğini viewe aktarır
     */
    @FXML
    void metniVieweAktar() {
        viewiDoldur(listViewDuzMetin, 0, null, txtMesaj.getText());
        txtMesaj.setText("");
    }

    @FXML
    void duzMetniDosyadanAl() throws IOException {
        dosyadanAl(0);
    }

    @FXML
    void sifreliMetniDosyadanAl() throws IOException {
        dosyadanAl(1);
    }


    /**
     * Txt sınıfına ait olan dosyadanAlDialog() mmetodu ile alınacak txt dosyasını belirlemenizi sağlar ve seçilen txt
     *     dosyasını belirtilen listviewe dolduru
     * @param listViewIndex doldurulacak listview indexi
     * @throws IOException dosya seçilmemesi durumu
     */
    public void dosyadanAl(int listViewIndex) throws IOException {
        Txt txt = new Txt();
        int resultDuz = JOptionPane.showConfirmDialog(null, listViewIndex == 0 ? "Düz Metin Alınacak" : "Şifreli Metin Alınacak", "Uyarı", JOptionPane.YES_NO_OPTION);
        if (resultDuz == JOptionPane.NO_OPTION) return;
        File file = txt.dosyadanAlDialog();  //TXT yi oku
        if(file==null)return;
        final List<String> txtDosyaIcerigi = txt.readAllFromFileToList(file);
        if (listViewIndex == 0)
            viewiDoldur(listViewDuzMetin, 0, txtDosyaIcerigi, null);
        else
            viewiDoldur(listViewSifreliMetin, 1, txtDosyaIcerigi, null);
    }
    


    /**
     * istenen ListViewe istenen metni doldurur. metin tek string olabileceği gibi bir listede olabilir.
     * eğer metin string ise metinList parametresi null belirtilmelidir.
     * eğer doldurulacak metin bir metin listesi ise bu defa metin parametresi null verilir.
     *
     * @param listView  veri doldurulmak istenen listView adı
     * @param listViewIndex viewin index numarası
     * @param metinList  doldurulmak istenen veri String List türünden.
     * @param metin  doldurulmak istenen veridir. Tek String.
     */
    public void viewiDoldur(ListView<String> listView, int listViewIndex, List<String> metinList, String metin) {
        listView.getItems().clear();
        if (metin == null)
            for (String m : metinList) {
                listView.getItems().add(m);
            }
        else
            listView.getItems().add(metin);
        listView.refresh();
        tabPane.getSelectionModel().select(listViewIndex);
        btnDisabledEnabled(listViewIndex);
    }








    /**
     * bu metod index numarası verilen viewde bulunan butunların enabled/disabled durumunu set eder
     * @param listViewIndex check edicelek listviewin indexi
     */
    private void btnDisabledEnabled(int listViewIndex) {
        if (listViewIndex == 0) { //listviewduz ün indexi
            if (listViewDuzMetin.getItems().size() > 0) {
                btnKaydetDuz.setDisable(false);
                btnListeyiSifrele.setDisable(false);
            } else {
                btnKaydetDuz.setDisable(true);
                btnListeyiSifrele.setDisable(true);
            }

        }
        if (listViewIndex == 1) {//listviewSifrelenin indexi
            if (listViewSifreliMetin.getItems().size() > 0) {
                btnKaydetSifreli.setDisable(false);
                btnListedekiSifreyiCoz.setDisable(false);
            } else {
                btnKaydetSifreli.setDisable(true);
                btnListedekiSifreyiCoz.setDisable(true);
            }
        }
    }




    /**
     * parametre olarak gelen listeyii metin olarak geri döndürür
     * şifreleme fonksiyonuna tüm listeyi parça parça göndermemek için metnin tek satır olarak alınması
     * ve şifrelenmesi için
     * @param list String data içeren liste
     * @return tek satır string return eder
     */
    protected String listeyiTekSatirlikMetneCevir(List<String> list) {
        StringBuilder str = new StringBuilder();
        for (String lineString : list) {
            str.append(lineString);
        }
        return str.toString();
    }




    /**
     * Formda ki RandomKeyÜret düğmesi ile ilişkilendirilmiş metotdur.
     * Kullanıcı yerine sistemin key üretmesini sağlar
     * Gerçek Random kullanır
     */
    @FXML
    void randomKeyUret() {
        if (!txtKey.getText().isEmpty()) {
            final String message = "Key mevcut.\nMevcut keyi kaybetmeniz durumunda şifrelenmiş verileri eski haline döndüremeyebilirsiniz. \nYeni key oluşturulsun mu? ";
            final int result = JOptionPane.showConfirmDialog(null, message, "Uyarı", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.NO_OPTION) return;
        }
        SecureRandom random = new SecureRandom();
        StringBuilder key = new StringBuilder();
        for (int i = 0; i < 8; i++)
            key.append(random.nextInt(9));

        txtKey.setText(key.toString());

    }




    /**
     * Hakkında adlı fxml penceresini görüntüler
     */
    @FXML
    void showHakkinda() {
        OpenFXML openFXML = new OpenFXML();
        openFXML.showFxmlAsDialog("/ui/hakkinda");
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //OnChange Listener ile txtMesaj kutusundaki değişiklikler takip ediliyor
        txtMesaj.textProperty().addListener((observable, oldValue, newValue) -> {
            if (txtMesaj.getText().isEmpty()) btnMetniListeyeAktar.setDisable(true);
            else btnMetniListeyeAktar.setDisable(false);
        });

        //ListView için editable özelliği açılıyor
        listViewDetay.setEditable(true);
        listViewDetay.setCellFactory(TextFieldListCell.forListView());
    }
}
