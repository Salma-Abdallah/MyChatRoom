package gov.iti.jets.api.responses;


import java.io.Serializable;

import gov.iti.jets.dtos.Chat;

public class CreateGroupChatResponse implements Serializable{
    private Chat newGroupChat;

    public CreateGroupChatResponse(Chat newGroupChat){
        this.newGroupChat = newGroupChat;
    }

    public Chat getNewGroupChat() {
        return newGroupChat;
    }

    public void setNewGroupChat(Chat newGroupChat) {
        this.newGroupChat = newGroupChat;
    }

    @Override
    public String toString() {
        return "CreateGroupChatResponse [newGroupChat=" + newGroupChat + "]";
    }
    

}
