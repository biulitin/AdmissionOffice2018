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
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;

/**
 * FXML Controller class
 *
 * @author Марина
 */
public class ExamController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableColumn<?, ?> date;

    @FXML
    private TableColumn<?, ?> score;

    @FXML
    private TableColumn<?, ?> right100;

    @FXML
    private CheckBox specialConditions;

    @FXML
    private Button edit;

    @FXML
    private TableColumn<?, ?> name;

    @FXML
    private TableColumn<?, ?> format;

    @FXML
    private TableColumn<?, ?> language;

    @FXML
    private TableView<?> table;

    @FXML
    private TableColumn<?, ?> group;

    @FXML
    private Button save;

    //right100.setCellValueFactory(newPropertyValueFactory<>( "checkBoxValue" ) );
//right100.setCellFactory( CheckBoxTableCell.forTableColumn( right100 ));
    @FXML
    void clickEdit(ActionEvent event) {
        specialConditions.setMouseTransparent(false);
        table.setEditable(true);
        name.setEditable(true);
        format.setEditable(true);
        language.setEditable(true);
        group.setEditable(true);
        right100.setEditable(true);
        date.setEditable(true);
        score.setEditable(true);
        edit.setDisable(true);
        save.setDisable(false);

    }

    @FXML
    void clickSave(ActionEvent event) {
        specialConditions.setMouseTransparent(true);
        table.setEditable(false);
        name.setEditable(false);
        format.setEditable(false);
        language.setEditable(false);
        group.setEditable(false);
        right100.setEditable(false);
        date.setEditable(false);
        score.setEditable(false);
        save.setDisable(true);
        edit.setDisable(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
