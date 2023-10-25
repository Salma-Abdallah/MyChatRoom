package gov.iti.jets.controllers;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Locale;
import java.util.ResourceBundle;

// import gov.iti.jets.controllers.validation.SignupValidation;
import gov.iti.jets.api.requests.RegisterRequest;
import gov.iti.jets.api.responses.RegisterResponse;
// import gov.iti.jets.dto.validation.RegisterValidation;
import gov.iti.jets.api.validation.SignUpValidation;

/* to be done */

// import gov.iti.jets.manager.MainPanelManager;
// import gov.iti.jets.manager.StageManager;
import gov.iti.jets.dtos.CurrentUser;
import gov.iti.jets.dtos.User;
import gov.iti.jets.manager.MainPanelManager;
import gov.iti.jets.manager.StageManager;
import gov.iti.jets.network.controllers.AuthenticationController;
import gov.iti.jets.network.manager.NetworkManager;
import gov.iti.jets.utilities.EncryptionUtililty;
import gov.iti.jets.utilities.ImageUtilities;
/* to be done */
// import gov.iti.jets.utils.EncryptionUtil;
// import gov.iti.jets.utils.ImageUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class SignupPageFxmlController implements Initializable, FXMLController{

    @FXML
    private TextField nameTextField;

    @FXML
    private Label nameValidationText;

    @FXML
    private TextField phoneNoTextField;

    @FXML
    private Label phoneValidationText;

    @FXML
    private PasswordField  passwordTextField;

    @FXML
    private Label passwordValidationText;

    @FXML
    private PasswordField  ConfirmPasswordTextField;

    @FXML
    private Label ConfirmPasswordValidationText;

    @FXML
    private TextField emailTextField;

    @FXML
    private Label emailValidationText;

    @FXML
    private ComboBox<String> countryComboBox;

    @FXML
    private DatePicker datePicker;

    @FXML
    private RadioButton maleRadioButton;

    @FXML
    private RadioButton FemaleRadioButton;

    @FXML
    private Label genderValidationText;

    @FXML
    private Button startButton;

    @FXML
    private Label previousLabel;

    @FXML
    private Label dateValidationText;

    Boolean validData = true;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        clearErrorMessages();
        setCountryComboBox();
        countryComboBox.setValue("Egypt");

        previousLabel.setOnMouseClicked((MouseEvent event)->{
            StageManager.INSTANCE.loadScene("welcome");
        });

        startButton.setOnAction((ActionEvent event)->{
            if(validateInputData()){
                RegisterResponse response = tryRegistration();
                if (response.getValidation() == null){
                    storeUserData(response);
                    MainAlignmentController mainAlignmentController = (MainAlignmentController)StageManager.INSTANCE.loadScene("main-alignment");
                    MainPanelManager.INSTANCE.setup(mainAlignmentController.getMainHBox());

                }else{
                    phoneValidationText.setText(response.getValidation().getPhoneNumberError());
                    emailValidationText.setText(response.getValidation().getEmailError());
                }  
            }  
        });
        
        nameTextField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if(!newVal)
                if(nameTextField.getText()=="") nameValidationText.setText("");
                else validateUsername();
        });
        phoneNoTextField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if(!newVal)    
                if(phoneNoTextField.getText()=="") phoneValidationText.setText("");
                else validatePhoneNumber();
        });
        passwordTextField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if(!newVal)
                if(passwordTextField.getText()=="") passwordValidationText.setText("");
                else validatePassword();
        });
        ConfirmPasswordTextField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if(!newVal)
                if(ConfirmPasswordTextField.getText()=="") ConfirmPasswordValidationText.setText("");
                else validateConfirmPassword();
        });
        emailTextField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if(!newVal)
                if(emailTextField.getText()=="") emailValidationText.setText("");
                else validateEmail();
        });

        datePicker.setOnAction((ActionEvent event) -> {
            if(datePicker.getValue()==null) dateValidationText.setText("");
            else validateBirthDate();
        });
        
        maleRadioButton.setOnAction((ActionEvent event)-> genderValidationText.setText(""));
        FemaleRadioButton.setOnAction((ActionEvent event)-> genderValidationText.setText(""));
    }

    private void setCountryComboBox(){        
        String[] countries  = Locale.getISOCountries();
        ObservableList<String> countryList = FXCollections.observableArrayList();
        for (String countryCode : countries ) {
            Locale obj = new Locale("", countryCode);
            countryList.add(obj.getDisplayCountry());
        }
        countryComboBox.setItems(countryList);
    }

    private boolean validateInputData(){
        //validate User Data locally first;
        validData=true;
        validateUsername();
        validatePhoneNumber();
        validatePassword();
        validateConfirmPassword();
        validateEmail();
        validateBirthDate();
        validateGender();
        return validData;
    }
    


    private void clearErrorMessages(){
        
        nameValidationText.setText("");
        phoneValidationText.setText("");
        passwordValidationText.setText("");
        ConfirmPasswordValidationText.setText("");
        emailValidationText.setText("");
        dateValidationText.setText("");
        genderValidationText.setText("");

    }
//done
    private RegisterResponse tryRegistration(){
        String currentUserGender;
        if(maleRadioButton.isSelected())currentUserGender="M";
        else currentUserGender="F";

        RegisterRequest registerRequest = new RegisterRequest(nameTextField.getText(), phoneNoTextField.getText() , emailTextField.getText(), EncryptionUtililty.encrypt(passwordTextField.getText()) ,
            currentUserGender,  countryComboBox.getValue(), datePicker.getValue()); 

        try {
            
            AuthenticationController authenticationController = (AuthenticationController) NetworkManager.getRegistry().lookup("AuthenticationController");
            RegisterResponse response = authenticationController.register(registerRequest);
            System.out.println(response);
            return response;
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        } catch (NotBoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void storeUserData(RegisterResponse response){
        CurrentUser.getInstance().setUser(new User(response.getUserName(), response.getPhoneNumber(),
                response.getEmail(), response.getPassword(), response.getGender(), response.getCountry(),
                response.getBirthDate(), response.getOnlineStatus(), response.getBio(), response.getPicture(),
                response.getPictureExtension()));
                ImageUtilities.storeImage(CurrentUser.getInstance().getUser());
    }
/************************************************************************************** */
    private void validateUsername(){
        if(!SignUpValidation.userNameValidate(nameTextField.getText())){
            validData = false;
            nameValidationText.setText("Name is invalid, must be at leat 6 characters of small and/or capital letters, and/or numbers only, no spaces.");
        }else nameValidationText.setText("");
    }

    private void validatePhoneNumber(){
        if(!SignUpValidation.phoneNumberValidate(phoneNoTextField.getText())){
            validData = false;
            phoneValidationText.setText("Phone number is invalid, number must be a valid egyption phone number");
        }else phoneValidationText.setText("");
    }

    private void validatePassword(){
        if(!SignUpValidation.passwordValidate(passwordTextField.getText())){
            validData = false;
            passwordValidationText.setText("Password must be at least 8 characters");
        }else passwordValidationText.setText("");
    }

    private void validateConfirmPassword(){
        if(!SignUpValidation.confirmPasswordValidate(passwordTextField.getText(), ConfirmPasswordTextField.getText())){
            validData = false;
            ConfirmPasswordValidationText.setText("Passwords must match");
        }else ConfirmPasswordValidationText.setText("");
    }

    private void validateEmail(){
        if(!SignUpValidation.emailValidate(emailTextField.getText())){
            validData = false;
            emailValidationText.setText("Invalid email");
        }else emailValidationText.setText("");
    }

    private void validateBirthDate(){
        if(!SignUpValidation.birthdateValidation(datePicker.getValue())){
            validData = false;
            dateValidationText.setText("Choose a valid Date");
        }else dateValidationText.setText("");
    }

    private void validateGender(){
        if(!maleRadioButton.isSelected() && !FemaleRadioButton.isSelected()){
            validData = false;
            genderValidationText.setText("You must choose one");
        }else genderValidationText.setText("");
    }
}
