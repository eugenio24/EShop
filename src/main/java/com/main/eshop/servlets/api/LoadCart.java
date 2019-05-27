/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.main.eshop.servlets.api;

import com.main.eshop.dao.CartDAO;
import com.main.eshop.dao.ProductDAO;
import com.main.eshop.model.Cart;
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
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Gianni
 */
public class LoadCart extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        
        HttpSession session = req.getSession(false);
        
        Cart cart = null;
        ArrayList<CartRow> cartRows = new ArrayList<>();
        
        if(session != null && session.getAttribute("currentUser") != null){
            User user = (User)session.getAttribute("currentUser");
            
            cart = CartDAO.getCartFormUserId(user.getId());
            cartRows = CartDAO.getListCartRow(cart);            
        }else{
            String cookie = findCookie(req.getCookies());
            JSONObject jsonCookie;

            if(cookie != null){
                jsonCookie = new JSONObject(cookie);
                
                JSONArray array = jsonCookie.getJSONArray("products");
                
                for (int i = 0; i < array.length(); i++) {
                    JSONObject obj = (JSONObject)array.get(i);
                    Product product = ProductDAO.getProduct(obj.getInt("id"));
                    int quantity = obj.getInt("quantity");
                    
                    cartRows.add(new CartRow(0, null, product, quantity));
                }
            }   
        }
        
        req.setAttribute("cart", cart);
        req.setAttribute("cartRows", cartRows);
    }
        
        
    public String findCookie(Cookie[] cookie){     
        for(Cookie coo:cookie){
            if(coo.getName().equals("cart")){
                return coo.getValue();
            }
        }
        return null;
    }
}
