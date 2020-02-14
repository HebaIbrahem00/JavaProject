/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gamev2;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import java.io.DataInputStream;
import java.io.IOException;
import java.net.*;
import java.io.PrintStream;
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


/**
 *
 * @author BOB
 */
public class GameServer extends Application {  
    
     Vector <CustomizedClientSocket>  players_sockets= new Vector();
      
    @Override
    public void start(Stage primaryStage) throws IOException {
        Button btn = new Button();
        btn.setText("Turn Server OFF");
        final Background serverInfo =new Background();
        serverInfo.start();
        
        
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    serverInfo.server.close();
                } catch (IOException ex) {
                    Logger.getLogger(GameServer.class.getName()).log(Level.SEVERE, null, ex);
                }
                    System.out.println("Server Closed successfully");
             
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn); 
        Scene scene = new Scene(root, 300, 250);
       Thread curr= Thread.currentThread();
       System.out.println(curr);
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

   
    public class Background extends Thread
    {
        
        CustomizedServerSocket server = new CustomizedServerSocket(9000); //creating a socket and binding it to a port with loopback ip 
        CustomizedClientSocket client;
         ClientHandlerThread x;
         
         public Background() throws IOException {   
          System.out.println("server is listening");
             while(true){
                try {
                    client = (CustomizedClientSocket)server.accept();
                    x = new ClientHandlerThread(client);
                      players_sockets.add(client);
                   
                } catch (IOException ex) {
                    Logger.getLogger(GameServer.class.getName()).log(Level.SEVERE, null, ex);
                }
                     System.out.println("a client connected");          
          }
        }

        @Override
        public void run() { super.run();}
                  
    }
    
    public class ClientHandlerThread extends Thread
    
    {   String player ,opponent;
        DataInputStream receiveOn;
        PrintStream sendOn;
        

        public ClientHandlerThread(CustomizedClientSocket c) throws IOException {
           receiveOn= new DataInputStream(c.getInputStream());
           sendOn = new PrintStream(c.getOutputStream());
           start(); } 
        
        
        public void run(){
        while(true){
           try {
                   player= receiveOn.readLine(); //dh mafrod ygely lma y click 3la asm  mn l online 3ando 
                   players_sockets.lastElement().statePlayer(player);//sets the playername in his socket 
                   
                   
                   System.out.println("NumOfPlayers = "+players_sockets.size());
                   System.out.println("player added and is "+players_sockets.lastElement().getPlayer());
                    
                   opponent= receiveOn.readLine();
                   
                   
                   System.out.println("opponent is"+opponent);
                   
                   
                    for(CustomizedClientSocket p :players_sockets)
                    { /*checks for opponent*/
                           System.out.println("Entered the loop");
                           System.out.println(p.getPlayer());
                         if (opponent.equals(p.getPlayer()))
                           {
                      System.out.println("Found Ahmed");
                      DataInputStream rev =new DataInputStream(p.getInputStream());
                      PrintStream sen = new PrintStream(p.getOutputStream());
                      String msg =receiveOn.readLine();//dh l mfrod l goz2 l hayt7t feh l X aw O 
                      sen.println(msg);
                       } 
                } 
                    
                    
             System.out.println("No such  a player ");
              sendOn.println("No such a player, wanna invite them ??");
              
              
           } catch (IOException ex) {
                Logger.getLogger(GameServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        }
    }
    public static void main(String[] args) {
       Application.launch(args);
   
    }
}