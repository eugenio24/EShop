
package com.main.eshop.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author eugenio.ferrari
 */
public class DBUtils {
    
    /**
     * Metodo per la chiusura della connessione
     * @param connection Connection to close
     */
    public static void close(Connection connection){
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) { /* ignored */}        
        }
    }
    
    /**
     * Metodo per la chiusura del ResultSet
     * @param resultSet ResultSet to close
     */
    public static void close(ResultSet resultSet){
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) { /* ignored */}
        }
    }
        
    /**
     * Metodo per la chiusura della Statement
     * @param stmt PreparedStatement to close
     */
    public static void close(PreparedStatement stmt){
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) { /* ignored */}            
        }
    }
}
