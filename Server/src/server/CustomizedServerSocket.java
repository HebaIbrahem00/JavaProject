/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.net.SocketImpl ;

import java.lang.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.*;

/**
 *
 * @author BOB
 */
public class CustomizedServerSocket extends ServerSocket{

    public CustomizedServerSocket(int port) throws IOException {
        super(port);
    }
    public Socket accept() throws IOException {
        Socket s;
        s = new CustomizedClientSocket((SocketImpl) null);
        implAccept(s);
        
        return s;
    }
}