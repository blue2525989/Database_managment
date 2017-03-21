package BlueDB;
/**
 * author: Jason Bierbrauer
 * program: BlueDB
 * function: Manage a MySQL server. 
 * currently set up locally.
 * 
 * <--VERSION 1.0 UNDER CONSTRUCTION-->
 */
import java.awt.event.*;
import java.sql.*;
import javax.swing.JOptionPane;

public class ControlActions implements ActionListener {
    //This is the URL for MySQL local server default
    //Useful for obtaining connection if no DB is selected
    String SQL = "SELECT * FROM "; 
    Connection con;
    Statement stmt1;
    ResultSet rs;
    //Creates instances of UI's
    dbUI dbnew = new dbUI();
    EditUI edit = new EditUI();
    ViewUI view = new ViewUI();
    MainUI main = new MainUI();
    //Constructor for ControlActions Class. 
    //Contains actionListeners for all UI's
    public ControlActions() throws SQLException{
        //MAIN UI actionListeners
        main.enterData.addActionListener(this);
        main.exit.addActionListener(this);
        main.createDB.addActionListener(this);
        main.viewData.addActionListener(this);
        //EDIT UI actionListeners
        edit.prev.addActionListener(this);
        edit.next.addActionListener(this);
        edit.resetData.addActionListener(this);
        edit.enterData.addActionListener(this);
        edit.updateData.addActionListener(this);
        edit.deleteData.addActionListener(this);
        edit.mainMenu.addActionListener(this);
        //VIEW UI actionListeners
        view.prev.addActionListener(this);
        view.next.addActionListener(this);
        view.mainMenu.addActionListener(this);
        //NEW DATABASES UI actionListeners
        dbnew.mainMenu.addActionListener(this);
        dbnew.delete.addActionListener(this);
        dbnew.create.addActionListener(this);
        dbnew.createDatabase.addActionListener(this);
    }
   
        //Action Listeners        
        public void actionPerformed(ActionEvent event) {
            /**
             * Enter data button
             * opens EditUI for editing
             * selected databases
             */
            if (event.getSource() == main.enterData) {
                main.main.setVisible(false);
                edit.loadDataEntryUI();
                //IMPORTANT BLOCK OF CODE
                //Choose your database and table
                //SET UP initial connection
                //Catch SQLException
                getConnect();
        
            }
            /**
             * View data button
             * opens up ViewUI for viewing stored databases
             */
            if (event.getSource() == main.viewData) {
                main.main.setVisible(false);
                view.loadViewDataUI();
                getConnect();
            }
            /**
             * createDB button opens dbNewUI which allows
             * client to create new databases with options 
             * to name db, table, four string fields, one integer field
             * Also allows to delete databases
             */
            if (event.getSource() == main.createDB) {
                main.main.setVisible(false);
                dbnew.loadDB_UI();
            }
            /**
             * exit button closes the program
             */
            if (event.getSource() == main.exit) {
                //Catch SQLException
                if (con == null) {
                    System.exit(0);
                } else {
                try {
                    //Close connnection to MySQL server
                    con.close();
                    System.exit(0);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                    }
                }
            }
            /**
             * mainMenu button opens MainUI
             */
             if (event.getSource() == view.mainMenu) {
                view.main.setVisible(false);
                main.loadMainUI();
                
                //Catch SQLException
                try{
                    //Close ResultSet and Connection
                    rs.close();
                    con.close();
                }catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            }
            /**
             * previous button moves cursor one
             * element back in ReslutSet
             */ 
            if (event.getSource() == view.prev) {
            //Catch SQLException            
            try {                
                if (rs.previous()) {  
                    //Retrieves table results
                    int id_col = rs.getInt(1);
                    String name = rs.getString(2);
                    String address = rs.getString(3);
                    String descript = rs.getString(4);
                    int numOfStar = rs.getInt(5);
                    //Sets textArea with results
                    view.setDatabaseText("Column 1 ID: "+id_col+"\n"
                    + "\n"
                    + "Column 2: "+name+"\n"
                    + "\n"
                    + "Column 3: "+address+"\n"
                    + "\n"
                    + "Column 4: "+descript+"\n"
                    + "\n"
                    + "Column 5: "+numOfStar+"\n");
                }
                else {
                    //If no previous informs client and loads next
                    rs.next();
                    JOptionPane.showMessageDialog(null, "End of File");
                }
            } catch (SQLException err) {
                JOptionPane.showMessageDialog(null, err.getMessage());
            }
        }
            /**
             * next button load next in ResultSet
             */ 
            if (event.getSource() == view.next) {
            //Catch SQLException            
            try {                
                if (rs.next()) {
                //Retrieves next results
                int id_col = rs.getInt(1);
                String name = rs.getString(2);
                String address = rs.getString(3);
                String descript = rs.getString(4);
                int numOfStar = rs.getInt(5);
                //Sets textArea
                view.setDatabaseText("Column 1 ID: "+id_col+"\n"
                + "\n"
                + "Column 2: "+name+"\n"
                + "\n"
                + "Column 3: "+address+"\n"
                + "\n"
                + "Column 4: "+descript+"\n"
                + "\n"
                + "Column 5: "+numOfStar+"\n");
                }
                else {
                    //if no next informs client and loads previous
                    rs.previous();            
                    JOptionPane.showMessageDialog(null, "End of File");
                }
            } catch (SQLException err) {
                JOptionPane.showMessageDialog(null, err.getMessage());
            }
        }
    
            /**
             * previous button loads previous in ResultSet
             */
            if (event.getSource() == edit.prev) {
                //Catchs SQLException
                try {                
                    if (rs.previous()) {
                    //Retrieves table data
                    int id_col = rs.getInt(1);
                    String name = rs.getString(2);
                    String address = rs.getString(3);
                    String descript = rs.getString(4);
                    int numOfStar = rs.getInt(5);
                    //Sets textAreas
                    edit.setName(name);
                    edit.setAddress(address);
                    edit.setDescript(descript);
                    edit.setStars(numOfStar);
                    edit.setID(id_col);
                }
                else {
                        //if no previous informs client and loads next
                        rs.next();
                        JOptionPane.showMessageDialog(edit.main, "Start of File");
                    }
            } catch (SQLException err) {
                JOptionPane.showMessageDialog(edit.main, err.getMessage());
            } 
        } 
        /**
         * next button loads next in ResultSet
         */
        if (event.getSource() == edit.next) {
            //Catch SQLException
            try {                
            if (rs.next()) {
            //Retrieves table data
            int id_col = rs.getInt(1);
            String name = rs.getString(2);
            String address = rs.getString(3);
            String descript = rs.getString(4);
            int numOfStar = rs.getInt(5);
            //Sets textAreas
            edit.setName(name);
            edit.setAddress(address);
            edit.setDescript(descript);
            edit.setStars(numOfStar);
            edit.setID(id_col);
            }
                else {
                    //if no next informs client and loads previous
                    rs.previous();
                    JOptionPane.showMessageDialog(edit.main, "End of File");
                }
            } catch (SQLException err) {
                JOptionPane.showMessageDialog(edit.main, err.getMessage());
            } 
        }
        /**
         * resetData button resets all texts fields
         * with pre-loaded message
         */
        if (event.getSource() == edit.resetData) {
            //Sets textAreas with pre-loaded message
            edit.setName("enter box one");
            edit.setAddress("enter box two");
            edit.setDescript("enter a description");
            edit.setStars(0);
            edit.setID(0);
        }
        /**
         * save button saves all data from
         * textAreas in to user selected table
         */
        if (event.getSource() == edit.enterData) {
            //Catch SQLException
            try {
                 //Get text in fields.
                String name = edit.getName();
                String address = edit.getAddress();
                int numOfStar = edit.getNumOfStar();
                String descript = edit.getDescript();
                int IDnum = edit.getID_num();            
                //Try to insert new entrie into table
                try {
                    //Move curoser to new spot in table
                    rs.moveToInsertRow();
                    //Update each column
                    rs.updateString(2,name);
                    rs.updateString(3, address);
                    rs.updateString(5, ""+numOfStar);
                    rs.updateString(4, descript);
                    rs.updateInt("ID", IDnum);                
                    //Insert into table
                    rs.insertRow();
                    //Moves cursour to next row
                    rs.next(); 
                    //Display message saved dialog
                    JOptionPane.showMessageDialog(null, "Record saved");
            } catch (SQLException err) {
                JOptionPane.showMessageDialog(null, err.getMessage());
        }
            } catch (Exception e){
                JOptionPane.showMessageDialog(null,"Please complete all boxes.");
            }
        }
        /**
         * update record button allows updating of
         * entries while NOT requiring ID column to
         * be filled out
         */
        if (event.getSource() == edit.updateData) {
            //Catch SQLException
            try {
                String name = edit.getName();
                String address = edit.getAddress();
                int numOfStar = edit.getNumOfStar();
                String descript = edit.getDescript();
                //Update current entry.
                try {
                    //Update columns
                    rs.updateString(2,name);
                    rs.updateString(3, address);
                    rs.updateString(5, ""+numOfStar);
                    rs.updateString(4, descript);
                    //Updates entire row
                    rs.updateRow();
                    //Notify client
                    JOptionPane.showMessageDialog(null, "Updated");
                } catch (SQLException err) {
                    JOptionPane.showMessageDialog(null, err.getMessage());
            } 
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null,"Please complete all boxes.");
                }
        }
        /**
         * deleteRecord button deletes the current record
         * relative to cursor position
         * <--SHOULD ADD CONFIRM DELETE? DIALOG-->
         */
        if (event.getSource() == edit.deleteData) {
            //Catch SQLException 
            try {
                //Delete row
                rs.deleteRow();
                //Inform client of delete
                JOptionPane.showMessageDialog(null, "Record deleted");
                //Moves cursor one element back
                rs.previous();
                //Retrieve table data
                int id_col = rs.getInt(1);
                String name = rs.getString(2);
                String address = rs.getString(3);
                String descript = rs.getString(4);
                int numOfStar = rs.getInt(5);
                //Set textAreas
                edit.setName(name);
                edit.setAddress(address);
                edit.setDescript(descript);
                edit.setStars(numOfStar);
                edit.setID(id_col);
            } catch (SQLException err) {
                JOptionPane.showMessageDialog(null, err.getMessage());
            }
        }
        /**
         * mainMenu button loads MainUI
         */
        if (event.getSource() == edit.mainMenu) {
                edit.main.setVisible(false);
                main.loadMainUI();
                //Catch SQLException
                try{
                    //Close ResultSet and Connection
                    rs.close();
                    con.close();
                }catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            }
        
            /**
             * mainMenu button opens MainUI
             */
            if (event.getSource() == dbnew.mainMenu) {
                dbnew.main.setVisible(false);
                main.loadMainUI();
            }
            /**
             * create button creates a new
             * database on the MySQL server
             * with client input for DBname 
             * and table name
             */
            if (event.getSource() == dbnew.create) {
                //Gets name for new database for adding to server URL
                String name = dbnew.getnewDbName();  // Also uses textArea to get name for table
                //Retrieve names for columns
                String box2 = dbnew.getBox2();
                String box3 = dbnew.getBox3();
                String box4 = dbnew.getBox4();
                String box5 = dbnew.getBox5();
                /**
                 * creates tables with retrieved values.
                 * it would be nice to get client input
                 * for column attributes like size of types
                 * of values to be stored
                 */
                String tables = "create table " + name + "( "
                        + "ID INT PRIMARY KEY, " + box2 + " VARCHAR(50), " + box3 + " VARCHAR(80), "
                        + box4 + " VARCHAR(250), " + box5 + " INT )" ;
                //Catch SQLException
                
                    String DURL = JOptionPane.showInputDialog(null, "Enter the server URL or localhost");
                    String dbName = JOptionPane.showInputDialog(null, "Enter the database you wish to add too");
                    String userID = JOptionPane.showInputDialog(null, "Enter user ID");
                    String userPASS = JOptionPane.showInputDialog(null, "Enter user password");
                try {                   
                    //Create new Statement consiting newly built SQL strings
                    //Execute new SQL strings
                    String MySQL = "jdbc:mysql://" + DURL + "/" + dbName + "?user=" + userID +
                    "&password=" + userPASS + "&useSSL=false"; 
                    con = DriverManager.getConnection(MySQL);
                    Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);                    
                    stmt.executeUpdate(tables);
                    
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            }
            /**
             * createDatabase button creates new Database
             */
            if (event.getSource() == dbnew.createDatabase) {                
                String dbName = dbnew.getnewDbName();
                createDatabase(dbName);
            }
            /**
             * delete button deletes entire database
             * so be careful
             */
            if (event.getSource() == dbnew.delete) {
                //Get name on the chopping block
                String delName = dbnew.getDeleteName();
                //Make SQL command string
                String drop = "drop database " + delName;
                //Catch SQLException
                 String choice = "";
                try{
                    choice = JOptionPane.showInputDialog(null, "Are you sure?");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
                if (choice.equalsIgnoreCase("yes")) {
                    try {
                        //Create new Statement
                        Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
                        //Execute new SQL string
                        stmt.executeUpdate(drop);
                        //Inform client of delete
                        JOptionPane.showMessageDialog(null, "Database Deleted");
                    
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }
                }
                else {
                    //Dont delete
                }
            }
        }
    /**
     * This gets connection to a database
     */
    public void getConnect() {
            //Get connection
                try{
                    String DURL = JOptionPane.showInputDialog(null, "Enter the server URL or localhost");
                    String dbName = JOptionPane.showInputDialog(null, "Enter the database you wish to connect too");
                    String dbTable = JOptionPane.showInputDialog(null, "Enter the table name");
                    String userID = JOptionPane.showInputDialog(null, "Enter user ID");
                    String userPASS = JOptionPane.showInputDialog(null, "Enter user password");
                    String useSSL = JOptionPane.showInputDialog(null, "Use SSL? true/false");
                    String MySQL = "jdbc:mysql://" + DURL + "/" + dbName + "?user=" + userID +
                    "&password=" + userPASS + "&useSSL=" + useSSL;
                    String SQL = "SELECT * FROM " + dbTable;
                    //Opens connection to the new selection
                    con = DriverManager.getConnection(MySQL);
                    //Opens a ResultSet of selected db
                    stmt1 = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
                    rs = stmt1.executeQuery(SQL);
                    //Sets ResultSet at absolute first position in set
                    //so NullPointerException does not arise
                    rs.absolute(0);
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
        }
    /**
     * This creates a new Database
     * @param dbName pass a user defined name for database 
     */
    public void createDatabase(String dbName) {
        //Get connection
        Statement stmt = null;
                try{
                    String DURL = JOptionPane.showInputDialog(null, "Enter the server URL or localhost");
                    String userID = JOptionPane.showInputDialog(null, "Enter user ID");
                    String userPASS = JOptionPane.showInputDialog(null, "Enter user password");
                    String MySQL = "jdbc:mysql://" + DURL + "/";
                    String SQL = "CREATE DATABASE " + dbName;
                    //Opens connection to the new selection
                    Class.forName("com.mysql.jdbc.Driver");
                    con = DriverManager.getConnection(MySQL, userID, userPASS);
                    //Creates database
                    stmt = con.createStatement();
                    stmt.execute(SQL);
                    JOptionPane.showMessageDialog(null, "Database " + dbName + " was created.");
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                } catch (ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
    }
    
}