package gov.iti.jets.api.responses;


import java.io.Serializable;

public class CancelFriendRequestResponse implements Serializable {
    private static final long serialVersionUID = -6546150382426913517L;
    private String phoneNumber;

    public CancelFriendRequestResponse(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
