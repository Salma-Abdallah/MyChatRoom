package gov.iti.jets.controllers;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import gov.iti.jets.api.requests.AcceptFriendRequest;
import gov.iti.jets.api.requests.CancelFriendRequest;
import gov.iti.jets.api.requests.RefuseFriendRequest;
import gov.iti.jets.api.responses.AcceptFriendResponse;
import gov.iti.jets.api.responses.CancelFriendRequestResponse;
import gov.iti.jets.api.responses.RefuseFriendFriendResponse;
import gov.iti.jets.manager.MainPanelManager;
import gov.iti.jets.dtos.CurrentUser;
import gov.iti.jets.dtos.FriendRequest;
import gov.iti.jets.network.controllers.FriendRequestController;
import gov.iti.jets.network.manager.NetworkManager;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class FriendRequestCardController implements Initializable, FXMLController{

    @FXML
    private ImageView userImage;

    @FXML
    private Text NameLabel;

    @FXML
    private Text phoneNumberLabel;

    @FXML
    private HBox ButtonsHBox;

    @FXML
    private Button acceptreq;

    @FXML
    private Pane middlespacePane;

    @FXML
    private Button rejectReq;

    @FXML
    private Button CancelSentFriendReqButton;


    FriendRequest friendRequest;

    public FriendRequestCardController(FriendRequest friendRequest){
        this.friendRequest = friendRequest;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        if(friendRequest.getSenderId().getPhoneNumber().equals(CurrentUser.getInstance().getUser().getPhoneNumber())){
            loadSentFRData();
        }

        if(friendRequest.getReceiverId().getPhoneNumber().equals(CurrentUser.getInstance().getUser().getPhoneNumber())){
            loadReceivedFRData();
        }

        acceptreq.setOnAction((ActionEvent event)->{
            try {
                FriendRequestController friendRequestController = (FriendRequestController) NetworkManager.getRegistry().lookup("FriendRequestController");
                AcceptFriendResponse response = friendRequestController.accept(new AcceptFriendRequest(friendRequest.getReceiverId().getPhoneNumber(), friendRequest.getSenderId().getPhoneNumber()));
                if(response.getRegularChat()!=null){
                    response.getRegularChat().resetChatOrder();
                    MainPanelManager.INSTANCE.getFriendRequestsController().deleteRecievedFRCard(friendRequest.getSenderId().getPhoneNumber());
                    // MainPanelManager.INSTANCE.getMessageController().addRegularChat(response.getRegularChat());
                }
            } catch (RemoteException | NotBoundException e) {
                e.printStackTrace();
            }
            
        });

        rejectReq.setOnAction((ActionEvent event)->{
            try {
                FriendRequestController friendRequestController = (FriendRequestController) NetworkManager.getRegistry().lookup("FriendRequestController");
                System.out.println("Rece:    "+friendRequest.getReceiverId().getPhoneNumber());
                System.out.println("Sender:    "+friendRequest.getSenderId().getPhoneNumber());
                RefuseFriendFriendResponse response = friendRequestController.refuse(new RefuseFriendRequest(friendRequest.getReceiverId().getPhoneNumber(), friendRequest.getSenderId().getPhoneNumber()));
                System.out.println(response);
                if(response.getPhoneNumber()!=null){
                    MainPanelManager.INSTANCE.getFriendRequestsController().deleteRecievedFRCard(friendRequest.getSenderId().getPhoneNumber());
                }
            } catch (RemoteException | NotBoundException e) {
                e.printStackTrace();
            }
        });

        CancelSentFriendReqButton.setOnAction((ActionEvent event)->{
            try {
                FriendRequestController friendRequestController = (FriendRequestController) NetworkManager.getRegistry().lookup("FriendRequestController");
                CancelFriendRequestResponse response = friendRequestController.cancel(new CancelFriendRequest(friendRequest.getSenderId().getPhoneNumber(),friendRequest.getReceiverId().getPhoneNumber()));
                System.out.println(response);
                if(response.getPhoneNumber()!=null){
                    MainPanelManager.INSTANCE.getFriendRequestsController().deleteSentFRCard(friendRequest.getReceiverId().getPhoneNumber());
                }
            } catch (RemoteException | NotBoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
        
    }
    
    private void loadReceivedFRData(){
        ButtonsHBox.getChildren().remove(CancelSentFriendReqButton);

        NameLabel.setText(friendRequest.getSenderId().getUserName());
        phoneNumberLabel.setText(friendRequest.getSenderId().getPhoneNumber());
        ByteArrayInputStream inStreambj = new ByteArrayInputStream(friendRequest.getSenderId().getPicture());
        userImage.setImage(new Image(inStreambj));
    }
    private void loadSentFRData(){
        ButtonsHBox.getChildren().remove(acceptreq);
        ButtonsHBox.getChildren().remove(rejectReq);
        ButtonsHBox.getChildren().remove(middlespacePane);

        NameLabel.setText(friendRequest.getReceiverId().getUserName());
        phoneNumberLabel.setText(friendRequest.getReceiverId().getPhoneNumber());
        ByteArrayInputStream inStreambj = new ByteArrayInputStream(friendRequest.getReceiverId().getPicture());
        userImage.setImage(new Image(inStreambj));
    }

}

