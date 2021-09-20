package bruteforceserver;

import static enums.WPath.SERVER;
import utility.ShowFXML;
import javafx.application.Application;
import javafx.stage.Stage;



public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        new ShowFXML().showAsResponsive(SERVER);
    }
    

    
    public static void main(String[] args) {
        launch(args);
    }
    
}
