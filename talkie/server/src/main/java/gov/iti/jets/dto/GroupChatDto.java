package gov.iti.jets.dto;

import java.util.List;

public class GroupChatDto extends ChatDto{
    private static final long serialVersionUID = 5887637195618767821L;

     private int owner;
    private String name;
    private List<UserDto> participants;
    public GroupChatDto(String chatId, int owner, String name, List<UserDto> participants) {
        this.chatId = chatId;
        this.owner = owner;
        this.name = name;
        this.participants = participants;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserDto> getParticipants() {
        return participants;
    }

    public void setParticipants(List<UserDto> participants) {
        this.participants = participants;
    }

    @Override
    public String toString() {
        return "GroupChat{" +
                "chatId='" + chatId + '\'' +
                ", owner=" + owner +
                ", name='" + name + '\'' +
                ", participants=" + participants +
                '}';
    }
}
