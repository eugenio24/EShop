
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
     * Metodo che controlla se un utente esiste
     * @param user Utente da cercare
     * @return true if exist
     */
    public static boolean userExist(User user){
        String sql = "SELECT * FROM user WHERE ";
        sql += user.getRegistrationType() == RegistrationType.NATIVE ? "username=?" : "external_id=?";
        
        try {
            PreparedStatement stmt = ConnectionManager.getConnection().prepareStatement(sql);
            stmt.setString(1, user.getRegistrationType() == RegistrationType.NATIVE ? user.getUsername() : user.getExternalId());
            
            ResultSet result = stmt.executeQuery();
            
            return result.next();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }        
        
        return false;
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
    
    /**
     * Metodo che aggiunge un Utente
     * @param user Utente da aggiungere
     * @return risultato dell'inserimento
     */
    public static boolean insertGoogleUser(User user){
        String sqlQuery = "INSERT INTO `user` (`id`, `name`, `surname`, `email`, `registration_date`, `registration_type`, `external_id`) "
                        + "VALUES (NULL, ?, ?, ?, ?, 'GOOGLE', ?)";
        
        try {
            PreparedStatement stmt = ConnectionManager.getConnection().prepareStatement(sqlQuery);
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getSurname());
            stmt.setString(3, user.getEmail());
            stmt.setTimestamp(4, user.getRegistrationDate());
            stmt.setString(5, user.getExternalId());
            
            stmt.executeUpdate();
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
