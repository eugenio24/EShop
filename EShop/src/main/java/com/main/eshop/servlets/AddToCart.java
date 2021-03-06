/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.main.eshop.servlets;

import com.main.eshop.dao.CartDAO;
import com.main.eshop.dao.ProductDAO;
import com.main.eshop.model.Cart;
import com.main.eshop.model.CartRow;
import com.main.eshop.model.User;
import java.io.IOException;
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
public class AddToCart extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {            
        
        int id = Integer.parseInt(req.getParameter("idProduct")); 
        int quantity = Integer.parseInt(req.getParameter("quantity"));              
        
        HttpSession session = req.getSession(false);        
        
        if(session != null && session.getAttribute("currentUser") != null){            
            User user = (User)session.getAttribute("currentUser");
            Cart cart = CartDAO.getCartFormUserId(user.getId());
            
            CartRow row = CartDAO.getCartRowFromProduct(cart, id);
            
            if(row != null){ 
                CartDAO.incrementQuantityRow(row, quantity);
            }else{                
                CartDAO.insertItemToCart(cart.getId(), id, quantity);
            }
            
          
        }else{
            String cookie = findCookie(req.getCookies());
            JSONObject jsonCookie;

            if(cookie != null){ 
                jsonCookie = new JSONObject(cookie);

                insertProduct(jsonCookie.getJSONArray("products"), id, quantity);

                Cookie temp = new Cookie("cart", jsonCookie.toString());
                temp.setMaxAge(-1);
                res.addCookie(temp); 
            }else{
                jsonCookie = new JSONObject();

                JSONObject product = new JSONObject();
                product.put("id", id);
                product.put("quantity", quantity);

                JSONArray products = new JSONArray();            
                products.put(product);

                jsonCookie.put("products", products);
                Cookie temp = new Cookie("cart", jsonCookie.toString());
                temp.setMaxAge(-1);
                res.addCookie(temp);
            }  
        }
        
        res.sendRedirect("index.jsp");
    }        
    
    public void insertProduct(JSONArray array, int id, int quantity){
        for (int i = 0; i < array.length(); i++) {
            JSONObject obj = (JSONObject)array.get(i);
            if(obj.getInt("id") == id){
                obj.put("quantity", obj.getInt("quantity")+quantity);
                return;
            }
        }
        
        JSONObject product = new JSONObject();
        product.put("id", id);
        product.put("quantity", quantity);
        
        array.put(product);
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
