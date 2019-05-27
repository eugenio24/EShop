package com.main.eshop.dao;

import com.main.eshop.model.Brand;
import com.main.eshop.model.Product;
import com.main.eshop.model.ProductCategory;
import com.main.eshop.util.ConnectionManager;
import com.main.eshop.util.DBUtils;
import com.main.eshop.util.enums.OrderType;
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
     * @param orderBy
     * @return ArrayList of Products
     */
    public static ArrayList<Product> getAllProducts(OrderType orderBy){
        String sqlQuery = "SELECT product.*, product_category.*, brand.*"
                        + "FROM product_category, product, brand "
                        + "WHERE product.category = product_category.id AND product.brand = brand.id ORDER BY " + getOrderBy(orderBy);
        
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
     * Metodo che controlla se un prodotto esiste
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
     * Metodo che ritorna la lista di tutti i prodotti di una categoria
     * @param categoryId
     * @param orderBy
     * @return ArrayList of Products
     */
    public static ArrayList<Product> getProductsOfCategory(int categoryId, OrderType orderBy){
        String sqlQuery = "SELECT product.*, product_category.*, brand.*"
                        + "FROM product_category, product, brand "
                        + "WHERE product.category = product_category.id AND product.brand = brand.id AND product.category=? ORDER BY " + getOrderBy(orderBy);
        
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        
        ArrayList<Product> result = new ArrayList<>();
        
        try {
            connection = ConnectionManager.getConnection();
            stmt = connection.prepareStatement(sqlQuery);
            
            stmt.setInt(1, categoryId);

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
     * Metodo che ritorna la lista di tutti i prodotti che contengono una certa stringa
     * @param text
     * @param orderBy
     * @return ArrayList of Products
     */
    public static ArrayList<Product> getProductsByText(String text, OrderType orderBy){
        String sqlQuery = "SELECT product.*, product_category.*, brand.*"
                        + "FROM product_category, product, brand "
                        + "WHERE product.category = product_category.id AND product.brand = brand.id AND product.name LIKE ? ORDER BY " + getOrderBy(orderBy);
        
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        
        ArrayList<Product> result = new ArrayList<>();
        
        try {
            connection = ConnectionManager.getConnection();
            stmt = connection.prepareStatement(sqlQuery);
                        
            stmt.setString(1, "%"+text+"%");

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
     * Metodo che ritorna la lista di tutti i prodotti di una categoria e con un nome simile
     * @param categoryId
     * @param text
     * @param orderBy
     * @return ArrayList of Products
     */
    public static ArrayList<Product> getProductsByTextAndCategory(int categoryId, String text, OrderType orderBy){
        String sqlQuery = "SELECT product.*, product_category.*, brand.*"
                        + "FROM product_category, product, brand "
                        + "WHERE product.category = product_category.id AND product.brand = brand.id AND product.category=? AND product.name LIKE ? ORDER BY " + getOrderBy(orderBy);
        
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        
        ArrayList<Product> result = new ArrayList<>();
        
        try {
            connection = ConnectionManager.getConnection();
            stmt = connection.prepareStatement(sqlQuery);
            
            stmt.setInt(1, categoryId);
            stmt.setString(2, "%" + text + "%"); 

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
    
    /**
     * Metodo che ritorna un prodotto 
     * @param nameToSearch del Prodotto da cercare
     * @return Prodotto cercato
     */
    public static Product getProduct(String nameToSearch){
        String sqlQuery = "SELECT product.*, product_category.*, brand.*"
                + "FROM product_category, product, brand "
                + "WHERE product.category = product_category.id AND product.brand = brand.id "
                + "AND product.name=?";
        
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        
        Product result = null;
        
        try {
            connection = ConnectionManager.getConnection();
            stmt = connection.prepareStatement(sqlQuery);
            stmt.setString(1, nameToSearch);

            resultSet = stmt.executeQuery();

            if(resultSet.next()){
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
                
                result = new Product(id, name, desc, price, category, brand, imagesList);
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
     * Metodo che ritorna un prodotto 
     * @param idProduct del Prodotto da cercare
     * @return Prodotto cercato
     */
    public static Product getProduct(int idProduct){
        String sqlQuery = "SELECT product.*, product_category.*, brand.*"
                + "FROM product_category, product, brand "
                + "WHERE product.category = product_category.id AND product.brand = brand.id "
                + "AND product.id=?";
        
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        
        Product result = null;
        
        try {
            connection = ConnectionManager.getConnection();
            stmt = connection.prepareStatement(sqlQuery);
            stmt.setInt(1, idProduct);

            resultSet = stmt.executeQuery();

            if(resultSet.next()){
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
                
                result = new Product(id, name, desc, price, category, brand, imagesList);
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
    
    public static String getOrderBy(OrderType order){
        switch (order) {
            case NAME_ASC:
                return "product.name ASC";
            case NAME_DESC:
                return "product.name DESC";
            case PRICE_ASC:
                return "product.price ASC";
            case PRICE_DESC:
                return "product.price DESC";
            default:
                return "product.name ASC";
        }
    }
                   
}
