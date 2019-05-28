<%-- 
    Document   : order
    Created on : 27-mag-2019, 21.39.48
    Author     : Eugenio
--%>

<%@page import="com.main.eshop.model.User"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="true" %>

<%
// controllo che solo gli admin possano accedere
if(session.getAttribute("currentUser") == null){
    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Non sei autorizzato per questa pagina");
    return;
}
%>

<jsp:include page="/api/GetListOrders" />

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Odiazon</title>        
                
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">        
        <link rel="stylesheet" href="css/my-style.css" />
        <link rel="stylesheet" href="css/ui.css" />
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>  
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
                    <li class="nav-item">
                        <a class="nav-link" href="index.jsp">Home</a>
                    </li>
                    <%
                    if(session.getAttribute("currentUser") != null){ %>                        
                    <li class="nav-item">
                        <a class="nav-link" href="list-orders.jsp">Lista Ordini</a>
                    </li>
                    <% }%>
                    <%
                    if(session.getAttribute("currentUser") != null && ((User)session.getAttribute("currentUser")).isIsAdmin()){ %>  
                    <li class="nav-item">
                        <a class="nav-link" href="admin.jsp">Admin Page</a>
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
                        <a class="nav-link" href="cart.jsp"><i class="fas fa-shopping-cart"></i><span>Carrello</span></a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
                    
        <section>
            <br>
            <div class="container">            

                <table class="table">
                  <thead class="thead-dark">
                    <tr>
                      <th scope="col">Numero Ordine</th>
                      <th scope="col">Data Ordine</th>
                      <th scope="col">Prezzo Totale</th>
                      <th scope="col">Indirizzo Consegna</th>
                    </tr>
                  </thead>
                  <tbody>
                    <c:forEach items="${orders}" var="item">               
                        <tr>
                            <th scope="row">${item.orderNumber}</td>
                            <td>${item.orderDate}</td>
                            <td>${item.totalPrice}</td>
                            <td>${item.shippingAddress}</td>                    
                        </tr>
                    </c:forEach>
                  </tbody>
                </table>
            </div>
            <br>
        </section>
    </body>
</html>
