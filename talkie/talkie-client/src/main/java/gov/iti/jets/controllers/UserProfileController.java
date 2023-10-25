package gov.iti.jets.controllers;


import java.io.File;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import gov.iti.jets.api.validation.SignUpValidation;
import gov.iti.jets.dtos.CurrentUser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class UserProfileController implements Initializable, FXMLController{

    @FXML
    private SplitPane contentSplitPane;

    @FXML
    private ImageView userImageView;

    @FXML
    private Button choosePicButton;

    @FXML
    private Button confirmButton;

    @FXML
    private TextField phoneNumberTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private TextField confirmPasswordTextField;

    @FXML
    private RadioButton maleRadioButton;

    @FXML
    private ToggleGroup gender_options;

    @FXML
    private RadioButton FemaleRadioButton;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextArea bioTextArea;

    @FXML
    private Label nameValidationText;

    @FXML
    private Label passwordValidationText;

    @FXML
    private Label ConfirmPasswordValidationText;

    @FXML
    private ComboBox<String> countryComboBox;

    @FXML
    private Label dateValidationText;

    Boolean validData = true;
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        loadCurrentUserData();
        clearErrorMessages();

        nameTextField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if(!newVal)
                if(nameTextField.getText()=="") nameValidationText.setText("");
                else validateUsername();
        });

        passwordTextField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if(!newVal)
                if(passwordTextField.getText()=="") passwordValidationText.setText("");
                else validatePassword();
        });
        
        confirmPasswordTextField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if(!newVal)
                if(confirmPasswordTextField.getText()=="") ConfirmPasswordValidationText.setText("");
                else validateConfirmPassword();
        });

        datePicker.setOnAction((ActionEvent event) -> {
            if(datePicker.getValue()==null) dateValidationText.setText("");
            else validateBirthDate();
        });

        choosePicButton.setOnAction((ActionEvent event)->{
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("Image Files", "*.png","*.jpg")
            );
            fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

            File OpendFile = fileChooser.showOpenDialog(null);
            if(OpendFile != null){
                Image userImage = new Image(OpendFile.toURI().toString(),userImageView.getFitWidth(),userImageView.getFitHeight(),false,true);
                userImageView.setImage(userImage);
            }
        });

        confirmButton.setOnAction((ActionEvent event)->{
            //TODO
        });
    }



    private void loadCurrentUserData(){
        phoneNumberTextField.setText(CurrentUser.getInstance().getUser().getPhoneNumber());
        phoneNumberTextField.setText(CurrentUser.getInstance().getUser().getUserName());
        phoneNumberTextField.setText(CurrentUser.getInstance().getUser().getPassword());
        phoneNumberTextField.setText(CurrentUser.getInstance().getUser().getPassword());
        setCountryComboBox();
        countryComboBox.setValue(CurrentUser.getInstance().getUser().getCountry());
        if(CurrentUser.getInstance().getUser().getGender().equals("M"))maleRadioButton.setSelected(true);
        if(CurrentUser.getInstance().getUser().getGender().equals("F"))FemaleRadioButton.setSelected(true);
        datePicker.setValue(CurrentUser.getInstance().getUser().getBirthDate());
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

    private void validateUsername(){
        if(!SignUpValidation.userNameValidate(nameTextField.getText())){
            validData = false;
            nameValidationText.setText("Name is invalid, must be at leat 6 characters of small and/or capital letters, and/or numbers only, no spaces.");
        }else nameValidationText.setText("");
    }


    private void validatePassword(){
        if(!SignUpValidation.passwordValidate(passwordTextField.getText())){
            validData = false;
            passwordValidationText.setText("Password must be at least 8 characters");
        }else passwordValidationText.setText("");
    }

    private void validateConfirmPassword(){
        if(!SignUpValidation.confirmPasswordValidate(passwordTextField.getText(), confirmPasswordTextField.getText())){
            validData = false;
            ConfirmPasswordValidationText.setText("Passwords must match");
        }else ConfirmPasswordValidationText.setText("");
    }


    private void validateBirthDate(){
        if(!SignUpValidation.birthdateValidation(datePicker.getValue())){
            validData = false;
            dateValidationText.setText("Choose a valid Date");
        }else dateValidationText.setText("");
    }

    private void clearErrorMessages(){
        
        nameValidationText.setText("");
        passwordValidationText.setText("");
        ConfirmPasswordValidationText.setText("");
        dateValidationText.setText("");

    }


    // private RegisterResponse tryRegistration(){
    //     String currentUserGender;
    //     if(maleRadioButton.isSelected())currentUserGender="M";
    //     else currentUserGender="F";

    //     RegisterRequest registerRequest = new RegisterRequest(nameTextField.getText(), phoneNoTextField.getText() , emailTextField.getText(), EncryptionUtil.encrypt(passwordTextField.getText()) ,
    //         currentUserGender,  countryComboBox.getValue(), datePicker.getValue()); 

    //     try {
            
    //         AuthenticationController authenticationController = (AuthenticationController) NetworkManager.getRegistry().lookup("AuthenticationController");
    //         RegisterResponse response = authenticationController.register(registerRequest);
    //         // System.out.println(response);
    //         return response;
    //     } catch (RemoteException e) {
    //         throw new RuntimeException(e);
    //     } catch (NotBoundException e) {
    //         throw new RuntimeException(e);
    //     }
    // }

}
