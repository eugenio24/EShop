
package com.main.eshop.servlets;

import com.main.eshop.dao.ProductCategoryDAO;
import com.main.eshop.dao.UserDAO;
import com.main.eshop.model.ProductCategory;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author eugenio.ferrari
 */
public class InsertProductCategory extends HttpServlet {

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
        
        String name = request.getParameter("name");
        String category = request.getParameter("category");
        
        if(!(name.equals("") || category.equals(""))){
                int categoryId = Integer.parseInt(category);
                ProductCategory productCategory = new ProductCategory(0, name, categoryId);   
                
                if(!ProductCategoryDAO.categoryExist(productCategory)){
                    
                }else{
                    // categoria gia esistente
                }
        }else{
            // errore dati
        }
    }

}
