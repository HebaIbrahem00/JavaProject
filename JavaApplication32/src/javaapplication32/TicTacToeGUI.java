package javaapplication32;

import static java.awt.SystemColor.text;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.StageStyle;

/**
 *
 * @author lamis
 */
public class TicTacToeGUI extends Application {

    boolean GameOver = false;
    TicTacToeAI tictac = new TicTacToeAI();
    Tile[] tiles = new Tile[9];
    Pane root = new Pane();
    int tilenumber = 1;

    private Parent createParent() {
        //process of creating the game board
        int k = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Tile tile = new Tile();
                tile.setTranslateX(j * 150);
                tile.setTranslateY(i * 150);
                tiles[k] = tile;
                root.getChildren().add(tile);
                k++;
            }
        }
        return root;
    }

    @Override
    public void start(Stage primaryStage) {
        StackPane bg = new StackPane();
        bg.setPadding(new Insets(10, 10, 10, 10));
        root.setId("pane");
        bg.setId("background");
        primaryStage.setTitle("TicTacToe");
        bg.getChildren().addAll(createParent());
        Scene scene = new Scene(bg, 600, 600);
        scene.getStylesheets().addAll(this.getClass().getResource("TicTacToeBoardCSS.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private class Tile extends StackPane {

        Text text = new Text();
        private int ID = tilenumber;

        public Tile() {
            tilenumber++;
            Rectangle rect = new Rectangle(150, 150, Color.web("rgba(255,0,0,0.2)"));
            rect.setStroke(Color.BLACK);
            rect.setStrokeWidth(7);
            text.setFont(Font.font(72));
            setAlignment(Pos.CENTER);
            //whenever any tile is chosen by the player
            setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.PRIMARY) {
                    //first check wether the tile is empty or the game is still on
                    if (GameOver == false && tictac.checkPlayerPos(ID)) {
                        text.setText("X");
                        //check if the player won so the cpu will play or not
                        String result = tictac.checkWinner();
                        if (result == "") {
                            int pos = tictac.CpuTurn() - 1;
                            tiles[pos].text.setText("O");
                            result = tictac.checkWinner();
                            if(result != "" )
                            {
                                GameOver = true;
                                System.out.println(result);
                            }
                        } else {
                            GameOver = true;
                            System.out.println(result);
                        }
                    }
                }
            });
            getChildren().addAll(rect, text);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
