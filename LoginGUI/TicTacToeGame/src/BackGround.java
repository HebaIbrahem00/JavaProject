
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class BackGround extends JPanel {
    private ImageIcon img;
    BackGround(){
        this.setLayout(null);
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        img = new ImageIcon(getClass().getResource("\\img\\login-bg.png"));
        img.paintIcon(this, g, 0, 0);
                
    }
}
