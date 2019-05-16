
package com.main.eshop.servlets;

import com.main.eshop.dao.ProductCategoryDAO;
import com.main.eshop.model.ProductCategory;
import java.io.IOException;
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
        
        if(!(name == null || category == null || name.equals("") || category.equals(""))){
            
            ProductCategory productCategory = null;
            
            if(category.equals("isMain")){
                productCategory = new ProductCategory(0, name);   
            }else{                
                int categoryId = Integer.parseInt(category);
                productCategory = new ProductCategory(0, name, categoryId);   
            }            

            if(!ProductCategoryDAO.categoryExist(productCategory)){
                if(ProductCategoryDAO.insertCategory(productCategory)){
                    response.sendRedirect("admin.jsp?successCategory");
                }else{
                    response.sendRedirect("admin.jsp?errorCategory=Errore durante l'inserimento");
                }
            }else{
                response.sendRedirect("admin.jsp?errorCategory=Categoria gia' esistente");
            }
        }else{
            response.sendRedirect("admin.jsp?errorCategory=Dati Errati");
        }
    }

}
