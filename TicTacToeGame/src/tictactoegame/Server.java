/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoegame;

/**
 *
 * @author omar
 */

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server extends Thread{
    
     private ServerSocket server;
     private Board board;
    
     
       Server(int port) throws IOException{
        this.server = new ServerSocket(port);
        this.board = new Board();
    }
      
       public void run(){
       
         try {
             Socket client = server.accept();
             PrintWriter out = new PrintWriter(client.getOutputStream(), true);
               BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
               
         } catch (IOException ex) {
             Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
         }

       } 
     
}
