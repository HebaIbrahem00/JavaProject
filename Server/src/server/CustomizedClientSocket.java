/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.BufferedReader;
import java.net.SocketImpl;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.UnknownHostException;
import java.net.*;

/**
 *
 * @author BOB
 */
public class CustomizedClientSocket extends Socket {

    String Player;
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

    public String getPlayer() {
        return this.Player;
    }

    public void statePlayer(String x) {
        this.Player = x;
    }

   

    }

