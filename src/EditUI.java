package BlueDB;
/**
 * author: Jason Bierbrauer
 * program: BlueDB
 * function: Manage a MySQL server. 
 * currently set up locally.
 */
import javax.swing.*;
import java.awt.*;

public class EditUI{
    
    private JPanel paneName = new JPanel();
    
    private JLabel ID = new JLabel("Column one ID:");
    private JTextArea IDtext = new JTextArea("",1,5);
    private JLabel nameL = new JLabel("Column two:");
    JTextArea name = new JTextArea("",2,20);
    
    private JPanel paneAddress = new JPanel();
    private JLabel addressL = new JLabel("Column three:");
    private JTextArea address = new JTextArea("",2,20);
    
    private JPanel paneDescription = new JPanel();
    private JScrollPane scroll = new JScrollPane(paneDescription);
    private JLabel descriptL = new JLabel("Column four:");
    private JTextArea descript = new JTextArea("",5,25);
    
    private JPanel paneStar = new JPanel();
    private JLabel starL = new JLabel("Column five:");
    private JTextArea star = new JTextArea("",1,5);
    
    private JPanel buttons = new JPanel();
    JButton prev = new JButton("Previous");
    JButton next = new JButton("Next");
    JButton resetData = new JButton("Reset Data");
    JButton enterData = new JButton("Save Data");
    JButton updateData = new JButton("Update Data");
    JButton deleteData = new JButton("Delete Data");
    JButton mainMenu = new JButton("Main Menu");
   
    JFrame main = new JFrame("Data Entry Form");

    public void loadDataEntryUI(){        
        //Set up UI
        paneName.add(ID);
        ID.setForeground(Color.cyan);
        paneName.add(IDtext);
        paneName.add(nameL);
        nameL.setForeground(Color.cyan);
        paneName.add(name);
        paneName.setBackground(Color.DARK_GRAY);
        name.setText("enter column 2 data");
        
        paneAddress.add(addressL);
        addressL.setForeground(Color.cyan);
        paneAddress.add(address);
        paneAddress.setBackground(Color.DARK_GRAY);
        address.setText("enter column 3 data");
        
        paneDescription.add(descriptL);
        descriptL.setForeground(Color.cyan);
        paneDescription.add(descript);  
        paneDescription.setBackground(Color.DARK_GRAY);
        descript.setText("enter column 4 data");
        descript.setLineWrap(true);
        
        paneStar.add(starL);
        starL.setForeground(Color.cyan);
        paneStar.add(star);
        paneStar.setBackground(Color.DARK_GRAY);
        star.setText("0");
        IDtext.setText("0");
        
        buttons.add(prev);
        buttons.add(next);
        buttons.add(resetData);
        buttons.add(enterData);
        buttons.add(updateData);
        buttons.add(deleteData);
        buttons.add(mainMenu);
        buttons.setBackground(Color.DARK_GRAY);
     
        GridLayout grd1 = new GridLayout(5,1);
        
        main.setLayout(grd1);
        main.add(paneName);
        main.add(paneAddress);
        main.add(scroll);
        main.add(paneStar);
        main.add(buttons);
        
        main.setVisible(true);
        main.setSize(500,600);
        main.setLocationRelativeTo(null);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
    }
    
    public String getName() {
        String a = name.getText().trim();
        if (a.isEmpty()) {
            JOptionPane.showMessageDialog(null,"Please Enter a Name.");
        } 
        return a;
    }
    public String getAddress() {
        String a = address.getText();
        return a;
    }
    public String getDescript() {
        String a = descript.getText();
        return a;
    }
    public int getNumOfStar() {
        String a = star.getText();
        int b = Integer.parseInt(a);
        return b;
    }
    public int getID_num() {
        String a = IDtext.getText();
        int b = Integer.parseInt(a);
        return b;
    }
    public void setName (String name1) {
        name.setText(name1);
    }
    public void setAddress(String address1) {
        address.setText(address1);
    }
    public void setDescript(String descript1) {
        descript.setText(descript1);
    }
    public void setStars(int starNum) {
        star.setText(""+starNum);
    }
    public void setID(int ID_num) {
        IDtext.setText(""+ID_num);
    }
}