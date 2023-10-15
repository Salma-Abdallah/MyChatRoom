package gov.iti.jets.dto;

import java.io.Serializable;

public class ContactDto implements Serializable{
    private UserDto userId;
    private UserDto contactId;
    private String category;

    public ContactDto(UserDto userId, UserDto contactId, String category) {
        this.userId = userId;
        this.contactId = contactId;
        this.category = category;
    }
    public ContactDto(){}

    public UserDto getUserId() {
        return userId;
    }

    public void setUserId(UserDto userId) {
        this.userId = userId;
    }

    public UserDto getContactId() {
        return contactId;
    }

    public void setContactId(UserDto contactId) {
        this.contactId = contactId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "userId=" + userId +
                ", contactId=" + contactId +
                ", category='" + category + '\'' +
                '}';
    }
}