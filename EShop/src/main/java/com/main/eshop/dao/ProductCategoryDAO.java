
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
                String name = resultSet.getString("name");                
                int idMain = resultSet.getInt("main_category");
                if(idMain != 0){
                    result.add(new ProductCategory(name, getProductCategory(idMain)));
                }else{
                    result.add(new ProductCategory(name));
                }                               
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
                
                if(idMain != 0){
                    result = new ProductCategory(name, getProductCategory(idMain));
                }else{
                    result = new ProductCategory(name);
                }
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
    
}
