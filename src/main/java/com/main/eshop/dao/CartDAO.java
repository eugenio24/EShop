/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.main.eshop.dao;

import static com.main.eshop.dao.UserDAO.getGoogleUserFromExternalId;
import com.main.eshop.model.Cart;
import com.main.eshop.model.CartRow;
import com.main.eshop.model.Product;
import com.main.eshop.model.User;
import com.main.eshop.util.ConnectionManager;
import com.main.eshop.util.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gianni
 */
public class CartDAO {
    
    public static boolean createCartForNativeUser(User user){
        String sqlQuery = "INSERT INTO `cart`(`id`, `idUser`) VALUES (NULL,?)";
        
        Connection connection = null;
        PreparedStatement stmt = null;
        
        boolean result = false;
        
        try {
            connection = ConnectionManager.getConnection();
            stmt = connection.prepareStatement(sqlQuery);
            
            stmt.setInt(1, UserDAO.getNativeUserFromUsername(user.getUsername()).getId());
            stmt.execute();
            
            result = true;
        } catch (SQLException ex) {
            Logger.getLogger(BrandDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtils.close(stmt);
            DBUtils.close(connection);
        }
        return result;         
    }
    
    public static boolean createCartForGoogleUser(User user){
        String sqlQuery = "INSERT INTO `cart`(`id`, `idUser`) VALUES (NULL,?)";
        
        Connection connection = null;
        PreparedStatement stmt = null;
        
        boolean result = false;
        
        try {
            connection = ConnectionManager.getConnection();
            stmt = connection.prepareStatement(sqlQuery);
            
            stmt.setInt(1, getGoogleUserFromExternalId(user.getExternalId()).getId());
            stmt.execute();
            
            result = true;
        } catch (SQLException ex) {
            Logger.getLogger(BrandDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtils.close(stmt);
            DBUtils.close(connection);
        }
        return result;        
    }
    
    
   public static boolean insertItemToCart(CartRow item){
        String sqlQuery = "INSERT INTO `cart_row`(`id`, `idCart`, `idProduct`, `quantity`) VALUES (NULL,?,?,?)";
        
        Connection connection = null;
        PreparedStatement stmt = null;
        
        boolean result = false;
        int idRow = -1;
        
        try {
            connection = ConnectionManager.getConnection();
            stmt = connection.prepareStatement(sqlQuery);
            
            boolean trovato = false;
            ArrayList<CartRow> cartRow = getAllRowPerCartId(item.getCart().getId());
            
            for(CartRow row : cartRow){
                if(row.getProduct().getId() == item.getProduct().getId()){
                    trovato = true;
                    idRow = row.getId();
                }
            }
            
            if(!trovato){
                stmt.setInt(1, item.getCart().getId());
                stmt.setInt(2, item.getProduct().getId());
                stmt.setInt(3, item.getQuantity());
                stmt.executeUpdate();
            }else{
                updateCartRow(getCardRowFromId(idRow));
            }
       
            result = true;
        } catch (SQLException ex) {
            Logger.getLogger(BrandDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtils.close(stmt);
            DBUtils.close(connection);
        }

        return result;
    }
   
    public static Cart getCartFormUserId(int userId){
       
        String sqlQuery = "SELECT * FROM cart WHERE idUser = ?";
       
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        
        Cart result = null;
       
        try {
            connection = ConnectionManager.getConnection();
            stmt = connection.prepareStatement(sqlQuery);
            
            stmt.setInt(1, userId);
            

            resultSet = stmt.executeQuery();
            
            if(resultSet.next()){
                result = new Cart( resultSet.getInt("id"), UserDAO.getUserFromId(resultSet.getInt("id")));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(BrandDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtils.close(stmt);
            DBUtils.close(connection);
        }
        
        return result;
    }
    
    public static Cart getCartFormCartId(int cartId){
       
        String sqlQuery = "SELECT * FROM cart WHERE id = ?";
       
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        
        Cart result = null;
       
        try {
            connection = ConnectionManager.getConnection();
            stmt = connection.prepareStatement(sqlQuery);
            
            stmt.setInt(1, cartId);
            

            resultSet = stmt.executeQuery();
            
            if(resultSet.next()){
                result = new Cart( resultSet.getInt("id"), UserDAO.getUserFromId(resultSet.getInt("id")));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(BrandDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtils.close(stmt);
            DBUtils.close(connection);
        }
        
        return result;
    }
   
   
   public static ArrayList<CartRow> getAllRowPerCartId(int cartId){
       
       String sqlQuery = "SELECT * FROM cart_row WHERE idCart = ?";
       
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        
        ArrayList<CartRow> result = new ArrayList<>();
       
        try {
            connection = ConnectionManager.getConnection();
            stmt = connection.prepareStatement(sqlQuery);

            stmt.setInt(1, cartId);

            resultSet = stmt.executeQuery();
            
            while(resultSet.next()){ 
                int id = resultSet.getInt("id");
                int idCart = resultSet.getInt("idCart");
                int idProduct = resultSet.getInt("idProduct");
                int quantity = resultSet.getInt("quantity");
                result.add(new CartRow(id,getCartFormCartId(idCart),ProductDAO.getProduct(idProduct),quantity));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(BrandDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtils.close(stmt);
            DBUtils.close(connection);
        }
        
        return result; 
    }
   
    public static CartRow getCardRowFromId(int id){
        String sqlQuery = "SELECT * FROM cart_row WHERE id = ?";
       
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        
        CartRow result = null;
       
        try {
            connection = ConnectionManager.getConnection();
            stmt = connection.prepareStatement(sqlQuery);

            stmt.setInt(1, id);

            resultSet = stmt.executeQuery();
            
            if(resultSet.next()){
                result = new CartRow(id, getCartFormCartId(resultSet.getInt("idCart")), ProductDAO.getProduct(resultSet.getInt("idProduct")), resultSet.getInt("quantity"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(BrandDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtils.close(stmt);
            DBUtils.close(connection);
        }
        
        return result;
    }
   
    public static boolean updateCartRow(CartRow row){
        
        String sqlQuery = "UPDATE `cart_row` SET `id` = ?,`idCart` = ?,`idProduct` = ?,`quantity`= ? WHERE id = ?";
       
        Connection connection = null;
        PreparedStatement stmt = null;
        
        boolean result = false;
       
        try {
            connection = ConnectionManager.getConnection();
            stmt = connection.prepareStatement(sqlQuery);

            stmt.setInt(1, row.getId());
            stmt.setInt(2, row.getCart().getId());
            stmt.setInt(3, row.getProduct().getId());
            stmt.setInt(4, (row.getQuantity()+1));
            stmt.setInt(5, row.getId());

            stmt.executeUpdate();
            
            result = true;
            
        } catch (SQLException ex) {
            Logger.getLogger(BrandDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtils.close(stmt);
            DBUtils.close(connection);
        }
        
        return result;
        
    }
    
}
