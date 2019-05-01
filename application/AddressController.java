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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Марина
 */
public class AddressController implements Initializable {

    @FXML
    private TextField index;
    @FXML
    private ComboBox<?> region;
    @FXML
    private ComboBox<?> settlement;
    @FXML
    private TextField address;
    @FXML
    private Button edit;
    @FXML
    private Button save;
    @FXML
    private TextField telephone;
    @FXML
    private TextField mail;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

     
    @FXML
    private void clickEdit(ActionEvent event) {
        index.setEditable(true);
        region.setEditable(true);
        settlement.setEditable(true);
        address.setEditable(true);
        region.setMouseTransparent(false);
        settlement.setMouseTransparent(false);
        telephone.setEditable(true);
        mail.setEditable(true);
        save.setDisable(false);
        edit.setDisable(true);
    }

    @FXML
    private void clickSave(ActionEvent event) {
        index.setEditable(false);
        region.setEditable(false);
        settlement.setEditable(false);
        address.setEditable(false);
        region.setMouseTransparent(true);
        settlement.setMouseTransparent(true);
        telephone.setEditable(false);
        mail.setEditable(false);
        save.setDisable(true);
        edit.setDisable(false);
    }

     
    
}
