package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class PassportController {

	@FXML
	private TextField number;

	@FXML
	private TextField birthplace;

	@FXML
	private DatePicker dateOf;

	@FXML
	private Button edit;

	@FXML
	private TextField series;

	@FXML
	private TextArea issuedBy;

	@FXML
	private TextField INN;

	@FXML
	private ChoiceBox<?> typeOfDocument;

	@FXML
	private Button save;

	@FXML
	void clickEdit(ActionEvent event) {
		number.setEditable(true);
		birthplace.setEditable(true);
		dateOf.setEditable(true);
		series.setEditable(true);
		issuedBy.setEditable(true);
		INN.setEditable(true);
		typeOfDocument.setMouseTransparent(false);
		save.setDisable(false);
		edit.setDisable(true);	
	}

	@FXML
	void clickSave(ActionEvent event) {
		number.setEditable(false);
		birthplace.setEditable(false);
		dateOf.setEditable(false);
		series.setEditable(false);
		issuedBy.setEditable(false);
		INN.setEditable(false);
		typeOfDocument.setMouseTransparent(true);
		save.setDisable(true);
		edit.setDisable(false);
	}
}
