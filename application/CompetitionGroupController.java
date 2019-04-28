/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Марина
 */
public class CompetitionGroupController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Button add;

    @FXML
    private Button edit;

    @FXML
    private Button delete;

    @FXML
    void clickAdd(ActionEvent event) throws IOException {
Stage stage;
                stage = (Stage)add.getScene().getWindow();
                // do what you have to do
               // stage.close();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddCompetitionGroup.fxml"));
                AnchorPane root1 = (AnchorPane) fxmlLoader.load();
                stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                //stage.setTitle("Другая форма");
                stage.setScene(new Scene(root1));
                stage.setResizable(false);
                //stage.setMaximized(true);
                stage.show();
    }

    @FXML
    void clickEdit(ActionEvent event) throws IOException {
Stage stage;
                stage = (Stage)add.getScene().getWindow();
                // do what you have to do
               // stage.close();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddCompetitionGroup.fxml"));
                AnchorPane root1 = (AnchorPane) fxmlLoader.load();
                stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                //stage.setTitle("Другая форма");
                stage.setScene(new Scene(root1));
                stage.setResizable(false);
                //stage.setMaximized(true);
                stage.show();
    }

    @FXML
    void ClickDelete(ActionEvent event) {

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
