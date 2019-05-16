
package com.main.eshop.servlets;

import com.main.eshop.dao.BrandDAO;
import com.main.eshop.dao.ProductCategoryDAO;
import com.main.eshop.dao.ProductDAO;
import com.main.eshop.model.Product;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Eugenio
 */
public class InsertProduct extends HttpServlet {

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
        
        double price = 0;
        int category = 0;
        int brand = 0;
        
        String name = request.getParameter("name");
        String desc = request.getParameter("desc");        
        
        try{
            price = Double.parseDouble(request.getParameter("price"));
            category = Integer.parseInt(request.getParameter("category"));
            brand = Integer.parseInt(request.getParameter("brand"));
        }catch(NumberFormatException ex){
            Logger.getLogger(InsertProduct.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect("admin.jsp?errorProduct=Dati Errati");
            return;
        }
        
        
        if(!(name == null || desc == null || name.equals(""))){
            Product product = new Product(0, name, desc, price, ProductCategoryDAO.getProductCategory(category), BrandDAO.getBrand(brand), "");
            
            if(!ProductDAO.productExist(product)){
                if(ProductDAO.insertProduct(product)){
                    response.sendRedirect("admin.jsp?successProduct");
                }else{
                    response.sendRedirect("admin.jsp?errorProduct=Errore durante l'inserimento");
                }
            }else{
                response.sendRedirect("admin.jsp?errorProduct=Prodotto gia' esistente");
            }
        }else{
            response.sendRedirect("admin.jsp?errorProduct=Dati Errati");            
        }
    }

}
