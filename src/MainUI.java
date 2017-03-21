package BlueDB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
/**
 * author: Jason Bierbrauer
 * program: BlueDB
 * function: Manage a MySQL server. 
 * currently set up locally.
 */
public class MainUI{
    
    private String welcomePrompt = "    Welcome to my Database mangement\n"
            + "system. With this program you can,\n"
            + "Create and Delete databases on a \n"
            + "local MySQL server. Also you have \n"
            + "full control of editing those DB's.\n"
            + "Lastly you can choose to view your\n"
            + "Databases.\n"
            + "\n"
            + "Try | schoolDB, school |or| schoolDB,\n"
            + "programmer |or| schoolDB, goats |or| schoolDB,\n"
            + "code |or| myFarm, people |or| myFarm, animals\n"
            + "|or| myFarm, vehicles\n"
            + "DURL: localhost\n"
            + "userID: blue\n"
            + "userPASS: jason99\n"
            + "useSSL?: false\n"
            + "\n"
            + "Or create your own Database and then\n"
            + "create some tables, next add some data,\n"
            + "Finally view your new database saved on the\n"
            + "MySQL server.\n."
            + "\n"
            + "    I hope you enjoy this program, And \n"
            + "feel free to start making some sample\n"
            + "databases to test out.\n"
            + "\n";
    
    private JPanel paneName = new JPanel();
    private JLabel nameL = new JLabel("Database Managment System");
    
    private JPanel paneDescription = new JPanel();
    private JScrollPane scroll = new JScrollPane(paneDescription);
    private JLabel promptL = new JLabel("Blue's Database Managment System,");
    private JTextArea prompt = new JTextArea(welcomePrompt,5,25);
    
    private JPanel buttons = new JPanel();
    JButton viewData = new JButton("View Data");
    JButton enterData = new JButton("Enter Data");
    JButton createDB = new JButton("Create Database");
    JButton exit = new JButton("Exit");
   
    JFrame main = new JFrame("Blue's Database Managment System");

    public void loadMainUI(){        
        //Set up UI
        paneName.add(nameL);
        nameL.setForeground(Color.cyan);
        paneName.setBackground(Color.DARK_GRAY);
        
        paneDescription.add(promptL);
        promptL.setForeground(Color.cyan);
        paneDescription.add(prompt);
        paneDescription.setBackground(Color.DARK_GRAY);
        prompt.setLineWrap(true);
        
        buttons.add(viewData);
        buttons.add(createDB);
        buttons.add(enterData);
        buttons.add(exit);
        buttons.setBackground(Color.DARK_GRAY);
     
        GridLayout grd1 = new GridLayout(3,1);
        main.setLayout(grd1);
        main.add(paneName);
        main.add(scroll);
        main.add(buttons);
        main.setVisible(true);
        main.setSize(500,600);
        main.setLocationRelativeTo(null);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
    }
}