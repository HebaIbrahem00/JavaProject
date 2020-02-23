package client.view;

import Model.OnlineUser;
import Model.OnlineUserList;
import static client.view.Main.mystage;
import static client.view.MainMenuController.onlineUserList;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

/**
 * @author lamis
 */
public class DisplayUsersController implements Initializable {

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
        System.out.println("invite pressed");
        label.setText("Hello World!");
    }

    @FXML
    private void BackPressed(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        Scene scene = new Scene(root);
        mystage.setTitle("Main Menu");
        mystage.setScene(scene);
    }

    @FXML
    private void invitePressed(ActionEvent event) throws IOException {
//        OnlineUserList person = (OnlineUserList) TableView.getSelectionModel().getSelectedItem();
//        System.out.println(person.getUserName());
        
        

        Parent root = FXMLLoader.load(getClass().getResource("OnlineTicTac.fxml"));
        Scene scene = new Scene(root);
        mystage.setTitle("MultiPlayer");
        mystage.setScene(scene);

      
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UsernameColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
        EmailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        StatusColumn.setCellValueFactory(new PropertyValueFactory<>("userStatus"));

        final ObservableList<OnlineUser> allData = FXCollections.observableArrayList();

        for (OnlineUserList user : onlineUserList) {
            TableView.getItems().add(user);
        }
    }

}
