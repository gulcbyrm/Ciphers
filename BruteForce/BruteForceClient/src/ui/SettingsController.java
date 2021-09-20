package ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import lombok.Getter;

public class SettingsController implements Initializable {

    @FXML private TextField txtPort, txtServerIp;
    @FXML private ToggleGroup toggleGroup;
    @FXML private RadioButton rdBtnLocalHost, rdBtnIP;

    @Getter private static Integer serverPortu = 6000;
    @Getter private static String serverIpsi= "localhost"; 


    
    
    //port numarasını set edecek listener oluşturuluyor
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rdBtnIP.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean eskiSecilen, Boolean yeniSecilen) {
                if (yeniSecilen) {
                    if(txtServerIp.getText().equals("localhost")) txtServerIp.setText("");
                } else {
                    txtServerIp.setText("localhost");
                }
            }
        });
        
        
        
        //Textedit değerleri değişkenlere changed listener ile aktarılıyor
        txtPort.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            try {
                serverPortu=Integer.valueOf(txtPort.getText());
            } catch (NumberFormatException e) {
            }
        });
        
        
        txtServerIp.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            try {
                serverIpsi=txtServerIp.getText();
            } catch (NumberFormatException e) {
            }
        });
        
        
        //ilk girişte txtPort alanı set ediliyor
        txtPort.setText(Integer.toString(serverPortu));
        txtServerIp.setText(serverIpsi);
        if(serverIpsi.equals("localhost")) rdBtnLocalHost.setSelected(true);
        else rdBtnIP.setSelected(true);
    }

    
    
    @FXML
    private void closeWindow(ActionEvent event) {
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
}
