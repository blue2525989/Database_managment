package BlueDB;

import java.sql.SQLException;

/**
 * author: Jason Bierbrauer
 * program: BlueDB
 * function: Manage a MySQL server. 
 * currently set up locally.
 */


public class BlueMainDB {
    
    ControlActions conA = new ControlActions();
    public BlueMainDB() throws SQLException{
        
      conA.main.loadMainUI();
    }
    public static void main(String[] args) throws SQLException{
        new BlueMainDB();
    }
    
}