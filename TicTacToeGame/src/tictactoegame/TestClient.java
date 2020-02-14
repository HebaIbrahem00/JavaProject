/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoegame;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.UnknownHostException;

/**
 *
 * @author BOB
 */
public class TestClient {
     public static void main(String[] args) throws UnknownHostException, IOException {
   
   CustomizedClientSocket s  =new CustomizedClientSocket("127.0.0.1",9000,"Ahmed");
  
while(true)
{
String msgfrompeer =s.fromServer.readLine();
System.out.println("kda waslt 3and Ahmed "+msgfrompeer);
}
   
   }
    
}
