/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverMain;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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

    Board board = new Board(); //msh ha3ml mnha object hna, ha5do mn l clients.

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
           // super.run();
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

        String player, opponent,message;

        BufferedReader fromPlayer;
        PrintWriter toPlayer;

        BufferedReader fromOpponent;
        PrintWriter toOpponent;
        CustomizedClientSocket client;
        int found = 0;

        public ClientHandlerThread(CustomizedClientSocket c) throws IOException {

            fromPlayer = new BufferedReader(new InputStreamReader(c.getInputStream(), "UTF-8"));
            toPlayer = new PrintWriter(c.getOutputStream(), true);
            this.client=c;
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
                while ((line = in1.readLine()) != null  || (line = in2.readLine()) != null) {
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
                            message = fromPlayer.readLine();////////////////////////////first readlin
                            
                            if (message.startsWith("opponent")) {
                                System.out.println("server entered if ");
                                opponent = message.substring(9);
                                
                                System.out.println("opponent is" + opponent);
                                
                                for (CustomizedClientSocket p : players_sockets) {
                                    /*checks for opponent*/
                                    if (p.getPlayer() != player) {
                                        System.out.println("Entered the loop");
                                        System.out.println(p.getPlayer());
                                        
                                        if (opponent.equals(p.getPlayer()))
                                        { 
                                            found += 1;
                                            System.out.println("Found Ahmed");
                                            fromOpponent = new BufferedReader(new InputStreamReader(p.getInputStream(),"UTF-8"));
                                            toOpponent = new PrintWriter(p.getOutputStream(), true);
                                            toOpponent.println(Protocol.INVITATION+":"+player+'\n');
                                        
                                        }  }  }}
                            
                            else if(message.startsWith("resultfrominvitation"))
                            { 
                             
                                
                                System.out.println("server entered if else");
                              
                                String[] invite=message.split(":");
                                String acceptance=invite[1];
                                opponent=invite[2];
                                player=invite[3];
                                System.out.println(acceptance+" from "+opponent);
                                
                                  System.out.println("the one sent invitation is "+player);
                                  
                                if(acceptance.equals(Protocol.ACCEPTED ))
                                {   
                                     for (CustomizedClientSocket p : players_sockets) {
                                          
                                        if (opponent.equals(p.getPlayer()))
                                        { 
                                            System.out.println("Entered whatever");
                                            
                                            fromOpponent = new BufferedReader(new InputStreamReader(p.getInputStream(),"UTF-8"));
                                            toOpponent = new PrintWriter(p.getOutputStream(), true);
                                            
                                            toOpponent.println(Protocol.CONNECTED); 
                                             System.out.println("server sent protocol to "+p.getPlayer());
                                        }  
                                        
                                          if (player.equals(p.getPlayer()))
                                          {
                                              toPlayer= new PrintWriter(p.getOutputStream(), true);
                                              
                                              fromPlayer=new BufferedReader(new InputStreamReader(p.getInputStream(),"UTF-8"));
                                             toPlayer.println(Protocol.CONNECTED);
                                            System.out.println("server sent protocol to "+p.getPlayer());
                                          }
                                          
                                    
                                          
                                }
                           
                                }
                                
                                //here we need to clear buffer or make a tiny wait between the two streams
                                
                                
                                
                                try {
                                    updateBoard(toPlayer, toOpponent, fromPlayer,fromOpponent);
                                } catch (IOException ex) {
                                    Logger.getLogger(GameServer.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            break;

                        default:
                    }
                } catch (IOException ex) {
                    Logger.getLogger(GameServer.class.getName()).log(Level.SEVERE, null, ex);
                }

                    
            }}
        
    
    }
    public static void main(String[] args) {

        launch(args);

    }
}               
