/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.main.eshop.servlets.api;

import com.main.eshop.dao.CartDAO;
import com.main.eshop.dao.ProductDAO;
import com.main.eshop.model.CartRow;
import com.main.eshop.model.Product;
import com.main.eshop.model.User;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Gianni
 */
public class LoadCart extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
       
        HttpSession session = req.getSession(false);
        
        if(session != null && session.getAttribute("currentUser") != null){  
            User user = (User)session.getAttribute("currentUser"); 
            
            ArrayList<CartRow> list = CartDAO.getAllRowPerCartId(CartDAO.getCartFormUserId(user.getId()).getId());
            req.setAttribute("prodotti",list);
        }
        
        
    }

    

}
