package clien.view;

//package client.view;
//
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.sql.SQLException;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPasswordField;
//import javax.swing.JTextField;
//
//public class SignUpScreen extends JFrame implements ActionListener {
//
//    JLabel userNameLabel, passLabel, emailLabel;
//    JTextField userNameField, emailField;
//    JPasswordField passwordField;
//    JButton submitBtn;
//    JButton backBtn;
//    Background bgPanel = new Background();
//
//    public SignUpScreen() {
//        showSignUpScreen();
//    }
//
//    public void showSignUpScreen() {
//        //initialize user, email and passweord labels
//        userNameLabel = new JLabel("User Name");
//        passLabel = new JLabel("Password");
//        emailLabel = new JLabel("Email");
//        userNameLabel.setBounds(10, 50, 80, 25);
//        passLabel.setBounds(10, 80, 80, 25);
//        emailLabel.setBounds(10, 110, 80, 25);
//        bgPanel.add(userNameLabel);
//        bgPanel.add(passLabel);
//        bgPanel.add(emailLabel);
//
//        //initialize user, email and passweord fields
//        userNameField = new JTextField();
//        passwordField = new JPasswordField();
//        emailField = new JTextField();
//        userNameField.setBounds(100, 50, 120, 20);
//        emailField.setBounds(100, 80, 120, 20);
//        passwordField.setBounds(100, 110, 120, 20);
//        bgPanel.add(userNameField);
//        bgPanel.add(passwordField);
//        bgPanel.add(emailField);
//
//        //initialize submit and back buttons
//        submitBtn = new JButton("Submit");
//        backBtn = new JButton("Back");
//        submitBtn.setBounds(210, 140, 80, 25);
//        backBtn.setBounds(120, 140, 80, 25);
//        bgPanel.add(submitBtn);
//        bgPanel.add(backBtn);
//        submitBtn.addActionListener(this);
//        backBtn.addActionListener(this);
//
//        //initialize login panel 
//        setTitle("Sign Up");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setResizable(false);
//        setSize(428, 322);
//        setVisible(true);
//        add(bgPanel);
//    }
//
//    public void changed() {
//        if (userNameField.getText().equals("") || passwordField.getText().equals("") || emailField.getText().equals("")) {
//            submitBtn.setEnabled(false);
//        } else {
//            submitBtn.setEnabled(true);
//        }
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        String choice = e.getActionCommand();
//        Object source = e.getSource();
//        if (choice.equals("Back")) {
//            this.dispose();
//            new LoginScreen();
//        }
//        if (choice.equals("Submit") ) {
////            try {
////                User_DB.insertUser(userNameField.getText(), passwordField.getText(), emailField.getText());
////                this.dispose();
////                new LoginScreen().showLogin();
////            } catch (SQLException | ClassNotFoundException ex) {
////                System.out.println(ex.getMessage());
////            }
//        }
//    }
//}