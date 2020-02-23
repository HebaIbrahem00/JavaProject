/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverpage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
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
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("Server Page");
        stage.setScene(scene);
        stage.show();
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

        String player, opponent;

        BufferedReader fromPlayer;
        PrintWriter toPlayer;

        BufferedReader fromOponnent;
        PrintWriter toOpponent;

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
//            try {
//                while ((line = in2.readLine()) != null) {
//                   
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//                System.exit(0);
//            }

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
                            System.out.println("Protocol.SignIn Recerived");
                            player = fromPlayer.readLine();
                            players_sockets.lastElement().statePlayer(player);//sets the playername in his socket 
                            System.out.println("NumOfPlayers = " + players_sockets.size());
                            System.out.println("player added and is " + players_sockets.lastElement().getPlayer());

                            break;
                        case "CHOOSEOPPONENT":

                            System.out.println("Protocol.ChooseOpp Received h");
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
                                        toOpponent = new PrintWriter(p.getOutputStream(), true);

                                        toPlayer.println(Protocol.CONNECTED);

                                        toOpponent.println(Protocol.CONNECTED);
                                        //here we need to clear buffer or make a tiny wait between the two streams 
//                                        toPlayer.println("X");
//                                        toOpponent.println("O");

                                        /////here we shall add the invitation
                                        System.out.println("server sent protocol connected");
                                        updateBoard(toPlayer, toOpponent, fromPlayer, fromOponnent);

                                    }
                                }
                                if (found == 0) {
                                    System.out.println("Player Went Offline");
                                    toPlayer.println("Player ");
                                }
                            }
                            break;

                        default:

                    }

                } catch (IOException ex) {
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
