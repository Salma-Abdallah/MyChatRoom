package gov.iti.jets.dto;

import gov.iti.jets.entities.UserEntity;

import java.io.Serializable;

public class RegularChatDto extends ChatDto implements Serializable {
    private static final long serialVersionUID = 5887637195618767821L;
    private UserDto firstParticipant;
    private UserDto secondParticipant;

    public RegularChatDto(String chatId, UserDto firstParticipant, UserDto secondParticipant) {
        this.chatId = chatId;
        this.firstParticipant = firstParticipant;
        this.secondParticipant = secondParticipant;
    }

    public RegularChatDto(UserDto firstParticipant, UserDto secondParticipant) {
        this.firstParticipant = firstParticipant;
        this.secondParticipant = secondParticipant;
    }
    public RegularChatDto (){}

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public UserDto getFirstParticipant() {
        return firstParticipant;
    }
    public void setFirstParticipant(UserDto firstParticipant) {
        this.firstParticipant = firstParticipant;
    }

    public UserDto getSecondParticipant() {
        return secondParticipant;
    }

    public void setSecondParticipant(UserDto secondParticipant) {
        this.secondParticipant = secondParticipant;
    }

    //    @Override
    //    public String toString() {
    //        return "RegularChat{" +
    //                "firstParticipant=" + firstParticipant +
    //                ", secondParticipant=" + secondParticipant +
    //                ", chatId='" + chatId + '\'' +
    //                '}';
    //    }

}