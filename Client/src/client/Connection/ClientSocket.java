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

    public void toServer(String SIGNIN) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
