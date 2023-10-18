package gov.iti.jets.dto;

public class RegularChatDto extends ChatDto{
    private static final long serialVersionUID = 5887637195618767821L;
    private int firstParticipant;
    private int secondParticipant;

    public RegularChatDto(String chatId, int firstParticipant, int secondParticipant) {
        this.chatId = chatId;
        this.firstParticipant = firstParticipant;
        this.secondParticipant = secondParticipant;
    }

    // public RegularChatDto(String chatId, UserDto firstParticipant) {
    //     this.chatId = chatId;
    //     this.firstParticipant = firstParticipant;
    // }
    public RegularChatDto (){}

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public int getFirstParticipant() {
        return firstParticipant;
    }
    public void setFirstParticipant(int firstParticipant) {
        this.firstParticipant = firstParticipant;
    }

    public int getSecondParticipant() {
        return secondParticipant;
    }

    public void setSecondParticipant(int secondParticipant) {
        this.secondParticipant = secondParticipant;
    }

    @Override
    public String toString() {
        return "RegularChat{" +
                "firstParticipant=" + firstParticipant +
                ", secondParticipant=" + secondParticipant +
                ", chatId='" + chatId + '\'' +
                '}';
    }
}