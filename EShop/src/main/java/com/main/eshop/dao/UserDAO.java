package com.main.eshop.dao;

import com.main.eshop.model.User;
import com.main.eshop.util.ConnectionManager;
import com.main.eshop.util.DBUtils;
import com.main.eshop.util.enums.LoginResult;
import com.main.eshop.util.enums.RegistrationType;
import java.sql.Connection;
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
     *
     * @param username Username del Utente
     * @param pswToCheck Password dell'utente
     * @return LoginResult
     */
    public static LoginResult authenticateNativeUser(String username, String pswToCheck) {
        String sqlQuery = "SELECT psw_hash, psw_salt FROM user WHERE username=?";

        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;

        LoginResult result = LoginResult.INVALID;

        try {
            connection = ConnectionManager.getConnection();
            stmt = connection.prepareStatement(sqlQuery);
            stmt.setString(1, username);

            resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                String storedHash = resultSet.getString("psw_hash");
                String storedSalt = resultSet.getString("psw_salt");

                String hashToCheck = BCrypt.hashpw(pswToCheck, storedSalt);

                result = hashToCheck.equals(storedHash) ? LoginResult.VALID : LoginResult.INVALID;
            }
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
     * Metodo che controlla se un utente esiste
     *
     * @param user Utente da cercare
     * @return true if exist
     */
    public static boolean userExist(User user) {
        String sql = "SELECT * FROM user WHERE ";
        sql += user.getRegistrationType() == RegistrationType.NATIVE ? "username=?" : "external_id=?";

        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;

        boolean result = false;

        try {
            connection = ConnectionManager.getConnection();
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, user.getRegistrationType() == RegistrationType.NATIVE ? user.getUsername() : user.getExternalId());

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
     * Metodo che ritorna un utente
     *
     * @param username Username del utente
     * @return User Model
     */
    public static User getNativeUserFromUsername(String username) {
        String sqlQuery = "SELECT * FROM user WHERE username=?";

        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;

        User result = null;

        try {
            connection = ConnectionManager.getConnection();
            stmt = connection.prepareStatement(sqlQuery);
            stmt.setString(1, username);

            resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                result = new User(resultSet.getString("email"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getTimestamp("registration_date"),
                        resultSet.getString("username"),
                        RegistrationType.NATIVE,
                        resultSet.getBoolean("is_admin"));
            }
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
     * Metodo che aggiunge un Utente
     *
     * @param user Utente da aggiungere
     * @return risultato dell'inserimento
     */
    public static boolean insertGoogleUser(User user) {
        String sqlQuery = "INSERT INTO `user` (`id`, `name`, `surname`, `email`, `registration_date`, `registration_type`, `external_id`, `is_admin`) "
                + "VALUES (NULL, ?, ?, ?, ?, 'GOOGLE', ?, '0')";

        Connection connection = null;
        PreparedStatement stmt = null;

        boolean result = false;

        try {
            connection = ConnectionManager.getConnection();
            stmt = connection.prepareStatement(sqlQuery);
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getSurname());
            stmt.setString(3, user.getEmail());
            stmt.setTimestamp(4, user.getRegistrationDate());
            stmt.setString(5, user.getExternalId());

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

    /**
     * Metodo che aggiunge un utente nativo
     *
     * @param user Dati dell utente
     * @param salt salt della psw
     * @param psw hash della psw + salt
     * @return risultato dell inserimento
     */
    public static boolean insertNativeUser(User user, String salt, String psw) {
        String sqlQuery = "INSERT INTO `user`(`id`, `name`, `surname`, `username`, `email`, `psw_hash`, `psw_salt`, `registration_date`, `registration_type`, `is_admin`)"
                + " VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, 'NATIVE', '0')";

        Connection connection = null;
        PreparedStatement stmt = null;

        boolean result = false;

        try {
            connection = ConnectionManager.getConnection();
            stmt = connection.prepareStatement(sqlQuery);
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getSurname());
            stmt.setString(3, user.getUsername());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, psw);
            stmt.setString(6, salt);
            stmt.setTimestamp(7, user.getRegistrationDate());

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

    /**
     * Metodo che controlla l' esistenza di un username
     *
     * @param username username da controllare
     * @return 
     */
    public static boolean checkUserFromUsername(String username) {
        String sqlQuery = "SELECT * FROM user WHERE username=?";

        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;

        boolean result = false;

        try {
            connection = ConnectionManager.getConnection();
            stmt = connection.prepareStatement(sqlQuery);
            stmt.setString(1, username);

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
}
