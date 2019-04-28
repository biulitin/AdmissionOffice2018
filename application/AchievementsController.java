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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Марина
 */
public class AchievementsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Button add;

    @FXML
    private TableColumn<?, ?> score;

    @FXML
    private Button edit;

    @FXML
    private TableColumn<?, ?> document;

    @FXML
    private Button save;

    @FXML
    private TableColumn<?, ?> name;
 
    @FXML
    private TableView<?> table;

    @FXML
    void clickAdd(ActionEvent event) {
//name.setCellValueFactory(new PropertyValueFactory<String>());
    }

    @FXML
    void clickEdit(ActionEvent event) {
add.setDisable(false);
save.setDisable(false);
table.setEditable(true);
name.setEditable(true);
document.setEditable(true);
score.setEditable(true);
    }

    @FXML
    void clickSave(ActionEvent event) {
add.setDisable(true);
save.setDisable(true);
table.setEditable(false);
name.setEditable(false);
document.setEditable(false);
score.setEditable(false);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
