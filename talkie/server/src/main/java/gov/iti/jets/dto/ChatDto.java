package gov.iti.jets.dto;

import java.io.Serializable;

public class ChatDto implements Serializable{
    private static final long serialVersionUID = 5887637195618767821L;

    protected String chatId;

    public ChatDto() {
    }

    public ChatDto(String chatId) {
        this.chatId = chatId;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }
}
