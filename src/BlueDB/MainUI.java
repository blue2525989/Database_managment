package BlueDB;

import javax.swing.*;
import java.awt.*;

/**
 * author: Jason Bierbrauer
 * program: BlueDB
 * function: Manage a MySQL server. 
 * 
 */
public class MainUI{
    
    private String welcomePrompt = "    Welcome to my Database mangement\n"
            + "\n"
            + "This program interacts with\n"
            + "a MySql Database.\n"
            + "\n"
            + "The thought is that it can be\n"
            + "used for any MySQL database.\n"
            + "\n"
            + "Please test it and let me know\n"
            + "of any bugs that may arise.\n"
            + "\n"
            + "contact me @\n"
            + "JBierbrauer99@gmail.com\n"
            + "\n"
            + "Thanks.";
    
    private JPanel paneName = new JPanel();
    private JLabel nameL = new JLabel("Database Managment System");
    
    private JPanel paneDescription = new JPanel();
    private JScrollPane scroll = new JScrollPane(paneDescription);
    private JTextArea prompt = new JTextArea(welcomePrompt,5,25);
    
    private JPanel buttons = new JPanel();
    JButton viewData = new JButton("View Data");
    JButton createDB = new JButton("Create Database");
    JButton exit = new JButton("Exit");
   
    JFrame main = new JFrame("Blue's Database Managment System");

    public void loadMainUI(){        
        //Set up UI
        paneName.add(nameL);
        nameL.setForeground(Color.cyan);
        paneName.setBackground(Color.DARK_GRAY);
        
        paneDescription.add(prompt);
        paneDescription.setBackground(Color.DARK_GRAY);
        prompt.setLineWrap(true);
        
        buttons.add(viewData);
        buttons.add(createDB);
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