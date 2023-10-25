package gov.iti.jets.api.responses;

import gov.iti.jets.dto.GroupChatDto;
import gov.iti.jets.dto.RegularChatDto;

import java.io.Serializable;
import java.util.List;

public class ChatResponse implements Serializable{

    private static final long serialVersionUID = 5773299112415985829L;

    private List<RegularChatDto> allRegularChatsList;
    private List<GroupChatDto> allGroupChatsList;

    public ChatResponse(){}

    public ChatResponse(List<RegularChatDto> allRegularChatsList, List<GroupChatDto> allGroupChatsList) {
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
