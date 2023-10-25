package gov.iti.jets.dtos;


import java.io.Serializable;
import java.sql.Timestamp;

public class FriendRequest implements Serializable {
    private static final long serialVersionUID = 3946396403679141704L;

    private User senderId;
    private User receiverId;
    private boolean status;
    private Timestamp sentAt;


    public FriendRequest(User senderId, User receiverId, boolean status, Timestamp sentAt) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.status = status;
        this.sentAt = sentAt;
    }

    public User getSenderId() {
        return senderId;
    }

    public void setSenderId(User senderId) {
        this.senderId = senderId;
    }

    public User getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(User receiverId) {
        this.receiverId = receiverId;
    }

    public boolean isStatus() {
        return status;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Timestamp getSentAt() {
        return sentAt;
    }

    public void setSentAt(Timestamp sentAt) {
        this.sentAt = sentAt;
    }

    @Override
    public String toString() {
        return "FriendRequest{" +
                "senderId=" + senderId +
                ", receiverId=" + receiverId +
                ", status=" + status +
                ", sentAt=" + sentAt +
                '}';
    }
}