
package bruteforceclient;

import javafx.application.Application;
import javafx.stage.Stage;
import utility.MyStagesShower;



public class BruteForceClient extends Application {

    @Override
    public void start(Stage primaryStage) {
        Stage stage=new MyStagesShower().showFXML_FitHeight("/ui/clientFXML");


    }


    public static void main(String[] args) {
        launch(args);
    }

}
