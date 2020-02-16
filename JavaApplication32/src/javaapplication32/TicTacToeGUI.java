/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication32;

import static java.awt.SystemColor.text;
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
    TicTacToeAI tictac = new TicTacToeAI();
    Pane root = new Pane();

    private Parent createParent() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Tile tile = new Tile();
                tile.setTranslateX(j * 150);
                tile.setTranslateY(i * 150);
                root.getChildren().add(tile);
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
        Scene scene = new Scene(bg, 800, 500);    
        scene.getStylesheets().addAll(this.getClass().getResource("TicTacToeBoardCSS.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private boolean turnX = true;

    private class Tile extends StackPane {
        Text text = new Text();
        public Tile() {
            Rectangle rect = new Rectangle(150, 150, Color.web("rgba(255,0,0,0.2)"));
            rect.setStroke(Color.BLACK);
            rect.setStrokeWidth(7);
            text.setFont(Font.font(72));
            setAlignment(Pos.CENTER);
            setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.PRIMARY) {
                    if (!turnX) {
                        text.setText("O");
                        turnX = true;
                    } else {
                        text.setText("X");
                        turnX = false;
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
