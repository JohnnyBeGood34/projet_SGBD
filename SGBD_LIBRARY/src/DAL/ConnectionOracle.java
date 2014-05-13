/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Easy
 */
public class ConnectionOracle {
    
    private static Connection connection;
    
    
    
    public static Connection getInstance(String url, String user, String pass) {
        
        if(connection==null){
            try {
                connection= DriverManager.getConnection(url,user,pass);
            } catch (SQLException ex) {
                Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return connection;
    }
    
}
