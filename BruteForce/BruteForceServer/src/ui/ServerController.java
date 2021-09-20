package ui;

import entities.ErrCheck;
import error.ErrorHelper;
import network.ParallelProc;
import entities.Gorev;
import entities.GorevDizi;
import static enums.WPath.HAKKINDA;
import static enums.WPath.SETTING;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import network.Server;
import network.ServerWorker;
import utility.Listeler;
import error.MyAlert;
import lombok.Getter;
import utility.ShowFXML;
import utility.Txt;

public class ServerController implements Initializable {

    //değişkenler
    @FXML private TabPane tabPane;
    @FXML private TextField txtSayilar, txtOzlKarakter, txtMinLength, txtMaxLength, txtSha256, txtAlphabe;
    @FXML private ListView<String> lvGenel, lvServerMonitor;
    @FXML private ListView<ServerWorker> lvClientler;
    @FXML private Button btnHashKoduKir, btnRunServer, btnDefaultKullan, btnStopServer;

    
    private final ErrorHelper s = new ErrorHelper();
    @Getter private static Server server;

    
    
    //setAll listeyi temizlediğinden ayrıca clear kullanmadık
    private <N> void listViewdeGoster(ListView<N> lv, ObservableList<N> list) {
        lv.getItems().clear();
        lv.getItems().addAll(list);
        lv.refresh();
    }

    private <N> void listenereEkle(ObservableList<N> oList, ListView<N> lv) {
        oList.addListener((ListChangeListener<N>) (change) -> {
            listViewdeGoster(lv, oList);
        });
    }

    
   
    
    //sınıfın listenerlerini burada oluşturduk listelerde bulunan verinin otomatik olarak listViewlere aktarılmasını sağlamaktadırlar
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listenereEkle(Listeler.getListGenel(), lvGenel);
        listenereEkle(Listeler.getListWorker(), lvClientler);
        listenereEkle(Listeler.getListServerDurum(), lvServerMonitor);
    }

    
  
    
    //serveri thread olarak başlatır
    @FXML
    public void runServer() {
        this.server = new Server();
        this.server.setDaemon(true);
        this.server.start();
        btnRunServer.setDisable(true);
        btnStopServer.setDisable(false);
    }
    
    
    

    //serveri durdurur
    @FXML
    private void stopServer() {
        if (this.server.serveriDurdur()) {
            btnRunServer.setDisable(false);
            btnStopServer.setDisable(true);
        }
    }

   
    
    
    //kullanıcının girdiği hashKodu brute force mantığıyla paralel olarak kırmaya çalışır
    @FXML
    private void hashKoduKir() {

        if (hataVarmi()) {
            return;                        // formda eksik, hatalı textField var mı, inceler.
        }
        final Gorev grv = clientGorevOlustur();         // görev nesnesi oluşturur
        int partNo = 0;                                   // Görevin hangi parçasının cliente verildiği

        //sending grv to each clients
        for (ServerWorker serverWorker : Listeler.getListWorker()) {
            if (serverWorker.sendOBJ("PartNo: " + partNo++)) {
                if (serverWorker.sendOBJ(grv)) {
                    Listeler.addList(Listeler.getListGenel(), "Görev " + serverWorker.id + " nolu cliente ulaştırıldı");
                }
            }
        }
    }

    
    
    
    
    //clientlere gönderilecek görev nesnesini oluşturur
    private Gorev clientGorevOlustur() {
        final String karakterSeti = txtAlphabe.getText() + txtSayilar.getText() + txtOzlKarakter.getText();
        final int min = Integer.valueOf(txtMinLength.getText());
        final int max = Integer.valueOf(txtMaxLength.getText());
        final int clientSayisi = Listeler.getListWorker().size();
        final long[] permustasyonDizisi = new ParallelProc().permutasyonDizisiOlustur(karakterSeti.length(), min, max);//str,min,max
        final GorevDizi[] clientGorevDizisi = new ParallelProc().clientGorevDizisiOlustur(karakterSeti.length(), clientSayisi);
        Listeler.addList(Listeler.getListGenel(), "Görevlendirilecek Toplam Client sayısı :" + Listeler.getListWorker().size());

        //builder tasarım kalıbı kullanılmıştır
        return new Gorev.GorevBuilder()
                .setKarakterSeti(karakterSeti)
                .setMinUzunluk(min)
                .setMaxUzunluk(max)
                .setHashKod(txtSha256.getText())
                .setClientGorevDizi(clientGorevDizisi)
                .setPermutasyonDizisi(permustasyonDizisi).build();
    }

    
    
    
    
    // TextField'lere default değerleri atar
    @FXML
    private void setDefaultValues() {
        txtAlphabe.setText("abcçdefgğhıijklmnoöprsştuüvyz" + "ABCÇDEFGĞHIİJKLMNOÖPRSŞTUÜVYZ");
        txtSayilar.setText("0123456789");
        txtOzlKarakter.setText("._,*-+!?/");
        txtMinLength.setText("1");
        txtMaxLength.setText("5");
        //txtSha256.setText("183c28f8ed3958a64110b2dc6a6d8f252a5371fbef7f39bb06c2698a1c8b137d");
    }

    
    
    
    // txt uzantılı dosyayı current viewe yükler
    @FXML
    private void dosyadanAl() {
        try {
            final Txt txt = new Txt();
            final File file = txt.dosyadanAlDialog();  //TXT yi oku
            if (file == null) {
                return;
            }
            this.getCurrentListView().getItems().addAll(txt.readAllFromFileToList(file));
        } catch (IOException ex) {
            new MyAlert().showErrorAlert("Dosya TXT den alınıp View'e doldurulurken hata oluştu\nDetay: " + ex.getMessage(), "TXT Hatası");
        }
    }

    
    
    
    
    //current listViewdeki veriyi dosyaya kaydeder
    @FXML
    private void dosyayaKaydet() {
        final Txt txt = new Txt();
        final File file = txt.keydetDialog();
        if (file == null) {
            return;
        }
        txt.insertTXT(file, this.getCurrentListView().getItems());
    }

    
    
    
    
    //aktif olan listviewdeki veriyi temizler.
    //aslında listeyi temizlemektedir ancak listenin datası otomatik olarak listviewe yansıdığından listView temizlenmiş olur.
    @FXML
    private void temizle() {
        getCurrentListView().getItems().clear();//bu gerekli silme
        switch (tabPane.getSelectionModel().getSelectedIndex()) {
            case 0:
                Listeler.getListGenel().clear();
                break;
            case 1:
                Listeler.getListWorker().clear();
                break;
            case 2:
                Listeler.getListServerDurum().clear();
                break;
        }
    }

    
    
    
    
    //current olan listViewi döndürür
    private ListView getCurrentListView() {
        switch (tabPane.getSelectionModel().getSelectedIndex()) {
            case 0:
                return lvGenel;
            case 1:
                return lvClientler;
            default:
                return lvServerMonitor;
        }
    }


    
    
    
    //bu fonksiyon parola kırma işleminden önce hata kontrolü yapmaktadır
    private boolean hataVarmi() {
        //metodun değişkenleri
        final ErrorHelper err = new ErrorHelper();
        
        final ErrCheck alfabeHarfleri = new ErrCheck<>(txtAlphabe, "Alfabe alanı");                                         //textField
        final ErrCheck sayilar = new ErrCheck<>(txtSayilar, "Sayılar alanı");                                               //textField
        final ErrCheck ozelKarakterler = new ErrCheck<>(txtOzlKarakter, "Özel Karakterler alanı");                          //textField
        final ErrCheck minUzunluk = new ErrCheck<>(txtMinLength, "Min Uzunluk alanı");                                      //textField
        final ErrCheck maxUzunluk = new ErrCheck<>(txtMaxLength, "Max Uzunluk alanı");                                      //textField
        final ErrCheck sha256 = new ErrCheck<>(txtSha256, "Sha256 kodu alanı");                                             //textField
        final ErrCheck clientler = new ErrCheck<>(Listeler.getListWorker(), "Client");                                      //Liste
        final ErrCheck dinlenecekPort = new ErrCheck<>(SettingsController.getDinlenecekPort(), "Dinlenecek Port");          //İnteger


        err.buFieldlerBosOlamaz(alfabeHarfleri, sayilar, ozelKarakterler, sha256, clientler);
        err.buFieldlerNumericOlmali(maxUzunluk, minUzunluk);
        if(err.HataliAlanVarsaMsjGoruntule()) return true;
        err.buFieldlerMinDegerinAltinaInemez(new ErrCheck[]{minUzunluk, maxUzunluk, dinlenecekPort}, 1, 2, 1);
        err.buFieldlerMaxDegeriAsamaz(new ErrCheck[]{minUzunluk, maxUzunluk,dinlenecekPort}, 8, 8, 65535);
        err.buFieldlerdeTextLengthSiniriUygula(new ErrCheck[]{sha256}, 64);
        err.buFieldlerdenIlkiIkincisindenBuyukOlamaz(minUzunluk, maxUzunluk);
        return err.HataliAlanVarsaMsjGoruntule();
    }

    
    
    
    @FXML
    private void showSettings() {
        new ShowFXML().showOnCenter(SETTING);
    }

    
    
    
    @FXML
    private void showHakkinda() {
        new ShowFXML().showOnCenter(HAKKINDA);
    }


}







//  private void hataKontroluYap(){
//    StringBuilder msg = new StringBuilder();
//    int sayac = 1;
//    if(SettingsController.getDinlenecekPort()<1||SettingsController.getDinlenecekPort()>65535) msg.append(sayac++).append(") Dinlenecek port numarası geçersiz (1-65535 aralığında olmalı). Ayarlardan düzeltiniz\n");
//    if (Listeler.getListWorker().isEmpty())  msg.append(sayac++).append(") Bağlı Client yok. Serveri başlatıp client bağlantısı sağlayınız\n");
//    if (s.boyut(txtSha256) != 64) msg.append(sayac++).append(") SHA256 kodu hatalı.(64karakter olmalı)\n");
//    if (s.isNullOrEmpty(txtAlphabe)) msg.append(sayac++).append(") Alfabe Karakterleri kutusu boş olamaz \n");
//    if (s.isNullOrEmpty(txtOzlKarakter)) msg.append(sayac++).append(") Özel Karakterler kutusu boş olamaz \n");
//    if (s.isNullOrEmpty(txtSayilar)) msg.append(sayac++).append(") Sayılar kutusu boş olamaz \n");
//    if (!s.isNumeric(txtMaxLength.getText()) || !s.isNumeric(txtMinLength.getText())) {
//        msg.append(sayac++).append(") MinUzunluk ve MaxUzunluk kutularına sayı girilmek zorunda.\n");
//    } else if (Integer.valueOf(txtMaxLength.getText()) < Integer.valueOf(txtMinLength.getText())) {
//        msg.append(sayac++).append(") MinUzunluk değerini MaxUzunluk değerinden büyük girdiğinizden hesaplama yapılamaz\n");
//    } else if (Integer.valueOf(txtMaxLength.getText()) < 1) {
//        msg.append(sayac++).append(") MaxUzunluk değeri 1 küçük olamaz\n");
//    } else if (Integer.valueOf(txtMaxLength.getText()) > 8) {
//        msg.append(sayac++).append(") Maximum 8 karakter kırılabilmektedir. Max uzunluk en fazla 8 seçiniz\n");
//    }
//    if (msg.length() > 0) {
//        new MyAlert().showErrorAlert(msg.toString(), "Tespit Edilen Hata/Hatalar");
//        return true;
//    }
//        return false;
//}
 