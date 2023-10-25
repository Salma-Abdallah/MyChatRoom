package gov.iti.jets.entities;



public class RegularChatEntity {
    private String id;
    private UserEntity firstParticipantId;
    private UserEntity secondParticipantId;

    public RegularChatEntity(String id, UserEntity firstParticipantId, UserEntity secondParticipantId) {
        this.id = id;
        this.firstParticipantId = firstParticipantId;
        this.secondParticipantId = secondParticipantId;

    }
    public RegularChatEntity(UserEntity firstParticipantId, UserEntity secondParticipantId) {
        this.firstParticipantId = firstParticipantId;
        this.secondParticipantId = secondParticipantId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserEntity getFirstParticipantId() {
        return firstParticipantId;
    }

    public void setFirstParticipantId(UserEntity firstParticipantId) {
        this.firstParticipantId = firstParticipantId;
    }

    public UserEntity getSecondParticipantId() {
        return secondParticipantId;
    }

    public void setSecondParticipantId(UserEntity secondParticipantId) {
        this.secondParticipantId = secondParticipantId;
    }

    @Override
    public String toString() {
        return "RegularChatEntity{" +
                "id='" + id + '\'' +
                ", firstParticipantId=" + firstParticipantId +
                ", secondParticipantId=" + secondParticipantId +
                '}';
    }
}