
import Database.User_DB;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.*;

public class LoginScreen extends JFrame implements ActionListener {

    JLabel userLabel, passLabel;
    JTextField userNameField;
    JPasswordField passwordField;
    JButton singInBtn, singUpBtn;

    BackGround bgPanel = new BackGround();

    public LoginScreen() {
        showFirstScreen();
    }

    public void showFirstScreen() {
        //initialize user and passweord labels
        userLabel = new JLabel("User Name");
        passLabel = new JLabel("Password");
        userLabel.setBounds(100, 70, 80, 25);
        passLabel.setBounds(100, 100, 80, 25);
        bgPanel.add(userLabel);
        bgPanel.add(passLabel);

        //initialize user and passweord labels
        userNameField = new JTextField();
        passwordField = new JPasswordField();
        userNameField.setBounds(190, 70, 150, 20);
        passwordField.setBounds(190, 100, 150, 20);
        bgPanel.add(userNameField);
        bgPanel.add(passwordField);

        //initialize sign in and sign up buttons
        singInBtn = new JButton("Sign In");
        singUpBtn = new JButton("Sign Up");
        singInBtn.setBounds(120, 140, 80, 25);
        singUpBtn.setBounds(210, 140, 80, 25);
        bgPanel.add(singInBtn);
        bgPanel.add(singUpBtn);
        singInBtn.addActionListener(this);
        singUpBtn.addActionListener(this);

        //initialize login panel 
        setTitle("XO Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(428, 322);
        setVisible(true);
        add(bgPanel);
        changed();
    }

    public void changed() {
        if (userNameField.getText().equals("") || passwordField.getText().equals("") ) {
            singInBtn.setEnabled(false);
        } else {
            singInBtn.setEnabled(true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == singInBtn) {
            System.out.println("sign in");
            try {
                int status = User_DB.checkUser(userNameField.getText(), passwordField.getText());
                switch (status) {
                    case 1:
                        JOptionPane.showMessageDialog(null, "Welcome, "+userNameField.getText(), "ok user" ,JOptionPane.INFORMATION_MESSAGE);
                        this.dispose();
                        break;
                    case 2:
                        JOptionPane.showMessageDialog(null, "error", "wrong psssword" ,JOptionPane.WARNING_MESSAGE);
                        System.out.println("wrong psssword");
                        break;
                    case 0:
                        System.out.println("user not exist");
                        break;
                }
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(LoginScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (e.getSource() == singUpBtn) {
            System.out.println("sign up");
            this.dispose();
            new SignUpScreen().showSecondScreen();
        }
        
        if (e.getSource() == passwordField || e.getSource() == userNameField) {
            changed();
        }
    }
}
