package gov.iti.jets.controllers;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

// import gov.iti.jets.dto.requests.SendMessageRequest;
// import gov.iti.jets.dto.responses.SendMessageResponse;
import gov.iti.jets.dtos.Chat;
import gov.iti.jets.dtos.CurrentUser;
import gov.iti.jets.dtos.RegularChat;
import gov.iti.jets.dtos.User;
// import gov.iti.jets.network.controllers.MessageController;
// import gov.iti.jets.network.manager.NetworkManager;/
import gov.iti.jets.dtos.RegularChat;
import gov.iti.jets.dtos.GroupChat;
import gov.iti.jets.dtos.Message;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class ChattingPanelController implements Initializable, FXMLController{
    @FXML
    private VBox chattingPanelVBox;

    @FXML
    private HBox topPartHBox;

    @FXML
    private ImageView chatImageView;

    @FXML
    private VBox chatInfoImageView;

    @FXML
    private Label chatNameLabel;

    @FXML
    private Label chatNumbersLabel;

    @FXML
    private Button videoCallButton;

    @FXML
    private Button voiceCallButton;

    @FXML
    private Button moreButton;

    @FXML
    private ScrollPane messagesScrollPane;

    @FXML
    private VBox messagesContainerVBox;

    @FXML
    private ComboBox<String> textFontComboBox;

    @FXML
    private ComboBox<Integer> textSizeComboBox;

    @FXML
    private ToggleButton textBoldToggleButton;

    @FXML
    private ToggleButton textItalicToggleButton;

    @FXML
    private ToggleButton textUnderlineToggleButton;

    @FXML
    private ColorPicker textColorPicker;

    @FXML
    private Button attachmentButton;

    @FXML
    private TextField typingTextField;

    @FXML
    private Button voiceNoteButton;

    @FXML
    private Button sendButton;


    private Chat chat;
    // private List<HBox> messagesHBoxList;
    private Message newMessage;
    private Font messageFont = new Font("Comic Sans MS",12);
    
    public Chat getChat() {
        return chat;
    }


    public void setChat(Chat chat) {
        this.chat = chat;
    }

    

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        String fonts[] = {"Verdana", "Helvetica", "Times New Roman", "Comic Sans MS", "Impact", "Lucida Sans Unicode"};
        textFontComboBox.setItems(FXCollections.observableArrayList(fonts));

        Integer fontSizes[] = {8,10,12,14,16,20};
        textSizeComboBox.setItems(FXCollections.observableArrayList(fontSizes));

        resetMessageFormating();

        textFontComboBox.setOnAction((ActionEvent event)->{
            newMessage.setFontStyle(textFontComboBox.getValue());
            updateMessageFont();  
        });

        textSizeComboBox.setOnAction((ActionEvent event)->{
            newMessage.setFontSize(textSizeComboBox.getValue());
            updateMessageFont();
        });

        textBoldToggleButton.setOnAction((ActionEvent event)->{
            if(textBoldToggleButton.isSelected())newMessage.setBold(true);
            if(!textBoldToggleButton.isSelected())newMessage.setBold(false);
            updateMessageFont();
        });

        textItalicToggleButton.setOnAction((ActionEvent event)->{
            if(textItalicToggleButton.isSelected())newMessage.setItalic(true);
            if(!textItalicToggleButton.isSelected())newMessage.setItalic(false);
            updateMessageFont();
        });

        textUnderlineToggleButton.setOnAction((ActionEvent event)->{
            if(textUnderlineToggleButton.isSelected())newMessage.setUnderlined(true);
            if(!textUnderlineToggleButton.isSelected())newMessage.setUnderlined(false);
            updateMessageFont();
        });

        textColorPicker.setOnAction((ActionEvent event)->{
            newMessage.setFontColor(textColorPicker.getValue().toString());
            System.out.println(textColorPicker.getValue().toString());
            typingTextField.setStyle(String.format("-fx-background-radius: 15; -fx-text-fill: #%s;", textColorPicker.getValue().toString().substring(2)));
            updateMessageFont();
        });

        // sendButton.setOnAction((ActionEvent event)->{
        //     sendNewMessage();
        // });
        // typingTextField.setOnAction((ActionEvent event)->{
        //     sendNewMessage();
        // });
        // messagesContainerVBox.heightProperty().addListener(Observable -> messagesScrollPane.setVvalue(1D));
    }
    // private void sendNewMessage(){
    //     newMessage.setContent(typingTextField.getText());
        // try {
            // MessageController messageController = (MessageController) NetworkManager.getRegistry().lookup("MessageController");
            // SendMessageResponse response = messageController.sendMessage(new SendMessageRequest(newMessage));
            // if(response.getMessage()!=null)typingTextField.setText("");
            //TODO if(response.getMessage() == null) display servererror message
        // } catch (RemoteException | NotBoundException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }
        
    // }
    private void resetMessageFormating(){
        textSizeComboBox.setValue(12);
        textFontComboBox.setValue("Comic Sans MS");
        textBoldToggleButton.setSelected(false);
        textItalicToggleButton.setSelected(false);
        textUnderlineToggleButton.setSelected(false);
        textColorPicker.setValue(Color.BLACK);
        typingTextField.setStyle("-fx-background-radius: 15;");
    }
    public void initiateChattingService(){
        typingTextField.setText("");
        instantiateNewMessage();
    }
    
    private void updateMessageFont(){
        messageFont = Font.font(newMessage.getFontStyle()
            ,newMessage.isBold()?FontWeight.BOLD:FontWeight.NORMAL
            ,newMessage.isItalic()?FontPosture.ITALIC:FontPosture.REGULAR
            ,newMessage.getFontSize());
        typingTextField.fontProperty().set(messageFont);
    }

    private void instantiateNewMessage(){
        newMessage = new Message(null, CurrentUser.getInstance().getUser()
                                , chat.getChatId()
                                , textFontComboBox.getValue()
                                , textColorPicker.getValue().toString()
                                , textSizeComboBox.getValue()
                                , textBoldToggleButton.isSelected(), textItalicToggleButton.isSelected(), textUnderlineToggleButton.isSelected()
                                , null, Timestamp.valueOf(LocalDateTime.now()), null, null, false);
    }

    public void setupChatInfo (){
        if(chat instanceof RegularChat){
            setupRegularChatInfo((RegularChat)chat);
        }else if(chat instanceof GroupChat){
            setupGroupChatInfo((GroupChat)chat);
            //To BE Modified when adding Group chat Image;
        }
    }

    private void setupRegularChatInfo(RegularChat chat){

        chatNameLabel.setText(chat.getFirstParticipant().getUserName()); 

        ByteArrayInputStream inStreambj = new ByteArrayInputStream(chat.getFirstParticipant().getPicture());
        chatImageView.setImage(new Image(inStreambj)); 

        chatNumbersLabel.setText(chat.getFirstParticipant().getPhoneNumber());

    }

    private void setupGroupChatInfo(GroupChat chat){
        chatNameLabel.setText(chat.getName());

        String chatParticipants= new String();
        int i=0;
        for(User user:chat.getParticipants()){
            if(i>0)chatParticipants+=", ";
            i++;
            chatParticipants+=user.getPhoneNumber();

        }
        chatNumbersLabel.setText(chatParticipants);
        chatImageView.setImage(new Image(getClass().getClassLoader().getResource("images/default_user.png").toString())); 
        //To BE Modified when adding Group chat Image;
    }

    public void loadMessages(Collection<HBox> messagesHBoxList){
        // this.messagesHBoxList=new ArrayList<>(messagesHBoxList);
        messagesContainerVBox.getChildren().clear();
        messagesContainerVBox.getChildren().addAll(messagesHBoxList);

        // for(HBox messageHBox:messagesHBoxList){
        //     messagesContainerVBox.getChildren().add(messageHBox);
        // }
    }



}
