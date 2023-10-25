package gov.iti.jets.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class DefaultContentController implements Initializable, FXMLController {

    @FXML
    private SplitPane defaultContentSplitPane;

    @FXML
    private VBox defaultContentVBox;

    @FXML
    private ImageView logoImageView;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        
    }

}
