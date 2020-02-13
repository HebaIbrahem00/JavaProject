/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectserver;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import javafx.application.Application;

/**
 *
 * @author BOB
 */
public class XO  extends Socket {
    
    String Player;
    
    public XO()
    {super();}
    
     XO(String address, int port) throws UnknownHostException, IOException {
        super(address,port);
    }
      public void statePlayer(String x)
    {
    this.Player=x;
    }
    public String getPlayer()
    {
    return this.Player;
    }
   
     
     
     
        public static void main(String[] args) throws UnknownHostException, IOException {
        XO x = new XO("127.0.0.1", 9000);
       
        // TODO code application logic here
    }
}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author BOB
 */
