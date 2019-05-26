
package com.main.eshop.servlets;

import com.main.eshop.dao.ProductDAO;
import com.main.eshop.model.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Eugenio
 */
public class ProductDetail extends HttpServlet {
    
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String idProductStr = request.getParameter("idProduct");
        
        if(idProductStr != null){
            try{                
                int idProduct = Integer.parseInt(idProductStr);
                
                Product product = ProductDAO.getProduct(idProduct);
                
                if(product != null){
                    request.setAttribute("product", product);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/product-detail.jsp");
                    rd.forward(request, response);
                }else{
                    response.sendRedirect("index.jsp");
                }
                
            }catch(NumberFormatException ex){
                Logger.getLogger(ProductDetail.class.getName()).log(Level.SEVERE, null, ex);
                response.sendRedirect("index.jsp");                
            }
        }else{
            response.sendRedirect("index.jsp");
        }
    }


}
