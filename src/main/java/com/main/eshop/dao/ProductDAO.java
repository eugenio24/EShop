package com.main.eshop.dao;

import com.main.eshop.model.Brand;
import com.main.eshop.model.Product;
import com.main.eshop.model.ProductCategory;
import com.main.eshop.util.ConnectionManager;
import com.main.eshop.util.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gianni.benigni
 */
public class ProductDAO {
    
    /**
     * Metodo che ritorna la lista di tutti i prodotti
     * @return ArrayList of Products
     */
    public static ArrayList<Product> getAllProducts(){
        String sqlQuery = "SELECT product.*, product_category.*, brand.*"
                        + "FROM product_category, product, brand "
                        + "WHERE product.category = product_category.id AND product.brand = brand.id";
        
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        
        ArrayList<Product> result = new ArrayList<>();
        
        try {
            connection = ConnectionManager.getConnection();
            stmt = connection.prepareStatement(sqlQuery);

            resultSet = stmt.executeQuery();

            while(resultSet.next()){
                int id = resultSet.getInt("product.id"); 
                String name = resultSet.getString("name");                
                String desc = resultSet.getString("descrizione");
                double price = resultSet.getDouble("price");
                String images = resultSet.getString("image_path");
                
                ArrayList<String> imagesList = new ArrayList<>();
                String[] array = images.split(";");
                
                for(String image: array) {
                    imagesList.add("ecommerce_images/product/"+id+"/"+image);
                }

                ProductCategory category = new ProductCategory(resultSet.getInt("product_category.id"), resultSet.getString("product_category.name"), resultSet.getInt("product_category.main_category"));                
                Brand brand = new Brand(resultSet.getInt("brand.id"), resultSet.getString("brand.name"));
                
                result.add(new Product(id, name, desc, price, category, brand, imagesList));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtils.close(resultSet);
            DBUtils.close(stmt);
            DBUtils.close(connection);
        }
        
        return result;
    }
    
    /**
     * Metodo che controlla se un utente esiste
     * @param product Product da cercare
     * @return 
     */
    public static boolean productExist(Product product){
        String sqlQuery = "SELECT * FROM product WHERE name=?";
        
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        
        boolean result = false;
        
        try {
            connection = ConnectionManager.getConnection();
            stmt = connection.prepareStatement(sqlQuery);
            stmt.setString(1, product.getName());

            resultSet = stmt.executeQuery();

            result = resultSet.next();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtils.close(resultSet);
            DBUtils.close(stmt);
            DBUtils.close(connection);
        }

        return result;
    }
    
    /**
     * Metodo per inserire un prodotto
     * @param product Product da inserire
     * @return 
     */
    public static boolean insertProduct(Product product){
        String sqlQuery = "INSERT INTO `product` (`id`, `name`, `descrizione`, `price`, `category`, `brand`, `image_path`) "
                        + "VALUES (NULL, ?, ?, ?, ?, ?, ?)";
        
        Connection connection = null;
        PreparedStatement stmt = null;
        
        boolean result = false;
        
        try {
            connection = ConnectionManager.getConnection();
            stmt = connection.prepareStatement(sqlQuery);
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDesc());
            stmt.setDouble(3, product.getPrice());
            stmt.setInt(4, product.getCategory().getId());
            stmt.setInt(5, product.getBrand().getId());
            stmt.setString(6, product.listProductImages());

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
