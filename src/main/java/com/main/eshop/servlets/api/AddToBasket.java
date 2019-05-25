/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.main.eshop.servlets.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Gianni
 */
public class AddToBasket extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {            
        
        String id = req.getParameter("idP"); 
        System.out.println("com.main.eshop.servlets.api.AddToBasket.doPost()*************************************************");
        Cookie[] cookie = req.getCookies();
        
        String tmp = findCookie(cookie);
        
        if(tmp != null){ 
            String content = tmp + "E" + id;
            Cookie temp = new Cookie("basket", content);
            temp.setMaxAge(-1);
            res.addCookie(temp); 
        }else{
            Cookie temp = new Cookie("basket", id);
            temp.setMaxAge(-1);
            res.addCookie(temp);
        }       
        res.sendRedirect("index.jsp");
    }
    
    
    public String findCookie(Cookie[] cookie){     
        for(Cookie coo:cookie){
            if(coo.getName().equals("basket")){
                return coo.getValue();
            }
        }
        return null;
    }

}
