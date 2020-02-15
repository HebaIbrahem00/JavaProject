package domain;

import javafx.beans.property.*;
import java.sql.Date;
 
public class User {
    //Declare user Table Columns
    private int userID;
    private String userName;
    private String password;
    private String email;
    private int userStatus;

    //constructor
    public User(int userID, String userName, String password, String email, int userStatus) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.userStatus = userStatus;
    }
 
    //setter methods
    public void setUserID(int userID){
        this.userID = userID;
    }
 
    public void setUserName(String userName){
        this.userName = userName;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
 
    public void setEmail (String email){
        this.email= email;
    }
    
    public void setUserStatus (int userStatus){
        this.userStatus = userStatus;
    }
    
    //getter methods
    public int getUserID() {
        return this.userID;
    }
       
    public String getUserName () {
        return this.userName;
    }
    
    public String getPassword () {
        return this.password;
    }
    
    public String getEmail () {
        return this.email;
    }

    public int getUserStatus() {
        return this.userStatus;
    }
}