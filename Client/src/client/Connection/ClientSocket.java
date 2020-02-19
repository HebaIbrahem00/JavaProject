package client.Connection;

import java.net.SocketImpl;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.UnknownHostException;
import java.net.*;
import java.util.HashMap;

/**
 *
 * @author BOB
 */
public class ClientSocket extends Socket {

    String userName;
    String pass;
    String email;
    String userStatus;   
    public DataInputStream fromServer;
    public PrintStream toServer;
    
    HashMap<Integer, String>MessageToServer;

    
    // Client Socket Constructor
    public ClientSocket(String ipAddress, int port) throws UnknownHostException, IOException {
        super(ipAddress, port);
        this.toServer = new PrintStream(this.getOutputStream());
        this.fromServer = new DataInputStream(this.getInputStream());
    }
    
    // this Function is used to send recieve login data from the client for authentication
    public String sendLoginData(String userName, String pass) throws UnknownHostException, IOException {
        this.userName = userName;
        this.pass = pass;
        
        this.toServer.println(userName);
        this.toServer.println(pass);
        
        String authenticationStatus = this.fromServer.readLine();
        
        return authenticationStatus;
    }
    
     public void sendSignUpData(String user_name,String password,String email){
        this.userName = user_name;
        this.pass = password;
        this.email = email;
        this.userStatus = "offline";
        
        this.toServer.println(userName);
        this.toServer.println(pass);
        this.toServer.println(this.email);
        this.toServer.println(userStatus);
    }

    public ClientSocket(SocketImpl x) {
        super();
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return this.pass;
    }

    public void setUserPass(String _pass) {
        this.pass = _pass;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
