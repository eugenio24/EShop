package com.main.eshop.dao;

import com.main.eshop.model.Product;
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
 * @author gianni.benigni
 */
public class ProductDAO {
    
    /**
     * Metodo che ritorna la lista di tutti i prodotti
     * @return ArrayList of Products
     */
    public static ArrayList<Product> getAllProducts(){
        String sqlQuery = "SELECT product.id, product.name, product.descrizione, product.price, product.image_path, product_category.name as categoria, brand.name as brand FROM product_category,product,brand WHERE product.category = product_category.id AND product.brand = brand.id";
        
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        
        ArrayList<Product> result = new ArrayList<>();
        
        try {
            connection = ConnectionManager.getConnection();
            stmt = connection.prepareStatement(sqlQuery);

            resultSet = stmt.executeQuery();

            while(resultSet.next()){
                int id = resultSet.getInt("id"); 
                String name = resultSet.getString("name");                
                String descrizione = resultSet.getString("descrizione");
                double prezzo = resultSet.getDouble("price");
                String caregoria = resultSet.getString("categoria");
                String brand = resultSet.getString("brand");
                String urlImmagine = resultSet.getString("image_path");
                
                result.add(new Product(id, name, descrizione, prezzo, caregoria, brand, urlImmagine));
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
    
    
}
