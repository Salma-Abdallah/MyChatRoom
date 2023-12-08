package gov.iti.jets.entities;


import java.io.Serializable;

public class RegularChatEntity  extends ChatEntity implements Serializable{
    private static final long serialVersionUID = 5887637195618767821L;
    private UserEntity firstParticipant;
    private UserEntity secondParticipant;

    public RegularChatEntity(String chatId, UserEntity firstParticipantId, UserEntity secondParticipantId) {
        this.chatId = chatId;
        this.firstParticipant = firstParticipantId;
        this.secondParticipant = secondParticipantId;

    }
    public RegularChatEntity(UserEntity firstParticipantId, UserEntity secondParticipantId) {
        this.firstParticipant = firstParticipantId;
        this.secondParticipant = secondParticipantId;
    }

    public String getId() {
        return chatId;
    }

    public void setId(String id) {
        this.chatId = id;
    }

    public UserEntity getFirstParticipantId() {
        return firstParticipant;
    }

    public void setFirstParticipantId(UserEntity firstParticipantId) {
        this.firstParticipant = firstParticipantId;
    }

    public UserEntity getSecondParticipantId() {
        return secondParticipant;
    }

    public void setSecondParticipantId(UserEntity secondParticipantId) {
        this.secondParticipant = secondParticipantId;
    }

    //    @Override
    //    public String toString() {
    //        return "RegularChatEntity{" +
    //                "id='" + chatId + '\'' +
    //                ", firstParticipantId=" + firstParticipant +
    //                ", secondParticipantId=" + secondParticipant +
    //                '}';
    //    }

}