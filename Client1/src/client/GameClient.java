/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;

/**
 *
 * @author omar
 */
public class GameClient extends Application{
CustomizedClientSocket clientSocket; 
  String name ="Heba";
   InvitationListen waitInvitation;
      private volatile boolean needed=true;
//dh mafrod haytbdl yb2a feh kol l scenes l abl l board

      @Override
    public void start(Stage s) throws IOException {
        
         clientSocket=  new CustomizedClientSocket("127.0.0.1",9000);//dh hayt7at f awl haga hat run fl client .jar
         signIn(clientSocket.toServer, name);
         
         clientSocket.toServer.println(Protocol.CHOOSEOPPONENT);//////////////////////////////////**********
          waitInvitation = new InvitationListen();
          waitInvitation.start();
        // Set up the button grid representing the tic tac toe board
        GridPane board = new GridPane();
        for (int i = 0; i < 3; i++) { //Constructing Board GUI
            for (int j = 0; j < 3; j++) {
                Button btn = new Button();
                btn.setStyle("-fx-border-color: Lightgray; -fx-text-fill: Gray; -fx-border-width: 1; -fx-background-color: transparent; -fx-font-size: 80; -fx-font-family: Monospaced;");
                btn.setMaxSize(100, 100);
                btn.setPrefSize(100,100);
                btn.setPadding(new Insets(0));
                btn.setUserData(new int[] {i, j});
                board.add(btn, i, j);
            }
        }
       
        
        
        final Group root = new Group(board);
        final Scene XOscene = new Scene(root, 300, 300, Color.WHITE);

     //   waitInvitation.stage=s;
       // waitInvitation.scene=XOscene;
        // start new game
        BorderPane spo = new BorderPane();
        spo.setStyle("-fx-background-color: transparent");
       
        
        TextField txtport = new TextField();
        //below we'll assume that stages of signup and/or sign in is done
        txtport.setOnAction(e -> { //This will be replaced by a click listener on one of the online list the server will sent
            try {
                
                System.out.println("choosing opponent");
            
                 String opponent = "opponent "+txtport.getText();
                 
                 System.out.println(opponent);
                 
                 clientSocket.toServer.println(opponent);
                 connect( "X", s, XOscene); //El X aw O mafrood l server y handle it , hwa elly yb3t l dh X w dh O, w hna tb2a variable 
                 
            } catch (IOException| NumberFormatException m) {
                System.out.println("something bad happened");
            }
        });
        
     
        Text st = new Text("Choose Opponent ");
        st.setFont(Font.font("Monospaced", 20));
        st.setFill(Color.GRAY);
        txtport.setStyle("-fx-border-width: 1; -fx-border-color: Gray; -fx-background-color: transparent;");
        VBox form = new VBox(st, txtport);
        spo.setCenter(form);

     
        BorderPane jpo = new BorderPane();
        jpo.setStyle("-fx-background-color: transparent");
        Text st2 = new Text("Join game on what port? ");
        st2.setFont(Font.font("Monospaced", 20));
        st2.setFill(Color.GRAY);
  
        final Scene startportscreen = new Scene(spo, 300, 300, Color.WHITE);
        final Scene joinportscreen  = new Scene(jpo, 300, 300, Color.WHITE);

        // Sets up the game initialization page
        Button start = new Button("Start a new game");
        start.setStyle("-fx-text-fill: Gray; -fx-border-width: 1; -fx-background-color: transparent; -fx-font-size: 21; -fx-font-family: Monospaced;");
        start.setPrefSize(300, 150);
        Button join = new Button("Join an existing game");
        join.setStyle("-fx-text-fill: Gray; -fx-border-width: 1; -fx-background-color: transparent; -fx-font-size: 21; -fx-font-family: Monospaced;");
        join.setPrefSize(300, 150);

        start.setOnMouseEntered(e -> start.setStyle("-fx-cursor: hand; -fx-underline: true; -fx-text-fill: Gray; -fx-border-width: 1; -fx-background-color: transparent; -fx-font-size: 21; -fx-font-family: Monospaced;"));
        start.setOnMouseExited(e -> start.setStyle("-fx-text-fill: Gray; -fx-border-width: 1; -fx-background-color: transparent; -fx-font-size: 21; -fx-font-family: Monospaced;"));

        join.setOnMouseEntered(e -> join.setStyle("-fx-cursor: hand; -fx-underline: true; -fx-text-fill: Gray; -fx-border-width: 1; -fx-background-color: transparent; -fx-font-size: 21; -fx-font-family: Monospaced;"));
        join.setOnMouseExited(e -> join.setStyle("-fx-text-fill: Gray; -fx-border-width: 1; -fx-background-color: transparent; -fx-font-size: 21; -fx-font-family: Monospaced;"));

        start.setOnMousePressed(e -> s.setScene(startportscreen));
        join.setOnMousePressed(e -> s.setScene(joinportscreen));

        VBox options = new VBox(start, join);
        Group ls = new Group(options);
        final Scene loginscreen = new Scene(ls, 300, 300, Color.WHITE);

        s.setTitle("Tic Tac Toe Heba");
        s.getIcons().add(new Image("file:icons/icon1.png"));
        s.setScene(loginscreen);
        s.show();
    }
    public class InvitationListen extends Thread{
            Scene scene;
            Stage stage;
            String listen="";
              
        public InvitationListen() 
        {  // start();
        }
        @Override
            public void run() {
                while(needed){
            try {
              
                listen = clientSocket.fromServer.readLine();
                
            } catch (IOException ex) {
                Logger.getLogger(GameClient.class.getName()).log(Level.SEVERE, null, ex);
            }
                if(listen.startsWith(Protocol.INVITATION))
                {
                    String[] invite=listen.split(":");
                    String fromWho=invite[1];
                    
                    Platform.runLater(() ->{
                        try {
                            invitationDetails("Game Invitation","!!",fromWho+" wanna play, do u?",stage,scene,fromWho);
                        } catch (IOException ex) {
                            Logger.getLogger(GameClient.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });
             
                }
                }
            }
              
     public void invitationDetails(String title,String header, String content,Stage s,Scene XOscene,String from) throws IOException
         {
     
             final Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
             alert.setTitle(title);
             alert.setHeaderText(header);
             alert.setContentText(content);   
             alert.getButtonTypes().clear();
   
             ButtonType approve = new ButtonType("Play");
             ButtonType decline = new ButtonType("Maybe Later");
             alert.getButtonTypes().addAll(approve,decline);
             Optional<ButtonType> result = alert.showAndWait();
 
           if (result.get() == approve){
               

                String msgToserver="resultfrominvitation"+":"+Protocol.ACCEPTED+":"+name+":"+from ;
                 System.out.println("approval sent to server");
                 clientSocket.toServer.println(msgToserver);
                 connect("O", s, XOscene);/////m7tagen n call l connect bara el thread
                
                 
             }
          else{
              clientSocket.toServer.println("don't");
             System.out.println("decline sent to server");
    }
    
         }  
     }

   

    private  void connect(  String XO, Stage stage, Scene sc) throws IOException {
       
        TicTacGUI client = new TicTacGUI(clientSocket, XO, stage, sc); //3ayze 
        client.runLogic();////.//////////here needs to be changed
    }
    private static void signIn( PrintWriter toServer, String username)
    { 
        toServer.println(Protocol.SIGNIN); //we dy mfrod awl ma yb2a fl scener bta3 l login asln(2bl l listener)
        toServer.println(username);//dy hatetshal mn hna lel event listener
    }

    @Override
    public void stop() {
        System.exit(0);
    }
    

    public static void main(String[] args) throws IOException 
    {
        Application.launch(args);
    }


}
