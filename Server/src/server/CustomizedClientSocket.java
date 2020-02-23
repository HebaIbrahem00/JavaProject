package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketImpl;
import java.net.UnknownHostException;
import server.Protocol;

public class CustomizedClientSocket extends Socket {

    static String userName;
    static String pass;
    static String email;
    static BufferedReader fromServer;
    static PrintWriter toServer;
    static public int port;
    public String ipAdress;

    public CustomizedClientSocket(String address, int port) throws UnknownHostException, IOException {

        super(address, port);
        this.toServer = new PrintWriter(this.getOutputStream(), true);
        this.fromServer = new BufferedReader(new InputStreamReader(this.getInputStream(), "UTF-8"));

    }

    public CustomizedClientSocket(SocketImpl x) {
        super();

    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    static public void initSocket(String ip, int port) throws IOException {
        // Socket client = new Socket(ip, port);
        CustomizedClientSocket client = new CustomizedClientSocket(ip, port);

    }

    public static String sendLoginData(String userName, String pass) throws IOException {
        //ClientSocket.toServer.println(Protocol.SIGNIN);
        CustomizedClientSocket.toServer.println(Protocol.SIGNIN);
        userName = userName;
        pass = pass;

        toServer.println(userName);
        toServer.println(pass);
        String authenticationStatus = fromServer.readLine();

        return authenticationStatus;
    }

    public static void sendSignUpData(String userName, String pass, String email) throws IOException {
        CustomizedClientSocket.userName = userName;
        CustomizedClientSocket.pass = pass;
        CustomizedClientSocket.email = email;

        System.out.println(CustomizedClientSocket.userName + CustomizedClientSocket.pass);

        toServer.println(CustomizedClientSocket.userName);
        toServer.println(CustomizedClientSocket.pass);
        toServer.println(CustomizedClientSocket.email);
    }

    public String getUserPass() {
        return this.pass;
    }

    public void setUserPass(String _pass) {
        this.pass = _pass;
    }

}
