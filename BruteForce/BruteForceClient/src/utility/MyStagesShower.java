package utility;

import java.awt.Toolkit;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MyStagesShower {

    public static Stage primaryStage;
    private double xOffset, yOffset;

    private Stage showFxml(final String path) {
        final Stage stage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path + ".fxml"));
            Parent root = loader.load();
            stage.initOwner(primaryStage);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            setXY(root, stage);
            //stage.initModality(Modality.APPLICATION_MODAL);

            //primaryStage.close();
        } catch (Exception e) {

            e.printStackTrace();
            Platform.exit();
        }
        return stage;
    }

    public void showFXML_Maximized(final String path) {
        final Stage stage = showFxml(path);
        stage.setMaximized(true);
        setLogoAndTitle(stage, path);
        stage.showAndWait();
    }

    public Stage showFXML_FitHeight(final String path) {
        final Stage stage = showFxml(path);
        setLogoAndTitle(stage, path);
        final double screenHeight = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        stage.setHeight(screenHeight - 60);
        stage.centerOnScreen();
        stage.setOnCloseRequest(event-> {
            System.out.println("Pencere Kapatıldı");
        });
        
        stage.showAndWait();
        return stage;
    }

    private void setXY(final Parent root, final Stage stage) {
        root.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        root.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });
    }

    public void showOnCenter(final String path) {
        final Stage stage = showFxml(path);
        stage.centerOnScreen();
        stage.showAndWait();
    }
    
    
    
    private void setLogoAndTitle(final Stage stage, final String title) {
        //stage.getIcons().add(new javafx.scene.image.Image("/resources/icons/LogoBK.jpg"));
        stage.setTitle(title);
    }

}
