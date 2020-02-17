package client;


import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.*;

public class ChooseLevelScreen extends JFrame implements ActionListener {

    JButton beginnerBtn, intermediateBtn, advancedBtn, backBtn;
    Background background = new Background();

    public ChooseLevelScreen() {
        showChooseLevel();
    }

    public void showChooseLevel() {
        //initialize sign in and sign up buttons
        beginnerBtn = new JButton("Beginner");
        intermediateBtn = new JButton("Intermediate");
        advancedBtn = new JButton("Advanced");
        backBtn = new JButton("Back");

        beginnerBtn.setBounds(120, 65, 150, 25);
        intermediateBtn.setBounds(120, 100, 150, 25);
        advancedBtn.setBounds(120, 135, 150, 25);
        backBtn.setBounds(120, 175, 150, 25);

        background.add(beginnerBtn);
        background.add(intermediateBtn);
        background.add(advancedBtn);
        background.add(backBtn);

        beginnerBtn.addActionListener(this);
        intermediateBtn.addActionListener(this);
        advancedBtn.addActionListener(this);
        backBtn.addActionListener(this);

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
        if (choice.equals("Beginner")) {
        }
        if (choice.equals("Intermediate")) {
        }
        if (choice.equals("Advanced")) {
        }
        if (choice.equals("Back")) {
            this.dispose();
            new ChooseGameScreen();
        }
    }
}