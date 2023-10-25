package gov.iti.jets.api.responses;


import java.io.Serializable;
import java.util.List;

import gov.iti.jets.dtos.GroupChat;
import gov.iti.jets.dtos.RegularChat;

public class GetChatsResponse implements Serializable{
    private static final long serialVersionUID = 5773299112415985829L;
    private List<RegularChat> allRegularChatsList;
    private List<GroupChat> allGroupChatsList;

    public List<RegularChat> getAllRegularChatsList() {
        return allRegularChatsList;
    }
    public List<GroupChat> getAllGroupChatsList() {
        return allGroupChatsList;
    }

    public GetChatsResponse(){}

    public GetChatsResponse(List<RegularChat> allRegularChatsList, List<GroupChat> allGroupChatsList) {
        this.allRegularChatsList = allRegularChatsList;
        this.allGroupChatsList = allGroupChatsList;
    }

    @Override
    public String toString() {
        return "GetChatsResponse{" +
                "allRegularChatsList=" + allRegularChatsList +
                ", allGroupChatsList=" + allGroupChatsList +
                '}';
    }
}
