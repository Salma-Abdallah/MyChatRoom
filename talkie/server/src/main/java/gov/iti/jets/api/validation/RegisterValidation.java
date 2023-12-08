package gov.iti.jets.api.validation;

import gov.iti.jets.api.requests.RegisterRequest;
import gov.iti.jets.api.requests.UserProfileRequest;
import gov.iti.jets.dto.UserDto;
import gov.iti.jets.mappers.UserMapper;
import gov.iti.jets.services.UserServices;

import java.io.Serializable;

public class RegisterValidation implements Serializable, Validation {

    private static final long serialVersionUID = -2449817711737079712L;
    transient private RegisterRequest request = new RegisterRequest();
    private String emailError = null;
    private String phoneNumberError = null;
    
    private UserProfileRequest usreRequest = new UserProfileRequest();

    private UserServices userServices = new UserServices();

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
        if(userServices.findUserByPhoneNumber(request.getPhoneNumber()) != null){
            phoneNumberError = "Phone number already exists";
            isValid = false;
        }
        if(userServices.findUserByEmail(request.getEmail()) != null ){
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
        if(userServices.findUserByPhoneNumber(usreRequest.getNewPhoneNumber()) !=null ){
            if(userServices.findUserByPhoneNumber(usreRequest.getNewPhoneNumber()).equals(userServices.findUserByPhoneNumber(usreRequest.getOldPhoneNumber()))){
                phoneNumberError = "your request is done successfully";
                isValid = true;
            }else {
                phoneNumberError = "Phone number already exists";
                isValid = false;
            }
        }
        if(userServices.findUserByEmail(usreRequest.getNewEmail()) != null){
            if(userServices.findUserByEmail(usreRequest.getNewEmail()).getClass() == userServices.findUserByEmail(usreRequest.getOldEmail()).getClass()) {
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
