package gov.iti.jets.api.responses;

import gov.iti.jets.api.validation.RegisterValidation;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;

public class UserProfileResponse implements Serializable {
    private String userName;
    private String phoneNumber;
    private String password;
    private String email;
    private String country;
    private String gender ;
    private LocalDate birthdate;
    private String bio;
    private byte[] image ;
    private int result;
    private String message;

    private RegisterValidation validation;

    public UserProfileResponse() {
    }

    public UserProfileResponse(String userName, String phoneNumber, String password, String email, String country, String gender, LocalDate birthdate, String bio, byte[] image) {
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.email = email;
        this.country = country;
        this.gender = gender;
        this.birthdate = birthdate;
        this.bio = bio;
        this.image = image;
    }

    public UserProfileResponse(String userName) {
        this.userName = userName;
    }

    public UserProfileResponse(byte[] image) {
        this.image = image;
    }

    public UserProfileResponse(int result) {
        this.result = result;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public RegisterValidation getValidation() {
        return validation;
    }

    public void setValidation(RegisterValidation validation) {
        this.validation = validation;
    }

    @Override
    public String toString() {
        return "EditUserProfileResponse{" +
                "userName='" + userName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", country='" + country + '\'' +
                ", gender='" + gender + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", bio='" + bio + '\'' +
                ", image=" + Arrays.toString(image) +
                '}';
    }

}
