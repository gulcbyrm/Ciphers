package ui;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import tools.Listeler;
import txt.Txt;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class BaseView implements Initializable {
    @FXML protected ListView<String> listViewDetay, listViewSifreliMetin, listViewDuzMetin;
    @FXML protected TabPane tabPane;
    @FXML protected Pane paneGiris;
    @FXML protected Label txtBaslik;
    @FXML protected Button btnSifreleCoz, btnKaydet, btnMetniListeyeAktar, btnHakkinda;
    @FXML public TextField txtKey, txtKey2, txtMesaj;
    @FXML protected Text txtAciklama, lblKey;


    //TabPane de aktif olan tab'ın indexsini verir
    protected int getSelectedTabIndex() {
        return tabPane.getSelectionModel().getSelectedIndex();
    }




    // Aktif olan panedeki listViewi return eder
    public ObservableList<String> getSelectedList( ) {
        switch (getSelectedTabIndex()) {
            case 0:
                return Listeler.getListDuzMetin();
            case 1:
                return Listeler.getListSifreliMetin();
            case 2:
                return Listeler.getListDetay();
        }
        return null;
    }




    // Aktif olan panedeki listViewi return eder
    public ObservableList<String> getAnotherList( ) {
        switch (getSelectedTabIndex()) {
            case 0:
                return Listeler.getListSifreliMetin();
            case 1:
                return Listeler.getListDuzMetin();
            case 2:
                return Listeler.getListDetay();
        }
        return null;
    }



    
    /**
     * bu metod aktif listviewdeki butonların enabled/disabled durumunu set eder
     */
    protected void btnDisabledEnabled(ObservableList<String> list) {
        if (AnaPencereController.secilenCipher==null) return;
        boolean viewBos = list.isEmpty();
        btnKaydet.setDisable(viewBos);
        btnSifreleCoz.setDisable(viewBos);
    }


   /* *//**
     * istenen ListViewe istenen metni doldurur. metin tek string olabileceği gibi bir listede olabilir.
     * eğer metin string ise metinList parametresi null belirtilmelidir.
     * eğer doldurulacak metin bir metin listesi ise bu defa metin parametresi null verilir.
     *

     *//*
    public void viewiDoldur(ListView<String> listView, int listViewIndex, List<String> metinList, String metin) {
        listView.getItems().clear();
        if (metin == null) {
            if (metinList != null)
                for (String m : metinList) {
                    listView.getItems().add(m);
                }
        } else
            listView.getItems().add(metin);
        listView.refresh();
        tabPane.getSelectionModel().select(listViewIndex);
        btnDisabledEnabled(listView);
    }*/




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //OnChange Listener ile txtMesaj kutusundaki değişiklikler takip ediliyor
        txtMesaj.textProperty().addListener((observable, oldValue, newValue) -> {
            if (txtMesaj.getText().isEmpty()) btnMetniListeyeAktar.setDisable(true);
            else btnMetniListeyeAktar.setDisable(false);
        });

        //tab geçişlerinde butonların disabled/enabled özelliğini ayarlamak için listener
        tabPane.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            btnDisabledEnabled(getSelectedList());
            if (getSelectedTabIndex() == 0) btnSifreleCoz.setText("Listeyi Şifrele");
            if (getSelectedTabIndex() == 1) btnSifreleCoz.setText("Şifreyi Çöz");
        });

        //ListViewDETAY için editable özelliğini açar
        listViewDetay.setEditable(true);
        listViewDuzMetin.setEditable(true);
        listViewSifreliMetin.setEditable(true);
        listViewDetay.setCellFactory(TextFieldListCell.forListView());

        listenereEkle(Listeler.getListDetay(), listViewDetay);
        listenereEkle(Listeler.getListDuzMetin(), listViewDuzMetin);
        listenereEkle(Listeler.getListSifreliMetin(), listViewSifreliMetin);
    }



    private <N> void listViewdeGoster(ListView<N> lv, ObservableList<N> list) {
        lv.getItems().clear();
        lv.getItems().addAll(list);
        lv.refresh();
    }



    private <N> void listenereEkle(ObservableList<N> oList, ListView<N> lv) {
        oList.addListener((ListChangeListener<N>) (change) -> {
            listViewdeGoster(lv, oList);
            btnDisabledEnabled(getSelectedList());
        });
    }




    /**
     * Verileri TXT dosyasına kaydeden FXML metod
     */
    @FXML
    void txtKaydet() {
        Txt txt = new Txt();
        File file = txt.keydetDialog();
        if (file == null) return;
        txt.insertTXT(file, getSelectedList());
    }


    /**
     * Listview içindeki verileri temizleyen FXML metod
     */
    @FXML
    public void listViewTemizle() {
        getSelectedList().clear();
        btnDisabledEnabled(getSelectedList());
    }


    /**
     * txtMesaj kutusunun içeriğini viewe aktarır
     */
    @FXML
    void txtMesajiVieweAktar() {
        Listeler.addListDuzMetin( txtMesaj.getText());
        btnDisabledEnabled(getSelectedList());
        txtMesaj.setText("");
    }




    /**
     * Txt sınıfına ait olan dosyadanAlDialog() mmetodu ile alınacak txt dosyasını belirlemenizi sağlar ve seçilen txt
     * dosyasını belirtilen listviewe doldurur
     *
     * @throws IOException dosya seçilmemesi durumu
     */
    @FXML
    void dosyadanAl() throws IOException {
        final Txt txt = new Txt();
        final File file = txt.dosyadanAlDialog();
        if (file == null) return;

        final List<String> txtDosyaIcerigi = txt.readAllFromFileToList(file);
        getSelectedList().addAll(txtDosyaIcerigi);
    }




    protected void setKey2Visible(boolean durum) {
        txtKey2.setManaged(durum);
        txtKey2.setVisible(durum);
    }




    /**
     * parametre olarak gelen listeyii metin olarak geri döndürür
     * şifreleme fonksiyonuna tüm listeyi parça parça göndermemek için metnin tek satır olarak alınması
     * ve şifrelenmesi için
     *
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

}
