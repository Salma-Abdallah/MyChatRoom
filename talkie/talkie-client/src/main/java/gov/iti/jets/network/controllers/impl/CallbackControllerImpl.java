package gov.iti.jets.network.controllers.impl;


import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import gov.iti.jets.manager.MainPanelManager;
import gov.iti.jets.manager.StageManager;
import gov.iti.jets.dtos.CurrentUser;
import gov.iti.jets.dtos.FriendRequest;
import gov.iti.jets.dtos.GroupChat;
import gov.iti.jets.dtos.Message;
import gov.iti.jets.dtos.RegularChat;
import gov.iti.jets.dtos.User;
import gov.iti.jets.network.controllers.CallbackController;
import gov.iti.jets.network.controllers.OnlineStatusController;
import gov.iti.jets.network.manager.NetworkManager;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.stage.Popup;
import gov.iti.jets.controllers.*;

public class CallbackControllerImpl extends UnicastRemoteObject implements CallbackController {
    private static CallbackControllerImpl instance;
    private static boolean isServerConnected = false;
    private CallbackControllerImpl() throws RemoteException {}
    public static CallbackControllerImpl getInstance(){
        try {
            if(instance == null){
                instance = new CallbackControllerImpl();
            }
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        return instance;
    }

    @Override
    public void respond() throws RemoteException {
        System.out.println("You are still connected");
        isServerConnected = true;
    }

    public void checkServerAvailability(){
        new Thread(()->{
            while (true){
                if(isServerConnected){
                    isServerConnected = false;
                }
                else{
                    System.out.println("Reconnecting");
                    try {
                        OnlineStatusController userController = (OnlineStatusController) NetworkManager.getRegistry().lookup("OnlineStatusController");
                        userController.connect(
                                CurrentUser.getInstance().getUser(),
                                CallbackControllerImpl.getInstance());
                    } catch (RemoteException e) {
                        System.out.println("Reconnection Failed...");
                    } catch (NotBoundException e) {
                        System.out.println("Reconnection Failed...");
                    }
                }
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
    @Override
    public void createNewRegularChat(RegularChat chat){
        //Used when a friend request sent by me is accepted, or when a person i sent a friend request to sends me one
        Platform.runLater(() -> {
            FriendRequestsController friendRequestsController = MainPanelManager.INSTANCE.getFriendRequestsController();
            if(friendRequestsController!=null){
                friendRequestsController.deleteFRCard(chat.getFirstParticipant().getPhoneNumber());
            }
            // MainPanelManager.INSTANCE.getMessageController().addRegularChat(chat);
        });
    }

   
    @Override
    public void createNewFriendRequest(FriendRequest friendRequest) throws RemoteException {
        Platform.runLater(() -> {
            FriendRequestsController controller = MainPanelManager.INSTANCE.getFriendRequestsController();
            if(controller!=null)controller.addRecievedFriendRequest(friendRequest);
        });
    }

    @Override
    public void deleteRecievedFriendRequest(String senderPhoneNumber) throws RemoteException {
        System.out.println(senderPhoneNumber);
        Platform.runLater(() -> {
            FriendRequestsController controller = MainPanelManager.INSTANCE.getFriendRequestsController();
            if(controller!=null){
                System.out.println("Delete     :"+senderPhoneNumber);
                controller.deleteFRCard(senderPhoneNumber);
            }
        });
    }
    
}
