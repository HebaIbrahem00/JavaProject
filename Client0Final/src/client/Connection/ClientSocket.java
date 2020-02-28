package client.Connection;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.*;

/**
 * @author BOB
 */
public class ClientSocket {

    public static BufferedReader fromServer;
    public static PrintWriter toServer;
    public static Socket client;

    static public void initSocket(String ip, int port) throws IOException {
        client = new Socket(ip, port);
        toServer = new PrintWriter(client.getOutputStream(), true);
        fromServer = new BufferedReader(new InputStreamReader(client.getInputStream(), "UTF-8"));
    }

    // this Function is used to send recieve login data from the client for authentication
    public static String sendLoginData(String userName, String password) throws IOException {

        ClientSocket.toServer.println(Protocol.SIGNIN);
        toServer.println(userName);
        toServer.println(password);
        String authenticationStatus = fromServer.readLine();

        return authenticationStatus;
    }

    // this Function is used to send recieve sign up data from the client for authentication
    public static String sendSignUpData(String userName, String email, String password) throws IOException {
        ClientSocket.toServer.println(Protocol.SIGNUP);
        toServer.println(userName);
        toServer.println(email);
        toServer.println(password);
        String isDataValid = fromServer.readLine();
        System.out.println("sendSignUpData");
        return isDataValid;
    }

    // this Function is used to send recieve login data from the client for authentication
    public static String retriveOnlineUsers() throws IOException {
        ClientSocket.toServer.println(Protocol.SHOWUSERS);
        String onlineUsers = fromServer.readLine();
        return onlineUsers;
    }

    public static void invitation(String opponent) {
        toServer.println(opponent);
    }

}
