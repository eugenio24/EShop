package com.main.eshop.servlets.api;

import com.main.eshop.dao.ProductDAO;
import com.main.eshop.model.Product;
import com.main.eshop.util.enums.OrderType;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Gianni
 */
public class LoadProducts extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        ArrayList<Product> list;        
        
        String category = req.getParameter("category");
        String text = req.getParameter("textSearch");
        
        String order = req.getParameter("orderBy");
        
        OrderType orderBy = OrderType.NAME_ASC;
        
        if(order != null){
            orderBy = getOrderType(order);
        }
        
        if(category != null && text != null && !category.equals("0") && !text.equals("")){
            // todo try cath per il parsing
            int categoryId = Integer.parseInt(category);
            
            list = ProductDAO.getProductsByTextAndCategory(categoryId, text, orderBy);
        }else if(category != null && !category.equals("0")){
            // todo try cath per il parsing
            int categoryId = Integer.parseInt(category);
            
            list = ProductDAO.getProductsOfCategory(categoryId, orderBy);
        }else if(text != null && !text.equals("")){
            list = ProductDAO.getProductsByText(text, orderBy);
        }else{            
            list = ProductDAO.getAllProducts(orderBy);
        }
                                          
        req.setAttribute("prodotti", list);        
    }

    
    public OrderType getOrderType(String order){
        switch (order) {
            case "price_asc":
                return OrderType.PRICE_ASC;
            case "price_desc":
                return OrderType.PRICE_DESC;
            case "name_asc":
                return OrderType.NAME_ASC;
            case "name_desc":
                return OrderType.NAME_DESC;
            default:
                return OrderType.NAME_ASC;
        }
    }
        
}
