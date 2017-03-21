package BlueDB;
/**
 * author: Jason Bierbrauer
 * program: BlueDB
 * function: Manage a MySQL server. 
 * currently set up locally.
 *  jasonspage.cfqhitdy3uy2.us-west-2.rds.amazonaws.com for testing 
 * <--VERSION 1.0 UNDER CONSTRUCTION-->
 */
import java.awt.event.*;
import java.sql.*;
import javax.swing.JOptionPane;
//  jasonspage.cfqhitdy3uy2.us-west-2.rds.amazonaws.com
public class ControlActions implements ActionListener {
	
    //This is the URL for MySQL local server default
    //Useful for obtaining connection if no DB is selected
    String SQL = "SELECT * FROM "; 
    Connection con;
    Statement stmt1;
    ResultSet rs;
    
    //Creates instances of UI's
    dbUI dbnew = new dbUI();
    ViewUI view = new ViewUI();
    MainUI main = new MainUI();
    
    // objects for rs return
    Object[] objects = new Object[10];
    String[] colNames = new String[10];
    String[] types = new String[10];
    Object id_col;
    Object name;
    Object address;
    Object descript;
    Object numOfStar;
    Object numOfStar2;
    Object numOfStar3;
    
    // strings for names
    String host, username, password, dbname, table, ssl;
                    
    //Constructor for ControlActions Class. 
    //Contains actionListeners for all UI's
    public ControlActions() throws SQLException{
    	
        //MAIN UI actionListeners
        main.exit.addActionListener(this);
        main.createDB.addActionListener(this);
        main.viewData.addActionListener(this);
        //VIEW UI actionListeners
        view.refresh.addActionListener(this);
        view.executer.addActionListener(this);
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
              * Refresh button
              * 
              * re connects to database so table info can re load
              */
             
             if (event.getSource() == view.refresh) {
            	 refresh();
             }
             
             /**
              * Execute button
              * 
              * executes a SQL statement
              */
             
             if (event.getSource() == view.executer) {
            	 try {
            		 String SQL = view.getExecute();
            		 Statement stmt = con.createStatement();
            		 stmt = con.createStatement();
                     stmt.execute(SQL);
                     JOptionPane.showMessageDialog(null, "Statement Executed.");
                     view.clearExecute();
            		 
            	 } catch (SQLException e) {
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
                    
                    String newString2 = "";
                    int rowSpot = rs.getRow();
                   // rs.absolute(1);
                    
                    // would like to use this to get results
                    ResultSetMetaData row = rs.getMetaData();
                    int colCount = row.getColumnCount();
                    
                    view.setDatabaseText("Currently on, " + rowSpot);
                }
                else {
                    //If no previous informs client and loads next
                    rs.beforeFirst();
                    view.setDatabaseText("Currently on, " + 0);
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
                String newString = "";
                    
                    ResultSetMetaData row = rs.getMetaData();
                    
                    int colCount = row.getColumnCount();
                    
                    for (int i = 1; i < colCount; i++) {
                        if (rs.getObject(i) != null) {
                            objects[i] = rs.getObject(i);
                        }
                    }
                    for (int i = 1; i < colCount; i++) {
                        newString += objects[i] + "\n";
                    }
                    
                    view.setDatabaseText(newString);
                    
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
                String box3 = dbnew.getBox3();
                /**
                 * creates tables with retrieved values.
                 * it would be nice to get client input
                 * for column attributes like size of types
                 * of values to be stored
                 */
                String tables = box3;
                //Catch SQLException
                
                    String DURL = JOptionPane.showInputDialog(null, "Enter the server URL or localhost");
                    String userID = JOptionPane.showInputDialog(null, "Enter user ID");
                    String userPASS = JOptionPane.showInputDialog(null, "Enter user password");
                    String dbName = JOptionPane.showInputDialog(null, "Enter the database you wish to add too");
                    
                try {                   
                    //Create new Statement consiting newly built SQL strings
                    //Execute new SQL strings
                    String MySQL = "jdbc:mysql://" + DURL + "/" + dbName + "?user=" + userID +
                    "&password=" + userPASS + "&useSSL=false"; 
                    con = DriverManager.getConnection(MySQL);
                    Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);                    
                    stmt.executeUpdate(tables);
                    JOptionPane.showMessageDialog(null, "Saved!");
                    
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
                	String DURL = JOptionPane.showInputDialog(null, "Enter the server URL or localhost");
                	String dbName = dbnew.getDeleteName();
                	String userID = JOptionPane.showInputDialog(null, "Enter user ID");
                    String userPASS = JOptionPane.showInputDialog(null, "Enter user password");
                try { 
                    String MySQL = "jdbc:mysql://" + DURL + "/" + dbName + "?user=" + userID +
                    "&password=" + userPASS + "&useSSL=false"; 
                    con = DriverManager.getConnection(MySQL);
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
                    host = DURL;
                    String dbName = JOptionPane.showInputDialog(null, "Enter the database you wish to connect too");
                    dbname = dbName;
                    String dbTable = JOptionPane.showInputDialog(null, "Enter the table name");
                    table = dbTable;
                    String userID = JOptionPane.showInputDialog(null, "Enter user ID");
                    username = userID;
                    String userPASS = JOptionPane.showInputDialog(null, "Enter user password");
                    password = userPASS;
                    String useSSL = JOptionPane.showInputDialog(null, "Use SSL? true/false");
                    ssl = useSSL;
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
     * This refreshes connection
     */
    public void refresh() {
        //Get connection
            try{
                String MySQL = "jdbc:mysql://" + host + "/" + dbname + "?user=" + username +
                "&password=" + password + "&useSSL=" + ssl;
                String SQL = "SELECT * FROM " + table;
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