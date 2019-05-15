
package com.main.eshop.dao;

import com.main.eshop.model.Brand;
import com.main.eshop.util.ConnectionManager;
import com.main.eshop.util.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eugenio
 */
public class BrandDAO {
    
    /**
     * Metodo che controlla se un brand esiste
     * @param brand Brand da controllare
     * @return true if exists
     */
    public static boolean brandExist(Brand brand){
        String sqlQuery = "SELECT * FROM brand WHERE name=?";
        
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        
        boolean result = false;
        
        try {
            connection = ConnectionManager.getConnection();
            stmt = connection.prepareStatement(sqlQuery);
            stmt.setString(1, brand.getName());

            resultSet = stmt.executeQuery();

            result = resultSet.next();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtils.close(resultSet);
            DBUtils.close(stmt);
            DBUtils.close(connection);
        }

        return result;
    }
    
    /**
     * Metodo per inserire un brand
     * @param brand Brand da inserire
     * @return true se l'inserimento è andato a buon fine
     */
    public static boolean insertBrand(Brand brand){
        String sqlQuery = "INSERT INTO `brand` (`id`, `name`) VALUES (NULL, ?)";
        
        Connection connection = null;
        PreparedStatement stmt = null;
        
        boolean result = false;
        
        try {
            connection = ConnectionManager.getConnection();
            stmt = connection.prepareStatement(sqlQuery);
            stmt.setString(1, brand.getName());

            stmt.executeUpdate();
            
            result = true;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtils.close(stmt);
            DBUtils.close(connection);
        }

        return result;
    }
    
}