package gov.iti.jets.network.manager;

import gov.iti.jets.network.manager.NetworkManager;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public class LoadLogin extends Application{

    public LoadLogin() {

    }

    public static void IntializeApp(){
        Application.launch();
    }
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Home");
        Parent home = null;
        try {
            home = FXMLLoader.load(getClass().getResource("/views/login-page-username.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(home);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

}
