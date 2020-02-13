/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoegame;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 *
 * @author pc
 */
public class FXMLDocumentController implements Initializable {
    Scanner sc = new Scanner(System.in);
    String x = sc.nextLine();
    int scorenum=50;
       @FXML
    private Text text1;

    @FXML
    private Text text2;

    @FXML
    private Text text3;

    @FXML
    private Text text4;

    @FXML
    private Text text5;

    @FXML
    private Text text6;

    @FXML
    private Text text8;

    @FXML
    private Text text9;

    @FXML
    private Text text7;
  
    @FXML
    private Label score;
    
     @FXML
    void score(MouseEvent event) {
        score.setText("Score:"+scorenum);
    }

    @FXML
    void test1(MouseEvent event,String s) {
          text1.setText(s);
          
    }

    @FXML
    void test2(MouseEvent event) {
         text2.setText(x);
    }

    @FXML
    void test3(MouseEvent event) {
        text3.setText(x);
    }

    @FXML
    void test4(MouseEvent event) {
         text4.setText(x);
    }

    @FXML
    void test5(MouseEvent event) {
         text5.setText(x);
    }

    @FXML
    void test6(MouseEvent event) {
         text6.setText(x);
    }

    @FXML
    void test7(MouseEvent event) {
          text7.setText(x);
    }

    @FXML
    void test8(MouseEvent event) {
         text8.setText(x);
    }

    @FXML
    void test9(MouseEvent event) {
         text9.setText(x);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }       
}
