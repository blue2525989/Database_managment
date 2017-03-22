package BlueDB;
/**
 * author: Jason Bierbrauer
 * program: BlueDB
 * function: Manage a MySQL server. 
 * currently set up locally.
 */
import javax.swing.*;
import java.awt.*;

public class dbUI{
    
    //Variables
    JFrame main = new JFrame("Edit Databases");
    
    //top panel
    JPanel top = new JPanel();
    JLabel header1 = new JLabel("Choose whether to create");
    JLabel header2 = new JLabel("or delete a database.");
        
    //new1 panel
    JPanel new1 = new JPanel();
    JLabel nameNewDb = new JLabel("Database name:");
    JTextArea name_text = new JTextArea("name here", 2,8);
   
    // new2 panel
    JPanel new2 = new JPanel();
    
    JLabel nameBox3 = new JLabel("Create a table:");
    JTextArea box3 = new JTextArea("enter sql statement here");
    
    
    //delete pane
    JPanel delete_pane = new JPanel();
    JLabel chooseDel = new JLabel("Enter DB to delete, BE CAREFUL");
    JTextArea delText = new JTextArea("name here");
    JButton mainMenu = new JButton("Main Menu");
    JButton delete = new JButton("Delete");
    JButton create = new JButton("Create Table");
    JButton createDatabase = new JButton("Create Database");
    
    //Void method to load UI
    public void loadDB_UI() {    
        //add components to panes and panes to frame
        top.add(header1);
        top.add(header2);
        header1.setForeground(Color.cyan);
        header2.setForeground(Color.cyan);
        top.setBackground(Color.DARK_GRAY);
        
        GridLayout grd3 = new GridLayout(3,2);
        new1.setLayout(grd3);
        new1.add(nameNewDb);
        nameNewDb.setForeground(Color.cyan);
        new1.add(name_text);
        new1.setBackground(Color.DARK_GRAY);
        
        GridLayout grd2 = new GridLayout(4,2);
        new2.setLayout(grd2);
        new2.add(nameBox3);
        nameBox3.setForeground(Color.cyan);
        new2.add(box3);
        new2.add(chooseDel);
        chooseDel.setForeground(Color.cyan);
        new2.add(delText);
        new2.setBackground(Color.DARK_GRAY);
        
        delete_pane.add(mainMenu);
        delete_pane.add(delete);
        delete_pane.add(create);
        delete_pane.add(createDatabase);
        delete_pane.setBackground(Color.DARK_GRAY);

        GridLayout grd1 = new GridLayout(4, 1);
        main.setLayout(grd1);
        main.add(top);
        main.add(new1);
        main.add(new2);
        main.add(delete_pane);
        
        main.setSize(500,600);
        main.setVisible(true);
        main.setLocationRelativeTo(null);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    
    //Getters
    public String getnewDbName() {
        String s = name_text.getText().trim();
        return s;
    }

    public String getBox3() {
        String s = box3.getText().trim();
        return s;
    }

    public String getDeleteName() {
        String s = delText.getText().trim();
        return s;
    }
    
    //Setters
    
    
}