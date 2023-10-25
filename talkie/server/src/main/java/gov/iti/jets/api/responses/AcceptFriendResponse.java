package gov.iti.jets.api.responses;

import gov.iti.jets.dto.RegularChatDto;
import java.io.Serializable;

public class AcceptFriendResponse implements Serializable {
    private RegularChatDto regularChat;


   public AcceptFriendResponse(RegularChatDto regularChat){
       this.regularChat = regularChat;
   }



   public RegularChatDto getRegularChat() {
        return regularChat;
    }

    public void setRegularChat(RegularChatDto regularChat) {
        this.regularChat = regularChat;
    }




    @Override
    public String toString() {
        return "AcceptFriendRequest{" +
                "regularChat=" + regularChat +
                '}';
    }
}
