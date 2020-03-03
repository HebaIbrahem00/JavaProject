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
 * @author omar
 */
public class ServerPage extends Application {

    Vector<CustomizedClientSocket> players_sockets = new Vector();
    Board board = new Board();

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
                    System.exit(1);
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
                        players_sockets.add(client);
                        players_sockets.lastElement().setThread(clientHandle);
                    } catch (IOException ex) {
                        Logger.getLogger(ServerPage.class.getName()).log(Level.SEVERE, null, ex);
                    }

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
            String line, line2 = null;
            boolean gameEnded = false;

            try {
                System.out.println("player " + player + " Entered update board" + Thread.currentThread());
                //  while ((line = in1.readLine()) != null  || (line2 = in2.readLine()) != null) {
                while (!gameEnded) {
                    line = in1.readLine();
                    line2 = in2.readLine();
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
                                gameEnded = true;
                                break;
                            case "O":
                                out2.println(Protocol.GAME_WON);
                                out1.println(Protocol.GAME_LOST);
                                gameEnded = true;
                                break;
                            case "tie":
                                out2.println(Protocol.GAME_TIED);
                                out1.println(Protocol.GAME_TIED);
                                gameEnded = true;
                                break;
                        }

                    }
                    if (line2.equals(Protocol.MAKE_MOVE)) {
                        String move = in2.readLine();
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
                            players_sockets.lastElement().gettThread().setName(userName);

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
                            toPlayer.println(User_DB.reteriveOnlinUser());
                            System.out.println(User_DB.reteriveOnlinUser());
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
                                        toOpponent.println(Protocol.INVITATION + ":" + userName);
                                        p.gettThread().join();
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
                                            toPlayer.println("X");////here
                                            System.out.println("server sent protocol to " + p.getUserName());
                                        }

                                        if (opponent.equals(p.getUserName())) {
                                            toOpponent = new PrintWriter(p.getOutputStream(), true);
                                            fromOpponent = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
                                            toOpponent.println(Protocol.CONNECTED);
                                            toOpponent.println("O");///here
                                            System.out.println("we got opponent " + p.getUserName() + "streams ");
                                        }

                                    }
                                    updateBoard(toPlayer, toOpponent, fromPlayer, fromOpponent);
                                }

                            } else {//Here we shall handle when invitation is rejected
                                System.out.println("server received " + message);
                            }
                            System.out.println("player " + userName + "finished choopse opp");
                            break;

                        default:
                            System.out.println("test " + request + Thread.currentThread());

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
                System.out.println("serverloop times=" + serverloop + Thread.currentThread());
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
