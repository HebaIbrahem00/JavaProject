package client.view;

import client.Connection.ClientSocket;
import java.awt.event.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.*;

public class LoginScreen extends JFrame implements ActionListener, KeyListener {

    JLabel userLabel, passLabel;
    JTextField userNameField;
    JPasswordField passwordField;
    JButton singInBtn, singUpBtn;
    JLabel authenticationStatusLabel;
    ClientSocket client;

    Background background = new Background();

    public LoginScreen(ClientSocket _client) {
        this.client = _client;
        showLogin();
    }

    public void showLogin() {
        //initialize user and passweord labels
        userLabel = new JLabel("User Name");
        passLabel = new JLabel("Password");
        userLabel.setBounds(100, 70, 80, 25);
        passLabel.setBounds(100, 100, 80, 25);
        background.add(userLabel);
        background.add(passLabel);

        //initialize user and passweord labels
        userNameField = new JTextField();
        passwordField = new JPasswordField();
        userNameField.setBounds(190, 70, 150, 20);
        passwordField.setBounds(190, 100, 150, 20);
        background.add(userNameField);
        background.add(passwordField);

        //initialize sign in and sign up buttons
        singInBtn = new JButton("Sign In");
        singUpBtn = new JButton("Sign Up");
        singInBtn.setBounds(120, 140, 80, 25);
        singUpBtn.setBounds(210, 140, 80, 25);
//        singInBtn.setEnabled(false);
        background.add(singInBtn);
        background.add(singUpBtn);
        singInBtn.addActionListener(this);
        singUpBtn.addActionListener(this);

        authenticationStatusLabel = new JLabel();
        authenticationStatusLabel.setBounds(120, 200, 80, 25);
        background.add(authenticationStatusLabel);

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
        if (choice.equals("Sign In")) {

            String userName = userNameField.getText();
            String password = passwordField.getText();
            String userStatus;
            
            try {
               authenticationStatusLabel.setText(client.sendLoginData(userName, password));
               
               
            } catch (IOException ex) {
                Logger.getLogger(LoginScreen.class.getName()).log(Level.SEVERE, null, ex);
            }

            userStatus = "online";
//            authenticationStatusLabel = sendLoginData(userName, password);

//            try {
//                String status = User_DB.checkUser(userName, password);
//                System.out.println(status);
//                switch (status) {
//                    case "userFound":
//                        JOptionPane.showMessageDialog(null, "Welcome, " + userNameField.getText(), "ok user", JOptionPane.INFORMATION_MESSAGE);
//                        this.dispose();
//                        System.out.println("welcome");
//                        new ChooseGameScreen();
////                        User_DB.updateUserStatus(userName, userStatus);
//                        break;
//                    case "invalidPassword":
//                        JOptionPane.showMessageDialog(null, "error", "wrong psssword", JOptionPane.WARNING_MESSAGE);
//                        System.out.println("wrong psssword");
//                        break;
//                    case "userNotFound":
//                        JOptionPane.showMessageDialog(null, "error", "wrong user", JOptionPane.WARNING_MESSAGE);
//                        System.out.println("user not exist");
//                        break;
//                }
//            } catch (SQLException | ClassNotFoundException ex) {
//                Logger.getLogger(LoginScreen.class.getName()).log(Level.SEVERE, null, ex);
//            }
        }
//        if (choice.equals("Sign Up")) {
//            System.out.println("sign up");
//            this.dispose();
//            new SignUpScreen().showSignUpScreen();
//        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        String userNameValue = userNameField.getText();
        String passwordValue = passwordField.getText();

        if (!userNameValue.equals("") && !passwordValue.equals("")) {
            singInBtn.setEnabled(true);
        } else {
            singInBtn.setEnabled(false);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
