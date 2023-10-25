package gov.iti.jets.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MainAlignmentController implements Initializable, FXMLController{

    @FXML
    private HBox mainHBox;
    
    @FXML
    private VBox sidebar;

    @FXML
    private VBox contactsBar;

    @FXML
    private SplitPane contentSplitPane;

    @FXML
    private VBox contentVBox;

    @FXML
    private VBox paticipantsVBox;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    public HBox getMainHBox(){
        return mainHBox;
    }


}
