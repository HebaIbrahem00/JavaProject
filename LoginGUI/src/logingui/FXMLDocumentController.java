/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logingui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 *
 * @author lamis
 */

public class FXMLDocumentController implements Initializable {
    public Text emailValidation ;
    public Text passwordValidation ;
    public TextField emailField;
    public PasswordField passwordField;
    
    @FXML
    private Label label;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    @FXML
    //this is the action listener of the login button
    private void loginPressed(ActionEvent event) {
        //passwordField.setText("password");
        //emailField.setText("email");
        //emailValidation.setText("Hello World!");
    }
    @FXML
    //this is the action listener of the Not a player hyperlink which should redirect to signuppage
    private void SignupLinkPressed(ActionEvent event) {
        
    }
    //after validation to write a warning for user to enter proper email or valid pw ,,, emailValidation.setText("type of email error") and passwordValidation.setText("pw error")
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
