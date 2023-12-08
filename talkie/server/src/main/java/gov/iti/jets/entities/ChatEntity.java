package gov.iti.jets.entities;


import gov.iti.jets.mappers.ChatMapper;

public class ChatEntity {
     protected String chatId;

    public ChatEntity(String id) {
        this.chatId = id;
    }

    public ChatEntity(){}
    public String getChatId() {
        return chatId;
    }

    public void setChatId(String id) {
        this.chatId = id;
    }
}