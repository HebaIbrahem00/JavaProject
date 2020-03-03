/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.view;

import static client.view.Main.mystage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 *
 * @author pc
 */
public class LevelsMenu implements Initializable {

    @FXML
    private Button easy;

    @FXML
    private Button medium;

    @FXML
    private Button hard;

    @FXML
    private Button back;

    @FXML
    private Label label;

    //buttons of main page
    @FXML
    private void handleButtonAction(ActionEvent event) {

    }

    @FXML
    void backPressed(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        mystage.setTitle("Main Menu");
        Scene scene = new Scene(root);
        mystage.setScene(scene);
    }

    @FXML
    void easyLevel(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SinglePlayer.fxml"));
        mystage.setTitle("Easy Level");
        Scene scene = new Scene(root);
        mystage.setScene(scene);
    }

    @FXML
    void hardLevel(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Hard.fxml"));
        mystage.setTitle("Hard Level");
        Scene scene = new Scene(root);
        mystage.setScene(scene);
    }

    @FXML
    void mediumLevel(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Medium.fxml"));
        mystage.setTitle("Medium Level");
        Scene scene = new Scene(root);
        mystage.setScene(scene);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
