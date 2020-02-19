/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Client.Board;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.util.Vector;
import static javafx.application.Application.launch;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

/**
 *
 * @author BOB
 */
public class GameServer extends Application {

    Vector<CustomizedClientSocket> players_sockets = new Vector();

    private Board board;

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

        onbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Here we need to Turn the Server Back on 
                serverInfo.resume();
            }
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

        CustomizedServerSocket server = new CustomizedServerSocket(9000); //creating a socket and binding it to a port with loopback ip 
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
                    try {
                        ClientHandlerThread x = new ClientHandlerThread(client);
                        x.start();
                    } catch (IOException ex) {
                        Logger.getLogger(GameServer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    players_sockets.add(client);//////atal3ha fo2???

                } catch (IOException ex) {
                    Logger.getLogger(GameServer.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
    }

    public class ClientHandlerThread extends Thread {

        String player, opponent;
        BufferedReader fromPlayer;
//        DataInputStream fromPlayer;
        private PrintWriter toPlayer;
//        PrintStream toPlayer;
        BufferedReader fromOponnent;
        PrintWriter toOpponent;
//        PrintStream toOpponent;
//        DataInputStream fromOpponent;
        int found = 0;

        public ClientHandlerThread(CustomizedClientSocket c) throws IOException {
            fromPlayer = new BufferedReader(new InputStreamReader(c.getInputStream(), "UTF-8"));
            toPlayer = new PrintWriter(c.getOutputStream(), true);
        }

        public void sendClientList() {

            for (CustomizedClientSocket p : players_sockets) {
                //excluding his name
                toPlayer.println(p.getPlayer());
            }
        }

        public void updateBoard(CustomizedClientSocket player, CustomizedClientSocket opponent) {

        }

        public void validateOpponent(CustomizedClientSocket client) {

        }

        public void updateScene(CustomizedClientSocket client) {
        }

        public void run() {

            while (true) {

                try {
                    switch (fromPlayer.readLine()) {
                        case "SIGNUP":

                            break;
                        case "SIGNIN":

                            player = fromPlayer.readLine();
                            players_sockets.lastElement().statePlayer(player);//sets the playername in his socket 
                            System.out.println("NumOfPlayers = " + players_sockets.size());
                            System.out.println("player added and is " + players_sockets.lastElement().getPlayer());

                            break;
                        case "CHOOSEOPPONENT":
                            System.out.println("server received chosoe opponent");
                            opponent = fromPlayer.readLine();
                            if (opponent.startsWith("opponent")) {
                                System.out.println("server entered if ");
                                opponent = opponent.substring(9);
                            }
                            System.out.println("opponent is" + opponent);

                            for (CustomizedClientSocket p : players_sockets) {
                                /*checks for opponent*/
                                if (p.getPlayer() != player) {
                                    System.out.println("Entered the loop");
                                    System.out.println(p.getPlayer());
                                    if (opponent.equals(p.getPlayer())) {
                                        found += 1;
                                        System.out.println("Found Ahmed");
                                        fromOponnent = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));

//                                      fromOpponent = new DataInputStream(p.getInputStream());
                                        toOpponent = new PrintWriter(p.getOutputStream(), true);

//                                      toOpponent = new PrintStream(p.getOutputStream());
                                        toPlayer.println("CONNECTED");
                                        toOpponent.println("CONNECTED");

                                        /////here we shall add the invitation
                                        System.out.println("server sent protocol connected");

                                    }
                                }
                                if (found == 0) {
                                    System.out.println("No such  a player ");
                                    toPlayer.println("No such a player, wanna invite them ??");
                                }
                            }
                            break;

                        default:

                    }

                } catch (IOException ex) {
                    Logger.getLogger(GameServer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static void main(String[] args) {

        launch(args);

    }
}
