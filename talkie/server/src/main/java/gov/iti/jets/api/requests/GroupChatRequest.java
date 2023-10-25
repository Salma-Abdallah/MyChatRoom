package gov.iti.jets.api.requests;

import java.io.Serializable;

public class GroupChatRequest implements Serializable {

    private String userPhoneNumber;
    private String groupName;

    public GroupChatRequest(String userPhoneNumber, String groupName) {
        this.userPhoneNumber = userPhoneNumber;
        this.groupName = groupName;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public String toString() {
        return "CreateGroupChatRequest{" +
                "userPhoneNumber='" + userPhoneNumber + '\'' +
                ", groupName='" + groupName + '\'' +
                '}';
    }
}
