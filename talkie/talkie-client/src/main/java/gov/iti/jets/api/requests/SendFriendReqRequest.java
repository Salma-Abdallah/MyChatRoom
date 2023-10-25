package gov.iti.jets.api.requests;

import java.io.Serializable;

public class SendFriendReqRequest implements Serializable {

    private String senderPhoneNumber;
    private String receiverPhoneNumber;

    public SendFriendReqRequest(String senderPhoneNumber, String receiverPhoneNumber) {
        this.senderPhoneNumber = senderPhoneNumber;
        this.receiverPhoneNumber = receiverPhoneNumber;
    }

    public String getSenderPhoneNumber() {
        return senderPhoneNumber;
    }

    public void setSenderPhoneNumber(String senderPhoneNumber) {
        this.senderPhoneNumber = senderPhoneNumber;
    }

    public String getReceiverPhoneNumber() {
        return receiverPhoneNumber;
    }

    public void setReceiverPhoneNumber(String receiverPhoneNumber) {
        this.receiverPhoneNumber = receiverPhoneNumber;
    }

    @Override
    public String toString() {
        return "SendFriendReqRequest{" +
                "senderPhoneNumber='" + senderPhoneNumber + '\'' +
                ", receiverPhoneNumber='" + receiverPhoneNumber + '\'' +
                '}';
    }
}
