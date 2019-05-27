
package com.main.eshop.servlets;

import com.main.eshop.dao.CartDAO;
import com.main.eshop.dao.OrderDAO;
import com.main.eshop.model.Cart;
import com.main.eshop.model.CartRow;
import com.main.eshop.model.OrderHead;
import com.main.eshop.model.OrderRow;
import com.main.eshop.model.User;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;

/**
 *
 * @author Eugenio
 */
public class ProcessOrder extends HttpServlet {

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                
        Cart cart;
        ArrayList<CartRow> cartRows = new ArrayList<>();
        HttpSession session = request.getSession(false);  
        
        String shippingAddress = request.getParameter("shippingAddress");
        
        if(session != null && session.getAttribute("currentUser") != null){
            User user = (User)session.getAttribute("currentUser");
                        
            cart = CartDAO.getCartFormUserId(user.getId());
            cartRows = CartDAO.getListCartRow(cart);   
            
            int totalPrice = 0;
            
            for (CartRow cartRow : cartRows) {
                totalPrice+=cartRow.getProduct().getPrice()*cartRow.getQuantity();
            }
            
            OrderHead orderHead = new OrderHead(0, user, new Timestamp(System.currentTimeMillis()), totalPrice, shippingAddress);
            
            ArrayList<OrderRow> orderRows = new ArrayList<>();
            
            for (CartRow cartRow : cartRows) {
                orderRows.add(new OrderRow(0, orderHead, cartRow.getProduct(), cartRow.getQuantity(), cartRow.getProduct().getPrice()));
            }
            
            OrderDAO.insertOrderHead(orderHead);
            OrderDAO.insertOrderRows(orderRows);
            
            CartDAO.deteteRows(cart);
            
            request.setAttribute("orderHead", orderHead);
            request.setAttribute("orderRows", orderRows);
            
            request.getRequestDispatcher("order.jsp").forward(request, response); 
        }else{
            // todo sistemare
            response.sendRedirect("login.jsp");
        }
    }


}
