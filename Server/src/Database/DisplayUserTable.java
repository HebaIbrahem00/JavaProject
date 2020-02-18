package Database;

import static Database.User_DB.getUsers;
import java.awt.BorderLayout;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DisplayUserTable extends JFrame {

    public DisplayUserTable() {
        super("Bind JTable From MySQL DataBase");
        setLocationRelativeTo(null);
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        // now we are gonna create and populate a jtable from the arraylist 
        //who is populated from mysql database
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel();

        Object[] columnsName = new Object[5];

        columnsName[0] = "Id";
        columnsName[1] = "Name";
        columnsName[2] = "Password";
        columnsName[3] = "Email";
        columnsName[4] = "User Status";

        model.setColumnIdentifiers(columnsName);

        Object[] rowData = new Object[5];

        for (User u : User_DB.getUsers() ) {
            rowData[0] = u.getUserID();
            rowData[1] = u.getUserName();
            rowData[2] = u.getPassword();
            rowData[3] = u.getEmail();
            rowData[4] = u.getUserStatus();
            model.addRow(rowData);
        }

        table.setModel(model);

        System.out.println(getUsers().size());
        DisplayUserTable window = new DisplayUserTable();
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JScrollPane pane = new JScrollPane(table);
        panel.add(pane, BorderLayout.CENTER);
        window.setContentPane(panel);
    }
}