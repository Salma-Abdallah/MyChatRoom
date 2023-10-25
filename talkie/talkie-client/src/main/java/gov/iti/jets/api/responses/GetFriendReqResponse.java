package gov.iti.jets.api.responses;
import java.io.Serializable;
import java.util.List;

import gov.iti.jets.dtos.FriendRequest;

public class GetFriendReqResponse implements Serializable {
 


    private List<FriendRequest> receivedFriendRequestList;

    public GetFriendReqResponse( List<FriendRequest> FriendRequestList) {
        this.receivedFriendRequestList = FriendRequestList;
    }

    public List<FriendRequest> getReceivedFriendRequestList() {
        return receivedFriendRequestList;
    }

    public void setReceivedFriendRequestList(List<FriendRequest> receivedFriendRequestList) {
        this.receivedFriendRequestList = receivedFriendRequestList;
    }



}