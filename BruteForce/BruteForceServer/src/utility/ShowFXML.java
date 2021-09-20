package utility;

import enums.WPath;
import error.MyAlert;
import java.awt.Toolkit;
import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ShowFXML {

    
    public void showAsResponsive(final WPath wpath) {
        final Stage stage = showFxml(wpath);
        final double screenHeight = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        stage.setHeight(screenHeight - 60);
        stage.centerOnScreen();
        stage.showAndWait();
    }
    
    
    

    public void showOnCenter(final WPath wpath) {
        final Stage stage = showFxml(wpath);
        stage.centerOnScreen();
        stage.showAndWait();
    }

    
    
    
    private Stage showFxml(final WPath wpath) {
        final Stage stage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(wpath.getFxmlPath() + wpath.getFxmlFileName() + ".fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            //stage.initModality(Modality.APPLICATION_MODAL);
        } catch (IOException e) {
            new MyAlert().showErrorAlert("Form açılırken hata oluştu\nDetay: " + e.getMessage(), "Form Açılamadı");
            e.printStackTrace();
            Platform.exit();
        }
        return stage;
    }

}
