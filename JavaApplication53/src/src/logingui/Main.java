package src.logingui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {

    private static Stage window;
    private static Parent root;
    public static Stage mystage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        mystage = primaryStage;
        root = FXMLLoader.load(getClass().getResource("LoginFXML.fxml"));
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
