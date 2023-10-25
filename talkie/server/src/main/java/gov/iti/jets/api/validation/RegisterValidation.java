package gov.iti.jets.api.validation;

import gov.iti.jets.api.requests.RegisterRequest;
import gov.iti.jets.api.requests.UserProfileRequest;
import gov.iti.jets.mappers.UserMapper;
import java.io.Serializable;

public class RegisterValidation implements Serializable, Validation {

    private static final long serialVersionUID = -2449817711737079712L;
    transient private RegisterRequest request;
    private String emailError = null;
    private String phoneNumberError = null;
    
    private UserProfileRequest usreRequest;
   

    public RegisterValidation() {}

    public RegisterValidation(RegisterRequest request) {
        this.request = request;
    }
    public RegisterValidation(UserProfileRequest userProfileRequest) {
        this.usreRequest = userProfileRequest;
    }


    @Override
    public boolean validate() {
        boolean isValid = true;
        UserMapper userMapper = new UserMapper();
        if(userMapper.findUserByPhoneNumber(request.getPhoneNumber()).isPresent()){
            phoneNumberError = "Phone number already exists";
            isValid = false;
        }
        if(userMapper.findUserByEmail(request.getEmail()).isPresent()){
            emailError = "Email already exists";
            isValid = false;
        }
        return isValid;
    }
    public String getEmailError() {
        return emailError;
    }

    public String getPhoneNumberError() {
        return phoneNumberError;
    }

    @Override
    public String toString() {
        return "RegisterValidation{" +
                "emailError='" + emailError + '\'' +
                ", phoneNumberError='" + phoneNumberError + '\'' +
                '}';
    }

    
    public boolean userValidation() {

        boolean isValid = true;
        UserMapper userMapper = new UserMapper();
        if(userMapper.findUserByPhoneNumber(usreRequest.getNewPhoneNumber()).isPresent() ){
            if(userMapper.findUserByPhoneNumber(usreRequest.getNewPhoneNumber()).get() == userMapper.findUserByPhoneNumber(usreRequest.getOldPhoneNumber()).get()){
                phoneNumberError = "your request is done successfully";
                isValid = true;
            }else {
                phoneNumberError = "Phone number already exists";
                isValid = false;
            }
        }
        if(userMapper.findUserByEmail(usreRequest.getNewEmail()).isPresent()){
            if(userMapper.findUserByEmail(usreRequest.getNewEmail()).get() == userMapper.findUserByEmail(usreRequest.getOldEmail()).get()) {
                emailError = "your request is done successfully";
                isValid = true;
            }
            else {
                emailError = "Email already exists";
                isValid = false;
            }
        }
        return isValid;
    }


}
