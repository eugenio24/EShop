<%-- 
    Document   : unauthorized
    Created on : 26-mag-2019, 16.17.13
    Author     : Eugenio
--%>

<%@page import="com.main.eshop.model.User"%>
<%@ page session="true" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Eshop</title>
        
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">        
        <link rel="stylesheet" href="css/my-style.css" />        
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark static-top">
            <div class="container">
                <a class="navbar-brand" href="index.jsp">
                    <img src="images/logo-white.png">
                </a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item active">
                            <a class="nav-link" href="index.jsp">Home
                                <span class="sr-only">(current)</span>
                            </a>
                        </li>
                        <%
                        if(session.getAttribute("currentUser") != null && ((User)session.getAttribute("currentUser")).isIsAdmin()){ %>  
                        <li class="nav-item">
                            <a class="nav-link" href="#">Admin Page</a>
                        </li> 
                        <% } %>
                        <li class="nav-item">
                        <%
                        if(session.getAttribute("currentUser") != null){ %>                        
                            <a class="nav-link" href="Logout">Log Out</a>
                        <% }else{ %>                        
                            <a class="nav-link" href="login.jsp">Log In</a>
                        <% } %>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="cart.jsp"><i class="fas fa-shopping-cart"></i>  <span>Carrello</span></a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <div class="container-fluid">
            <h1> 401 – Unauthorized</h1>
            <p>Non sei autorizzato per questa pagina</p>
        </div>              
    </body>
</html>
