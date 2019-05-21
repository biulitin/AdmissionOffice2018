/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import backend.ModelDBConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
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
 
    public static boolean delete = false;
    @FXML
    private Button no;
    
     @FXML
    private Button yes;

    @FXML
    void clickYes(ActionEvent event) throws SQLException {
       // ModelDBConnection.deleteAbiturient(SampleController.getSelectedIndex());
      //  SampleController.getTableWithAbiturients().getItems().removeAll(SampleController.getSelectedRow());
        //SampleController.getSelectedRow();
        //ObservableList items = SampleController.tableWithAbiturients.getItems();
       // ModelDBConnection.deleteAbiturient(SampleController.getTableWithAbiturients().getSelectionModel().getSelectedCells().get(0).toString());//  getSelectedIndex());
                //SampleController.getTableWithAbiturients().getItems().);
                delete = true;
Stage stage =  (Stage)yes.getScene().getWindow();
                 
        stage.close();
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
