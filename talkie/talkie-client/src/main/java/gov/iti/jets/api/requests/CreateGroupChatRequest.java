package gov.iti.jets.api.requests;
import java.io.Serializable;

public class CreateGroupChatRequest implements Serializable{


    private String userPhoneNumber;
    private String groupName;

    public CreateGroupChatRequest(String userPhoneNumber, String chatName){
        this.userPhoneNumber = userPhoneNumber;
        this.groupName = chatName;
    }

    public String getGroupName() {
        return groupName;
    }
    public void setGroupName(String chatName) {
        this.groupName = chatName;
    }
    
    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }
    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    @Override
    public String toString() {
        return "CreateGroupChatRequest [userPhoneNumber=" + userPhoneNumber + ", chatName=" + groupName + "]";
    }
    

    
}
