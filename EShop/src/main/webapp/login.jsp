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
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Login Page</h1>
        
        <form action="Login" method="POST">
            USername: <input type="text" name="username">
            Psw: <input type="password" name="psw">
            <input type="submit" value="Login">
        </form>
        <%
            if(request.getAttribute("error") != null){
                out.println("username o password errati");
            }
        %>
    </body>
</html>
