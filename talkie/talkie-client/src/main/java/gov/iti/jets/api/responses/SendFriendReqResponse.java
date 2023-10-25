
package gov.iti.jets.api.responses;
import gov.iti.jets.dtos.FriendRequest;
import gov.iti.jets.dtos.RegularChat;

import java.io.Serializable;

public class SendFriendReqResponse implements Serializable {

    private FriendRequest friendRequest;
    private RegularChat regularChat;
    private String error;

    public SendFriendReqResponse(FriendRequest friendRequest, RegularChat regularChat, String error) {
        this.friendRequest = friendRequest;
        this.regularChat = regularChat;
        this.error = error;
    }

    public FriendRequest getFriendRequest() {
        return friendRequest;
    }

    public void setFriendRequest(FriendRequest friendRequest) {
        this.friendRequest = friendRequest;
    }

    public RegularChat getRegularChat() {
        return regularChat;
    }

    public void setRegularChat(RegularChat regularChat) {
        this.regularChat = regularChat;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "SendFriendReqResponse{" +
                "friendRequest=" + friendRequest +
                ", regularChat=" + regularChat +
                '}';
    }
}
