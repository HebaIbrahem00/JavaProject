/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import static java.lang.System.in;
import java.net.Socket;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Elkholy
 */
public class TicTacGUI  {
    private PrintWriter out;
    private BufferedReader in;
    private Board board;
    private  String me;
    private Stage stage;
    private Scene sc;
    private Button[] buttons = new Button[9];
    private CustomizedClientSocket socket;

    public TicTacGUI(CustomizedClientSocket _socket, String me, Stage stage, Scene sc) throws IOException {
     
        this.socket=_socket; //socket l client nafso msh haya5od ay sockets tanya 
        this.out=_socket.toServer;
        this.in=_socket.fromServer;
        this.board = new Board();
        this.me = me; //X or O
        this.stage = stage;
        this.sc = sc;
    }

    public void runLogic() {
        
        System.out.println("Game On !"); ///////////////
       
        BorderPane w = new BorderPane();
        w.setStyle("-fx-background-color: transparent;");
        Text txt = new Text("Waiting for player two...");
        txt.setFill(Color.GRAY);
        txt.setFont(Font.font("Monospaced", 20));
        w.setTop(txt);
        Scene waitingScene = new Scene(w, 300, 300, Color.WHITE);
        stage.setScene(waitingScene);
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: transparent");
        final Group ro = (Group) sc.getRoot();
        GridPane grid = (GridPane) ro.getChildren().get(0);
        root.setCenter(grid);
        root.setBottom(new Text("Connecting. . ."));
        Scene boardScene = new Scene(root,300, 310, Color.WHITE);
   //1st Thread inside runlogic function //////////////////////////////////////////////////////////////////////////////////////////////////////////////
       //The thread is after choosing the opponent and waits  for Protocol.CONNECTED
        //
        //Either if leave it like that and add the scene of waiting in GUI also, Or show the board and freeze the clicking until invitation is accepted
      
        Runnable forWaiting;
        forWaiting = new Runnable() {
             public void run() {
                 try {
                     System.out.println("entered the try");
                     String fromServ;
                     fromServ = in.readLine();
                     System.out.println(fromServ);
                     if (fromServ.equals(Protocol.CONNECTED)){
                         /////////////////////////////////
                         System.out.println("server confirmed opponent");
//                          me=in.readLine(); //reading X or O
                         Platform.runLater(()->stage.setScene(boardScene));
                         System.out.println("I'm "+me);
                         ///Here w shall start another thread that will handle the Data Exchange from and to Server 
                         //and every thing else until WON OR Lose
                         
                     } else {
                         System.out.println("2nd print out"+fromServ);
                     }}
                 
                 catch (IOException e) {
                     System.out.println("something went wrong");
                 }
             }
         };
        Thread waitingThread = new Thread(forWaiting);
        waitingThread.start();
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        root.setBottom(new Text("Waiting for opponent. . ."));
        root.setDisable(true);
        
        //////////////////////////////////
       if (me.equals("X")) {
            root.setBottom(new Text("Your turn. . ."));
            root.setDisable(false);
        }


        int i = 0;
        for (Node n : grid.getChildren()) {
            Button b = (Button) n;
            b.setOnAction(e -> {
                int[] data = (int[]) b.getUserData();
                
                System.out.println("data[0]= "+data[0]);
                 System.out.println("data[1]"+data[1]);
                
                if (board.isValidMove(data[1], data[0])) {
                    board.makeMove(data[1], data[0], me);
                    out.println(Protocol.MAKE_MOVE);/////this one is sent
                    out.println(data[1] + " " + data[0]);
                    
                    
                    b.setText(me);
                    root.setBottom(new Text("Waiting for opponent. . ."));
                    root.setDisable(true);
                }
            });
            buttons[i] = b;
            i++;
        }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //2nd Thread in runLogic function that  ???
        Runnable r = new Runnable() {
            public void run() {
                try {
                    String line;
                    while ((line = in.readLine()) != null) {
                        
                        switch (line) {
                            case Protocol.MOVE_MADE:
                                System.out.println("MOVE MADE"); //TODO
                                String move = in.readLine();     
                                System.out.println("MOVE " + move); // TODO
                                String[] l = move.split(" ");
                                String p;
                                if (me.equals("X")) p = "O";
                                else p = "X";
                                board.makeMove(Integer.parseInt(l[0]), Integer.parseInt(l[1]), p);
                                Platform.runLater(() -> refresh(Integer.parseInt(l[0]), Integer.parseInt(l[1])));
                                root.setDisable(false);
                                Platform.runLater(() -> root.setBottom(new Text("Your turn. . .")));
                                System.in.read(new byte[System.in.available()]); // Clears System.in
                                System.out.println("DONE WITH  MOVE");
                                break;
                            case Protocol.GAME_WON:
                                Platform.runLater(() ->root.setBottom(new Text("You won!")));
                                root.setDisable(true);
                                break;
                            case Protocol.GAME_LOST:
                                in.close();
                                socket.close();
                                root.setDisable(true);
                                Platform.runLater(() -> root.setBottom(new Text("You lost :(")));
                                break;
                            case Protocol.GAME_TIED:
                                Platform.runLater(() ->root.setBottom(new Text("It was a tie")));
                                root.setDisable(true);
                                break;
                        }
                    }
                } catch (IOException e) {
                    System.out.println("game ended");
                }
            }
        };

        Thread t = new Thread(r);
       // t.start();
    }

    private void refresh(int row, int col) {
        buttons[(col * 3) + row].setText(this.board.getTile(row, col));
    }
}
