/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.apache.ApacheHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.client.testing.json.MockJsonFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
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
    protected void doPost (HttpServletRequest req, HttpServletResponse resp)
                        throws ServletException, IOException {

        resp.setContentType("text/html");

        try {
            String idTokenStr = req.getParameter("token");
            System.out.println(idTokenStr);
            
            ApacheHttpTransport transport = new ApacheHttpTransport();
            JsonFactory jsonFactory = new JacksonFactory();

            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
            // Specify the CLIENT_ID of the app that accesses the backend:
            .setAudience(Collections.singletonList("480786958905-op964noete7459djt21nub8gj0c0iuo5.apps.googleusercontent.com"))
            // Or, if multiple clients access the backend:
            //.setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
            .build();

        // (Receive idTokenString by HTTPS POST)

        GoogleIdToken idToken = verifier.verify(idTokenStr);
        if (idToken != null) {
          Payload payload = idToken.getPayload();

          // Print user identifier
          String userId = payload.getSubject();
          System.out.println("User ID: " + userId);

          // Get profile information from payload
          String email = payload.getEmail();
          boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
          String name = (String) payload.get("name");
          String pictureUrl = (String) payload.get("picture");
          String locale = (String) payload.get("locale");
          String familyName = (String) payload.get("family_name");
          String givenName = (String) payload.get("given_name");

          

        } else {
          System.out.println("Invalid ID token.");
        }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
