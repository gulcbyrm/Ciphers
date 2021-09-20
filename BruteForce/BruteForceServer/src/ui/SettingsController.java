package ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import lombok.Getter;
import utility.Listeler;

public class SettingsController implements Initializable {

    
    @FXML private TextField txtPort;
    @FXML private ToggleGroup toggleGroup;
    @FXML private Text ip;

    @Getter private static Integer dinlenecekPort=6000;



    //port numarasını set edecek listener oluşturuluyor
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //ilk girişte txtPort alanı set ediliyor
        txtPort.setText(Integer.toString(dinlenecekPort));
        
        //listenerler oluşturuluyor
        txtPort.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            try {
                dinlenecekPort=Integer.valueOf(txtPort.getText());
            } catch (NumberFormatException e) {
            }
        });
    }
}
