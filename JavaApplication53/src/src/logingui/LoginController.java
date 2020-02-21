package src.logingui;

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
 * @a
 * 
 * 
 * uthor lamis
 */
public class LoginController implements Initializable {

    public Text emailValidation;
    public Text passwordValidation;
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
    private void SignupLinkPressed(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        Main.mystage.setScene(new Scene(root));
    }

    //after validation to write a warning for user to enter proper email or valid pw ,,, emailValidation.setText("type of email error") and passwordValidation.setText("pw error")
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
