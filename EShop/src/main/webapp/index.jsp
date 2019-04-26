<%@ page import="com.main.eshop.model.User"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>EShop</h1>
        <a href="login.jsp">login form</a>
        <hr>
        <%
        User currentUser = (User)session.getAttribute("currentUser");
        
        if(currentUser != null){
            out.println("<p>Utente Loggato:</p><br>Name: " + currentUser.getName());            
            out.println("<br>Cognome: " + currentUser.getSurname());            
            out.println("<br>Email: " + currentUser.getEmail());            
        }
        %>        
    </body>
</html>
