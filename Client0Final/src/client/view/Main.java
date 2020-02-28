package client.view;

import Model.CurrentUser;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import client.Connection.ClientSocket;
import client.Connection.Protocol;
import java.io.IOException;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Main extends Application {

    private static Parent root;
    public static Stage mystage;
    static ClientSocket CLIENT;

    @Override
    public void start(Stage primaryStage) throws IOException {

        //initialize the stage to display the first scene (login)
        mystage = primaryStage;
        root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        mystage.setTitle("Login");
        mystage.setScene(new Scene(root));
        mystage.show();

        //a thread used to initiate a socket to connect to the server
        new Thread() {
            @Override
            public void run() {
                try {
                    ClientSocket.initSocket("localhost", 7000);
               
                } catch (IOException ex) {
                    Platform.runLater(() -> {
                        Alert alert = new Alert(AlertType.WARNING);
                        alert.setTitle("Connection Error");
                        alert.setHeaderText(null);
                        alert.setContentText("Server is Not Connected");
                        alert.showAndWait();
                    });
                }
            }
        }.start();
    }

    public void stop() {
        ClientSocket.toServer.println(Protocol.DISCONNECTED);
        ClientSocket.toServer.println(CurrentUser.getUserName());
    }

    @FXML
    private void miniHandler() {
        System.out.println("Minimise.");
    }

    public static void main(String[] args) {
        launch(args);
        ClientSocket.toServer.println(Protocol.DISCONNECTED);
        ClientSocket.toServer.println(CurrentUser.getUserName());
    }
}
