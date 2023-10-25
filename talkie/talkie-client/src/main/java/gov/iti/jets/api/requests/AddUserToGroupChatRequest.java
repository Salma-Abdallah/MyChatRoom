package gov.iti.jets.api.requests;
import java.io.Serializable;

public class AddUserToGroupChatRequest implements Serializable{


    private String userPhoneToBeAdded;
    private String chatId;

    public AddUserToGroupChatRequest(String userPhoneToBeAdded, String chatId){
        this.userPhoneToBeAdded = userPhoneToBeAdded;
        this.chatId = chatId;
    }

    public String getUserPhoneToBeAdded() {
        return userPhoneToBeAdded;
    }

    public void setUserPhoneToBeAdded(String userPhoneToBeAdded) {
        this.userPhoneToBeAdded = userPhoneToBeAdded;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    

    @Override
    public String toString() {
        return "AddUserToGroupChat [userPhoneToBeAdded=" + userPhoneToBeAdded + ", chatId=" + chatId + "]";
    }

    
}
