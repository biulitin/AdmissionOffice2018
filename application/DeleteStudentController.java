/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Марина
 */
public class DeleteStudentController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Button no;
    
     @FXML
    private Button yes;

    @FXML
    void clickYes(ActionEvent event) {

    }

    @FXML
    void clickNo(ActionEvent event) {
Stage stage =  (Stage)no.getScene().getWindow();
                 
        stage.close();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
