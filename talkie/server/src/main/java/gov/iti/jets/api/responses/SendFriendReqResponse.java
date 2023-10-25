package gov.iti.jets.api.responses;

import gov.iti.jets.api.validation.Validation;
import gov.iti.jets.dto.FriendRequestDto;
import gov.iti.jets.dto.RegularChatDto;

import java.io.Serializable;

public class SendFriendReqResponse implements Serializable {

    private FriendRequestDto friendRequest;
    private RegularChatDto regularChat;
    private String error;

    public SendFriendReqResponse(FriendRequestDto friendRequest, RegularChatDto regularChat, String error) {
        this.friendRequest = friendRequest;
        this.regularChat = regularChat;
        this.error = error;
    }

    public FriendRequestDto getFriendRequest() {
        return friendRequest;
    }

    public void setFriendRequest(FriendRequestDto friendRequest) {
        this.friendRequest = friendRequest;
    }

    public RegularChatDto getRegularChat() {
        return regularChat;
    }

    public void setRegularChat(RegularChatDto regularChat) {
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
