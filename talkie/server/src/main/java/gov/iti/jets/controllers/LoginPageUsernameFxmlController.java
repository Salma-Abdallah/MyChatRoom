package gov.iti.jets.controllers;

// import gov.iti.jets.dto.CurrentAdmin;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import gov.iti.jets.dto.CurrentAdminDto;

public class LoginPageUsernameFxmlController implements Initializable{

    @FXML
    private Label nextLabel;
    @FXML
    private TextField phoneNoTextField;
  @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        phoneNoTextField.setText(CurrentAdminDto.getInstance().getAdmin().getPhoneNumber());
        nextLabel.setOnMouseClicked((MouseEvent event)-> {
            CurrentAdminDto.getInstance().getAdmin().setPhoneNumber(phoneNoTextField.getText());
            Parent home = null;
            try {
                home = FXMLLoader.load(getClass().getResource("/views/login-page-password.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Scene scene = new Scene(home);
            ((Stage) (((Node) event.getSource()).getScene().getWindow())).setScene(scene);
        });
         }

    public TextField getPhoneNoTextField() {
        return phoneNoTextField;
    }
}