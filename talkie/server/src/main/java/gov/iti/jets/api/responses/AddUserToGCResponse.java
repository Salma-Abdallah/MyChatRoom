package gov.iti.jets.api.responses;

import java.io.Serializable;

import gov.iti.jets.dto.UserDto;

public class AddUserToGCResponse implements Serializable {

    private UserDto addedUser;
    private String error;

    public AddUserToGCResponse(UserDto addedUser, String error) {
        this.addedUser = addedUser;
        this.error = error;
    }

    public UserDto getAddedUser() {
        return addedUser;
    }

    public void setAddedUser(UserDto addedUser) {
        this.addedUser = addedUser;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "AddUserToGroupChatResponse [addedUser=" + addedUser + ", error=" + error + "]";
    }

}
