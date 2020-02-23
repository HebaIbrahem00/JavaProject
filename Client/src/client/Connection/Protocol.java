/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.Connection;

/**
 *
 *
 * @author omar
 */
public interface Protocol {

    String SIGNUP = "SIGNUP";

    String SIGNIN = "SIGNIN";

    String SHOWUSERS = "SHOWUSERS";  
        
    String CHOOSEOPPONENT = "CHOOSEOPPONENT";

    String CONNECTED = "CONNECTED";
    
    String DISCONNECTED = "DISCONNECTED";

    String AUTHINTICATED = "AUTHINTICATED";

    String ONLINEPLAYERS = "ONLINEPLAYERS";

    String GAMESCENE = "GAMESCENE";

    String MAKE_MOVE = "MAKE_MOVE";

    String MOVE_MADE = "MOVE_MADE";

    String GAME_WON = "GAME_WON";

    String GAME_LOST = "GAME_LOST";

    String GAME_TIED = "GAME_TIED";

    String ERROR = "ERROR";
    
    String INVITATION = "INVITATION";
    
    String ACCEPTED = "ACCEPTED";

}
