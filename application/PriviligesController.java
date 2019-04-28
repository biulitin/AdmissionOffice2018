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
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Марина
 */
public class PriviligesController implements Initializable {

    @FXML
    private Button deleteBVI;
    @FXML
    private Button editBVI;
    @FXML
    private TableView<?> tableBVI;
    @FXML
    private Button addBVI;
    @FXML
    private Button deleteQuote;
    @FXML
    private Button editQuote;
    @FXML
    private TableView<?> tableQuote;
    @FXML
    private Button addQuote;
    @FXML
    private Button deleteRight;
    @FXML
    private Button editRight;
    @FXML
    private Button addRight;
    @FXML
    private TableView<?> tableRight;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clickDeleteBVI(ActionEvent event) {
    }

    @FXML
    private void clickEditBVI(ActionEvent event) {
    }

    @FXML
    private void clickAddBVI(ActionEvent event) {
        if ("Добавить".equals(addBVI.getText())){
        deleteBVI.setDisable(false);
        editBVI.setDisable(false);
        tableBVI.setEditable(true);
        addBVI.setText("Сохранить");}
        else {
            deleteBVI.setDisable(true);
        editBVI.setDisable(true);
        tableBVI.setEditable(false);
        addBVI.setText("Добавить");
        }
    }

    @FXML
    private void clickDeleteQuote(ActionEvent event) {
    }

    @FXML
    private void clickEditQuote(ActionEvent event) {
    }

    @FXML
    private void clickAddQuote(ActionEvent event) {
        if ("Добавить".equals(addQuote.getText())){
        deleteQuote.setDisable(false);
        editQuote.setDisable(false);
        tableQuote.setEditable(true);
        addQuote.setText("Сохранить");}
        else {
            deleteQuote.setDisable(true);
        editQuote.setDisable(true);
        tableQuote.setEditable(false);
        addQuote.setText("Добавить");
        }
    }

    @FXML
    private void clickDeleteRight(ActionEvent event) {
    }

    @FXML
    private void clickEditRight(ActionEvent event) {
    }

    @FXML
    private void clickAddRight(ActionEvent event) {
        
         if ("Добавить".equals(addRight.getText())){
        deleteRight.setDisable(false);
        editRight.setDisable(false);
        tableRight.setEditable(true);
        addRight.setText("Сохранить");}
        else {
            deleteRight.setDisable(true);
        editRight.setDisable(true);
        tableRight.setEditable(false);
        addRight.setText("Добавить");
        }
    }
    
}
