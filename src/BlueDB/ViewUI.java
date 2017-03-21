package BlueDB;
/**
 * author: Jason Bierbrauer
 * program: BlueDB
 * function: Manage a MySQL server. 
 * currently set up locally.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class ViewUI{
    
    private JPanel paneName = new JPanel();
    private JLabel nameL = new JLabel("Execute statements and View Selected Database information:");
    
    private JPanel executePane = new JPanel();
    private JTextArea execute = new JTextArea("", 8,35);
    JButton executer = new JButton("Execute");
    JButton refresh = new JButton("Refresh table");
    
    private JPanel paneDescription = new JPanel();
    private JScrollPane scroll = new JScrollPane(paneDescription);
    private JTextArea dataViewArea = new JTextArea("",20,35);
    
    private JPanel buttons = new JPanel();
    JButton mainMenu = new JButton("Main Menu");
    JButton prev = new JButton("Previous");
    JButton next = new JButton("Next");
   
    JFrame main = new JFrame("View Data");
       
    public void loadViewDataUI(){        
        //Set up UI

        paneName.add(nameL);
        paneName.setBackground(Color.DARK_GRAY);
        
    	
    	executePane.add(execute);
    	executePane.setBackground(Color.DARK_GRAY);
    	
    	
        nameL.setForeground(Color.cyan);
        dataViewArea.setText("Please click next to cycle through the database.");
        dataViewArea.setLineWrap(true);
        paneDescription.add(dataViewArea); 
        paneDescription.setBackground(Color.DARK_GRAY);
        
        buttons.add(refresh);
    	buttons.add(executer);
        buttons.add(prev);
        buttons.add(next);
        buttons.add(mainMenu);
        buttons.setBackground(Color.DARK_GRAY);
     
        GridLayout grd1 = new GridLayout(4,1);
        main.setLayout(grd1);
        main.add(paneName);
        main.add(executePane);
        main.add(scroll);
        main.add(buttons);
        main.setVisible(true);
        main.setSize(500,600);
        main.setLocationRelativeTo(null);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
    }
    
    public void setDatabaseText(String s1) {
        dataViewArea.setText(s1);
    }
    
    public String getExecute() {
    	String text = execute.getText().trim();
    	return text;
    }
    
    public void clearExecute() {
    	execute.setText("");
    }
}