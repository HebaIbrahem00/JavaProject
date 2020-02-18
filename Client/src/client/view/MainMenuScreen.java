package client.view;


import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.*;

public class MainMenuScreen extends JFrame implements ActionListener {

    JButton singleplayerBtn, multiplayerBtn, quitBtn;

    Background background = new Background();

    public MainMenuScreen() {
        showChooseGameScreen();
    }

    public void showChooseGameScreen() {
        //initialize sign in and sign up buttons
        singleplayerBtn = new JButton("Singleplayer");
        multiplayerBtn = new JButton("Multiplayer");
        quitBtn = new JButton("Quit Game");
        
        singleplayerBtn.setBounds(120, 100, 150, 25);
        multiplayerBtn.setBounds(120, 135, 150, 25);
        quitBtn.setBounds(120, 175, 150, 25);
        
        background.add(singleplayerBtn);
        background.add(multiplayerBtn);
        background.add(quitBtn);
        singleplayerBtn.addActionListener(this);
        multiplayerBtn.addActionListener(this);
        quitBtn.addActionListener(this);
        
        //initialize login panel 
        setTitle("XO Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(428, 322);
        setVisible(true);
        add(background);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String choice = e.getActionCommand();
        Object source = e.getSource();
        if (choice.equals("Singleplayer")) {
            this.dispose();
            new ChooseLevelScreen();
        }
        if (choice.equals("Multiplayer")) {
            this.dispose();
            new OnlineUsersScreen();
        }
        if (choice.equals("Quit Game")) {
            this.dispose();
        }
    }
}