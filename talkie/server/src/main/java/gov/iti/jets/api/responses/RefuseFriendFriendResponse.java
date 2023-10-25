package gov.iti.jets.api.responses;


import java.io.Serializable;

public class RefuseFriendFriendResponse implements Serializable {
    private static final long serialVersionUID = -652405811389313082L;
    private String phoneNumber;

    public RefuseFriendFriendResponse(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
