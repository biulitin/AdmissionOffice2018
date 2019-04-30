package application;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import static javafx.scene.input.DataFormat.URL;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SampleController implements Initializable {

	@FXML
	private BorderPane border1;

	@FXML
	private AnchorPane tab1;

	@FXML
	private AnchorPane tab2;

	@FXML
	private AnchorPane tab3;

	@FXML
	private AnchorPane tab4;

	@FXML
	private AnchorPane tab5;

	@FXML
	private AnchorPane tab6;

	@FXML
	private AnchorPane tab7;

	@FXML
	private AnchorPane tab8;

	@FXML
	private AnchorPane tab9;

	@FXML
	private Button addition;

	@FXML
	private DatePicker birthday;

	@FXML
	private ComboBox<?> reason;

	@FXML
	private ComboBox<?> gender;

	@FXML
	private Button edit;

	@FXML
	private CheckBox hostel;

	@FXML
	private ComboBox<?> citizenship;

	@FXML
	private TextField receiveDate;

	@FXML
	private CheckBox takeDocs;

	@FXML
	private TextField patronymic;

	@FXML
	private TextField returnDate;

	@FXML
	private TextField surname;

	@FXML
	private TextField name;

	@FXML
	private TextField id;

	@FXML
	private Button delete;

	@FXML
	void clickAddition(ActionEvent event) throws IOException {
		Stage stage;
		stage = (Stage) addition.getScene().getWindow();
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddAbiturient.fxml"));
		AnchorPane root1 = (AnchorPane) fxmlLoader.load();
		stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setScene(new Scene(root1));
		stage.setResizable(false);
		stage.show();

	}

	@FXML
	void clickEdit(ActionEvent event) throws IOException {
		if ("Редактировать".equals(edit.getText())) {
			id.setEditable(true);
			name.setEditable(true);
			surname.setEditable(true);
			patronymic.setEditable(true);
			receiveDate.setEditable(true);
			returnDate.setEditable(true);
			birthday.setMouseTransparent(false);
			birthday.setEditable(true);
			gender.setMouseTransparent(false);
			gender.setEditable(true);
			reason.setMouseTransparent(false);
			reason.setEditable(true);
			citizenship.setMouseTransparent(false);
			citizenship.setEditable(true);
			hostel.setMouseTransparent(false);
			takeDocs.setMouseTransparent(false);
			edit.setText("Сохранить");
		} else {
			id.setEditable(false);
			name.setEditable(false);
			surname.setEditable(false);
			patronymic.setEditable(false);
			receiveDate.setEditable(false);
			returnDate.setEditable(false);
			birthday.setMouseTransparent(true);
			birthday.setEditable(false);
			gender.setMouseTransparent(true);
			gender.setEditable(false);
			reason.setMouseTransparent(true);
			reason.setEditable(false);
			citizenship.setMouseTransparent(true);
			citizenship.setEditable(false);
			hostel.setMouseTransparent(true);
			takeDocs.setMouseTransparent(true);
			edit.setText("Редактировать");
		}

	}

	@FXML
	void clickDelete(ActionEvent event) throws IOException {
		Stage stage;
		stage = (Stage) delete.getScene().getWindow();

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DeleteStudent.fxml"));
		AnchorPane root1 = (AnchorPane) fxmlLoader.load();
		stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		// stage.setTitle("Р”СЂСѓРіР°СЏ С„РѕСЂРјР°");
		stage.setScene(new Scene(root1));
		stage.setResizable(false);
		stage.show();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// birthday.setValue(LocalDate.now());
		FXMLLoader loader = new FXMLLoader();
		try {
			loader.setLocation(getClass().getResource("CompetitionGroup.fxml"));

			AnchorPane newPane1 = (AnchorPane) loader.load();
			tab1.getChildren().add(newPane1);
		} catch (IOException iex) {
			System.out.println("unable to load tab1");
		}

		loader = new FXMLLoader();

		try {
			AnchorPane anch2 = loader.load(getClass().getResource("Exam.fxml"));
			tab2.getChildren().add(anch2);
		} catch (IOException iex) {
			System.out.println("unable to load tab2");
		}

		try {
			AnchorPane anch3 = loader.load(getClass().getResource("Achievements.fxml"));
			tab3.getChildren().add(anch3);
		} catch (IOException iex) {
			System.out.println("unable to load tab2");
		}

		try {
			AnchorPane anch4 = loader.load(getClass().getResource("Priviliges.fxml"));
			tab4.getChildren().add(anch4);
		} catch (IOException iex) {
			System.out.println("unable to load tab2");
		}

		try {
			AnchorPane anch5 = loader.load(getClass().getResource("100_ballov.fxml"));
			tab5.getChildren().add(anch5);
		} catch (IOException iex) {
			System.out.println("unable to load tab2");
		}

		try {
			AnchorPane anch6 = loader.load(getClass().getResource("Education.fxml"));
			tab6.getChildren().add(anch6);
		} catch (IOException iex) {
			System.out.println("unable to load tab2");
		}

		try {
			AnchorPane anch7 = loader.load(getClass().getResource("Address.fxml"));
			tab7.getChildren().add(anch7);
		} catch (IOException iex) {
			System.out.println("unable to load tab2");
		}

		try {
			AnchorPane anch8 = loader.load(getClass().getResource("PassportAndINN.fxml"));
			tab8.getChildren().add(anch8);
		} catch (IOException iex) {
			System.out.println("unable to load tab2");
		}

		try {
			AnchorPane anch9 = loader.load(getClass().getResource("Additional_information.fxml"));
			tab9.getChildren().add(anch9);
		} catch (IOException iex) {
			System.out.println("unable to load tab2");
		}
	}

}
