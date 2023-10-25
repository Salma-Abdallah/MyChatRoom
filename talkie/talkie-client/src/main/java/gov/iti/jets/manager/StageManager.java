package gov.iti.jets.manager;

import gov.iti.jets.controllers.FXMLController;
import gov.iti.jets.controllers.FXMLControllerFactory;
import gov.iti.jets.controllers.FriendRequestsController;
import gov.iti.jets.dtos.CurrentUser;
import gov.iti.jets.network.controllers.OnlineStatusController;
import gov.iti.jets.network.manager.NetworkManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

public enum StageManager {
    INSTANCE;

    private Stage primaryStage;
    private final Map<String, Scene> scenes = new HashMap<>();
    private final Map<String, FXMLController> controllers = new HashMap<>();

    public void initStage(Stage stage) {
        if (primaryStage != null) {
            throw new IllegalArgumentException("The stage has already been initialized");
        }
        primaryStage = stage;
    }

    public FXMLController loadScene(String name) {
        System.out.println("Load:      " + name + "     Holaaaaaaaaaaaaa!!!!!!");
        if (primaryStage == null) {
            throw new RuntimeException("Initial stage is required");
        }
        System.out.println("ADD LISTENER");
        if (name.equals("main-alignment")) {
            System.out.println("ADD LISTENER");
            primaryStage.setOnCloseRequest((WindowEvent) -> {
                setOnClose();
            });
        }
        if (!scenes.containsKey(name)) {
            try {
                FXMLLoader loader = new FXMLLoader();
                FXMLController controller = FXMLControllerFactory.getController(name);
                loader.setController(controller);
                Scene scene = new Scene(loader.load(getClass().getClassLoader()
                        .getResource(String.format("views/stages/%s.fxml", name)).openStream()));
                scenes.put(name, scene);
                controllers.put(name, controller);
                primaryStage.setScene(scene);
                return controller;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            primaryStage.setScene(scenes.get(name));
            return controllers.get(name);
        }
        return null;
    }

    public void setOnClose() {
        try {
            OnlineStatusController userController = (OnlineStatusController) NetworkManager.getRegistry()
                    .lookup("OnlineStatusController");
            userController.disconnect(CurrentUser.getInstance().getUser().getPhoneNumber());
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
        
        if (controllers.get("friend-request") != null) {
            FriendRequestsController friendRequestsController = (FriendRequestsController) controllers
                    .get("friend-request");
            friendRequestsController.clearAll();
        }
        if (controllers.get("message") != null) {
        }

        controllers.clear();
        scenes.clear();
        System.exit(0);
    }

}
