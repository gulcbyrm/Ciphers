package ui;

import ciphers.SelectedCipher;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import txt.Txt;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class HakkindaController implements Initializable {
    @FXML
    private Label lblHakkimda;
    @FXML
    private ListView<String> listViewHakkinda;
    private String seciliCipher;


    /**
     * Hakkind.txt dosyasını list viewe yükler
     *
     * @throws IOException giriş çıkış hatası oluşabilir. en genel durum dosyanın klsörde olmamasıdır
     */
    public void dosyadanAl() throws IOException {
        Txt txt = new Txt();
        File file = new File(seciliCipher);
        final List<String> txtDosyaIcerigi = txt.readAllFromFileToList(file);
        for (String m : txtDosyaIcerigi) {
            listViewHakkinda.getItems().add(m);
        }
        listViewHakkinda.refresh();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        final SelectedCipher sCipher=AnaPencereController.secilenCipher;
        if (sCipher == null) {
            this.seciliCipher = "hakkinda.txt";
            lblHakkimda.setText("HAKKIMDA");
        } else {
            this.seciliCipher = sCipher.name().toLowerCase() + ".txt";
            lblHakkimda.setText(sCipher.name()+" HAKKINDA");
        }
        try {
            dosyadanAl();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, seciliCipher + " bulunamadı\n Dosyanın çalışma klasöründe olmasını sağlayınız.");
        }

    }
}
