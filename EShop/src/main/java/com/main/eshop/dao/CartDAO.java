/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.main.eshop.dao;

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
    
    public static boolean createCart(User user){
        String sqlQuery = "INSERT INTO `cart`(`id`, `idUser`) VALUES (NULL, ?)";
        
        Connection connection = null;
        PreparedStatement stmt = null;
        
        boolean result = false;
        
        try {
            connection = ConnectionManager.getConnection();
            stmt = connection.prepareStatement(sqlQuery);
            
            stmt.setInt(1, user.getId());
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
    
    public static boolean insertItemToCart(int idCart, int idProduct, int quantity){
        String sqlQuery = "INSERT INTO `cart_row`(`id`, `idCart`, `idProduct`, `quantity`) VALUES (NULL,?,?,?)";
        
        Connection connection = null;
        PreparedStatement stmt = null;
        
        boolean result = false;
        
        try {
            connection = ConnectionManager.getConnection();
            stmt = connection.prepareStatement(sqlQuery);
            
            stmt.setInt(1, idCart);
            stmt.setInt(2, idProduct);
            stmt.setInt(3, quantity);

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
   
    public static Cart getCartFormUserId(int userId){       
        String sqlQuery = "SELECT * FROM cart WHERE idUser=?";
       
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
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtils.close(resultSet);
            DBUtils.close(stmt);
            DBUtils.close(connection);            
        }
        
        return result;
    }
   
    /**
     * Metodo che ritorna la lista delle righe del carrello
     * @param cart id cart
     * @return 
     */
    public static ArrayList<CartRow> getListCartRow(Cart cart){
        String sqlQuery = "SELECT * FROM cart_row WHERE idCart=?";
       
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        
        ArrayList<CartRow> result = new ArrayList<>();
       
        try {
            connection = ConnectionManager.getConnection();
            stmt = connection.prepareStatement(sqlQuery);
            
            stmt.setInt(1, cart.getId());
            
            resultSet = stmt.executeQuery();
            
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                int quantity = resultSet.getInt("quantity");
                
                Product product = ProductDAO.getProduct(resultSet.getInt("idProduct"));
                
                result.add(new CartRow(id, cart, product, quantity));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtils.close(resultSet);
            DBUtils.close(stmt);
            DBUtils.close(connection);            
        }
        
        return result;
    }
    
    /**
     * etodo che controlla se esiste gia una riga con quel prodotto
     * @param cart
     * @param idProduct 
     * @return 
     */
    public static boolean rowProductExist(Cart cart, int idProduct){
        String sqlQuery = "SELECT * FROM cart_row WHERE idCart=? AND idProduct=?";
        
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        
        boolean result = false;
        int idRow = -1;
        
        try {
            connection = ConnectionManager.getConnection();
            stmt = connection.prepareStatement(sqlQuery);
            
            stmt.setInt(1, cart.getId());
            stmt.setInt(2, idProduct);

            resultSet = stmt.executeQuery();
            
            result = resultSet.next();
        } catch (SQLException ex) {
            Logger.getLogger(BrandDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtils.close(resultSet);
            DBUtils.close(stmt);
            DBUtils.close(connection);
        }

        return result;
    }
    
    public static CartRow getCartRowFromProduct(Cart cart, int idProduct){
        String sqlQuery = "SELECT * FROM cart_row WHERE idCart=? AND idProduct=?";
       
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        
        CartRow result = null;
       
        try {
            connection = ConnectionManager.getConnection();
            stmt = connection.prepareStatement(sqlQuery);
            
            stmt.setInt(1, cart.getId());
            stmt.setInt(2, idProduct);
            
            resultSet = stmt.executeQuery();
            
            if(resultSet.next()){
                int id = resultSet.getInt("id");
                int quantity = resultSet.getInt("quantity");
                
                Product product = ProductDAO.getProduct(resultSet.getInt("idProduct"));
                
                result = new CartRow(id, cart, product, quantity);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtils.close(resultSet);
            DBUtils.close(stmt);
            DBUtils.close(connection);            
        }
        
        return result;
    }
    
    public static boolean incrementQuantityRow(CartRow row, int quantity){
        String sqlQuery = "UPDATE `cart_row` SET `quantity`=? WHERE id=?";
        
        Connection connection = null;
        PreparedStatement stmt = null;
        
        boolean result = false;
        
        try {
            connection = ConnectionManager.getConnection();
            stmt = connection.prepareStatement(sqlQuery);
            
            stmt.setInt(1, row.getQuantity()+quantity);
            stmt.setInt(2, row.getId());

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
