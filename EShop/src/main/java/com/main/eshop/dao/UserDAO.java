
package com.main.eshop.dao;

import com.main.eshop.model.User;
import com.main.eshop.util.ConnectionManager;
import com.main.eshop.util.enums.LoginResult;
import com.main.eshop.util.enums.RegistrationType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 *
 * @author Eugenio
 */
public class UserDAO {
    
    /**
     * Metodo per autenticare un utente
     * @param username Username del Utente
     * @param pswToCheck Password dell'utente
     * @return LoginResult
     */
    public static LoginResult authenticateNativeUser(String username, String pswToCheck){
        String sqlQuery = "SELECT psw_hash, psw_salt FROM user WHERE username=?";        
        
        try {
            PreparedStatement stmt = ConnectionManager.getConnection().prepareStatement(sqlQuery);
            stmt.setString(1, username);
            
            ResultSet result = stmt.executeQuery();
            
            if(result.next()){
                String storedHash = result.getString("psw_hash");
                String storedSalt = result.getString("psw_salt");
                
                String hashToCheck = BCrypt.hashpw(pswToCheck, storedSalt);
                
                return hashToCheck.equals(storedHash) ? LoginResult.VALID : LoginResult.INVALID;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return LoginResult.INVALID;
    }
    
    /**
     * Metodo che ritorna un utente
     * @param username Username del utente
     * @return User Model
     */
    public static User getNativeUserFromUsername(String username){
        String sqlQuery = "SELECT * FROM user WHERE username=?";        
        
        try {
            PreparedStatement stmt = ConnectionManager.getConnection().prepareStatement(sqlQuery);
            stmt.setString(1, username);
            
            ResultSet result = stmt.executeQuery();
            
            if(result.next()){
                return new User(result.getString("email"), 
                                result.getString("name"), 
                                result.getString("surname"), 
                                result.getTimestamp("registration_date"), 
                                result.getString("username"), 
                                RegistrationType.NATIVE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
}
