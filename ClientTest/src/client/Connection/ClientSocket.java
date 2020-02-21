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

    static String userName;
    static String pass;
    static String email;
    public static ClientSocket CLIENT;
    static public int port;
    public String ipAdress;

    public static DataInputStream fromServer;
    public static PrintStream toServer;

    // Client Socket Constructor
    public ClientSocket(String ipAddress, int port) throws UnknownHostException, IOException {
        super(ipAddress, port);
        toServer = new PrintStream(this.getOutputStream());
        fromServer = new DataInputStream(this.getInputStream());
    }

    static public void initSocket(String ip, int port) throws IOException {
        CLIENT = new ClientSocket(ip, port);
    }

    // this Function is used to send recieve login data from the client for authentication
    public static String sendLoginData(String userName, String pass) throws IOException {
        ClientSocket.userName = userName;
        ClientSocket.pass = pass;
        System.out.println(ClientSocket.userName +  ClientSocket.pass);
        toServer.println( ClientSocket.userName);
        toServer.println(ClientSocket.pass);

        String authenticationStatus = fromServer.readLine();

        return authenticationStatus;
    }
    
     public static void sendSignUpData(String userName, String pass,String email) throws IOException {
        ClientSocket.userName = userName;
        ClientSocket.pass = pass;
        ClientSocket.email=email;
        System.out.println(ClientSocket.userName +  ClientSocket.pass);
        toServer.println( ClientSocket.userName);
        toServer.println(ClientSocket.pass);
        toServer.println(ClientSocket.email);
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
}
