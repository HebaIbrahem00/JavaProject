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
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
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

            System.out.println("server is listening");
            while (true) {
                try {
                    client = (CustomizedClientSocket) server.accept();
                    System.out.println("a client connected");
                    try {
                        ClientHandlerThread clientHandle = new ClientHandlerThread(client);
                        clientHandle.start();
                    } catch (IOException ex) {
                        Logger.getLogger(ServerPage.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    players_sockets.add(client);//////atal3ha fo2???
                    System.out.println("a client added to vector");

                } catch (IOException ex) {
                    Logger.getLogger(ServerPage.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
    }

    public class ClientHandlerThread extends Thread {

        String player, opponent, message, request, password, email, userName;
        BufferedReader fromPlayer;
        PrintWriter toPlayer;

        BufferedReader fromOpponent;
        PrintWriter toOpponent;

        int serverloop = 0;

        public ClientHandlerThread(CustomizedClientSocket c) throws IOException {

            fromPlayer = new BufferedReader(new InputStreamReader(c.getInputStream(), "UTF-8"));
            toPlayer = new PrintWriter(c.getOutputStream(), true);
        }

        public void updateBoard(PrintWriter out1, PrintWriter out2, BufferedReader in1, BufferedReader in2) throws IOException {
            String line;
            System.out.println(out1 + "   " + out2 + "    " + in1 + "   " + in2);

            // Thread for Client 1
            Thread t1 = new Thread() {
                public void run() {
                    System.out.println("t1");
                    String line;
                    try {
                        while ((line = in1.readLine()) != null) {
                            if (line.equals(Protocol.MAKE_MOVE)) {
                                System.out.println("test1");
                                String move = line;
                                String[] l = move.split(" ");
                                board.makeMove(Integer.parseInt(l[0]), Integer.parseInt(l[1]), "X");
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
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.exit(0);
                    }
                }

            };
            //t1.start();
            // Thread for Client 2
            Thread t2 = new Thread() {
                public void run() {
                    String line;
                    System.out.println("t2");
                    try {
                        while ((line = in2.readLine()) != null) {
                            if (line.equals(Protocol.MAKE_MOVE)) {
                                String move = line;
                                System.out.println("test2");

                                String[] l = move.split(" ");
                                board.makeMove(Integer.parseInt(l[0]), Integer.parseInt(l[1]), "O");
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
                                out1.println(Protocol.MOVE_MADE);
                                out1.println(move);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.exit(0);
                    }
                }
            };
            //t2.start();
            ScheduledThreadPoolExecutor executor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(2);
            executor.scheduleAtFixedRate(t1, 0, 1, TimeUnit.SECONDS);
            executor.scheduleAtFixedRate(t2, 0, 1, TimeUnit.SECONDS);

//                while ((line = in1.readLine()) != null || (line = in2.readLine()) != null) {
//                    System.out.println("server received> " + line);
//
//                    if (line.equals(Protocol.MAKE_MOVE)) {
//                        System.out.println("received makemove protocol");
//                        String move = line;
//                        String[] l = move.split(" ");
//                        board.makeMove(Integer.parseInt(l[0]), Integer.parseInt(l[1]), "X");
//                        out2.println(Protocol.MOVE_MADE);
//                        out2.println(move);
//                        System.out.println("Move made here abdo");
//                        switch (board.getStatus()) {
//                            case "X":
//                                out2.println(Protocol.GAME_LOST);
//                                out1.println(Protocol.GAME_WON);
//                                break;
//                            case "O":
//                                out2.println(Protocol.GAME_WON);
//                                out1.println(Protocol.GAME_LOST);
//                                break;
//                            case "tie":
//                                out2.println(Protocol.GAME_TIED);
//                                out1.println(Protocol.GAME_TIED);
//                                break;
//                        }
//
//                    }
//
//                    if (line.equals(Protocol.MAKE_MOVE)) {
//                        String move = in2.readLine();
//                        String[] l = move.split(" ");
//                        board.makeMove(Integer.parseInt(l[0]), Integer.parseInt(l[1]), "O");
//                        out1.println(Protocol.MOVE_MADE);
//                        out1.println(move);
//                        switch (board.getStatus()) {
//                            case "X":
//                                out2.println(Protocol.GAME_LOST);
//                                out1.println(Protocol.GAME_WON);
//                                break;
//                            case "O":
//                                out2.println(Protocol.GAME_WON);
//                                out1.println(Protocol.GAME_LOST);
//                                break;
//                            case "tie":
//                                out2.println(Protocol.GAME_TIED);
//                                out1.println(Protocol.GAME_TIED);
//                                break;
//                        }
//
//                    }
//                }
        }

        public void validateOpponent(CustomizedClientSocket client) {

        }

        @Override
        public void run() {

            while (true) {
                serverloop += 1;
                try {
                    request = fromPlayer.readLine();
                    switch (request) {
                        case Protocol.SIGNIN:
                            System.out.println("Protocol sign in received");
                            userName = fromPlayer.readLine();
                            password = fromPlayer.readLine();
                            players_sockets.lastElement().setUserName(userName);

                            System.out.println("player added and is " + players_sockets.lastElement().getUserName());
                            System.out.println("NumOfPlayers = " + players_sockets.size());
                            toPlayer.println(User_DB.checkUser(userName, password));//authintication returned
                            break;

                        case Protocol.SIGNUP:
                            System.out.println("protocol sign up received ");
                            userName = fromPlayer.readLine();
                            email = fromPlayer.readLine();
                            password = fromPlayer.readLine();
                            toPlayer.println(User_DB.validateNewUserData(userName, email, password));

                            break;
                        case Protocol.DISCONNECTED: //bttb3t mn Main.java ??
                            System.out.println("protocol disconnect received ");
                            userName = fromPlayer.readLine();
                            User_DB.setUserOffline(userName);
                            break;

                        case Protocol.SHOWUSERS:
                            System.out.println("protocol showusers received ");
                            /*toPlayer.println(User_DB.reteriveOnlinUser());
                            System.out.println(User_DB.reteriveOnlinUser());*/
                            break;

                        case Protocol.CHOOSEOPPONENT:

                            System.out.println("Protocol.ChooseOpp Received ");
                            message = fromPlayer.readLine();////////////////////////////first readlin

                            if (message.startsWith("opponent")) {
                                System.out.println("server received opponent name  ");
                                opponent = message.substring(9);

                                System.out.println("opponent is " + opponent);

                                for (CustomizedClientSocket p : players_sockets) {

                                    System.out.println(p.getUserName());

                                    if (opponent.equals(p.getUserName())) {
                                        System.out.println("Found " + opponent);
                                        fromOpponent = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
                                        toOpponent = new PrintWriter(p.getOutputStream(), true);
                                        toOpponent.println(Protocol.INVITATION + ":" + userName + '\n');
//                                        System.out.println(fromOpponent);
//                                        System.out.println(toOpponent);

                                    }
                                }
                            } else if (message.startsWith("resultfrominvitation")) {
                                System.out.println("server received result from invitation");
                                String[] invite = message.split(":");
                                String acceptance = invite[1];
                                opponent = invite[2];
                                player = invite[3];
                                System.out.println(acceptance + " from " + opponent);

                                System.out.println("the one sent invitation is " + player);

                                if (acceptance.equals(Protocol.ACCEPTED)) {
                                    for (CustomizedClientSocket p : players_sockets) {

                                        System.out.println("searching for who sent the invitatiion");

                                        if (player.equals(p.getUserName())) {
                                            toPlayer = new PrintWriter(p.getOutputStream(), true);
                                            fromPlayer = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
                                            toPlayer.println(Protocol.CONNECTED);
                                            //we may try to receive something back to ensure it received CoNNECTED 
                                            System.out.println("server sent protocol to " + p.getUserName());
                                        }

                                        if (opponent.equals(p.getUserName())) {
                                            fromOpponent = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
                                            toOpponent = new PrintWriter(p.getOutputStream(), true);
                                            System.out.println("we got opponent streams ");
                                        }
                                    }
                                }
//                                toOpponent.println(Protocol.CONNECTED);
                                System.out.println(toPlayer);
                                System.out.println(toOpponent);
                                System.out.println(fromPlayer);
                                System.out.println(fromOpponent);

                                updateBoard(toPlayer, toOpponent, fromPlayer, fromOpponent);
                            } else {//Here we shall handle when invitation is rejected
                                System.out.println("server received " + message);
                            }
                            //we may try to receive something from the one who 
                            break;
                    }

                } catch (IOException ex) {
                    Logger.getLogger(ServerPage.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(ServerPage.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ServerPage.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("serverloop times=" + serverloop);
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
