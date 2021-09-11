package des;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class OpenFXML {

    /**
     * Bu fonksiyon kendisine parametre olarak gelen fxml dosyasını çalıştırır.
     * @param path fxml dosyasının yeri ve adı
     */
    public void showFxmlAsDialog(String path) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path + ".fxml"));
            Parent root = loader.load();
            Stage primaryStage = new Stage();
            primaryStage.setTitle("HAKKINDA");
            primaryStage.initModality(Modality.APPLICATION_MODAL);
            primaryStage.setScene(new Scene(root));
            //if (isMaximized) primaryStage.setMaximized(true);
            primaryStage.showAndWait();//wait mevzusu bazı durumlara zarar veridmi bi bak
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
