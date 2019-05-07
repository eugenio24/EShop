package com.main.eshop.servlets;

import com.main.eshop.dao.UserDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.crypto.bcrypt.BCrypt;
import com.main.eshop.model.User;
import com.main.eshop.util.enums.RegistrationType;
import java.sql.Timestamp;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Gianni
 */
public class Registration extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{              
        
        String username = req.getParameter("username");
        
        if(UserDAO.checkUserFromUsername(username) == false){
            String name = req.getParameter("name");
            name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
            String surname =req.getParameter("surname");
            surname = surname.substring(0, 1).toUpperCase() + surname.substring(1).toLowerCase();
            String email = req.getParameter("email").toLowerCase();
            
            String salt = BCrypt.gensalt();
            String psw = BCrypt.hashpw(req.getParameter("psw"), salt);
            
            User user = new User(email, name, surname, new Timestamp(System.currentTimeMillis()), username, RegistrationType.NATIVE);
            
            if(UserDAO.insertNativeUser(user,salt,psw)){
                HttpSession session = req.getSession();       
                session.setAttribute("currentUser", UserDAO.getNativeUserFromUsername(username)); 
                resp.sendRedirect("index.jsp");
            }    
        }else{
            req.setAttribute("usernameError", "Username already exist");
        }
          
    } 
}
