
package client.view;

import client.Connection.ClientSocket;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * @author lamis
 */
public class SignUpController implements Initializable {

    public Text emailValidation;
    public Text passwordValidation;
    public Text usernameValidation;
    public TextField usernameField;
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
    //signup button action handler
    private void signupPressed(ActionEvent event) throws IOException {
       
        System.out.println("You clicked me!");
        String userName = usernameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        String isDataValid = ClientSocket.sendSignUpData(userName, email, password);
        
        switch (isDataValid) {
            case "validData":
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Welcome");
                alert.setHeaderText(null);
                alert.setContentText("Welcome, "+userName);
                alert.showAndWait();
                Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
                Scene scene = new Scene(root);
                Main.mystage.setScene(scene);
                break;
            case "User with email address already exists":
                label.setText(isDataValid);
                break;
        }
    }

    @FXML
    //login hyper link to redirect to login page
    
    private void LoginLinkPressed(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(root);
        Main.mystage.setScene(scene);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
