package gov.iti.jets.api.responses;

import java.io.Serializable;
import java.time.LocalDate;

public class LoginResponse implements Serializable {

    private static final long serialVersionUID = 2556755676703403250L;
    private String userName;
    private String phoneNumber;
    private String email;

    private String password;
    private String gender;
    private String country;
    private LocalDate birthDate;
    private String onlineStatus;
    private String bio;
    private byte[] picture;
    private String pictureExtension;

    public LoginResponse() {}

    public LoginResponse(String userName, String phoneNumber, String email, String password, String gender,
                         String country, LocalDate birthDate, String onlineStatus, String bio,
                         byte[] picture, String pictureExtension) {
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.country = country;
        this.birthDate = birthDate;
        this.onlineStatus = onlineStatus;
        this.bio = bio;
        this.picture = picture;
        this.pictureExtension = pictureExtension;
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

    public void setPassword(String password) {
        this.password = password;
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

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public String getPictureExtension() {
        return pictureExtension;
    }

    public void setPictureExtension(String pictureExtension) {
        this.pictureExtension = pictureExtension;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "userName='" + userName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                ", country='" + country + '\'' +
                ", birthDate=" + birthDate +
                ", onlineStatus='" + onlineStatus + '\'' +
                ", bio='" + bio + '\'' +
                ", picture=" + picture.length +
                ", pictureExtension='" + pictureExtension + '\'' +
                '}';
    }
}
