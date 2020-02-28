package client.view;

import static client.view.Main.mystage;
import Model.OnlineUserList;
import client.Connection.ClientSocket;
//import com.google.gson.Gson;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;

/**
 * @author lamis
 */
public class MainMenuController implements Initializable {
    static public OnlineUserList[] onlineUserList;

    @FXML
    private Label label;

    //buttons of main page
    @FXML
    private void handleButtonAction(ActionEvent event) {

    }

    @FXML
    private void SinglePlayerMode(ActionEvent event) throws IOException {
        System.out.println("hi");
        Parent root = FXMLLoader.load(getClass().getResource("LevelsMenu.fxml"));
        mystage.setTitle("Single Player");
        Scene scene = new Scene(root);
        mystage.setScene(scene);
    }

    /*@FXML
    private void MultiPlayerMode(ActionEvent event) throws IOException {
        String json = ClientSocket.retriveOnlineUsers();
        Gson g = new Gson();
        onlineUserList = g.fromJson(json, OnlineUserList[].class);
        
        Parent root = FXMLLoader.load(getClass().getResource("DisplayUsers.fxml"));
        Scene scene = new Scene(root);
        mystage.setTitle("Display Users");
        mystage.setScene(scene);
    }*/

    @FXML
    private void HowTo(ActionEvent event) {

    }

    @FXML
    private void Quit(ActionEvent event) {
        Platform.exit();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
