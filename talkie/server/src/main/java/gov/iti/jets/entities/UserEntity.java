package gov.iti.jets.entities;

import java.time.LocalDate;
import java.sql.Timestamp;


public class UserEntity{

    private Integer id;
    private String userName;
    private String phoneNumber;
    private String email;
    private String password;
    private String gender;
    private String country;
    private LocalDate birthDate;
    private String onlineStatus;
    private String bio;
    private String pictureUrl;
    private Timestamp createdAt;
    private String salt;

    public UserEntity() {}

    public UserEntity(Integer id, String userName, String phoneNumber, String email, String password,
                      String gender, String country, LocalDate birthDate, String onlineStatus,
                      String bio, String pictureUrl, Timestamp createdAt, String salt) {
        this.id = id;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.country = country;
        this.birthDate = birthDate;
        this.onlineStatus = onlineStatus;
        this.bio = bio;
        this.pictureUrl = pictureUrl;
        this.createdAt = createdAt;
        this.salt = salt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String passWord) {
        this.password = passWord;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(String onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
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
                ", userName='" + userName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                ", country='" + country + '\'' +
                ", birthDate=" + birthDate +
                ", onlineStatus='" + onlineStatus + '\'' +
                ", bio='" + bio + '\'' +
                ", pictureUrl='" + pictureUrl + '\'' +
                ", createdAt=" + createdAt +
                ", salt='" + salt + '\'' +
                '}';
    }
}