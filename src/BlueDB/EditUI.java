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
    
    private JPanel pane1 = new JPanel();
    private JScrollPane scroll1 = new JScrollPane(pane1);
    private JLabel label1 = new JLabel("Column 1");
    private JTextArea text1 = new JTextArea("", 4,20);
    private JLabel label2 = new JLabel("Column 2");
    private JTextArea text2 = new JTextArea("", 4,20);
    
    private JPanel pane2 = new JPanel();
    private JScrollPane scroll2 = new JScrollPane(pane2);    
    private JLabel label3 = new JLabel("Column 3");
    private JTextArea text3 = new JTextArea("", 4,20);
    private JLabel label4 = new JLabel("Column 4");
    private JTextArea text4 = new JTextArea("", 4,20);
    
    private JPanel pane3 = new JPanel();
    private JScrollPane scroll3 = new JScrollPane(pane3);
    private JLabel label5 = new JLabel("Column 5");
    private JTextArea text5 = new JTextArea("", 4,20);
    private JLabel label6 = new JLabel("Column 6");
    private JTextArea text6 = new JTextArea("", 4,20);
    private JLabel label7 = new JLabel("Column 7");
    private JTextArea text7 = new JTextArea("", 4,20);
    
    private JPanel pane4 = new JPanel();
    private JScrollPane scroll4 = new JScrollPane(pane4);
    private JLabel label8 = new JLabel("Column 8");
    private JTextArea text8 = new JTextArea("", 4,20);
    
    private JPanel pane5 = new JPanel();
    private JScrollPane scroll5 = new JScrollPane(pane5);
    private JLabel label9 = new JLabel("Column 9");
    private JTextArea text9 = new JTextArea("", 4,20);
    
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
        text1.setLineWrap(true);
        text2.setLineWrap(true);
        text3.setLineWrap(true);
        text4.setLineWrap(true);
        text5.setLineWrap(true);
        text6.setLineWrap(true);
        text7.setLineWrap(true);
        text8.setLineWrap(true);
        text9.setLineWrap(true);
        
        label1.setForeground(Color.cyan);
        label2.setForeground(Color.cyan);
        pane1.add(label1);
        pane1.add(text1);
        pane1.add(label2);
        pane1.add(text2);
        pane1.setBackground(Color.DARK_GRAY);
        
        label3.setForeground(Color.cyan);
        label4.setForeground(Color.cyan);
        pane2.add(label3);
        pane2.add(text3);
        pane2.add(label4);
        pane2.add(text4);
        pane2.setBackground(Color.DARK_GRAY);
        
        label5.setForeground(Color.cyan);
        label6.setForeground(Color.cyan);
        label7.setForeground(Color.cyan);
        pane3.add(label5);
        pane3.add(text5);
        pane3.add(label6);
        pane3.add(text6);
        pane3.setBackground(Color.DARK_GRAY);
        
        label8.setForeground(Color.cyan);
        label9.setForeground(Color.cyan);
        pane4.add(label7);
        pane4.add(text7);
        pane4.add(label8);
        pane4.add(text8);
        pane4.setBackground(Color.DARK_GRAY);        
        
        pane5.add(label9);
        pane5.add(text9);
        pane5.setBackground(Color.DARK_GRAY);
        
        buttons.add(prev);
        buttons.add(next);
        buttons.add(resetData);
        buttons.add(enterData);
        buttons.add(updateData);
        buttons.add(deleteData);
        buttons.add(mainMenu);
        buttons.setBackground(Color.DARK_GRAY);
     
        GridLayout grd1 = new GridLayout(6,1);
        
        main.setLayout(grd1);
        main.add(scroll1);
        main.add(scroll2);
        main.add(scroll3);
        main.add(scroll4);
        main.add(scroll5);
        main.add(buttons);
        
        main.setVisible(true);
        main.setSize(700,700);
        main.setLocationRelativeTo(null);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
    }
    
    public void setText1(String s) {
        text1.setText(s);
    }
    
    public Object getText1() {
        return text1.getText().trim();
    }
    
    public void setText2(String s) {
        text2.setText(s);
    }
    
    public Object getText2() {
        return text2.getText().trim();
    }
    
    public void setText3(String s) {
        text3.setText(s);
    }
    
    public Object getText3() {
        return text3.getText().trim();
    }
    
    public void setText4(String s) {
        text4.setText(s);
    }
    
    public Object getText4() {
        return text4.getText().trim();
    }
    
    public void setText5(String s) {
        text5.setText(s);
    }
    
    public Object getText5() {
        return text5.getText().trim();
    }
    
    public void setText6(String s) {
        text6.setText(s);
    }
    
    public Object getText6() {
        return text6.getText().trim();
    }
    
    public void setText7(String s) {
        text7.setText(s);
    }
    
    public Object getText7() {
        return text7.getText().trim();
    }
    
    public void setText8(String s) {
        text7.setText(s);
    }
    
    public Object getText8() {
        return text8.getText().trim();
    }
    
    public void setText9(String s) {
        text9.setText(s);
    }
    
    public Object getText9() {
        return text9.getText().trim();
    }
    
}