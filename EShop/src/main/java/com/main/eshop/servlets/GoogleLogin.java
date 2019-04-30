
package com.main.eshop.servlets;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.main.eshop.dao.UserDAO;
import com.main.eshop.model.User;
import com.main.eshop.util.enums.RegistrationType;
import java.io.IOException;
import java.sql.Timestamp;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Eugenio
 */
public class GoogleLogin extends HttpServlet {

    /**
     * Handles the HTTP <code>POST</code> method. For GoogleLogin
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String authCode = request.getParameter("code");               
        
        if(request.getHeader("X-Requested-With") == null || authCode == null){
            // Without the `X-Requested-With` header, this request could be forged. Aborts.
            // todo: Richiesta non valida
            return;
        }                
                
        // Exchange auth code for access token        
        GoogleTokenResponse tokenResponse = new GoogleAuthorizationCodeTokenRequest(new NetHttpTransport(), 
                                                                                    JacksonFactory.getDefaultInstance(), 
                                                                                    "https://www.googleapis.com/oauth2/v4/token",
                                                                                    "480786958905-knf9821bhhm4aanvkjtcb72iuu7kthe3.apps.googleusercontent.com",
                                                                                    "BCkQHQA7u6s_6wLYT1V6qci6",
                                                                                    authCode,
                                                                                    "postmessage").execute();            
       
        String accessToken = tokenResponse.getAccessToken();
        
        // Get profile info from ID token
        GoogleIdToken idToken = tokenResponse.parseIdToken();
        GoogleIdToken.Payload payload = idToken.getPayload();
        
        String userId = payload.getSubject();
        String email = payload.getEmail();              
        String familyName = (String) payload.get("family_name");
        String givenName = (String) payload.get("given_name");
        
        User user = new User(email, givenName, familyName, new Timestamp(System.currentTimeMillis()), userId, RegistrationType.GOOGLE);
        
        if(UserDAO.userExist(user)){
            HttpSession session = request.getSession();
            session.setAttribute("currentUser", user); 
            response.getWriter().write("{ \"success\": true, \"url\": \"index.jsp\" }");            
        }else{
            if(UserDAO.insertGoogleUser(user)){                              
                HttpSession session = request.getSession();       
                session.setAttribute("currentUser", user); 
                response.getWriter().write("{ \"success\": true, \"url\": \"index.jsp\" }");         
            }else{
                // todo: mettere un errore per l'utente                
                
            }
        }
    }

}
