
package com.main.eshop.dao;

import com.main.eshop.model.ProductCategory;
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
 * @author eugenio.ferrari
 */
public class ProductCategoryDAO {
    
    /**
     * Metodo che ritorna la lista di tutte le categorie
     * @return ArrayList of ProductCategory
     */
    public static ArrayList<ProductCategory> getListCategories(){
        String sqlQuery = "SELECT * FROM product_category";
        
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        
        ArrayList<ProductCategory> result = new ArrayList<>();
        
        try {
            connection = ConnectionManager.getConnection();
            stmt = connection.prepareStatement(sqlQuery);

            resultSet = stmt.executeQuery();

            while(resultSet.next()){
                int id = resultSet.getInt("id"); 
                String name = resultSet.getString("name");                
                int idMain = resultSet.getInt("main_category");
                
                result.add(new ProductCategory(id, name, idMain));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductCategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtils.close(resultSet);
            DBUtils.close(stmt);
            DBUtils.close(connection);
        }
        
        return result;
    }
    
    /**
     * Metodo che ritorna una categoria in base all'id
     * @param id Id da cercare
     * @return ProductCategory
     */
    public static ProductCategory getProductCategory(int id){
        String sqlQuery = "SELECT * FROM product_category WHERE id=?";
        
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        
        ProductCategory result = null;
        
        try {
            connection = ConnectionManager.getConnection();
            stmt = connection.prepareStatement(sqlQuery);
            stmt.setInt(1, id);

            resultSet = stmt.executeQuery();

            if(resultSet.next()){  
                String name = resultSet.getString("name");                
                int idMain = resultSet.getInt("main_category");
                
                result = new ProductCategory(id, name, idMain);
            }            
        } catch (SQLException ex) {
            Logger.getLogger(ProductCategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtils.close(resultSet);
            DBUtils.close(stmt);
            DBUtils.close(connection);
        }
        
        return result;
    }
    
    /**
     * Metodo che controlla se una categoria esite
     * @param category Categoria da cercare
     * @return 
     */
    public static boolean categoryExist(ProductCategory category){
        String sqlQuery = "SELECT * FROM brand WHERE name=?";
        
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        
        boolean result = false;
        
        try {
            connection = ConnectionManager.getConnection();
            stmt = connection.prepareStatement(sqlQuery);
            stmt.setString(1, category.getName());

            resultSet = stmt.executeQuery();

            result = resultSet.next();
        } catch (SQLException ex) {
            Logger.getLogger(ProductCategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtils.close(resultSet);
            DBUtils.close(stmt);
            DBUtils.close(connection);
        }

        return result;
    }
    
    /**
     * Metodo che inserisce una categoria
     * @param category Categoria da inserire 
     * @return Ritorna il risultato del inserimento
     */
    public static boolean insertCategory(ProductCategory category){
        String main = category.isIsMainCategory() ? "NULL" : "?";
        String sqlQuery = "INSERT INTO `product_category` (`id`, `name`, `is_main`, `main_category`) VALUES (NULL, ?, ?, "+ main +")";
        
        Connection connection = null;
        PreparedStatement stmt = null;
        
        boolean result = false;
        
        try {
            connection = ConnectionManager.getConnection();
            stmt = connection.prepareStatement(sqlQuery);
            stmt.setString(1, category.getName());
            stmt.setBoolean(2, category.isIsMainCategory());
            
            if(!category.isIsMainCategory()){
                stmt.setInt(3, category.getMainCategoryId());
            }

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
