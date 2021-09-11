package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/ui/anaPencere.fxml"));
        primaryStage.setTitle("Des Encript/Decript");
        primaryStage.setScene(new Scene(root));
        final double screenHeight = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        primaryStage.setHeight(screenHeight - 60);
       // primaryStage.initModality(Modality.WINDOW_MODAL);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
