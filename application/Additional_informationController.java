package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class Additional_informationController {
	 @FXML
	    private Button add;

	    @FXML
	    private TableColumn<?, ?> number;

	    @FXML
	    private Button edit;

	    @FXML
	    private TableColumn<?, ?> series;

	    @FXML
	    private TableColumn<?, ?> document;

	    @FXML
	    private TableColumn<?, ?> issuedBy;

	    @FXML
	    private TableColumn<?, ?> category;

	    @FXML
	    private TableColumn<?, ?> dateOfIssue;

	    @FXML
	    private Button delete;
	    
	    @FXML
	    private TableView<?> table;
	    
	    @FXML
	    private Button save;

	    @FXML
	    void clickDelete(ActionEvent event) {

	    }

	    @FXML
	    void clickEdit(ActionEvent event) {
	    	table.setEditable(true);
	    	number.setEditable(true);
	    	series.setEditable(true);
	    	document.setEditable(true);
	    	issuedBy.setEditable(true);
	    	category.setEditable(true);
	    	dateOfIssue.setEditable(true);
	    	edit.setDisable(true);
	    	save.setDisable(false);
	    	delete.setDisable(false);
	    	add.setDisable(false);
	    }

	    @FXML
	    void clickAdd(ActionEvent event) {

	    }
	    
	    @FXML
	    void clickSave(ActionEvent event) {
	    	table.setEditable(false);
	    	number.setEditable(false);
	    	series.setEditable(false);
	    	document.setEditable(false);
	    	issuedBy.setEditable(false);
	    	category.setEditable(false);
	    	dateOfIssue.setEditable(false);
	    	edit.setDisable(false);
	    	save.setDisable(true);
	    	delete.setDisable(true);
	    	add.setDisable(true);
	    }

	
}
