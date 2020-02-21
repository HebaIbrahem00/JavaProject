package client.logingui;

import client.Connection.ClientSocket;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import client.Connection.ClientSocket;

public class Main extends Application {

    private static Stage window;
    private static Parent root;
    public static Stage mystage;
    ClientSocket CLIENT;

    @Override
    public void init() throws Exception {
        super.init(); //To change body of generated methods, choose Tools | Templates.
    }

    public static Stage getPrimaryStage() {
        return mystage;
    }

    private void setPrimaryStage(Stage pStage) {
        Main.mystage = pStage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //initiate new socket
        ClientSocket.CLIENT.initSocket("localhost", 9000);
        setPrimaryStage(primaryStage);
        Parent root = FXMLLoader.load(getClass().getResource("LoginFXML.fxml"));
        mystage.setTitle("Login In");
        mystage.setScene(new Scene(root));
        mystage.show();

    }

    @FXML
    private void sceneHandler() throws IOException {
        System.out.println("Scene changing...");
        root = FXMLLoader.load(getClass().getResource("mainGUI.fxml"));
        mystage.setScene(new Scene(root));
    }

    @FXML
    private void exitHandler() {
        System.out.println("Exit.");
        System.exit(0);
    }

    @FXML
    private void miniHandler() {
        System.out.println("Minimise.");
    }

    public static void main(String[] args) {
        //        System.out.println("hello");
        launch(args);
    }
}
