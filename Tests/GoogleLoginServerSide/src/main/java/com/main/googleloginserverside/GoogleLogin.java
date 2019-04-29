/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.main.googleloginserverside;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Eugenio
 */
public class GoogleLogin extends HttpServlet {

    /**
     * Handles the HTTP <code>POST</code> method.
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
        
        if(request.getHeader("X-Requested-With") == null){
            // Without the `X-Requested-With` header, this request could be forged. Aborts.
        }                
        
        
        // Exchange auth code for access token        
        GoogleTokenResponse tokenResponse = new GoogleAuthorizationCodeTokenRequest(new NetHttpTransport(), 
                                                                                    JacksonFactory.getDefaultInstance(), 
                                                                                    "https://www.googleapis.com/oauth2/v4/token",
                                                                                    "480786958905-9apnlcit3b9uuahh45t38b8h5gidt1rl.apps.googleusercontent.com",
                                                                                    "CRwUz92alusDJxHohLS9BIaq",
                                                                                    authCode,
                                                                                    "postmessage").execute();            
       
        String accessToken = tokenResponse.getAccessToken();
        
        // Use access token to call API
        GoogleCredential credential = new GoogleCredential().setAccessToken(accessToken);
        
        // Get profile info from ID token
        GoogleIdToken idToken = tokenResponse.parseIdToken();
        GoogleIdToken.Payload payload = idToken.getPayload();
        String userId = payload.getSubject();  // Use this value as a key to identify a user.
        String email = payload.getEmail();
        boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
        String name = (String) payload.get("name");
        String pictureUrl = (String) payload.get("picture");
        String locale = (String) payload.get("locale");
        String familyName = (String) payload.get("family_name");
        String givenName = (String) payload.get("given_name");
        
        System.out.println("prova");
    }

}
