/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlineusers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author lamis
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private TableView TableView;
    @FXML
    private TableColumn UsernameColumn;
    @FXML
    private TableColumn EmailColumn;
    @FXML
    private TableColumn StatusColumn;
    @FXML
    private TableColumn ScoreColumn;
    @FXML
    private TableColumn ActionColumn;
    @FXML
    private Button inviteBtn;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

    @FXML
    private void BackPressed(ActionEvent event) {
        System.out.println("You clicked me!");

    }
    
    @FXML
    private void invitePressed(ActionEvent event) {
        System.out.println("You clicked me!");

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        TableColumn username = new TableColumn("UsernameColumn");
//        TableColumn email = new TableColumn("EmailColumn");
//        TableColumn status = new TableColumn("StatusColumn");
//        TableColumn score = new TableColumn("ScoreColumn");
        //TableView.getColumns().addAll(username, email, status, score);
        final ObservableList<User> data = FXCollections.observableArrayList(
                new User("Lamis", "myemail", "online", "5000","invite"),
                new User("Heba", "Hebaemail", "online", "4000", "invite")
        );
        
        UsernameColumn.setCellValueFactory(new PropertyValueFactory<User,String>("username"));
        EmailColumn.setCellValueFactory(new PropertyValueFactory<User,String>("email"));
        StatusColumn.setCellValueFactory(new PropertyValueFactory<User,String>("status"));
        ScoreColumn.setCellValueFactory(new PropertyValueFactory<User,String>("score"));
        ActionColumn.setCellValueFactory(new PropertyValueFactory<User,Button>("Actionbtn"));
        //System.out.println();
        TableView.setItems(data);
    }

}
