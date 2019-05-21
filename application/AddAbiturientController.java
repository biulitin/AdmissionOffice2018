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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Марина
 */
public class AddAbiturientController implements Initializable {

    @FXML
    private DatePicker birthday;

    @FXML
    private Button add;

    @FXML
    private ComboBox<String> reason;

    @FXML
    private ComboBox<String> gender;

    @FXML
    private CheckBox hostel;

    @FXML
    private ComboBox<String> citizenship;

    @FXML
    private DatePicker receiveDate;

    @FXML
    private ComboBox<String> is_enrolled;

    @FXML
    private TextField patronymic;

    @FXML
    private DatePicker returnDate;

    @FXML
    private TextField surname;

    @FXML
    private TextField name;

    @FXML
    private TextField id;

    @FXML
    void clickAdd(ActionEvent event) throws SQLException {

        String[] info = new String[12];
        info[0] = id.getText();
        info[1] = surname.getText();
        info[2] = name.getText();
        info[3] = patronymic.getText();
        info[4] = birthday.getValue().toString();

        info[5] = gender.getSelectionModel().getSelectedIndex() + 1 + "";//gender.getValue().toString();
        info[6] = citizenship.getSelectionModel().getSelectedIndex() + 1 + ""; //citizenship.getValue().toString();
        int hos = hostel.isSelected() ? 1 : 0;
        info[7] = hos + "";
        info[8] = receiveDate.getValue().toString();
        info[9] = returnDate.getValue().toString();
        info[10] = reason.getSelectionModel().getSelectedIndex() + 1 + "";//reason.getValue().toString();
        switch (is_enrolled.getSelectionModel().getSelectedItem()) {
            case "Зачислен на 0 этапе":
                info[11] = "0";
                break;
            case "Зачислен в 1 волну":
                info[11] = "1";
                break;
            case "Зачислен во 2 волну":
                info[11] = "2";
                break;
            case "Зачислен в волну коммерции":
                info[11] = "3";
                break;
            case "Не зачислен":
                info[11] = "-1";
                break;
            case "Нет информации":
                info[11] = "";
                break;

        }

        //is_enrolled.getValue().toString();
        //System.out.println(info.length +" this is right"); 
        ModelDBConnection.insertAbiturient(info);
        Stage stage = (Stage) add.getScene().getWindow();

        stage.close();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //ModelDBConnection.getNamesFromTableOrderedById("Gender");
        ObservableList<String> genderValues = FXCollections.observableArrayList(ModelDBConnection.getNamesFromTableOrderedById("Gender"));
        gender.setItems(genderValues);

        ObservableList<String> nationalValues = FXCollections.observableArrayList(ModelDBConnection.getNamesFromTableOrderedById("Nationality"));
        citizenship.setItems(nationalValues);

        ObservableList<String> reasonValues = FXCollections.observableArrayList(ModelDBConnection.getNamesFromTableOrderedById("ReturnReasons"));
        reason.setItems(reasonValues);

        //String [] enrollment = ["Зачислен на 0 этапе", "Зачислен в 1 волну"];
        ObservableList<String> enrollValues = FXCollections.observableArrayList("Зачислен на 0 этапе",
                "Зачислен в 1 волну", "Зачислен во 2 волну",
                "Зачислен в волну коммерции", "Не зачислен", "Нет информации");
        is_enrolled.setItems(enrollValues);
    }

}
