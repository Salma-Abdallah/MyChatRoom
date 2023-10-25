/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gov.iti.jets.controllers;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import gov.iti.jets.api.requests.GetFriendReqRequest;
import gov.iti.jets.api.requests.SendFriendReqRequest;
import gov.iti.jets.api.responses.GetFriendReqResponse;
import gov.iti.jets.api.responses.SendFriendReqResponse;
import gov.iti.jets.api.validation.SignUpValidation;
import gov.iti.jets.dtos.CurrentUser;
import gov.iti.jets.dtos.FriendRequest;
import gov.iti.jets.manager.MainPanelManager;
import gov.iti.jets.network.controllers.FriendRequestController;
import gov.iti.jets.network.manager.NetworkManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author Sara Adel
 */
public class FriendRequestsController implements Initializable, FXMLController {
    // @FXML
    // private HBox hgrow;

    @FXML
    private HBox contactAddHBox;

    @FXML
    private VBox clearButtonVBox;

    @FXML
    private Button clearMessagesButton;

    @FXML
    private TextField addContactsTextField;

    @FXML
    private ImageView addContactsButton;

    @FXML
    private ToggleButton sentToggleButton;

    @FXML
    private ToggleButton receiveToggleButton;

    @FXML
    private VBox messagesTextBox;

    @FXML
    private ScrollPane fRscrollPane;

    @FXML
    private FlowPane friendRequestFlowPane;

    private FlowPane ReceivedfriendRequestFlowPane= new FlowPane();
    private FlowPane sentfriendRequestFlowPane = new FlowPane();

    private Map<String,FriendRequestCardController> fRControllerMap = new HashMap<>();
    private final Map<String, VBox> receivedFRLayoutsMap = new HashMap<>(); 
    private final Map<String, VBox> sentFRLayoutsMap = new HashMap<>(); 

    private List<String> responseMessageList = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // HBox.setHgrow(hgrow, Priority.ALWAYS);
        System.out.println("FRIEND REQ!!!!!!!!!!!!!!!!!!!!");

        clearButtonVBox.getChildren().remove(clearMessagesButton);

        receiveToggleButton.setSelected(true);
        loadOldFriendRequests();
        fRscrollPane.setContent(ReceivedfriendRequestFlowPane);

        sentToggleButton.setOnAction((ActionEvent event)->{
            receiveToggleButton.setSelected(false);
            System.out.println(sentfriendRequestFlowPane.getChildren().size());
            fRscrollPane.setContent(sentfriendRequestFlowPane);
        });

        receiveToggleButton.setOnAction((ActionEvent event)->{
            sentToggleButton.setSelected(false);
            System.out.println(ReceivedfriendRequestFlowPane.getChildren().size());
            fRscrollPane.setContent(ReceivedfriendRequestFlowPane);
        });

        clearMessagesButton.setOnAction((ActionEvent event)->{
            messagesTextBox.getChildren().clear();
            clearButtonVBox.getChildren().remove(clearMessagesButton);
        });

        addContactsTextField.setOnAction((ActionEvent event)->{
            sendFriendRequests();
        });
        addContactsButton.setOnMouseClicked((MouseEvent event)->{
            sendFriendRequests();
        });

    }

    private void sendFriendRequests(){
        messagesTextBox.getChildren().clear();
        String phoneNumber[] = addContactsTextField.getText().split(",");
        Arrays.asList(phoneNumber).forEach((x)->SendSingleFR(x.trim()));
        //TODO display messages
        clearButtonVBox.getChildren().add(clearMessagesButton);
        addContactsTextField.setText("");
    }

    private void SendSingleFR(String phoneNumber){
        if(!SignUpValidation.phoneNumberValidate(phoneNumber)){
            Label errorLabel = new Label(phoneNumber + "- Not a valid Phone Number");
            errorLabel.setFont(Font.font("Comic Sans MS",12));
            errorLabel.setTextFill(Paint.valueOf("red"));
            messagesTextBox.getChildren().add(errorLabel);
            // responseMessageList.add(phoneNumber)
        }else{
            try {
                System.out.println("SEND NEW FRIEND REQUEST");
                FriendRequestController friendRequestController = (FriendRequestController) NetworkManager.getRegistry().lookup("FriendRequestController");
                SendFriendReqResponse response = (SendFriendReqResponse) friendRequestController.sendFriendRequest(new SendFriendReqRequest(CurrentUser.getInstance().getUser().getPhoneNumber(), phoneNumber));
                System.out.println(response);
                if(response.getRegularChat()!=null){
                    response.getRegularChat().resetChatOrder();
                    // MainPanelManager.INSTANCE.getMessageController().addRegularChat(response.getRegularChat());
                    Label errorLabel = new Label(phoneNumber + "- You are now Friends ");
                    errorLabel.setFont(Font.font("Comic Sans MS",12));
                    errorLabel.setTextFill(Paint.valueOf("green"));
                    messagesTextBox.getChildren().add(errorLabel);
                }else if(response.getFriendRequest()!=null){
                    addSentFriendRequest(response.getFriendRequest());
                    Label errorLabel = new Label(phoneNumber + "- Friend Request Sent Successfully");
                    errorLabel.setFont(Font.font("Comic Sans MS",12));
                    errorLabel.setTextFill(Paint.valueOf("green"));
                    messagesTextBox.getChildren().add(errorLabel);
                }else{
                    Label errorLabel = new Label(phoneNumber + "- User Not Found");
                    errorLabel.setFont(Font.font("Comic Sans MS",12));
                    errorLabel.setTextFill(Paint.valueOf("red"));
                    messagesTextBox.getChildren().add(errorLabel);
                }
            } catch (RemoteException | NotBoundException e) {
                e.printStackTrace();
            }

        }
    }

    private void loadOldFriendRequests(){
        try {
            FriendRequestController sentFRController = (FriendRequestController) NetworkManager.getRegistry().lookup("FriendRequestController");
            GetFriendReqResponse sentResponse = sentFRController.getSentFriendRequestByUserID(new GetFriendReqRequest(CurrentUser.getInstance().getUser().getPhoneNumber()));
            sentResponse.getReceivedFriendRequestList().forEach((x)-> addSentFriendRequest(x) );

            FriendRequestController receivedFRController = (FriendRequestController) NetworkManager.getRegistry().lookup("FriendRequestController");
            GetFriendReqResponse receivedResponse = receivedFRController.getReceivedFriendReqByUserID(new GetFriendReqRequest(CurrentUser.getInstance().getUser().getPhoneNumber()));
            receivedResponse.getReceivedFriendRequestList().forEach((x)-> addRecievedFriendRequest(x) );
        } catch (RemoteException e){
            e.printStackTrace();
        } catch(NotBoundException e) {
            e.printStackTrace();
        }
    }


    public void addRecievedFriendRequest(FriendRequest receivedFriendRequest){
        System.out.println("2::::::::::::::::::::"+receivedFriendRequest.getSenderId());
        FriendRequestCardController friendRequestCardController = new FriendRequestCardController(receivedFriendRequest);
        fRControllerMap.put(receivedFriendRequest.getSenderId().getPhoneNumber(),friendRequestCardController);
        VBox freindReqCard = creatFriendReqCard(friendRequestCardController);
        receivedFRLayoutsMap.put(receivedFriendRequest.getSenderId().getPhoneNumber(),freindReqCard);
        if(freindReqCard!=null)ReceivedfriendRequestFlowPane.getChildren().add(0,freindReqCard);
    }

    public void addSentFriendRequest(FriendRequest sentFriendRequest){
        System.out.println("1                    "+sentFriendRequest.getReceiverId());
        FriendRequestCardController friendRequestCardController = new FriendRequestCardController(sentFriendRequest);
        fRControllerMap.put(sentFriendRequest.getReceiverId().getPhoneNumber(),friendRequestCardController);
        VBox freindReqCard = creatFriendReqCard(friendRequestCardController);
        sentFRLayoutsMap.put(sentFriendRequest.getReceiverId().getPhoneNumber(),freindReqCard);
        if(freindReqCard!=null)sentfriendRequestFlowPane.getChildren().add(0,freindReqCard);
    }

    private VBox creatFriendReqCard(FriendRequestCardController friendRequestCardController){
        FXMLLoader loader = new FXMLLoader();
        loader.setController(friendRequestCardController);
        try {
            VBox freindReqCard= loader.load(getClass().getClassLoader().getResource("views/components/Friend-cards.fxml").openStream());
            return freindReqCard;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void deleteRecievedFRCard(String friendPhoneNumber){
        ReceivedfriendRequestFlowPane.getChildren().remove(receivedFRLayoutsMap.get(friendPhoneNumber));
    }

    public void deleteSentFRCard(String friendPhoneNumber){
        sentfriendRequestFlowPane.getChildren().remove(sentFRLayoutsMap.get(friendPhoneNumber));
    }

    public void deleteFRCard(String friendPhoneNumber){
        if(receivedFRLayoutsMap.get(friendPhoneNumber)!=null)deleteRecievedFRCard(friendPhoneNumber);
        if(sentFRLayoutsMap.get(friendPhoneNumber)!=null)deleteSentFRCard(friendPhoneNumber);
    }

    public void clearAll(){
        fRControllerMap.clear();
        receivedFRLayoutsMap.clear();
        sentFRLayoutsMap.clear();
    }
    
    
}
