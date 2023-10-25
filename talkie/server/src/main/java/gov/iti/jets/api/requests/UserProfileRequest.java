package gov.iti.jets.api.requests;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;

public class UserProfileRequest implements Serializable {
    private String userName;
    private String oldPhoneNumber;
    private String newPhoneNumber;
    private String password;
    private String newEmail;
    private String oldEmail;
    private String country;
    private String gender ;
    private LocalDate birthdate;
    private String bio;
    private byte[] image;

    public UserProfileRequest() {
    }
    public UserProfileRequest(String userName, String newPhoneNumber, String password, String email, String country, String gender, LocalDate birthdate, String bio, byte[] image) {
        this.userName = userName;
        this.newPhoneNumber = newPhoneNumber;
        this.password = password;
        this.newEmail = email;
        this.country = country;
        this.gender = gender;
        this.birthdate = birthdate;
        this.bio = bio;
        this.image = image;
    }

    public UserProfileRequest(String userName) {
        this.userName = userName;
    }

    public UserProfileRequest(byte[] image) {
        this.image = image;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNewPhoneNumber() {
        return newPhoneNumber;
    }

    public void setNewPhoneNumber(String newPhoneNumber) {
        this.newPhoneNumber = newPhoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return newEmail;
    }

    public void setEmail(String email) {
        this.newEmail = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getOldPhoneNumber() {
        return oldPhoneNumber;
    }

    public void setOldPhoneNumber(String oldPhoneNumber) {
        this.oldPhoneNumber = oldPhoneNumber;
    }

    public String getNewEmail() {
        return newEmail;
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }

    public String getOldEmail() {
        return oldEmail;
    }

    public void setOldEmail(String oldEmail) {
        this.oldEmail = oldEmail;
    }

    @Override
    public String toString() {
        return "UserProfileRequest{" +
                "userName='" + userName + '\'' +
                ", oldPhoneNumber='" + oldPhoneNumber + '\'' +
                ", newPhoneNumber='" + newPhoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", newEmail='" + newEmail + '\'' +
                ", oldEmail='" + oldEmail + '\'' +
                ", country='" + country + '\'' +
                ", gender='" + gender + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", bio='" + bio + '\'' +
                ", image=" + Arrays.toString(image) +
                '}';
    }
}
