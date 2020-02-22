/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoegame;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

/**
 *
 * @author lamis
 */
public class FXMLDocumentController implements Initializable {
//   TicTacToeAI tictac = new TicTacToeAI();

//    Board board = new Board();
//    boolean GameOver = false;
//    private static PrintWriter out;
//    private BufferedReader in;
//    private static String me;
//    private Socket socket;
//
//    public Button b1;
//    public Button b2;
//    public Button b3;
//    public Button b4;
//    public Button b5;
//    public Button b6;
//    public Button b7;
//    public Button b8;
//    public Button b9;
//    public Label winloselabel;
//    public Button[] buttons = new Button[9];
//
//    public void init() {
//        buttons[0] = b1;
//        buttons[1] = b2;
//        buttons[2] = b3;
//        buttons[3] = b4;
//        buttons[4] = b5;
//        buttons[5] = b6;
//        buttons[6] = b7;
//        buttons[7] = b8;
//        buttons[8] = b9;
//    }
//    public void playerXO(String me,PrintWriter out){
//    this.me=me;
//    this.out=out;
//    }
//    @FXML
//    private Label label;
//    
//    @FXML
//    private void handleButtonAction(ActionEvent event) {
//        System.out.println("You clicked me!");
//        label.setText("Hello World!");
//    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
//    boolean []setflag={true,true,true,true,true,true,true,true,true};
//    @FXML
//    private void b1pressed(ActionEvent event) {
//        System.out.println("button 1");
//    //     int[] data = (int[]) b1.getUserData();
//    //     System.out.print("Data 0 1= " + data[0] + data[0]);
//    //    if(setflag[0]==true){}
//         if (board.isValidMove(0,0)) {
//                        board.makeMove(0,0, me);
//                        out.println(Protocol.MAKE_MOVE);
//                        out.println(0 + " " + 0);
//                        b1.setText(me);
//                         
//                    }
//    }
//    
//
//    @FXML
//    private void b2pressed(ActionEvent event) {
//        System.out.println("button 2");
//         
//    }
//
//    @FXML
//    private void b3pressed(ActionEvent event
//    ) {
//                System.out.println("button 3");
//
//    }
//
//    @FXML
//    private void b4pressed(ActionEvent event
//    ) {
//                System.out.println("button 4");
// 
//    }
//
//    @FXML
//    private void b5pressed(ActionEvent event
//    ) {
//                System.out.println("button 5");
//
//        
//    }
//
//    @FXML
//    private void b6pressed(ActionEvent event
//    ) {
//                System.out.println("button 6");
// 
//    }
//
//    @FXML
//    private void b7pressed(ActionEvent event
//    ) {
//                System.out.println("button 7");
// 
//
//    }
//
//    @FXML
//    private void b8pressed(ActionEvent event
//    ) {
//                System.out.println("button 8");
// 
//    }
//
//    @FXML
//    private void b9pressed(ActionEvent event
//    ) {
//                System.out.println("button 9");
//
//         
//    }
//
//    //when the replay button ispressed clear everything
//    @FXML
//    private void replayPressed(ActionEvent event) {
//        System.out.println("replay");
//        GameOver = false;
//        for (int i = 0; i < 9; i++) {
//            buttons[i].setText("");
//            winloselabel.setText("");
//        }
//    }
}
