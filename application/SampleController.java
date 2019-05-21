package application;

import backend.MessageProcessing;
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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableRow;
import static javafx.scene.input.DataFormat.URL;
 
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.grios.tableadapter.DefaultTableAdapter;
import org.grios.tableadapter.DefaultTableAdapter;

import backend.ModelDBConnection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.stage.WindowEvent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class SampleController implements Initializable{
	
	
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
    private ComboBox<String> reason;

    @FXML
    private ComboBox<String> gender;

    @FXML
    private Button edit;

    @FXML
    private CheckBox hostel;
    
     @FXML
    private ComboBox<String> citizenship;
     
      @FXML
    private DatePicker receiveDate;


        @FXML
    private ChoiceBox<String> is_enrolled;
    
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
    private Button delete;
    
    @FXML
    public  TableView tableWithAbiturients;
    private DefaultTableAdapter dta;




            @FXML
            void clickAddition(ActionEvent event) throws IOException{
                //Stage stage;
                //stage = (Stage)addition.getScene().getWindow();
                // do what you have to do
               // stage.close();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddAbiturient.fxml"));
                AnchorPane root1 = (AnchorPane) fxmlLoader.load();
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                //stage.setTitle("Другая форма");
                stage.setScene(new Scene(root1));
                stage.setResizable(false);
                //stage.setMaximized(true);
                stage.show();
                 
                stage.setOnHidden(new EventHandler<WindowEvent>(){
 
        @Override
 
         public void handle(WindowEvent event) {
 
           initTableWithAbiturients();
 
         }
 
        });
 
                //if(!stage.isShowing())
                   // initTableWithAbiturients();
                 
                 
                 

            }
            
            @FXML
            void clickEdit(ActionEvent event)throws IOException {
                if ("Редактировать".equals(edit.getText())) {
                id.setEditable(true);
                 name.setEditable(true);
                  surname.setEditable(true);
                  patronymic.setEditable(true);
                   receiveDate.setEditable(true);
                   receiveDate.setMouseTransparent(false);
                    returnDate.setEditable(true);
                    returnDate.setMouseTransparent(false);
                    birthday.setMouseTransparent(false);  
                    birthday.setEditable(true);
                    gender.setMouseTransparent(false);  
                    gender.setEditable(true);
                    reason.setMouseTransparent(false);  
                    reason.setEditable(true);
                    citizenship.setMouseTransparent(false);  
                    citizenship.setEditable(true);
                    hostel.setMouseTransparent(false);
                    is_enrolled.setMouseTransparent(false);
                 edit.setText("Сохранить");}
                else {
                    id.setEditable(false);
                 name.setEditable(false);
                  surname.setEditable(false);
                  patronymic.setEditable(false);
                   receiveDate.setEditable(false);
                   receiveDate.setMouseTransparent(true);  
                    returnDate.setEditable(false);
                    returnDate.setMouseTransparent(true); 
                    birthday.setMouseTransparent(true);  
                    birthday.setEditable(false);
                    gender.setMouseTransparent(true);  
                    gender.setEditable(false);
                    reason.setMouseTransparent(true);  
                    reason.setEditable(false);
                    citizenship.setMouseTransparent(true);  
                    citizenship.setEditable(false);
                    hostel.setMouseTransparent(true);
                    is_enrolled.setMouseTransparent(true);
                 edit.setText("Редактировать");
                }


    }

 
             @FXML
    void clickDelete(ActionEvent event) {
        
        
                
                /*Stage stage;
                stage = (Stage)delete.getScene().getWindow();
                // do what you have to do
               // stage.close();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DeleteStudent.fxml"));
                AnchorPane root1 = (AnchorPane) fxmlLoader.load();
                stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                //stage.setTitle("Другая форма");
                stage.setScene(new Scene(root1));
                stage.setResizable(false);
                //stage.setMaximized(true);
                stage.show();*/
               /* TablePosition pos = (TablePosition) tableWithAbiturients.getSelectionModel().getSelectedCells().get(0);
                System.out.println(pos);
                int row = pos.getRow();
                System.out.println(row);
                  
               String item = tableWithAbiturients.getItems().get(row).toString();
               System.out.println(item);
               item = item.substring(1,item.indexOf(","));
               System.out.println(item);*/
             // TableColumn col = pos.getTableColumn();
              //System.out.println(tableWithAbiturients.get);
              
              
                  //getTableView().getItems().get(0));//  .getSelectedItem().toString());
                int result = MessageProcessing.displayDialogMessage(new JPanel(), 1);
                if (result == JOptionPane.YES_OPTION){
                     
                    //System.out.println(dta.getValueAt( tableWithAbiturients.getSelectionModel().getSelectedIndex()  , 0));
                    //dta.removeRow( selectedRow );
                    //System.out.println(col.getCellObservableValue(item).getValue() );
                     //  getSelectedIndex());
                    try {
                        ModelDBConnection.deleteAbiturient(dta.getValueAt( tableWithAbiturients.getSelectionModel().getSelectedIndex()  , 0).toString());
                         
                    } catch (SQLException ex) {
                        Logger.getLogger(SampleController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    initTableWithAbiturients();
                }
//System.out.println(MessageProcessing.displayDialogMessage(new JPanel(), tableWithAbiturients.getSelectionModel().getSelectedIndex()));
               /* TableViewSelectionModel selectedModel = tableWithAbiturients.getSelectionModel();
                         Object selectedRow = selectedModel.getSelectedItem();
                if(DeleteStudentController.delete){
                     tableWithAbiturients.getItems().removeAll(selectedRow );
                    try {
                        ModelDBConnection.deleteAbiturient(selectedModel.getTableView().getItems().get(0).toString() );
                    } catch (SQLException ex) {
                        Logger.getLogger(SampleController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    //initTableWithAbiturients();
                }*/
    }
     
    
         
	       @Override
	    public void initialize(URL location, ResourceBundle resources)
	    {
                initTableWithAbiturients();
                  
                //birthday.setValue(LocalDate.now());
	        FXMLLoader loader = new FXMLLoader();
	        try
	        {
	            loader.setLocation(getClass().getResource("CompetitionGroup.fxml"));
	            
	            AnchorPane newPane1=(AnchorPane) loader.load();
	            tab1.getChildren().add(newPane1);
	        }
	        catch(IOException iex)
	        {
	            System.out.println("unable to load tab1");
	        }

	        loader = new FXMLLoader();
	        
	         try{
	           AnchorPane anch2 = loader.load(getClass().getResource("Exam.fxml"));
	            tab2.getChildren().add(anch2);
	        }
	        catch(IOException iex)
	        {
	            System.out.println("unable to load tab2");
	        }

	         try{
		           AnchorPane anch3 = loader.load(getClass().getResource("Achievements.fxml"));
		            tab3.getChildren().add(anch3);
		        }
		        catch(IOException iex)
		        {
		            System.out.println("unable to load tab2");
		        }
	         
	         try{
		           AnchorPane anch4 = loader.load(getClass().getResource("Priviliges.fxml"));
		            tab4.getChildren().add(anch4);
		        }
		        catch(IOException iex)
		        {
		            System.out.println("unable to load tab2");
		        }
	         
	         try{
		           AnchorPane anch5 = loader.load(getClass().getResource("100_ballov.fxml"));
		            tab5.getChildren().add(anch5);
		        }
		        catch(IOException iex)
		        {
		            System.out.println("unable to load tab2");
		        }
	         
	         try{
		           AnchorPane anch6 = loader.load(getClass().getResource("Education.fxml"));
		            tab6.getChildren().add(anch6);
		        }
		        catch(IOException iex)
		        {
		            System.out.println("unable to load tab2");
		        }
	         
	         try{
		           AnchorPane anch7 = loader.load(getClass().getResource("Address.fxml"));
		            tab7.getChildren().add(anch7);
		        }
		        catch(IOException iex)
		        {
		            System.out.println("unable to load tab2");
		        }
	         
	         try{
		           AnchorPane anch8 = loader.load(getClass().getResource("PassportAndINN.fxml"));
		            tab8.getChildren().add(anch8);
		        }
		        catch(IOException iex)
		        {
		            System.out.println("unable to load tab2");
		        }
	         
	         try{
		           AnchorPane anch9 = loader.load(getClass().getResource("Additional_information.fxml"));
		            tab9.getChildren().add(anch9);
		        }
		        catch(IOException iex)
		        {
		            System.out.println("unable to load tab2");
		        }
	    }
             //public int selectedIndex = tableWithAbiturients.getSelectionModel().getSelectedIndex();
            public void initTableWithAbiturients() {
		String[] columns = {"№Л/д", "Фамилия", "Имя", "Отчество"};
		String[][] data = ModelDBConnection.getAllAbiturients();
                 
		dta = new DefaultTableAdapter(tableWithAbiturients, data, columns);
                
                ObservableList<String> genderValues = FXCollections.observableArrayList(ModelDBConnection.getNamesFromTableOrderedById("Gender"));
       gender.setItems(genderValues);
       
       ObservableList<String> nationalValues = FXCollections.observableArrayList(ModelDBConnection.getNamesFromTableOrderedById("Nationality"));
    citizenship.setItems(nationalValues);
    
    ObservableList<String> reasonValues = FXCollections.observableArrayList(ModelDBConnection.getNamesFromTableOrderedById("ReturnReasons"));
    reason.setItems(reasonValues);
    
    //String [] enrollment = ["Зачислен на 0 этапе", "Зачислен в 1 волну"];
    ObservableList<String> enrollValues = FXCollections.observableArrayList("Зачислен на 0 этапе", 
            "Зачислен в 1 волну","Зачислен во 2 волну", 
            "Зачислен в волну коммерции", "Не зачислен", "Нет информации");
    is_enrolled.setItems(enrollValues);
    
    tableWithAbiturients.setOnMouseClicked(e ->{
        String [] info;
                    try {
                        System.out.println(dta.getValueAt( tableWithAbiturients.getSelectionModel().getSelectedIndex()  , 0));
                        info = ModelDBConnection.getAbiturientGeneralInfoByID(dta.getValueAt( tableWithAbiturients.getSelectionModel().getSelectedIndex()  , 0).toString());
                   // for (int i=0;i<info.length;i++){
               //System.out.println(info[i]);
           //}
            surname.setText(info[1]);
                    } catch (SQLException ex) {
                        Logger.getLogger(SampleController.class.getName()).log(Level.SEVERE, null, ex);
                    }
            
    });
    //tableWithAbiturients.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
    if (!tableWithAbiturients.getSelectionModel().isEmpty()) {
        try {
            String [] info = ModelDBConnection.getAbiturientGeneralInfoByID(dta.getValueAt( tableWithAbiturients.getSelectionModel().getSelectedIndex()  , 0).toString());
           for (int i=0;i<info.length;i++){
               System.out.println(info[i]);
           }
            surname.setText(info[1]);
           
            //tableview2.getSelectionModel().clearSelection();
        } catch (SQLException ex) {
            Logger.getLogger(SampleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
                
                //System.out.println(dta.getValueAt(0, 0));
                
		 //int selectedIndex = tableWithAbiturients.getSelectionModel().getSelectedIndex();
		/*tableWithAbiturients.setRowFactory(table -> {
            final TableRow row = new TableRow();
            row.setOnMousePressed(event -> {
                //int selectedIndex = tableWithAbiturients.getSelectionModel().getSelectedIndex();
                if(row != null && DeleteStudentController.delete )
                	System.out.println(selectedIndex);
            });
            return row;
        });*/
}
             
	    
	}
	
	
