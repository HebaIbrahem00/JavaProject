/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.view;

/**
 *
 * @author elkholy
 */
import client.Connection.Protocol;
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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Iterator;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class TicTacGUI {

    private PrintWriter out;
    private BufferedReader in;
    private Board board;
    private String me;
    private Stage stage;
    private Scene sc;
    private Button[] buttons = new Button[9];
    private Socket socket;
    OnlineTicTacController myFXML = new OnlineTicTacController();

    public TicTacGUI(String host, int port, String me, Stage stage, Scene sc) throws IOException {
        this.socket = new Socket(host, port);
        this.out = new PrintWriter(socket.getOutputStream(), true);
        this.in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
        this.board = new Board();
        this.me = me;
        System.out.println(me);
        this.stage = stage;
        this.sc = sc;
    }

    public void run() throws IOException {
        System.out.println("RUNNNNN"); //TODO
         
        Parent parent = FXMLLoader.load(getClass().getResource("OnlineTicTac.fxml"));
        Label winloselabel = (Label) parent.lookup("#winloselabel");
        Scene se = new Scene(parent);
        buttons[0] = (Button) se.lookup("#b1");
        buttons[1] = (Button) se.lookup("#b2");
        buttons[2] = (Button) se.lookup("#b3");
        buttons[3] = (Button) se.lookup("#b4");
        buttons[4] = (Button) se.lookup("#b5");
        buttons[5] = (Button) se.lookup("#b6");
        buttons[6] = (Button) se.lookup("#b7");
        buttons[7] = (Button) se.lookup("#b8");
        buttons[8] = (Button) se.lookup("#b9");

        buttons[0].setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("b1");
                if (board.isValidMove(0, 0)) {
                    board.makeMove(0, 0, me);
                    out.println(Protocol.MAKE_MOVE);
                    out.println(0 + " " + 0);
                    System.out.println(me);
                    buttons[0].setText(me);
                    winloselabel.setText("Waiting for opponent. . .");
                    parent.setDisable(true);
                }
            }

        });
        buttons[1].setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("b1");
                if (board.isValidMove(0, 1)) {
                    board.makeMove(0, 1, me);
                    out.println(Protocol.MAKE_MOVE);
                    out.println(1 + " " + 0);
                    System.out.println(me);
                    buttons[1].setText(me);
                    winloselabel.setText("Waiting for opponent. . .");
                    parent.setDisable(true);
                }
            }

        });
        buttons[2].setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("b1");
                if (board.isValidMove(0, 2)) {
                    board.makeMove(0, 2, me);
                    out.println(Protocol.MAKE_MOVE);
                    out.println(2 + " " + 0);
                    System.out.println(me);
                    buttons[2].setText(me);
                    winloselabel.setText("Waiting for opponent. . .");
                    parent.setDisable(true);
                }
            }

        });
        buttons[3].setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("b1");
                if (board.isValidMove(1, 0)) {
                    board.makeMove(1, 0, me);
                    out.println(Protocol.MAKE_MOVE);
                    out.println(0 + " " + 1);
                    System.out.println(me);
                    buttons[3].setText(me);
                    //   myFXML.playerXO(me,out);
                    winloselabel.setText("Waiting for opponent. . .");
                    parent.setDisable(true);
                }
            }

        });
        buttons[4].setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("b1");
                if (board.isValidMove(1, 1)) {
                    board.makeMove(1, 1, me);
                    out.println(Protocol.MAKE_MOVE);
                    out.println(1 + " " + 1);
                    System.out.println(me);
                    buttons[4].setText(me);
                    //   myFXML.playerXO(me,out);
                    winloselabel.setText("Waiting for opponent. . .");
                    parent.setDisable(true);
                }
            }

        });
        buttons[5].setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("b1");
                if (board.isValidMove(1, 2)) {
                    board.makeMove(1, 2, me);
                    out.println(Protocol.MAKE_MOVE);
                    out.println(2 + " " + 1);
                    System.out.println(me);
                    buttons[5].setText(me);
                    winloselabel.setText("Waiting for opponent. . .");
                    parent.setDisable(true);
                }
            }

        });
        buttons[6].setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("b1");
                if (board.isValidMove(2, 0)) {
                    board.makeMove(2, 0, me);
                    out.println(Protocol.MAKE_MOVE);
                    out.println(0 + " " + 2);
                    System.out.println(me);
                    buttons[6].setText(me);
                    winloselabel.setText("Waiting for opponent. . .");
                    parent.setDisable(true);
                }
            }

        });
        buttons[7].setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("b1");
                if (board.isValidMove(2, 1)) {
                    board.makeMove(2, 1, me);
                    out.println(Protocol.MAKE_MOVE);
                    out.println(1 + " " + 2);
                    System.out.println(me);
                    buttons[7].setText(me);
                    winloselabel.setText("Waiting for opponent. . .");
                    parent.setDisable(true);
                }
            }

        });
        buttons[8].setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("b1");
                if (board.isValidMove(2, 2)) {
                    board.makeMove(2, 2, me);
                    out.println(Protocol.MAKE_MOVE);
                    out.println(2 + " " + 2);
                    System.out.println(me);
                    buttons[8].setText(me);
                    winloselabel.setText("Waiting for opponent. . .");
                    parent.setDisable(true);
                }
            }

        });
        
        stage.setScene(se);

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
                                System.out.print(l);
                                String p;
                                if (me.equals("X")) {
                                    p = "O";
                                } else {
                                    p = "X";
                                }
                                System.out.print(l[0]);
                                System.out.print(Integer.parseInt(l[0]));
                                System.out.print(l[1]);
                                System.out.print(Integer.parseInt(l[1]));
                                System.out.print(p);
                                board.makeMove(Integer.parseInt(l[0]), Integer.parseInt(l[1]), p);
                                Platform.runLater(() -> refresh(Integer.parseInt(l[0]), Integer.parseInt(l[1])));
                                parent.setDisable(false);
                                Platform.runLater(() -> winloselabel.setText("Your turn. . ."));
                                System.in.read(new byte[System.in.available()]); // Clears System.in
                                System.out.println("DONE WITH  MOVE");
                                break;
                            case Protocol.GAME_WON:
                                Platform.runLater(() -> winloselabel.setText("You won!"));
                                parent.setDisable(true);
                                break;
                            case Protocol.GAME_LOST:
                                in.close();
                                socket.close();
                                parent.setDisable(true);
                                Platform.runLater(() -> winloselabel.setText("You lost :("));
                                break;
                            case Protocol.GAME_TIED:
                                Platform.runLater(() -> winloselabel.setText("It was a tie"));
                                parent.setDisable(true);
                                break;
                        }
                    }
                } catch (IOException e) {
                    System.out.println("game ended");
                }
            }
        };

        Thread t = new Thread(r);
        t.start();
    }

    private void refresh(int row, int col) {
        buttons[(col * 3) + row].setText(this.board.getTile(row, col));
        // buttons[0].setText("o");

    }
}
