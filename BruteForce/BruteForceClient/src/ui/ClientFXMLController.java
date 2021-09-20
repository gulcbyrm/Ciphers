package ui;

import network.Client;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import lombok.Getter;
import lombok.Setter;
import utility.Listeler;
import utility.MyStagesShower;

public class ClientFXMLController implements Initializable {


    @FXML ListView<String> lvGenel;
    @FXML private Button btnBaglan, btnBaglantiyiKes, btnServereGonder;
    @FXML private TextField txtMesaj;
    
    @Getter private static Client client;



    @FXML
    public void connectToServer() throws InterruptedException, ExecutionException, UnknownHostException {
        client = new Client(SettingsController.getServerPortu(), InetAddress.getByName(SettingsController.getServerIpsi()));
        boolean basladi = client.baslat();
    }



    @FXML
    private void closeConnection() {
        client.servereBaglantiyiKes();
    }

    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Listeler.getListGenel().addListener((ListChangeListener<String>) change -> {
            lvGenel.getItems().clear();
            lvGenel.getItems().setAll(Listeler.getListGenel());
            lvGenel.refresh();
        });
        
        lvGenel.getItems().addListener(new ListChangeListener() {
            @Override
            public void onChanged(ListChangeListener.Change c) {
               if(client.getSocket()!=null) setEnableDisable(client.getSocket().isClosed());
               else setEnableDisable(true);
            }
        });
    }


    
    

    @FXML
    private void servereGonder() {
        client.sendObj(txtMesaj.getText());
        txtMesaj.clear();
    }

    

    
    
    private void setEnableDisable(boolean baglantiYok) {
        btnBaglan.setDisable(!baglantiYok);
        btnBaglantiyiKes.setDisable(baglantiYok);
        btnServereGonder.setDisable(baglantiYok);
    }


 
    
    
    @FXML
    private void temizle() {
        Listeler.getListGenel().clear();
    }

    
    
    
   
    @FXML
    private void showSettings(ActionEvent event) {
        new MyStagesShower().showOnCenter("/ui/settings");
    }

}
