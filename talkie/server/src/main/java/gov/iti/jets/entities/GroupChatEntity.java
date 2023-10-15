package gov.iti.jets.entities;


public class GroupChatEntity {
    private String chatId;
    private Integer ownerId;
    private String name;

    public GroupChatEntity(String chatId, Integer ownerId, String name) {
        this.chatId = chatId;
        this.ownerId = ownerId;
        this.name = name;
    }

    public GroupChatEntity(Integer ownerId, String name) {
        this.ownerId = ownerId;
        this.name = name;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "GroupChatEntity{" +
                "chatId='" + chatId + '\'' +
                ", ownerId=" + ownerId +
                ", name='" + name + '\'' +
                '}';
    }
}