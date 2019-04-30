package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class EducationController {

    @FXML
    private TextField number;

    @FXML
    private DatePicker dateOf;

    @FXML
    private Button edit;

    @FXML
    private TextField series;

    @FXML
    private TextField issuedBy;

    @FXML
    private Button save;

    @FXML
    private ComboBox<?> educLevel;

    @FXML
    private ComboBox<?> typeOfEduc;

    @FXML
    void clickEdit(ActionEvent event) {
    	number.setEditable(true);
		dateOf.setEditable(true);
		series.setEditable(true);
		issuedBy.setEditable(true);
		educLevel.setMouseTransparent(false);
		typeOfEduc.setMouseTransparent(false);
		save.setDisable(false);
		edit.setDisable(true);	
    }

    @FXML
    void clickSave(ActionEvent event) {
    	number.setEditable(false);
		dateOf.setEditable(false);
		series.setEditable(false);
		issuedBy.setEditable(false);
		educLevel.setMouseTransparent(true);
		typeOfEduc.setMouseTransparent(true);
		save.setDisable(true);
		edit.setDisable(false);	
    }

}
