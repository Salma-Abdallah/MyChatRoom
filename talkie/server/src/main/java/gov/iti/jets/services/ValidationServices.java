package gov.iti.jets.services;

import gov.iti.jets.api.validation.RegisterValidation;
import gov.iti.jets.api.validation.Validation;

public class ValidationServices {

    public boolean validate(Validation validation){
        return validation.validate();
    }

    public boolean userProfileValidate(RegisterValidation validation) {
        return validation.userValidation();
    }
}
