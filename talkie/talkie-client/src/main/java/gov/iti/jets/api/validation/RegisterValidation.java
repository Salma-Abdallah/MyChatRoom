package gov.iti.jets.api.validation;


import java.io.Serializable;

public class RegisterValidation implements Serializable {
    private static final long serialVersionUID = -2449817711737079712L;
    private String emailError;
    private String phoneNumberError;

    public RegisterValidation() {
    }

    public RegisterValidation(String emailError, String phoneNumberError) {
        this.emailError = emailError;
        this.phoneNumberError = phoneNumberError;
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
}
