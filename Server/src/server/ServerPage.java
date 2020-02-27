/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import Database.User_DB;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author lamis
 */
public class ServerPage extends Application {

    Vector<CustomizedClientSocket> players_sockets = new Vector();

    Board board = new Board(); //msh ha3ml mnha object hna, ha5do mn l clients.

    public static void stopServer() {

    }

    public static void startServer() {

    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ServerFXML.fxml"));
        Button On = (Button) root.lookup("#OnBtn");
        Button Off = (Button) root.lookup("#OffBtn");

        final Background serverInfo = new Background();
        On.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                serverInfo.start();

            }
        });
        Off.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    serverInfo.server.close();
                } catch (IOException ex) {
                    Logger.getLogger(ServerPage.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        Scene scene = new Scene(root);
        stage.setTitle("Server Page");
        stage.setScene(scene);
        stage.show();
    }

    public class Background extends Thread {

        CustomizedServerSocket server = new CustomizedServerSocket(7000); //creating a socket and binding it to a port with loopback ip 
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
                        Logger.getLogger(ServerPage.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    players_sockets.add(client);//////atal3ha fo2???

                } catch (IOException ex) {
                    Logger.getLogger(ServerPage.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
    }

    public class ClientHandlerThread extends Thread {

        String player, opponent, message;

        BufferedReader fromPlayer;
        PrintWriter toPlayer;

        BufferedReader fromOpponent;
        PrintWriter toOpponent;

        int found = 0;

        public ClientHandlerThread(CustomizedClientSocket c) throws IOException {

            fromPlayer = new BufferedReader(new InputStreamReader(c.getInputStream(), "UTF-8"));
            toPlayer = new PrintWriter(c.getOutputStream(), true);
        }

        public void sendClientList() {

            for (CustomizedClientSocket p : players_sockets) {
                //excluding his name 
                toPlayer.println(p.getUserName());
            }
        }

        public void updateBoard(PrintWriter out1, PrintWriter out2, BufferedReader in1, BufferedReader in2) throws IOException {
            String line;

            try {
                while ((line = in1.readLine()) != null || (line = in2.readLine()) != null) {
                    if (line.equals(Protocol.MAKE_MOVE)) {
                        String move = in1.readLine();
                        String[] l = move.split(" ");
                        board.makeMove(Integer.parseInt(l[0]), Integer.parseInt(l[1]), "X");////////
                        out2.println(Protocol.MOVE_MADE);
                        out2.println(move);
                        switch (board.getStatus()) {
                            case "X":
                                out2.println(Protocol.GAME_LOST);
                                out1.println(Protocol.GAME_WON);
                                break;
                            case "O":
                                out2.println(Protocol.GAME_WON);
                                out1.println(Protocol.GAME_LOST);
                                break;
                            case "tie":
                                out2.println(Protocol.GAME_TIED);
                                out1.println(Protocol.GAME_TIED);
                                break;
                        }

                    }

                    if (line.equals(Protocol.MAKE_MOVE)) {
                        String move = in2.readLine();
                        String[] l = move.split(" ");
                        board.makeMove(Integer.parseInt(l[0]), Integer.parseInt(l[1]), "O");
                        out1.println(Protocol.MOVE_MADE);
                        out1.println(move);
                        switch (board.getStatus()) {
                            case "X":
                                out2.println(Protocol.GAME_LOST);
                                out1.println(Protocol.GAME_WON);
                                break;
                            case "O":
                                out2.println(Protocol.GAME_WON);
                                out1.println(Protocol.GAME_LOST);
                                break;
                            case "tie":
                                out2.println(Protocol.GAME_TIED);
                                out1.println(Protocol.GAME_TIED);
                                break;
                        }

                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
                System.exit(0);
            }

        }

        public void validateOpponent(CustomizedClientSocket client) {

        }

        public void updateScene(CustomizedClientSocket client) {
        }

        @Override
        public void run() {

            String userName;
            String email;
            String password;
            int noUser = 0;
            while (true) {

                try {
                    switch (fromPlayer.readLine()) {
                        case Protocol.SIGNIN:
                            userName = fromPlayer.readLine();
                            password = fromPlayer.readLine();
                            players_sockets.elementAt(noUser).setUserName(userName);
                            System.out.println("UserNo" + players_sockets.get(noUser).getUserName());
//                            players_sockets.lastElement().statePlayer(userName);
//                            System.out.println(players_sockets.lastElement().getPlayer());
                            toPlayer.println(User_DB.checkUser(userName, password));
                            break;
                        case Protocol.SIGNUP:
                            System.out.println("server sign up");
                            userName = fromPlayer.readLine();
                            email = fromPlayer.readLine();
                            password = fromPlayer.readLine();
                            toPlayer.println(User_DB.validateNewUserData(userName, email, password));
//                            System.out.println("Protocol.SignIn Recerived");
//                            player = fromPlayer.readLine();
//                            players_sockets.lastElement().statePlayer(player);//sets the playername in his socket 
//                            System.out.println("NumOfPlayers = " + players_sockets.size());
//                            System.out.println("player added and is " + players_sockets.lastElement().getPlayer());
                            break;
                        case Protocol.DISCONNECTED:
                            userName = fromPlayer.readLine();
                            User_DB.setUserOffline(userName);
                            break;

                        case Protocol.SHOWUSERS:
                            toPlayer.println(User_DB.reteriveOnlinUser());
                            System.out.println(User_DB.reteriveOnlinUser());
                            break;
                        case "CHOOSEOPPONENT":
                            System.out.println("Protocol.ChooseOpp Received h");
                            message = fromPlayer.readLine();////////////////////////////first readlin

                            if (message.startsWith("opponent")) {
                                System.out.println("server entered if ");
                                opponent = message.substring(9);

                                System.out.println("opponent is" + opponent);

                                for (CustomizedClientSocket p : players_sockets) {
                                    /*checks for opponent*/
                                    if (p.getUserName() != player) {

                                        System.out.println(p.getUserName());

                                        if (opponent.equals(p.getUserName())) {
                                            found += 1;
                                            fromOpponent = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
                                            toOpponent = new PrintWriter(p.getOutputStream(), true);
                                            toOpponent.println(Protocol.INVITATION + ":" + player + '\n');

                                        }
                                    }
                                }
                            } else if (message.startsWith("resultfrominvitation")) {

                                System.out.println("server entered if else");

                                String[] invite = message.split(":");
                                String acceptance = invite[1];
                                opponent = invite[2];
                                player = invite[3];
                                System.out.println(acceptance + " from " + opponent);

                                System.out.println("the one sent invitation is " + player);

                                if (acceptance.equals(Protocol.ACCEPTED)) {
                                    for (CustomizedClientSocket p : players_sockets) {

                                        System.out.println("Entered for");
                                        if (opponent.equals(p.getUserName())) {

                                            fromOpponent = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
                                            toOpponent = new PrintWriter(p.getOutputStream(), true);
                                            this.sleep(2000);
                                            toOpponent.println(Protocol.CONNECTED);
                                            System.out.println("server sent protocol to " + p.getUserName());
                                        }

                                        if (player.equals(p.getUserName())) {
                                            toPlayer = new PrintWriter(p.getOutputStream(), true);

                                            fromPlayer = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
                                            //this.sleep(2000);
                                            toPlayer.println(Protocol.CONNECTED);
                                            System.out.println("server sent protocol to " + p.getUserName());
                                        }
                                    }
                                }

                                //here we need to clear buffer or make a tiny wait between the two streams
                                try {
                                    updateBoard(toPlayer, toOpponent, fromPlayer, fromOpponent);
                                } catch (IOException ex) {
                                    Logger.getLogger(ServerPage.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            break;

                        default:
                    }

                } catch (IOException ex) {
                    Logger.getLogger(ServerPage.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(ServerPage.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ServerPage.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ServerPage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
