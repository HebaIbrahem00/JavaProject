/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainmenugui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author lamis
 */
public class FXMLPopUpController implements Initializable {
       public Label InviteLabel;
       public Button AcceptBtn;
       public Button Ignorebtn;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    //this is the action listener of the login button
    private void AcceptInvite(ActionEvent event) {
        //passwordField.setText("password");
        //emailField.setText("email");
        //emailValidation.setText("Hello World!");
    }
    @FXML
    //this is the action listener of the login button
    private void IgnoreInvite(ActionEvent event) {
        //passwordField.setText("password");
        //emailField.setText("email");
        //emailValidation.setText("Hello World!");
    }
}
