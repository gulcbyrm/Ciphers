package error;

import javafx.scene.control.Alert;


public class MyAlert {


    public void showErrorAlert(String mesaj, String header) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setContentText(mesaj);
        a.setTitle(null);
        a.setResizable(true);
        a.setHeaderText(header);
        a.show();
    }

}
