package client;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class Background extends JPanel {
    private ImageIcon img;
    Background(){
        this.setLayout(null);
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
//        img = new ImageIcon(getClass().getResource("\\img\\login-bg.png"));
//        img.paintIcon(this, g, 0, 0);
    }
}