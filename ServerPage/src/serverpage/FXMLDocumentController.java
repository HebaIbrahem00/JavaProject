/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverpage;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

/**
 *
 * @author lamis
 */
public class FXMLDocumentController implements Initializable {
    public Text OnClients;
    @FXML
    private Label label;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    //function of switch server on button,, OnClients is the number of clients online
    @FXML
    private void ServerOnPressed(ActionEvent event) {
        //System.out.println("You clicked me server on");
        //OnClients.setText("5");
    }
    //function of switch server off button
    @FXML
    private void ServerOffPressed(ActionEvent event) {
        //System.out.println("You clicked me server off");
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
