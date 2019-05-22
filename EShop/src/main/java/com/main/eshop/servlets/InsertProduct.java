
package com.main.eshop.servlets;

import com.main.eshop.dao.BrandDAO;
import com.main.eshop.dao.ProductCategoryDAO;
import com.main.eshop.dao.ProductDAO;
import com.main.eshop.model.Product;
import com.main.eshop.util.Image;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;


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
        
        String name = "";
        String desc = "";
        double price = 0;
        int category = 0;
        int brand = 0;

        ArrayList<Image> images = new ArrayList<>();
               
        
        try {
            List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
            
            for (FileItem item : items) {
                if (item.isFormField()) {
                    switch(item.getFieldName()){
                        case "name":
                            name = item.getString();
                            break;
                        case "desc":
                            desc = item.getString();
                            break;
                        case "price":                            
                            price = Double.parseDouble(item.getString());
                            break;
                        case "category":
                            category = Integer.parseInt(item.getString());
                            break;
                        case "brand":
                            brand = Integer.parseInt(item.getString());
                            break;
                    }
                } else {                                        
                    String fileName = FilenameUtils.getName(item.getName());
                    InputStream fileContent = item.getInputStream();
                    
                    images.add(new Image(fileName, fileContent));
                }
            }
        } catch (FileUploadException e) {
            Logger.getLogger(InsertProduct.class.getName()).log(Level.SEVERE, null, e);
            response.sendRedirect("admin.jsp?errorProduct=Errore");
            return;
        } catch(NumberFormatException ex){
            Logger.getLogger(InsertProduct.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect("admin.jsp?errorProduct=Dati Errati");
            return;
        }
        
        
        if(!(name == null || desc == null || name.equals(""))){
            
            ArrayList<String> imgNames = new ArrayList<>();
            
            images.forEach((img) -> {
                imgNames.add(img.getName());
            });
            
            Product product = new Product(0, name, desc, price, ProductCategoryDAO.getProductCategory(category), BrandDAO.getBrand(brand), imgNames);
            
            if(!ProductDAO.productExist(product)){
                if(ProductDAO.insertProduct(product)){
                    response.sendRedirect("admin.jsp?successProduct");
                    
                    int id = ProductDAO.getProduct(product.getName()).getId();
                    String p = getServletContext().getRealPath("/");
                    
                    for(Image img: images){                        
                        File targetFile = new File(p+"ecommerce_images/product/"+id+"/"+img.getName());
                                                
                        FileUtils.copyInputStreamToFile(img.getStream(), targetFile);
                    }
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
