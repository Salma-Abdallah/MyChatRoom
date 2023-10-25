package gov.iti.jets.api.responses;

import java.io.Serializable;

import gov.iti.jets.dto.ChatDto;

public class GroupChatResponse implements Serializable{
    private ChatDto newGroupChat;

    public GroupChatResponse(ChatDto newGroupChat){
        this.newGroupChat = newGroupChat;
    }

    public ChatDto getNewGroupChat() {
        return newGroupChat;
    }

    public void setNewGroupChat(ChatDto newGroupChat) {
        this.newGroupChat = newGroupChat;
    }

    @Override
    public String toString() {
        return "CreateGroupChatResponse [newGroupChat=" + newGroupChat + "]";
    }
    
    
}
