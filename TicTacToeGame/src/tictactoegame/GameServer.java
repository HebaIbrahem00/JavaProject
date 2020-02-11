/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectserver;


import java.io.IOException;
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
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

/**
 *
 * @author BOB
 */
public class GameServer extends Application {
    
    public void init() throws IOException
    {
  
    }
    
    
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

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    
    public class Background extends Thread
    {
        
        ServerSocket server = new ServerSocket(9000);//creating a socket and binding it to a port with loopback ip
        Vector  clients_sockets= new Vector();
        
        public Background() throws IOException {
        
     }

        @Override
        public void run() {
            super.run();
            System.out.println("server is listening");
             while(true){
                 try {
                     clients_sockets.add(server.accept());
                     
                     
                 } catch (IOException ex) {
                     Logger.getLogger(GameServer.class.getName()).log(Level.SEVERE, null, ex);
                 }
          }
          
        
    }}
    public static void main(String[] args) {
       
           Thread curr= Thread.currentThread();
                System.out.println(curr);
                  int noOfThreads= Thread.activeCount();
                     System.out.println(noOfThreads);
            launch(args);
               System.out.println(noOfThreads);
    }
}
