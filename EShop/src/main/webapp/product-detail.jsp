<%-- 
    Document   : product-detail
    Created on : 24-mag-2019, 19.19.58
    Author     : Eugenio
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>        
        <meta charset="UTF-8">    
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
        <title>Odiazon</title>
        
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">        
        <link rel="stylesheet" href="css/my-style.css" />                      
        
        <link rel="stylesheet" href="css/ui.css" />        
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>                
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
                    <li class="nav-item">
                        <a class="nav-link" href="#">About</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="login.jsp">Log In</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#"><i class="fas fa-shopping-cart"></i>  <span>Carrello</span></a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    
    
    <div class="card">
	<div class="row no-gutters">
            <aside class="col-sm-5 border-right">
                <article class="gallery-wrap"> 
                    <div class="img-big-wrap">
                        <div><img src="${product.images[0]}"></div>
                    </div> 
                    <div class="img-small-wrap">
                        <c:forEach items="${product.images}" var="image">
                            <div class="item-gallery"> <img src="${image}"></div>
                        </c:forEach>
                    </div> 
                </article>
            </aside>
            <aside class="col-sm-7">
                <article class="p-5">
                    <h3 class="title mb-3">${product.name}</h3>
                    <div class="mb-3"> 
                        <var class="price h3 text-warning"> 
                            <span class="currency">€ </span><span class="num">${product.price}</span>
                        </var> 	
                    </div> 
                    <dl>
                        <dt>Descrizione</dt>
                        <dd><p>${product.desc}</p></dd>
                    </dl>
                    <hr>
                    <div class="row">
                        <div class="col-sm-5">
                            <dl class="dlist-inline">
                                <dt>Quantit&agrave;: </dt>
                                <dd> 
                                    <select class="form-control form-control-sm" style="width:70px;">
                                        <option> 1 </option>
                                        <option> 2 </option>
                                        <option> 3 </option>
                                    </select>
                                </dd>
                            </dl> 
                        </div> 
                    </div>
                    <hr>	
                    <a href="#" class="btn  btn-outline-primary"> <i class="fas fa-shopping-cart"></i> Aggiungi al carrello </a>
                </article> 
            </aside> 
	</div> 
    </div> 

    
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

    </body>
</html>
