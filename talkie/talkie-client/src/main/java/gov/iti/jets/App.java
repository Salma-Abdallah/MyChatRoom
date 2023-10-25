package gov.iti.jets;

import gov.iti.jets.manager.StageManager;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Hello world!
 *
 */
public class App extends Application
{
   

     public static void main(String[] args) {
      
        Application.launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        StageManager.INSTANCE.initStage(primaryStage);
        StageManager.INSTANCE.loadScene("signup-page");

        primaryStage.setMinHeight(700);
        primaryStage.setMinWidth(1050);
        

        primaryStage.show();
    }
}
