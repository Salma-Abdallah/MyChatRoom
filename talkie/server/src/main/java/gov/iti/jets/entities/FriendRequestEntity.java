package gov.iti.jets.entities;

import java.sql.Timestamp;

public class FriendRequestEntity {

    private Integer id;
    private UserEntity sender;
    private UserEntity receiver;
    private boolean status;
    private Timestamp sentAt;


    public FriendRequestEntity(Integer id, UserEntity sender, UserEntity receiver, boolean status, Timestamp sentAt) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.status = status;
        this.sentAt = sentAt;
    }
    public FriendRequestEntity(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserEntity getSender() {
        return sender;
    }

    public void setSender(UserEntity sender) {
        this.sender = sender;
    }

    public UserEntity getReceiver() {
        return receiver;
    }

    public void setReceiver(UserEntity receiver) {
        this.receiver = receiver;
    }

    public boolean isStatus() {
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
        return "FriendRequestEntity{" +
                "id=" + id +
                ", sender=" + sender +
                ", receiver=" + receiver +
                ", status=" + status +
                ", sentAt=" + sentAt +
                '}';
    }
}