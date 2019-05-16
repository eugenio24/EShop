
package com.main.eshop.servlets;

import com.main.eshop.dao.BrandDAO;
import com.main.eshop.model.Brand;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Eugenio
 */
public class InsertBrand extends HttpServlet {

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
        
        if(name != null && !name.equals("")){
            Brand brand = new Brand(0, name);
            
            if(!BrandDAO.brandExist(brand)){
                if(BrandDAO.insertBrand(brand)){
                    response.sendRedirect("admin.jsp?successBrand");
                }else{
                    response.sendRedirect("admin.jsp?errorBrand=Errore durante l'inserimento");
                }
            }else{
                response.sendRedirect("admin.jsp?errorBrand=Marca gia' esistente");
            }
        }else{
            response.sendRedirect("admin.jsp?errorBrand=Dati Errati");
        }
    }

}