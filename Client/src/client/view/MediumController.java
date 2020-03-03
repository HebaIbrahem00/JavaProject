/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import static client.view.Main.mystage;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 *
 * @author pc
 */
public class MediumController implements Initializable {

    MediumLevel tictac = new MediumLevel();
    boolean GameOver = false;

    public Button b1;
    public Button b2;
    public Button b3;
    public Button b4;
    public Button b5;
    public Button b6;
    public Button b7;
    public Button b8;
    public Button b9;
    public Label winloselabel;
    public Button[] buttons = new Button[9];

    public void init() {
        buttons[0] = b1;
        buttons[1] = b2;
        buttons[2] = b3;
        buttons[3] = b4;
        buttons[4] = b5;
        buttons[5] = b6;
        buttons[6] = b7;
        buttons[7] = b8;
        buttons[8] = b9;
    }
    @FXML
    private Label label;

    @FXML
    private void handleButtonAction(ActionEvent event) {
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void b1pressed(ActionEvent event) {
        //first check whether the game is over or the position chosenis not available
        if (GameOver == false && tictac.checkPlayerPos(1)) {
            b1.setText("X");
            //check if the player won so the cpu will play or not
            String result = tictac.checkWinner();
            if (result == "") {
                //the game is not over yet, allow the computer to play its turn
                int pos = tictac.CpuTurn();
                init();
                for (int i = 0; i <= 9; i++) {
                    if (i == pos) {
                        buttons[i - 1].setText("O");
                    }
                }
                result = tictac.checkWinner();
                if (result != "") {
                    GameOver = true;
                    winloselabel.setText(result);

                }
            } else {
                GameOver = true;
                winloselabel.setText(result);
            }
        }
    }

    @FXML
    private void b2pressed(ActionEvent event) {
        if (GameOver == false && tictac.checkPlayerPos(2)) {
            b2.setText("X");
            //check if the player won so the cpu will play or not
            String result = tictac.checkWinner();
            if (result == "") {
                int pos = tictac.CpuTurn();
                init();
                for (int i = 0; i <= 9; i++) {
                    if (i == pos) {
                        buttons[i - 1].setText("O");
                    }
                }
                result = tictac.checkWinner();
                if (result != "") {
                    GameOver = true;
                    winloselabel.setText(result);
                }
            } else {
                GameOver = true;
                winloselabel.setText(result);
            }
        }

    }

    @FXML
    private void b3pressed(ActionEvent event) {
        if (GameOver == false && tictac.checkPlayerPos(3)) {
            b3.setText("X");
            //check if the player won so the cpu will play or not
            String result = tictac.checkWinner();
            if (result == "") {
                int pos = tictac.CpuTurn();
                init();
                for (int i = 0; i <= 9; i++) {
                    if (i == pos) {
                        buttons[i - 1].setText("O");
                    }
                }
                result = tictac.checkWinner();
                if (result != "") {
                    GameOver = true;
                    winloselabel.setText(result);
                }
            } else {
                GameOver = true;
                winloselabel.setText(result);
            }
        }

    }

    @FXML
    private void b4pressed(ActionEvent event) {
        if (GameOver == false && tictac.checkPlayerPos(4)) {
            b4.setText("X");
            //check if the player won so the cpu will play or not
            String result = tictac.checkWinner();
            if (result == "") {
                int pos = tictac.CpuTurn();
                init();
                for (int i = 0; i <= 9; i++) {
                    if (i == pos) {
                        buttons[i - 1].setText("O");
                    }
                }
                result = tictac.checkWinner();
                if (result != "") {
                    GameOver = true;
                    winloselabel.setText(result);
                }
            } else {
                GameOver = true;
                winloselabel.setText(result);
            }
        }

    }

    @FXML
    private void b5pressed(ActionEvent event) {
        if (GameOver == false && tictac.checkPlayerPos(5)) {
            b5.setText("X");
            //check if the player won so the cpu will play or not
            String result = tictac.checkWinner();
            if (result == "") {
                int pos = tictac.CpuTurn();
                init();
                for (int i = 0; i <= 9; i++) {
                    if (i == pos) {
                        buttons[i - 1].setText("O");
                    }
                }
                result = tictac.checkWinner();
                if (result != "") {
                    GameOver = true;
                    winloselabel.setText(result);
                }
            } else {
                GameOver = true;
                winloselabel.setText(result);
            }
        }

    }

    @FXML
    private void b6pressed(ActionEvent event) {
        if (GameOver == false && tictac.checkPlayerPos(6)) {
            b6.setText("X");
            //check if the player won so the cpu will play or not
            String result = tictac.checkWinner();
            if (result == "") {
                int pos = tictac.CpuTurn();
                init();
                for (int i = 0; i <= 9; i++) {
                    if (i == pos) {
                        buttons[i - 1].setText("O");
                    }
                }
                result = tictac.checkWinner();
                if (result != "") {
                    GameOver = true;
                    winloselabel.setText(result);
                }
            } else {
                GameOver = true;
                winloselabel.setText(result);
            }
        }

    }

    @FXML
    private void b7pressed(ActionEvent event) {
        if (GameOver == false && tictac.checkPlayerPos(7)) {
            b7.setText("X");
            //check if the player won so the cpu will play or not
            String result = tictac.checkWinner();
            if (result == "") {
                int pos = tictac.CpuTurn();
                init();
                for (int i = 0; i <= 9; i++) {
                    if (i == pos) {
                        buttons[i - 1].setText("O");
                    }
                }
                result = tictac.checkWinner();
                if (result != "") {
                    GameOver = true;
                    winloselabel.setText(result);
                }
            } else {
                GameOver = true;
                winloselabel.setText(result);
            }
        }

    }

    @FXML
    private void b8pressed(ActionEvent event) {
        if (GameOver == false && tictac.checkPlayerPos(8)) {
            b8.setText("X");
            //check if the player won so the cpu will play or not
            String result = tictac.checkWinner();
            if (result == "") {
                int pos = tictac.CpuTurn();
                init();
                for (int i = 0; i <= 9; i++) {
                    if (i == pos) {
                        buttons[i - 1].setText("O");
                    }
                }
                result = tictac.checkWinner();
                if (result != "") {
                    GameOver = true;
                    winloselabel.setText(result);
                }
            } else {
                GameOver = true;
                winloselabel.setText(result);
            }
        }

    }

    @FXML
    private void b9pressed(ActionEvent event) {
        if (GameOver == false && tictac.checkPlayerPos(9)) {
            b9.setText("X");
            //check if the player won so the cpu will play or not
            String result = tictac.checkWinner();
            if (result == "") {
                int pos = tictac.CpuTurn();
                init();
                for (int i = 0; i <= 9; i++) {
                    if (i == pos) {
                        buttons[i - 1].setText("O");
                    }
                }
                result = tictac.checkWinner();
                if (result != "") {
                    GameOver = true;
                    winloselabel.setText(result);
                }
            } else {
                GameOver = true;
                winloselabel.setText(result);
            }
        }

    }

    //when the replay button ispressed clear everything
    @FXML
    private void exitPressed(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("LevelsMenu.fxml"));
        Scene scene = new Scene(root);
        Main.mystage.setScene(scene);
    }

    @FXML
    private void replayPressed(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Medium.fxml"));
        Scene scene = new Scene(root);
        Main.mystage.setScene(scene);
    }
}
