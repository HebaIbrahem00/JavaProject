package client.logingui;

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
 * uthor lamis
 */
public class LoginController implements Initializable {

    public Text emailValidation;
    public Text passwordValidation;
    public TextField emailField;
    public PasswordField passwordField;
    String email;
    String password;

    @FXML
    private Label label;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

    @FXML
    //this is the action listener of the login button
    private void loginPressed(ActionEvent event) throws IOException {
        email = emailField.getText();
        if(email.equals("")){
            System.out.println("vjfiojdodifj");
            //emailValidation.setText("PLease Enter Your Name");
            email = emailField.getText();
        }
        /*if (emailField.getText() == null || emailField.getText().isEmpty()){
            emailValidation.setText("PLease Enter Your Name");
        }*/
        password = passwordField.getText();
        if(password.equals("")){
            System.out.println("vjfiojdodifj");
            //passwordValidation.setText("PLease Enter Your Password");
            password = passwordField.getText();
        }
        ClientSocket.toServer.println(Protocol.SIGNIN);
        ClientSocket.sendLoginData(email, password);
        ClientSocket.toServer.println(Protocol.ONLINEPLAYERS);
        //label.setText("Hello World!");
        //System.out.println(email+password);
    }

    @FXML
    //this is the action listener of the Not a player hyperlink which should redirect to signuppage
    private void SignupLinPressed(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        Scene scene = new Scene(root);

        Main.mystage.setScene(scene);
    }

    //after validation to write a warning for user to enter proper email or valid pw ,,, emailValidation.setText("type of email error") and passwordValidation.setText("pw error")
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
