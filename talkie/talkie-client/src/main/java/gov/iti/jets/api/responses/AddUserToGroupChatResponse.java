package gov.iti.jets.api.responses;

import java.io.Serializable;

import gov.iti.jets.dtos.User;

public class AddUserToGroupChatResponse implements Serializable{
    private User addedUser;
    private String error;
    
    AddUserToGroupChatResponse (User addedUser, String error){
        this.addedUser = addedUser;
        this.error = error;
    }
    
    public User getAddedUser() {
        return addedUser;
    }

    public void setAddedUser(User addedUser) {
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