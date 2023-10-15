package gov.iti.jets.entities;


public class ContactEntity {
     private Integer id;
    private Integer userId;
    private UserEntity contact;
    private String category;

    public ContactEntity(){}

    public ContactEntity(Integer id, Integer userId, UserEntity contact, String category) {
        this.id = id;
        this.userId = userId;
        this.contact = contact;
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public UserEntity getContact() {
        return contact;
    }

    public void setContact(UserEntity contact) {
        this.contact = contact;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "ContactEntity{" +
                "id=" + id +
                ", userId=" + userId +
                ", contact=" + contact +
                ", category='" + category + '\'' +
                '}';
    }
}