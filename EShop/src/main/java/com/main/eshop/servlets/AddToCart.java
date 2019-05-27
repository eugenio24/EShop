/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.main.eshop.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Gianni
 */
public class AddToCart extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {            
        
        String id = req.getParameter("idProduct");      
        int quantity = Integer.parseInt(req.getParameter("quantity"));              
        
        String cookie = findCookie(req.getCookies());
        JSONObject jsonCookie = new JSONObject();
        
        if(cookie != null){ 
            jsonCookie = new JSONObject(cookie);
            
            insertProduct(jsonCookie.getJSONArray("products"));
            
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
        res.sendRedirect("index.jsp");
    }
    
    public void insertProduct(JSONArray array){
        for (int i = 0; i < array.length(); i++) {
            if(((JSONObject)array.get(i)).getInt("id")){
                
            }
        }
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
