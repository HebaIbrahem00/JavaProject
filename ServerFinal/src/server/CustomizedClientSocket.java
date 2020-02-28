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

    String userName;
     BufferedReader fromServer;
    PrintWriter toServer;
 

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

    public void setUserName(String _userName) {
        this.userName = _userName;
    }


}
