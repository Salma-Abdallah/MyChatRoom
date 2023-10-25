package gov.iti.jets.dtos;


public class Contact {
    private User userId;
    private User contactId;
    private String category;

    public Contact(User userId, User contactId, String category) {
        this.userId = userId;
        this.contactId = contactId;
        this.category = category;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public User getContactId() {
        return contactId;
    }

    public void setContactId(User contactId) {
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