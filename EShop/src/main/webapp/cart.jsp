<%-- 
    Document   : admin
    Created on : 8-mag-2019, 12.11.44
    Author     : gianni.benigni
--%>

<%@page import="com.main.eshop.model.User"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="/api/LoadCart"/>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Odiazon</title>
        
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">        
        <link rel="stylesheet" href="css/my-style.css" />
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
        
        <script src="js/animate-arrow.js"></script>
    </head>
    <body>
        
    <!-- Navigation -->
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
                        <a class="nav-link" href="#"><i class="fas fa-shopping-cart"></i><span>Carrello</span></a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    
    <section>        
        <br>
        <div class="container">
            <div class="card">
                <div class="card-header">
                    <h2>Carrello</h2>
                </div>
                <div class="card-body">            
                    <div class="table-responsive">
                        <table class="table product-table">
                            <!-- Table head -->
                            <thead class="mdb-color lighten-5">
                              <tr>
                                <th></th>
                                <th class="font-weight-bold">
                                  <strong>Product</strong>
                                </th>
                                <th></th>
                                <th class="font-weight-bold">
                                  <strong>Price</strong>
                                </th>
                                <th class="font-weight-bold">
                                  <strong>Quantity</strong>
                                </th>
                                <th class="font-weight-bold">
                                  <strong>Amount</strong>
                                </th>
                                <th></th>
                              </tr>
                            </thead>
                            <!-- /.Table head -->

                            <!-- Table body -->
                            <tbody>

                                
                                <c:forEach items="${prodotti}" var="prodotto">
                                    <tr>
                                      <th scope="row">
                                        <img src="${prodotto.getProduct().getImages()[0]}" alt="" class="img-fluid z-depth-0">
                                      </th>
                                      <td>
                                        <h5 class="mt-3">
                                          <strong>${prodotto.getProduct().getName()}</strong>
                                        </h5>
                                        <p class="text-muted">${prodotto.getProduct().getBrand().getName()}</p>
                                      </td>
                                      <td></td>
                                      <td></td>
                                      <td>${prodotto.getProduct().getPrice()} €</td>
                                      <td>
                                        <input type="number" value="${prodotto.getQuantity()}" aria-label="Search" class="form-control" style="width: 100px">
                                      </td>
                                      <td class="font-weight-bold">
                                        <strong>${prodotto.getProduct().getPrice() * prodotto.getQuantity()} €</strong>
                                      </td>
                                      <td>
                                        <button type="button" class="btn btn-sm btn-primary" data-toggle="tooltip" data-placement="top"
                                          title="Remove item">X</button>
                                      </td>
                                    </tr>
                              </c:forEach>
                              <!-- /.First row -->


                            </tbody>
                            <!-- /.Table body -->

                    </table>

    </div>
    <!-- /.Shopping Cart table -->
                </div>
            </div>
        </div>    
    </section>
    
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
</html>
