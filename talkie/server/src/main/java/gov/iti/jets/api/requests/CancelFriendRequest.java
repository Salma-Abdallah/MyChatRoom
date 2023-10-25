package gov.iti.jets.api.requests;

import java.io.Serializable;

public class CancelFriendRequest implements Serializable {

    private String userPhoneNumber;
    private String friendPhoneNumber;

    public CancelFriendRequest(String userPhoneNumber, String friendPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
        this.friendPhoneNumber = friendPhoneNumber;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getFriendPhoneNumber() {
        return friendPhoneNumber;
    }

    public void setFriendPhoneNumber(String friendPhoneNumber) {
        this.friendPhoneNumber = friendPhoneNumber;
    }

    @Override
    public String toString() {
        return "CancelFriendRequest{" +
                "userPhoneNumber='" + userPhoneNumber + '\'' +
                ", friendPhoneNumber='" + friendPhoneNumber + '\'' +
                '}';
    }
}
