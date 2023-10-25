package gov.iti.jets.api.responses;


import gov.iti.jets.dto.FriendRequestDto;

import java.io.Serializable;
import java.util.List;

public class GetFriendResponse implements Serializable {
    private List<FriendRequestDto> receivedFriendRequestList;
    public List<FriendRequestDto> getReceivedFriendRequestList() {
        return receivedFriendRequestList;
    }
    public void setReceivedFriendRequestList(List<FriendRequestDto> receivedFriendRequestList) {
        this.receivedFriendRequestList = receivedFriendRequestList;
    }
    public GetFriendResponse( List<FriendRequestDto> FriendRequestList) {
        this.receivedFriendRequestList = FriendRequestList;
    }

}