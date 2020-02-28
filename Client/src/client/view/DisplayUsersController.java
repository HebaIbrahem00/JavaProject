package client.view;

import Model.CurrentUser;
import Model.OnlineUser;
import Model.OnlineUserList;
import client.Connection.ClientSocket;
import static client.view.Main.mystage;
import static client.view.MainMenuController.onlineUserList;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * @author lamis
 */
public class DisplayUsersController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private TableView TableView;
    @FXML
    private TableColumn UsernameColumn;
    @FXML
    private TableColumn EmailColumn;
    @FXML
    private TableColumn StatusColumn;
    @FXML
    private TableColumn ScoreColumn;
    @FXML
    private TableColumn ActionColumn;
    @FXML
    private Button inviteBtn;

    InvitationListen waitInvitation;
    private final AtomicBoolean running = new AtomicBoolean(true);
  

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("invite pressed");
        label.setText("Hello World!");
    }

    @FXML
    private void BackPressed(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        Scene scene = new Scene(root);
        mystage.setTitle("Main Menu");
        mystage.setScene(scene);
    }

    @FXML
    private void invitePressed(ActionEvent event) {
        OnlineUserList person = (OnlineUserList) TableView.getSelectionModel().getSelectedItem();
        System.out.println("choosing opponent " + person.getUserName());
        String opponent = "opponent " + person.getUserName();
        ClientSocket.invitation(opponent);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UsernameColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
        EmailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        StatusColumn.setCellValueFactory(new PropertyValueFactory<>("userStatus"));
        ScoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
        ClientSocket.toServer.println(Protocol.CHOOSEOPPONENT);
        waitInvitation = new InvitationListen();

        final ObservableList<OnlineUser> allData = FXCollections.observableArrayList();

        for (OnlineUserList user : onlineUserList) {
            TableView.getItems().add(user);
        }

    }

    public class InvitationListen extends Thread {

        Scene scene;
        Stage stage;
        String listen = "";

        public InvitationListen() {
            start();
        }

        @Override
        public void run() {
          
            while (running.get()) {
                try {
                        listen = ClientSocket.fromServer.readLine();
                           
                } catch (IOException ex) {
                    Logger.getLogger(DisplayUsersController.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (listen.startsWith(Protocol.INVITATION)) {
                    String[] invite = listen.split(":");
                    String fromWho = invite[1];
                    System.out.println("invitation is from>>" + fromWho);
                      Platform.runLater(() ->{
                      try {
                            invitationDetails("Game Invitation", "!!", fromWho + " wanna play, do u?", stage, scene, fromWho);
                        } catch (IOException ex) {
                            Logger.getLogger(DisplayUsersController.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(DisplayUsersController.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (Exception ex) {
                            Logger.getLogger(DisplayUsersController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                }   );
                }
                else if (listen.startsWith(Protocol.CONNECTED)) {
                    System.out.println("protocol .connected received ");
                    running.set(false);
                    Platform.runLater(() -> {
                        Parent root;
                        try {
                            Stage onlineStage = new Stage();
                            root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                            Scene Xscene = null;
                            onlineStage.setTitle("MultiPlayer");
                            running.set(false);
                            connect("O", onlineStage, Xscene, root);
                        } catch (IOException ex) {
                            Logger.getLogger(DisplayUsersController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });

                }
                
                else{System.out.println("received in wrong place "+listen);}
            }
        }

        public void invitationDetails(String title, String header, String content, Stage s, Scene XOscene, String from) throws IOException, InterruptedException, Exception {
            final Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle(title);
            alert.setHeaderText(header);
            alert.setContentText(content);
            alert.getButtonTypes().clear();

            ButtonType approve = new ButtonType("Play");
            ButtonType decline = new ButtonType("Maybe Later");
            alert.getButtonTypes().addAll(approve, decline);
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == approve) {

                String msgToserver = "resultfrominvitation" + ":" + Protocol.ACCEPTED + ":" + CurrentUser.getUserName() + ":" + from;//asm l accepted 

                ClientSocket.toServer.println(msgToserver);
                System.out.println("approval sent to server");
       
            } else {
                ClientSocket.toServer.println("don't");
                System.out.println("decline sent to server");
            }

        }
    }


    private static void connect(String s, Stage stage, Scene sc, Parent parent) throws IOException {
        TicTacGUI client = new TicTacGUI(s, stage, sc, parent);
        client.run();
    }

}
