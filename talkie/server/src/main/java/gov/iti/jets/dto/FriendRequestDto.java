package gov.iti.jets.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class FriendRequestDto implements Serializable{
    private static final long serialVersionUID = 3946396403679141704L;

 private UserDto senderId;
    private UserDto receiverId;
    private boolean status;
    private Timestamp sentAt;


    public FriendRequestDto(UserDto senderId, UserDto receiverId, boolean status, Timestamp sentAt) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.status = status;
        this.sentAt = sentAt;
    }
    public FriendRequestDto(){}

    public UserDto getSenderId() {
        return senderId;
    }

    public void setSenderId(UserDto senderId) {
        this.senderId = senderId;
    }

    public UserDto getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(UserDto receiverId) {
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