/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoegame;


import java.io.DataInputStream;
import java.io.IOException;
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
                    System.out.println("Server Closed successfully");
                } catch (IOException ex) {
                    System.out.println("Coudnt close??");
                    Logger.getLogger(GameServer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn); 
        Scene scene = new Scene(root, 300, 250);
       Thread curr= Thread.currentThread();
     //  int noOfThreads= Thread.activeCount(); 
       System.out.println(curr);
       //   System.out.println(noOfThreads);
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

   
    public class Background extends Thread
    {
        
        ServerSocket server = new ServerSocket(9000);//creating a socket and binding it to a port with loopback ip
        XO c;
        Socket client;
        public Background() throws IOException {}

        @Override
        public void run() {
            super.run();
            System.out.println("server is listening");
             while(true){
                 try {
                      
                     client= (Socket)c;
                     client = server.accept();
                     //c=client;//????
                     ClientHandlerThread x = new ClientHandlerThread(c);
                     System.out.println("a client connected");
                    
                     
                } catch (IOException ex) {
                     Logger.getLogger(GameServer.class.getName()).log(Level.SEVERE, null, ex);
                 }
          }
          
        
    }}
    
    public class ClientHandlerThread extends Thread
    
    {
    /*To open a thread for every client connects */
         DataInputStream receiveOn;
         PrintStream sendOn;
           Vector <XO> clients_sockets= new Vector();

        public ClientHandlerThread(XO c) throws IOException {
           clients_sockets.add(c);
           receiveOn= new DataInputStream(c.getInputStream());
           sendOn = new PrintStream(c.getOutputStream());
       
        }
        
        public void run()
        {
        while(true)
        {
            String opponent;
            try {
                opponent= receiveOn.readLine(); //dh mafrod ygely lma y click 3la asm  mn l online 3ando 
                    for(XO p :clients_sockets)
                    {
                    /* this loop shall iterate over client sockets and find the suitable one get his outputstream 
                      * and send to him the what ever
                      */
                         if (p.getPlayer()==opponent)
                           { 
                            DataInputStream rev =new DataInputStream(p.getInputStream());
                            PrintStream sen = new PrintStream(p.getOutputStream());
                            String msg =receiveOn.readLine();//dh l mfrod l goz2 l hayt7t feh l update bta3 l gui 
                            sen.println(msg);
                            }
                    System.out.println(opponent);
               
                } } catch (IOException ex) {
                Logger.getLogger(GameServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        }
         
         
    
    }
    public static void main(String[] args) {
       
           //Thread curr= Thread.currentThread();
                //System.out.println(curr);
                 // int noOfThreads= Thread.activeCount();
                //     System.out.println(noOfThreads);
       Application.launch(args);
              // System.out.println(noOfThreads);
    }
}
