package gov.iti.jets.api.responses;


import gov.iti.jets.dtos.RegularChat;
import java.io.Serializable;

public class AcceptFriendResponse implements Serializable {
    private RegularChat regularChat;


   public AcceptFriendResponse(RegularChat regularChat){
       this.regularChat = regularChat;
   }


   public RegularChat getRegularChat() {
        return regularChat;
    }

    public void setRegularChat(RegularChat regularChat) {
        this.regularChat = regularChat;
    }




    @Override
    public String toString() {
        return "AcceptFriendRequest{" +
                "regularChat=" + regularChat +
                '}';
    }
}
