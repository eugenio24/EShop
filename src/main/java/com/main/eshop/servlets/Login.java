
package com.main.eshop.servlets;

import com.main.eshop.dao.UserDAO;
import com.main.eshop.util.enums.LoginResult;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Eugenio
 */
public class Login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
        throws ServletException, IOException {
        
        String username = req.getParameter("username");
        String psw = req.getParameter("psw");
        
        if(UserDAO.authenticateNativeUser(username, psw) == LoginResult.VALID){
            HttpSession session = req.getSession();       
            session.setAttribute("currentUser", UserDAO.getNativeUserFromUsername(username)); 
            resp.sendRedirect("index.jsp"); 
        }else{
            req.setAttribute("error", "invalid-login");            
            req.getRequestDispatcher("login.jsp").include(req, resp);
        }
    }
}
