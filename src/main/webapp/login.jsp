<%-- 
    Document   : login
    Created on : 26-apr-2019, 13.24.37
    Author     : Eugenio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Odiazon</title>
        
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">        
        <link rel="stylesheet" href="css/my-style.css" />
        <link rel="stylesheet" href="css/login.css" />
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
                       
        <script src="https://apis.google.com/js/client:platform.js?onload=start" async defer></script>
        <script src="js/GoogleLogin.js"></script>
        
        <script src="js/registration.js"></script>
        
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
                    <li class="nav-item active">
                        <a class="nav-link" href="login.jsp">Log In
                        <span class="sr-only">(current)</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="cart.jsp"><i class="fas fa-shopping-cart"></i>  <span>Carrello</span></a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
        
    <div class="form">

        <ul class="tab-group">
          <li class="tab active"><a href="#login">Log In</a></li>
          <li class="tab"><a href="#signup">Sign Up</a></li>
        </ul>

        <div class="tab-content">
            <div id="login">   
                <form action="Login" method="POST">
                    <div class="field-wrap">
                        <input type="text" placeholder="Username" name="username" required autocomplete="off"/>
                    </div>
                    <div class="field-wrap">
                        <input type="password" placeholder="Password" name="psw" required autocomplete="off"/>
                    </div>            

                    <%
                    if(request.getAttribute("error") == "invalid-login"){  %>
                        <div class="alert alert-danger" role="alert">
                            Username o Password errati.
                        </div>
                    <%   }   %>

                    <button type="submit" class="btn button button-block"/>Log In</button>
                    
                    <div class="or-block">
                        <span>or</span>
                    </div>
                                    
                    <button type="button" onclick="btnGoogle_onClick();" class="btn btn-danger btn-block button-block"><i class="fab fa-google"></i>   Sign in with <b>Google</b></button>                    
                </form>
                                
            </div>
                    
          <div id="signup">   
                <h1>Sign Up for Free</h1>

                <form action="Registration" method="POST" onsubmit="return validateForm();">
                    <div class="top-row">
                        <div class="field-wrap">
                            <input type="text" placeholder="Name" name="name" id="name" required autocomplete="off" />
                            <span class="registrationError" id="nameError"></span>
                        </div>
                        <div class="field-wrap">
                            <input type="text" placeholder="Surname" name="surname" id="surname" required autocomplete="off"/>
                            <span class="registrationError" id="surnameError"></span>
                        </div>
                    </div>
                    
                    <div class="field-wrap">
                        <input type="text" placeholder="Username" name="username" id="username" required autocomplete="off"/>
                        <span class="registrationError" id="usernameError"></span>
                    </div>

                    <div class="field-wrap">
                        <input type="email" placeholder="Email Address" name="email" id="email" required autocomplete="off"/>
                    </div>

                    <div class="top-row">
                        <div class="field-wrap">
                            <input type="password" placeholder="Password" name="psw" id="psw" required autocomplete="off" />
                            <span class="registrationError" id="passwordError"></span>
                        </div>
                        <div class="field-wrap">
                            <input type="password" placeholder="Confirm Password" name="cPsw" id="cPsw" required autocomplete="off"/>
                        </div>
                    </div>

                    <button type="submit" class="button button-block"/>Get Started</button>
                    
                    <div class="or-block">
                        <span>or</span>
                    </div>
                                    
                    <button type="button" onclick="btnGoogle_onClick();" class="btn btn-danger btn-block button-block"><i class="fab fa-google"></i>   Sign in with <b>Google</b></button>                    
               
                </form>
            </div>
        </div>
    </div>
    
    <script src="js/login.js"></script>   
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

    </body>
</html>
