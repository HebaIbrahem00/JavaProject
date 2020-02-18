package server;

import java.net.SocketImpl;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.UnknownHostException;
import java.net.*;

/**
 *
 * @author BOB
 */
public class CustomizedClientSocket extends Socket {

    String Player;
    String pass;
    DataInputStream fromServer;
    PrintStream toServer;

    public CustomizedClientSocket(String ipAddress, int port, String username, String _pass) throws UnknownHostException, IOException {

        super(ipAddress, port);

        Player = username;
        pass = _pass;
        this.toServer = new PrintStream(this.getOutputStream());
        this.fromServer = new DataInputStream(this.getInputStream());

        this.toServer.println(this.getPlayer());
        this.toServer.println(this.getPass());
    }

    public CustomizedClientSocket(SocketImpl x) {
        super();
    }

    public String getPlayer() {
        return this.Player;
    }

    public void statePlayer(String x) {
        this.Player = x;
    }

    public String getPass() {
        return this.pass;
    }

    public void setPass(String _pass) {
        this.pass = _pass;
    }

}
