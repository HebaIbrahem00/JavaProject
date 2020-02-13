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

public class Server {
    
    private ServerSocket server;
    private Board board;

    Server(int port) throws IOException{
        this.server = new ServerSocket(port);
        this.board = new Board();
    }

    public void run() {
        try {
            Socket client = server.accept();
            PrintWriter out =
                    new PrintWriter(client.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(client.getInputStream()));
            System.out.println("Waiting for player two...");

            Socket client2 = server.accept();
            PrintWriter out2 =
                    new PrintWriter(client2.getOutputStream(), true);
            BufferedReader in2 = new BufferedReader(
                    new InputStreamReader(client2.getInputStream()));
            System.out.println("Player two connected");

            // Server sends message to the clients to confirm their connection
            out.println(Protocol.CONNECTED);
            out2.println(Protocol.CONNECTED);
}
