
package com.main.eshop.dao;

import com.main.eshop.model.Brand;
import com.main.eshop.model.OrderHead;
import com.main.eshop.model.OrderRow;
import com.main.eshop.util.ConnectionManager;
import com.main.eshop.util.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eugenio
 */
public class OrderDAO {
        /**
     * Metodo per inserire un orderhead
     * @param orderHead 
     * @return true se l'inserimento è andato a buon fine
     */
    public static boolean insertOrderHead(OrderHead orderHead){
        String sqlQuery = "INSERT INTO `order_head` (`order_number`, `idUser`, `order_date`, `total_price`, `shipping_address`) VALUES (NULL, ?, ?, ?, ?)";
        
        Connection connection = null;
        PreparedStatement stmt = null;
        
        boolean result = false;
        
        try {
            connection = ConnectionManager.getConnection();
            stmt = connection.prepareStatement(sqlQuery);
            
            stmt.setInt(1, orderHead.getUser().getId());
            stmt.setTimestamp(2, orderHead.getOrderDate());
            stmt.setDouble(3, orderHead.getTotalPrice());
            stmt.setString(4, orderHead.getShippingAddress());

            stmt.executeUpdate();
            
            result = true;
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtils.close(stmt);
            DBUtils.close(connection);
        }

        return result;
    }
    
    public static boolean insertOrderRows(ArrayList<OrderRow> list){
        String sqlQuery = "INSERT INTO `order_row` (`id`, `idOrder`, `idProduct`, `quantity`, `price`) VALUES (NULL, ?, ?, ?, ?)";        
        
        Connection connection = null;
        PreparedStatement stmt = null;
        
        boolean result = false;
        
        try {
            connection = ConnectionManager.getConnection();
            stmt = connection.prepareStatement(sqlQuery);
            
            int count = 0;
            for (OrderRow orderRow : list) {
                stmt.setInt(1, orderRow.getOrderHead().getOrderNumber());
                stmt.setInt(2, orderRow.getProduct().getId());
                stmt.setInt(3, orderRow.getQuantity());
                stmt.setDouble(4, orderRow.getPrice());
                
                stmt.addBatch();
                count++;
                
                if (count % 100 == 0 || count == list.size()) {
                    stmt.executeBatch();
                }
            }
            
            stmt.executeUpdate();
            
            result = true;
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtils.close(stmt);
            DBUtils.close(connection);
        }

        return result;
    }
}
