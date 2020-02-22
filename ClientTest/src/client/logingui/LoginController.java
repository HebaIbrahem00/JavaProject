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
import Validation.*;

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
        boolean result = Sign_In_and_Sign_Up_Validation.nameNotEmpty(emailField, emailValidation, "Name Reequired");
        boolean result2 = Sign_In_and_Sign_Up_Validation.passwordValidation(passwordField, passwordValidation, "Pssword Required");
        if (result && result2) {
            send_To_Server(emailField.getText(), passwordField.getText());
        }
    }

    public void send_To_Server(String email, String password) throws IOException {
        ClientSocket.toServer.println(Protocol.SIGNIN);
        ClientSocket.sendLoginData(email, password);
        ClientSocket.toServer.println(Protocol.ONLINEPLAYERS);
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
