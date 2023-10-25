package gov.iti.jets.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import gov.iti.jets.dto.AdminDto;
import gov.iti.jets.dto.CurrentAdminDto;
import gov.iti.jets.services.AdminServices;

public class LoginPagePasswordFxmlController implements Initializable{

    @FXML
    private ResourceBundle resources;

    @FXML
    private TextField passwordTextField;

    @FXML
    private Label previousButton;

    @FXML
    private Button submitButton;

    @FXML
    private Label loginFailedMessageLabel;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        loginFailedMessageLabel.setText("");

        previousButton.setOnMouseClicked((MouseEvent event)->{
            Parent home = null;
            try {
                home = FXMLLoader.load(getClass().getResource("/views/login-page-username.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Scene scene = new Scene(home);
            ((Stage) (((Node) event.getSource()).getScene().getWindow())).setScene(scene);
        });


        submitButton.setOnAction((ActionEvent event)->{
            AdminServices adminServices = new AdminServices();
            AdminDto adminDto= adminServices.getAdminByPhoneNumber(CurrentAdminDto.getInstance().getAdmin().getPhoneNumber());
            if(adminDto.getPassword().equals(passwordTextField.getText())) {
                loginFailedMessageLabel.setText("");
                CurrentAdminDto.getInstance().setAdmin(adminDto);
                Parent home = null;
                try {
                    home = FXMLLoader.load(getClass().getResource("/views/server.fxml"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Scene scene = new Scene(home);
                ((Stage) (((Node) event.getSource()).getScene().getWindow())).setScene(scene);
            }else{
                loginFailedMessageLabel.setText("PhoneNumber or Password Incorrect");
            }
        });
    }
}
