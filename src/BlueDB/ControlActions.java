package BlueDB;

/**
 * author: Jason Bierbrauer
 *
 * program: BlueDB
 * function: Manage a MySQL server. 
 *
 *  
 *  
 * <--VERSION 2.0-->
 * 
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
    ViewUI view = new ViewUI();
    MainUI main = new MainUI();
    
    // objects for rs return
    Object[] objects = new Object[10];
    // used for displayng column names
    String[] colNames = new String[10];
       
    // strings for saving credentials
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
             * 
             * opens up ViewUI for viewing stored databases
             */
        	
            if (event.getSource() == main.viewData) {
            	// closes mainUI and opens viewUI
                main.main.setVisible(false);
                view.loadViewDataUI();
                
                // asks for login as soon as view opens
                getConnect();
            }
            
            /**
             * createDB button 
             * 
             * opens dbNewUI which allows
             * 
			 * Creating of new Databases
			 * 
			 * Creating of new Tables
			 * 
             * And deleting of Databases
             * 
             */
            
            if (event.getSource() == main.createDB) {
            	// opens dbUI and closes mainUI
                main.main.setVisible(false);
                dbnew.loadDB_UI();
            }
            
            /**
             * exit button
             * 
             * closes the program
             * 
             */
            
            if (event.getSource() == main.exit) {
                // if no connection, close
                if (con == null) {
                	// exit
                    System.exit(0);
                }
                
                // else if there is  connection, close it
                else {
                	
                	// try statement for SQL exceptions
	                try {
	                	
	                    //Close connection to MySQL server
	                    con.close();
	                    
	                    // exit
	                    System.exit(0);
	                    
	                // catch SQL exception
	                } catch (SQLException ex) {
	                    JOptionPane.showMessageDialog(null,ex.getMessage());
	                }
                }
            }
            
            /**
             * mainMenu button 
             * 
             * opens MainUI
             * 
             */
            
             if (event.getSource() == view.mainMenu) {
            	 // opens mainUI and closes viewUI
            	 view.main.setVisible(false);
            	 main.loadMainUI();
                
                // try statement for SQLException
                try{
                    // Close ResultSet and Connection
                    rs.close();
                    con.close();
                // catch SQL exception    
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            }
             
             /**
              * Refresh button
              * 
              * re connects to database so table info can re load
              * 
              */
             
             if (event.getSource() == view.refresh) {
            	 // re-connects to database
            	 refresh();
             }
             
             /**
              * Execute button
              * 
              * executes a SQL statement
              * 
              */
             
             if (event.getSource() == view.executer) {
            	 // try statement for SQL exceptions
            	 try {
            		 // gets text from text area
            		 String SQL = view.getExecute();
            		 
            		 // create new statement
            		 Statement stmt = con.createStatement();
            		 stmt = con.createStatement();
            		 
            		 // execute statement and notify with JOptionPane
                     stmt.execute(SQL);
                     JOptionPane.showMessageDialog(null, "Statement Executed.");
                     
                     // clear text area
                     view.clearExecute();
                     
            	// catch SQL exception 
            	} catch (SQLException e) {
            		JOptionPane.showMessageDialog(null, e.getMessage());
            	}
             }
             
            /**
             * previous button moves cursor one
             * 
             * element back in ReslutSet
             */ 
             
            if (event.getSource() == view.prev) {
            	
            	// try statement for SQLException            
	            try {            
	            	// result set previous
	                if (rs.previous()) {  
	                	
	                    //Retrieves table results
	                    int rowSpot = rs.getRow();
	                    
	                    // sets text area with current spot
	                    view.setDatabaseText("Currently on, " + rowSpot);
	                }
	                // else if on spot 0
	                else {
	                    //If no previous informs client and loads next
	                    rs.beforeFirst();
	                    view.setDatabaseText("Currently on, " + 0);
	                    // notify of end of file
	                    JOptionPane.showMessageDialog(null, "End of File");
	                }
	            // catch SQL exception    
		        } catch (SQLException err) {
		            JOptionPane.showMessageDialog(null, err.getMessage());
		        }
	        }
            
            /**
             * next button 
             * 
             * load next in ResultSet
             * 
             */ 
            
            if (event.getSource() == view.next) {
            //Catch SQLException            
            try {  
            	
            	// result set next
                if (rs.next()) {
                	// new string for adding elements too
                	String newString = "";
                    
                	// use result set meta data to get a count
                	// of how many columns for the for loop
                    ResultSetMetaData row = rs.getMetaData();
                    int colCount = row.getColumnCount();
                    
                    // use for loop and colCount to loop through 
                    // each row
                    for (int i = 1; i <= colCount; i++) {
                        if (rs.getObject(i) != null) {
                            objects[i] = rs.getObject(i);
                            colNames[i] = row.getColumnName(i);
                        }
                        
                        // adds each object to new string
                        // temp string for comparing length
                        String temp = colNames[i];
                        
                        
                        // if length of string is less then 3,
                        // adds a second \t tab char
                        if (temp.length() <= 3) {
                        	newString += "Column: " + colNames[i] + "\t\tValue: " + objects[i] + "\n\n";
                        }
                        // else if string is greater then three
                        // use a single \t tab char
                        else {
                        	newString += "Column: " + colNames[i] + "\tValue: " + objects[i] + "\n\n";
                        }
                        
                    }
                    
                    // sets text area with results
                    view.setDatabaseText(newString);
                    
                }
                // else if no more, informs user and loads
                // one before last
                else {
                    //if no next informs client and loads previous
                    rs.previous();            
                    JOptionPane.showMessageDialog(null, "End of File");
                }
            // catches SQL exception
            } catch (SQLException err) {
                JOptionPane.showMessageDialog(null, err.getMessage());
            }
        }
    
            /**
             * mainMenu button 
             * 
             * opens MainUI
             * 
             */
            
            if (event.getSource() == dbnew.mainMenu) {
            	// closes dbUI and opens mainUI
                dbnew.main.setVisible(false);
                main.loadMainUI();
            }
            
            /**
             * create button 
             * 
             * creates a new
             * database on the MySQL server
             * with client input for table name
             * 
             */
            
            if (event.getSource() == dbnew.create) {
            	
                //Retrieve names for columns
                String box3 = dbnew.getBox3();
                
                // gets SQL text for new table
                String tables = box3;
                
                // get user credentials                
                String DURL = JOptionPane.showInputDialog(null, "Enter the server URL or localhost");
                String userID = JOptionPane.showInputDialog(null, "Enter user ID");
                String userPASS = JOptionPane.showInputDialog(null, "Enter user password");
                String dbName = JOptionPane.showInputDialog(null, "Enter the database you wish to add too");
                
                // try statement for SQL execution
                try {                   
                    //Create new Statement made of newly built SQL strings
                    //Execute new SQL strings
                    String MySQL = "jdbc:mysql://" + DURL + "/" + dbName + "?user=" + userID +
                    "&password=" + userPASS + "&useSSL=false"; 
                    
                    // open connection
                    con = DriverManager.getConnection(MySQL);
                    
                    // create new statement and result set
                    Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);   
                    
                    // execute statement and notify user of save with JOptionPane
                    stmt.executeUpdate(tables);
                    JOptionPane.showMessageDialog(null, "Saved!");
                // catch SQL exception    
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            }
            
            /**
             * createDatabase button creates new Database
             *  DBname 
             * and
             * gets text from text area 
             * then logs in to account and 
             * creates new database. 
             *
             */
            
            if (event.getSource() == dbnew.createDatabase) {    
            	// gets name user wants to use
                String dbName = dbnew.getnewDbName();
                
                // saves new database
                createDatabase(dbName);
            }
            
            /**
             * delete button 
             * 
             * deletes entire database
             * so be careful
             * 
             * WHEN YOU GET HOME FROM VACATION ADD THE OPTION 
             * TO DELETE TABLE OR DATABASE.
             * 
             * POSSIBLY EITHER BY CHOOSING WITH A JOPTIONPANE
             * WITH ONE BUTTON, OR MAKE A SEPERATE BUTTON THAT USES 
             * SAME TEXT AREA. THIS MIGHT BE EASIER. I COULD JUST REUSE ALL
             * THE SAME CODE AND CHANGE A FEW VARIABLE NAMES.
             * 
             */
            
            if (event.getSource() == dbnew.delete) {
            	
                //Get name on the chopping block
                String delName = dbnew.getDeleteName();
                
                //Make SQL command string
                String drop = "drop database " + delName;

                // empty string for yes or no answer
                String choice = "";
                
                //Catch SQLException
                try{
                	// use JOptionPane to ask whether user
                	// is sure they want to delete
                    choice = JOptionPane.showInputDialog(null, "Are you sure?");
                // catch exceptions
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
                
                // if user enters yes
                if (choice.equalsIgnoreCase("yes")) {
                	
                	// ask for credentials
                	String DURL = JOptionPane.showInputDialog(null, "Enter the server URL or localhost");
                	String dbName = dbnew.getDeleteName();
                	String userID = JOptionPane.showInputDialog(null, "Enter user ID");
                    String userPASS = JOptionPane.showInputDialog(null, "Enter user password");
                    
                    // try statement for connections
	                try { 
	                	// SQL statement for logging in
	                    String MySQL = "jdbc:mysql://" + DURL + "/" + dbName + "?user=" + userID +
	                    "&password=" + userPASS + "&useSSL=false"; 
	                    
	                    // opens connection
	                    con = DriverManager.getConnection(MySQL);
	                    
	                    // Create new Statement and result set
	                    Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
	                    ResultSet.CONCUR_UPDATABLE);
	                    
	                    //Execute new SQL string
	                    stmt.executeUpdate(drop);
	                    
	                    //Inform client of delete
	                    JOptionPane.showMessageDialog(null, "Database Deleted");
	                // catch SQL exceptions
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
     * 
     * This gets connection to a database
     * 
     * Prompts users with JOptionPanes to get credentials
     * to log in to MySQL database
     * 
     */
        
    public void getConnect() {
    	
            // try statement to catch exceptions
                try{
                	// use JOptionPanes to get users credentials and set variable
                	// and set variables for refresh button.
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
                    
                    // use credentials to build SQL statement
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
                
                // catch SQL error and show in JOptionPane    
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
        }
    
    /**
     * 
     * This refreshes connection
     * 
     * uses variables set during connection to 
     * re-establish a connection.
     * 
     * this is used on the view page after executing 
     * a SQL statement.
     * 
     */
    
    public void refresh() {
        //Get connection
            try{
            	// uses variables set during connection to reconnect
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
                view.setDatabaseText("You are currently at the start of the table.");
                
            // catch SQL errors and show in  JOptionPane    
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
    }
    
    
    /**
     * This creates a new Database
     * 
     * the user is prompted to enter credentials
     * to log in before able to create database
     * 
     * @param dbName pass a user defined name for database 
     * 
     */
    
    public void createDatabase(String dbName) {
        // empty statement
        Statement stmt = null;
        // try statement to catch SQL errors
            try{
            	
            	// use JOptionPanes to get credentials to verify permission to create
                String DURL = JOptionPane.showInputDialog(null, "Enter the server URL or localhost");
                String userID = JOptionPane.showInputDialog(null, "Enter user ID");
                String userPASS = JOptionPane.showInputDialog(null, "Enter user password");
                String MySQL = "jdbc:mysql://" + DURL + "/";
                String SQL = "CREATE DATABASE " + dbName;
                
                //Opens connection to the new selection
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection(MySQL, userID, userPASS);
                
                //Creates database by executing statement
                stmt = con.createStatement();
                stmt.execute(SQL);
                
                // notify user that database was created
                JOptionPane.showMessageDialog(null, "Database " + dbName + " was created.");
                
            // catch SQL errors and ClaasNotFound errors and show in JOptionPane    
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
    }
    
}
