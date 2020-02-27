/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;

/**
 *
 * @author lamis
 */
public class User {
    private SimpleStringProperty username;
    private SimpleStringProperty email;
    private SimpleStringProperty status;
    private SimpleStringProperty score;
    private Button Actionbtn;
    private SimpleStringProperty btn;
    
    User(String username, String email, String status, String score, String inviteBtn){
        this.username = new SimpleStringProperty(username);
        this.email = new SimpleStringProperty(email);
        this.status = new SimpleStringProperty(status);
        this.score = new SimpleStringProperty(score);
        this.Actionbtn = new Button(inviteBtn);
        
    }

    public String getUsername() {
        return username.get();
    }

    public Button getActionbtn() {
        return Actionbtn;
    }

    public void setActionbtn(Button Actionbtn) {
        this.Actionbtn = Actionbtn;
    }

//    public SimpleStringProperty getBtn() {
//        return btn;
//    }
//
//    public void setBtn(SimpleStringProperty btn) {
//        this.btn = btn;
//    }

    public void setUsername(SimpleStringProperty username) {
        this.username = username;
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(SimpleStringProperty email) {
        this.email = email;
    }

    public String getStatus() {
        return status.get();
    }

    public void setStatus(SimpleStringProperty status) {
        this.status = status;
    }

    public String getScore() {
        return score.get();
    }

    public void setScore(SimpleStringProperty score) {
        this.score = score;
    }
}
