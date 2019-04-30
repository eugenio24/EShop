
package com.main.eshop.util;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Eugenio
 */
public class ConnectionManager {
    
//    public static final String URL = "jdbc:mysql://52.29.239.198:3306/sql7289239";
//    public static final String USER = "sql7289239";
//    public static final String PSW = "Progetto123#"; 
      
    public static final String URL = "jdbc:mysql://localhost:3306/eshop";
    public static final String USER = "root";
    public static final String PSW = ""; 
        
    public static Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PSW);        
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return connection; 
    }
}
