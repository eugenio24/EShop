<%-- 
    Document   : admin
    Created on : 8-mag-2019, 12.11.44
    Author     : eugenio.ferrari
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">


<jsp:include page="/api/GetListProductCategories" />

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Odiazon</title>
        
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">        
        <link rel="stylesheet" href="css/my-style.css" />
        
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
    
    <section>        
        <br>
        <div class="container">
            <div class="card">
                <div class="card-header">
                    <h2>Inserimento Prodotti</h2>
                </div>
                <div class="card-body">            
                    <form method="POST" action="">
                        <div class="form-group row">
                            <label class="col-sm-2 col-form-label" for="productName">Nome Prodotto</label>
                            <div class="col-sm-10">
                                <input type="text" name="name" class="form-control" id="productName" placeholder="Nome Prodotto">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-sm-2 col-form-label" for="productDesc">Descrizione</label>
                            <div class="col-sm-10">
                                <textarea type="text" name="desc" class="form-control" id="productDesc" placeholder="Descrizione Prodotto" rows="4"></textarea>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-sm-2 col-form-label" for="priceName">Prezzo Prodotto</label>
                            <div class="input-group col-sm-10">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">€</span>
                                </div>
                                <input step="0.1" name="price" class="form-control currency" id="priceDesc" placeholder="Prezzo Prodotto" type="number" />
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-sm-2 col-form-label" for="productCat">Categoria Prodotto</label>
                            <div class="col-sm-10">
                                <select class="form-control" id="productCat">

                                </select>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-sm-2 col-form-label" for="productBrand">Marca Prodotto</label>
                            <div class="col-sm-10">
                                <select class="form-control" id="productBrand">

                                </select>
                            </div>
                        </div>
                        <button type="submit" class="btn btn-secondary btn-lg btn-block">Inserisci Prodotto</button>
                    </form>
                </div>
            </div>
        </div>
        <br> 
        <div class="container">
            <div class="card">
                <div class="card-header">
                    <h2>Inserimento Categorie Prodotti</h2>
                </div>
                <div class="card-body">
                    <form method="POST" action="">
                        <div class="form-group row">
                            <label class="col-sm-2 col-form-label" for="categoryName">Nome Categoria</label>
                            <div class="col-sm-10">
                                <input type="text" name="name" class="form-control" id="categoryName" placeholder="Nome Categoria">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-sm-2 col-form-label" for="categoryMain">Categoria Principale</label>
                            <div class="col-sm-10">
                                <select class="form-control" id="categoryMain">
                                    <option value="isMain">Seleziona categoria principale se necessario</option>
                                    
                                </select>
                            </div>
                        </div>
                        <button type="submit" class="btn btn-secondary btn-lg btn-block">Inserisci Categoria</button>
                    </form>
                </div>
            </div>
        </div>
        <br>
        <div class="container">
            <div class="card">
                <div class="card-header">
                    <h2>Inserimento Marche</h2>
                </div>
                <div class="card-body">
                    <form method="POST" action="InsertBrand">
                        <div class="form-group row">
                            <label class="col-sm-2 col-form-label" for="brandName">Nome Marca</label>
                            <div class="col-sm-10">
                                <input type="text" name="name" class="form-control" id="brandName" placeholder="Nome Marca" required>
                            </div>
                        </div>                    
                        <button type="submit" class="btn btn-secondary btn-lg btn-block">Inserisci Marca</button>
                        <%  if(request.getAttribute("errorBrand") != null){  %>                            
                            <div class="alert alert-danger mt-3" role="alert">
                                    ${errorBrand}
                            </div>                            
                        <%   }
                            if(request.getAttribute("successBrand") != null){  %>                            
                            <div class="alert alert-success mt-3" role="alert">
                                Marca inserita correttamente
                            </div>                            
                        <%   }   %>
                    </form>
                </div>
            </div>
        </div>     
        <br>
    </section>
    
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
</html>
