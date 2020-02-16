/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ComputerVsPlayer;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
//import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 *
 * @author pc
 */
public class TicTacToeGame extends Application {
         //string carry X or O
        final String[] s = new String[9];
        //the text that will be added to the Grid wether X or O 
        final Text[] txt = new Text[9];
        //carries the score
        Label score = new Label("Score:");
    @Override
    public void start(Stage primaryStage) {
        //Label score = new Label("Score:");
        //the board of X and O
        GridPane grid = new GridPane();
        //the pane of the scene
        StackPane root = new StackPane();
        //variable carries the score of the player
        int numscore = 50;
        int counter = 0;
        //int colnum = 3;
        //int rownum = 3;
        //link the score label to it's part in css
        score.setId("score");
        //color of the score label
        score.setTextFill(Color.web("#8EBDDB"));
        //link the grid to it's part in css 
        grid.setId("grid");
        //set constraint number of columns 3
        ColumnConstraints column2 = new ColumnConstraints(3);
        //set constraint number of rows 3
        RowConstraints row = new RowConstraints(3);
        //create txt nodes and link it to it's part in css
        for (int i = 0; i < txt.length; i++) {
            txt[i] = new Text("                                                                         ");
            txt[i].setId("text");
            txt[i].setX(20);
            txt[i].setY(180);
            txt[i].setTextAlignment(TextAlignment.CENTER);
        }
        //put the text in it's cell
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid.add(txt[counter], j, i);
                counter++;
            }
        }
        /*grid.setOnMouseClicked((MouseEvent event) -> {
            System.out.println("dhsydsuy");
            txt[0].setText("Score:"+numscore);
            Node source = (Node)event.getSource() ;
            Integer colIndex = GridPane.getColumnIndex(source);
            Integer rowIndex = GridPane.getRowIndex(source);
            System.out.println(event);
            System.out.println(colIndex);
        });*/
        //assign an action evwnt on each text
        txt[0].setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
             txt[0].setText(s[0]);
             //score.setText("Score:"+numscore);
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }          
        });
        
        txt[1].setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
             txt[1].setText(s[1]);
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }          
        });
        txt[2].setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
             txt[2].setText(s[2]);
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }          
        });
        txt[3].setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
             txt[3].setText(s[3]);
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }          
        });
        txt[4].setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
             txt[4].setText(s[4]);
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }          
        });
        txt[5].setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
             txt[5].setText(s[5]);
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }          
        });
        txt[6].setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
             txt[6].setText(s[6]);
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }          
        });
        txt[7].setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
             txt[7].setText(s[7]);
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }          
        });
        txt[8].setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
             txt[8].setText(s[8]);
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }          
        });
        //set the constaraint to make the height 180
        row.setPercentHeight(180);
        //set the constaraint to make the width 20
        column2.setPercentWidth(20);
        //add the constaraints to each column
        grid.getColumnConstraints().addAll(column2, column2, column2);
        //set the constraint to each column
        grid.getRowConstraints().addAll(row, row, row);
        //make the borders of the grid visible
        grid.setGridLinesVisible(true);
        //make padding to the grid
        grid.setPadding(new Insets(10, 10, 10, 10));
        //link the stack pane to it's part incss
        root.setId("pane");
        //adding chilederen to the root pane
        root.getChildren().addAll(score, grid);
        //add the score to top_right position
        StackPane.setAlignment(score, Pos.TOP_RIGHT);
       // StackPane.setAlignment(grid, Pos.CENTER_LEFT);
        //set the dimensions of the scene to 800 and 500
        Scene scene = new Scene(root, 800, 500);
        //link the scene to the css flle
        scene.getStylesheets().addAll(this.getClass().getResource("style_1.css").toExternalForm());
        //set title of the stage
        primaryStage.setTitle("XO GAME");
        //add the scene to the stage
        primaryStage.setScene(scene);
        //show the stage
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
