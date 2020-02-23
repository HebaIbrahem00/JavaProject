/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlineusers;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author lamis
 */
public class Users {
    private SimpleStringProperty username;
    private SimpleStringProperty email;
    private SimpleStringProperty status;
    private SimpleStringProperty score;
    
    Users(String username, String email, String status, String score){
        this.username = new SimpleStringProperty(username);
        this.email = new SimpleStringProperty(email);
        this.status = new SimpleStringProperty(status);
        this.score = new SimpleStringProperty(score);
    }

    public SimpleStringProperty getUsername() {
        return username;
    }

    public void setUsername(SimpleStringProperty username) {
        this.username = username;
    }

    public SimpleStringProperty getEmail() {
        return email;
    }

    public void setEmail(SimpleStringProperty email) {
        this.email = email;
    }

    public SimpleStringProperty getStatus() {
        return status;
    }

    public void setStatus(SimpleStringProperty status) {
        this.status = status;
    }

    public SimpleStringProperty getScore() {
        return score;
    }

    public void setScore(SimpleStringProperty score) {
        this.score = score;
    }
    
}
