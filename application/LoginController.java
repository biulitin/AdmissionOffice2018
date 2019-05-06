package application;

import backend.*;

import java.awt.Component;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JPanel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoginController implements Initializable {

	@FXML
	private PasswordField passwd;

	@FXML
	private Button enter;

	@FXML
	private TextField login;

	@FXML
	void click(ActionEvent event) throws IOException {
		ModelDBConnection.setConnectionParameters("localhost", "Abiturient", login.getText(), passwd.getText());
		ModelDBConnection.initConnection();
		
		if (ModelDBConnection.checkUser(login.getText(), passwd.getText()) > 0) {
			Stage stage;
			stage = (Stage) enter.getScene().getWindow();
			// do what you have to do
			stage.close();
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Sample.fxml"));
			AnchorPane root1 = (AnchorPane) fxmlLoader.load();
			stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(new Scene(root1));
			// stage.setMaximized(true);
			stage.setResizable(false);
			stage.show();
		} else {
			MessageProcessing.displayErrorMessage(new JPanel(), 4);
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}
