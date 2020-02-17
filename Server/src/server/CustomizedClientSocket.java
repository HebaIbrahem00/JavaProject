/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.net.SocketImpl ;

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
    
     String Player ;
     DataInputStream fromServer;
     PrintStream toServer;
     
     public CustomizedClientSocket(String address, int port,String x) throws UnknownHostException, IOException {
       super(address,port);
       Player=x;   
       this.toServer=  new PrintStream(this.getOutputStream());
       this.fromServer= new DataInputStream(this.getInputStream());
       this.toServer.println(this.getPlayer());
       
    }
      
    public CustomizedClientSocket(SocketImpl x)
    {super();
    
    }
     
    public String getPlayer()
    {
    return this.Player;
    }
    
     public void statePlayer(String x)
    {
    this.Player=x;
    }
   public static void main(String[] args) throws UnknownHostException, IOException {
   
   CustomizedClientSocket s =new CustomizedClientSocket("127.0.0.1", 9000,"Heba");
   System.out.println(s.getPlayer());
   

   s.toServer.println("Ahmed");
   s.toServer.println("working ya Ahmed ?");

   while(true)
{
String working =s.fromServer.readLine();
System.out.println(working);
}
   
   
   }
   
     
}