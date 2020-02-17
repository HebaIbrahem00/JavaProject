package client;


import java.awt.Color;
import java.awt.ComponentOrientation;

import java.awt.event.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;

public class OnlineUsersScreen extends JFrame {

    JTable table;
    DefaultTableModel model;
    JScrollPane scroll;

    Object[][]OnlineUsersData;

    //headers for the table
    Object[] header = {"User Name", "User Status"};

    public OnlineUsersScreen() {
//        this.setLayout(null);
        this.setBackground(Color.red);
        showOnlineUsersScreen();
    }

    public void showOnlineUsersScreen() {
        try {

            OnlineUsersData = new Object[User_DB.getOnlineUsers().size()][2];
            model = new DefaultTableModel(OnlineUsersData, header);
            table = new JTable();
            table.setModel(model);

            int index = 0;

            //actual data for the table in a 2d array
            for (User u : User_DB.getOnlineUsers()) {
                OnlineUsersData[index][0] = u.getUserName();
                OnlineUsersData[index][1] = u.getUserStatus();
                model.addRow(OnlineUsersData[index]);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(OnlineUsersScreen.class.getName()).log(Level.SEVERE, null, ex);
        }

        table.setBounds(30, 40, 200, 300);
        table.setCellSelectionEnabled(true);
        scroll = new JScrollPane(table);
        table.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        add(scroll);

//        table.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent me) {
//                if (me.getClickCount() == 2) {     // to detect doble click events
//                    JTable target = (JTable) me.getSource();
//                    int row = target.getSelectedRow(); // select a row
//                    int column = target.getSelectedColumn(); // select a column
//                    JOptionPane.showMessageDialog(null, table.getValueAt(row, column)); // get the value of a row and column.
//                }
//            }
//        });
        this.add(new JScrollPane(table));
        this.setSize(300, 400);
        this.setTitle("Online Users");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                String userName = "m";
                String userStatus = "offline";
                try {
                    User_DB.updateUserStatus(userName, userStatus);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(OnlineUsersScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("exit");
            }
        });
    }
    
    public static void main(String[] args){
        new OnlineUsersScreen();
    }
}