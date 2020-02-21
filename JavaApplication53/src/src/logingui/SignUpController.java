/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.logingui;

import java.io.IOException;
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
public class SignUpController implements Initializable {
    public Text emailValidation;
    public Text passwordValidation;
    public Text usernameValidation;
    public TextField usernameField;
    public TextField emailField;
    public PasswordField passwordField;
    Main m= new Main();
    
    @FXML
    private Label label;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    @FXML
    //signup button action handler
    private void signupPressed(ActionEvent event) {
        //System.out.println("You clicked me signup!");
        //System.out.println(emailField.getText());
        //emailValidation.setText("email valid");
        //passwordValidation.setText("password not valid");
        //usernameValidation.setText("username valid");
        
    }
    @FXML
    //login hyper link to redirect to login page
    private void LoginLinkPressed(ActionEvent event) throws IOException {
        //System.out.println("You clicked me link!");
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
