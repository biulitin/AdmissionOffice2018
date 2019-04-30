package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class Controller100Ballov {

    @FXML
    private Button edit;
    
    @FXML
    private Button delete;

    @FXML
    private TableColumn<?, ?> subject;

    @FXML
    private TableColumn<?, ?> document;

    @FXML
    private TableColumn<?, ?> name;

    @FXML
    private Button save;

    @FXML
    private Button addCondition;
    
    @FXML
    private TableView<?> table;

    @FXML
    void addNewCondition(ActionEvent event) {

    }

    @FXML
    void clickEdit(ActionEvent event) {
    	table.setEditable(true);
    	name.setEditable(true);
    	subject.setEditable(true);
    	document.setEditable(true);
    	edit.setDisable(true);
    	addCondition.setDisable(false);
    	save.setDisable(false);
    	delete.setDisable(false);
    }

    @FXML
    void clickSave(ActionEvent event) {
    	table.setEditable(false);
    	name.setEditable(false);
    	subject.setEditable(false);
    	document.setEditable(false);
    	edit.setDisable(false);
    	addCondition.setDisable(true);
    	save.setDisable(true);
    	delete.setDisable(true);
    }
    
    @FXML
    void clickDelete(ActionEvent event) {

    }

}
