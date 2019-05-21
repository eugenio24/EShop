
package com.main.eshop.servlets;

import com.main.eshop.dao.BrandDAO;
import com.main.eshop.dao.ProductCategoryDAO;
import com.main.eshop.dao.ProductDAO;
import com.main.eshop.model.Product;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

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

        boolean isMultipart = ServletFileUpload.isMultipartContent(request);

        DiskFileItemFactory factory = new DiskFileItemFactory();
        
        factory.setSizeThreshold(1024*500);
        
        factory.setRepository(new File("c:\\temp"));
        
        ServletFileUpload upload = new ServletFileUpload(factory);

        upload.setSizeMax(1024*500);
        
        File file;

        try { 
            // Parse the request to get file items.
            List fileItems = upload.parseRequest(request);

            // Process the uploaded file items
            Iterator i = fileItems.iterator();

            while (i.hasNext()) {
               FileItem fi = (FileItem)i.next();
               if (!fi.isFormField()) {
                  String fieldName = fi.getFieldName();
                  String fileName = fi.getName();
                  String contentType = fi.getContentType();
                  boolean isInMemory = fi.isInMemory();
                  long sizeInBytes = fi.getSize();

                  if( fileName.lastIndexOf("\\") >= 0 ) {
                     file = new File(fileName.substring( fileName.lastIndexOf("\\"))) ;
                  } else {
                     file = new File(fileName.substring(fileName.lastIndexOf("\\")+1)) ;
                  }
                  fi.write(file);
               }
            }
        } catch(Exception ex) {
           System.out.println(ex);
        }
  
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
            Product product = new Product(0, name, desc, price, ProductCategoryDAO.getProductCategory(category), BrandDAO.getBrand(brand), new ArrayList<>());
            
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
