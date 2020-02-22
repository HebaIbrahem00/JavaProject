/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.logingui;

import Validation.Sign_In_and_Sign_Up_Validation;
import client.Connection.ClientSocket;
import client.Connection.Protocol;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    String email;
    String password;
    String userName;
    @FXML
    private Label label;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

    @FXML
    //signup button action handler
    private void signupPressed(ActionEvent event) throws IOException {
        boolean nameResult = Sign_In_and_Sign_Up_Validation.nameNotEmpty(usernameField, usernameValidation, "Name is Required");
        boolean passwordResult = Sign_In_and_Sign_Up_Validation.passwordValidation(passwordField, passwordValidation, "Password is required");
        boolean emailResult = Sign_In_and_Sign_Up_Validation.emailValidation(emailField, emailValidation, "Email is required");
        if (nameResult && passwordResult && emailResult) {
            sendToServer(usernameField.getText(), passwordField.getText(), emailField.getText());
        }
    }

    private void sendToServer(String name, String password, String email) throws IOException {
        ClientSocket.toServer.println(Protocol.SIGNUP);
        ClientSocket.sendSignUpData(name, password, email);
    }

    @FXML
    //login hyper link to redirect to login page
    private void LoginLinkPressed(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("LoginFXML.fxml"));
        Main.mystage.setScene(new Scene(root));

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
