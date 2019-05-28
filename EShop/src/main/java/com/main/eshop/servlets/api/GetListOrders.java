/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.main.eshop.servlets.api;

import com.main.eshop.dao.OrderDAO;
import com.main.eshop.model.OrderHead;
import com.main.eshop.model.OrderRow;
import com.main.eshop.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author eugenio.ferrari
 */
public class GetListOrders extends HttpServlet {
    
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);        
        
        if(session != null && session.getAttribute("currentUser") != null){ 
            User user = (User)session.getAttribute("currentUser");
            ArrayList<OrderHead> list = OrderDAO.getListOrderHeads(user);
            
            request.setAttribute("orders", list);
            return;
        }  
        
        response.sendError(401, "Unathorized");        
    }

}
