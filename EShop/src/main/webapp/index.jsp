<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="/api/LoadProducts"/>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Odiazon</title>
        
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">        
        <link rel="stylesheet" href="css/my-style.css" />
        <link rel="stylesheet" href="css/ui.css" />
        
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
                    <li class="nav-item active">
                        <a class="nav-link" href="index.jsp">Home
                            <span class="sr-only">(current)</span>
                        </a>
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

    <div id="carouselExampleControls" class="carousel slide carousel-fade" data-ride="carousel">
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img class="d-block w-100" src="images/banner1.jpg">
                <div class="carousel-caption d-none d-md-block banner-font">
                    <h1>Il negozio per gli appassionati della montagna</h1>
                    <p>Prova subito i nostri prodotti.</p>
                    <div class="arrow bounce">
                        <a class="fa fa-angle-down fa-2x my-arrow-down" href="#products"></a>
                    </div>
                </div>
            </div>
            <div class="carousel-item">
                <img class="d-block w-100" src="images/banner2.jpg">
                <div class="carousel-caption d-none d-md-block banner-font">
                    <h1>Il negozio per gli appassionati della montagna</h1>
                    <p>Prova subito i nostri prodotti.</p>
                    <div class="arrow bounce">
                        <a class="fa fa-angle-down fa-2x my-arrow-down" href="#products"></a>
                    </div>
                </div>
            </div>
            <div class="carousel-item">
                <img class="d-block w-100" src="images/banner3.jpg">
                <div class="carousel-caption d-none d-md-block banner-font">
                    <h1>Il negozio per gli appassionati della montagna</h1>
                    <p>Prova subito i nostri prodotti.</p>
                    <div class="arrow bounce">
                        <a class="fa fa-angle-down fa-2x my-arrow-down" href="#products"></a>
                    </div>
                </div>
            </div>
        </div>
        <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
    
    <div id="products">
        
        <div class="padding-y-sm prova">
            <span style="padding: 35px;">${fn:length(prodotti)} prodotti disponibili</span>	
        </div>
        
        <div class="row margine" id="products">          

            <c:forEach items="${prodotti}" var="prodotto">
                <div class="col-md-3 col-sm-6" style="padding-right: 0px; box-sizing: content-box;">
                    <form name="${prodotto.id}" id="${prodotto.id}" method="POST">
                        <figure class="card card-product">
                             <div class="img-wrap"> <img src="${prodotto.images[0]}"></div>
                               <figcaption class="info-wrap">
                                     <a href="#" class="title">${prodotto.name}</a>
                                        <div class="price-wrap">
                                            <span class="price-new">${prodotto.price} €</span>
                                            <button type="submit" class="btn btn-default ">Aggiungi al Carrello</button>
                                        </div>
                                </figcaption>
                        </figure>
                    </form>
                </div>
                
            </c:forEach>   
            
        </div>
    </div>

    
    
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
</html>
