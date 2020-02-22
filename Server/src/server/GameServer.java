/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import Database.User;
import Database.User_DB;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
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
        DataInputStream receiveOn;
        PrintStream sendOn;

        public ClientHandlerThread(CustomizedClientSocket c) throws IOException {
            receiveOn = new DataInputStream(c.getInputStream());
            sendOn = new PrintStream(c.getOutputStream());
        }

        public void sendClientList() {

            for (CustomizedClientSocket p : players_sockets) {
                //excluding his name
                sendOn.println(p.getPlayer());
            }
        }

        public void updateBoard(CustomizedClientSocket player, CustomizedClientSocket opponent) {
        }

        public void validateOpponent(CustomizedClientSocket client) {
        }

        public void updateScene(CustomizedClientSocket client) {
        }

        @Override
        public void run() {

            int found = 0;

            while (true) {
                try {
                    String request = receiveOn.readLine();
                    switch (request) {
                        case Protocol.SIGNIN:
                            String userName = receiveOn.readLine();
                            String pass = receiveOn.readLine();
                            System.out.println("from server" + userName + pass);
                             {
                                try {
                                    String result = User_DB.checkUser(userName, pass);
                                    System.out.println(result);
                                    if (result.equals("userFound")) {
                                        System.out.println("fiuhfiuhiur");
                                        sendOn.println(User_DB.updateUserStatus(userName, "online"));
                                    }
                                    //sendOn.println(User_DB.checkUser(userName, pass));
                                    //sendOn.println(User_DB.updateUserStatus(userName, "online"));
                                } catch (SQLException | ClassNotFoundException ex) {
                                    Logger.getLogger(GameServer.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            break;
                        case Protocol.SIGNUP:
                            String user = receiveOn.readLine();
                            String password = receiveOn.readLine();
                            String email = receiveOn.readLine();
                            System.out.println("from server" + user + password + email);
                             {
                                try {
                                    sendOn.println(User_DB.insertUser(user, password, email));
                                } catch (SQLException | ClassNotFoundException ex) {
                                    Logger.getLogger(GameServer.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            break;
                        case Protocol.ONLINEPLAYERS:
                            ArrayList<User> users = new ArrayList<User>();
                            users = User_DB.getOnlineUsers();
                    for (Iterator<User> it = users.iterator(); it.hasNext();) {
                                 //it.next();
                        System.out.println(it.next().getUserName());
                    }
                        case "CHOOSEOPPONENT":
                            player = receiveOn.readLine(); //dh mafrod ygely lma y click 3la asm  mn l online 3ando 
                            players_sockets.lastElement().statePlayer(player);//sets the playername in his socket 

                            //updateScene to scene 2 of choosing players or invitation
                            System.out.println("NumOfPlayers = " + players_sockets.size());
                            System.out.println("player added and is " + players_sockets.lastElement().getPlayer());

                            if (receiveOn.readLine().startsWith("opponent")) {
                                opponent = receiveOn.readLine().substring(9);
                            }

                            System.out.println("opponent is" + opponent);

                            for (CustomizedClientSocket p : players_sockets) {
                                /*checks for opponent*/
                                System.out.println("Entered the loop");
                                System.out.println(p.getPlayer());
                                if (opponent.equals(p.getPlayer())) {
                                    found += 1;
                                    System.out.println("Found Ahmed");
                                    DataInputStream rev = new DataInputStream(p.getInputStream());
                                    PrintStream sen = new PrintStream(p.getOutputStream());
                                    String msg = receiveOn.readLine();//dh l mfrod l goz2 l hayt7t feh l X aw O 
                                    sen.println(msg);
                                }
                                if (found == 0) {
                                    System.out.println("No such  a player ");
                                    sendOn.println("No such a player, wanna invite them ??");
                                }
                            }
                            break;
                        default:

                    }

                } catch (IOException ex) {
                    Logger.getLogger(GameServer.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(GameServer.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(GameServer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static void main(String[] args) {

        launch(args);

    }
}
