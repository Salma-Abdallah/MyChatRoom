package gov.iti.jets.entities;

public class AdminEntity {
    private Integer id;
    private String adminName;
    private String phoneNumber;
    private String password;
    private String salt;

    public AdminEntity() {}

    public AdminEntity(Integer id, String adminName, String phoneNumber, String password,String salt) {
        this.id = id;
        this.adminName = adminName;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.salt = salt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String passWord) {
        this.password = passWord;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", adminName='" + adminName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                '}';
    }
}
