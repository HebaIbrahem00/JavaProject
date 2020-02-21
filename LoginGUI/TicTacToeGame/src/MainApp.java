
import java.awt.EventQueue;

class MainApp {
    
    public static void main(String[] args){
        //run the first screen (login screen) in the game
        
                EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
        new LoginScreen().showFirstScreen();
            }
        });
    }
}
