package server;

import Database.User_DB;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import static javafx.application.Application.launch;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

/**
 * @author BOB
 */
public class Server extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        Button btn = new Button();
        btn.setText("Turn Server OFF");
        Button onbtn = new Button();
        onbtn.setText("Turn Server On");
        final Background serverInfo = new Background();
        serverInfo.start();

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                serverInfo.stop();
                System.out.println("Server Closed successfully");

            }
        });

        onbtn.setOnAction((ActionEvent event) -> {
            // Here we need to Turn the Server Back on
            serverInfo.resume();
//                serverInfo.start();
        });

        HBox box1 = new HBox();
        box1.getChildren().addAll(btn, onbtn);
        box1.setAlignment(Pos.CENTER);

        StackPane root = new StackPane();
        root.getChildren().addAll(box1);
        Scene scene = new Scene(root, 300, 250);
        Thread curr = Thread.currentThread();
        System.out.println(curr);
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public class Background extends Thread {

        //creating a socket and binding it to a port with loopback ip
        CustomizedServerSocket server = new CustomizedServerSocket(7000);
        CustomizedClientSocket client;

        public Background() throws IOException {
        }

        @Override
        public void run() {
            super.run();
            System.out.println("server is listening");
            while (true) {
                try {
                    client = (CustomizedClientSocket) server.accept();
                    System.out.println("a client connected");
                    ClientHandlerThread x = new ClientHandlerThread(client);

                    x.start();

                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public class ClientHandlerThread extends Thread {

        String player, opponent, password;
        DataInputStream fromPlayer;
        PrintStream toPlayer;

        public ClientHandlerThread(CustomizedClientSocket c) throws IOException {
            fromPlayer = new DataInputStream(c.getInputStream());
            toPlayer = new PrintStream(c.getOutputStream());
        }

        @Override
        public void run() {
            String userName;
            String email;
            String password;
            while (true) {
                try {
                    //server is listening to a request from the client
                    String request = fromPlayer.readLine();

                    System.out.println(request);
                    switch (request) {

                        case Protocol.SIGNIN:
                            userName = fromPlayer.readLine();
                            password = fromPlayer.readLine();
                            toPlayer.println(User_DB.checkUser(userName, password));
                            break;

                        case Protocol.SIGNUP:
                            System.out.println("server sign up");
                            userName = fromPlayer.readLine();
                            email = fromPlayer.readLine();
                            password = fromPlayer.readLine();
                            toPlayer.println(User_DB.validateNewUserData(userName, email, password));

                        case Protocol.DISCONNECTED:
                            userName = fromPlayer.readLine();
                            User_DB.setUserOffline(userName);
                            break;
                            
                       case Protocol.SHOWUSERS:
                            toPlayer.println(User_DB.reteriveOnlinUser());
                            System.out.println(User_DB.reteriveOnlinUser());
                            break;
                        

//                        case "CHOOSEOPPONENT":
//                            int found = 0;
//                            System.out.println("server received chosoe opponent");
//                            opponent = fromPlayer.readLine();
//                            if (opponent.startsWith("opponent")) {
//                                System.out.println("server entered if ");
//                                opponent = opponent.substring(9);
//                            }
//                            System.out.println("opponent is" + opponent);
//                            /*checks for opponent*/
//                            for (CustomizedClientSocket p : players_sockets) {
//                                if (p.getPlayer() != player) {
//                                    System.out.println("Entered the loop");
//                                    System.out.println(p.getPlayer());
//                                    if (opponent.equals(p.getPlayer())) {
//                                        found += 1;
//                                        System.out.println("Found Ahmed");
//
//                                        DataInputStream fromOpponent = new DataInputStream(p.getInputStream());
//
//                                        PrintStream toOpponent = new PrintStream(p.getOutputStream());
//
//                                        toPlayer.println(Protocol.CONNECTED);
//                                        /////here we shall add the invitation
//                                        toOpponent.println(Protocol.CONNECTED);
//
//                                        System.out.println("server sent protocol connected");
//
//                                    }
//                                }
//                                if (found == 0) {
//                                    System.out.println("No such  a player ");
//                                    toPlayer.println("No such a player, wanna invite them ??");
//                                }
//                            }
//                            break;
                        default:
                    }

                } catch (IOException | SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    }

//    public static void main(String[] args) {
//        launch(args);
//    }

}
