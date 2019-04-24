<%-- 
    Document   : index
    Created on : 24-apr-2019, 9.46.16
    Author     : Eugenio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="https://apis.google.com/js/platform.js" async defer></script>

<meta name="google-signin-scope" content="profile email">
<meta name="google-signin-client_id" content="480786958905-op964noete7459djt21nub8gj0c0iuo5.apps.googleusercontent.com">

<title>Servlet OAuth example</title>
</head>
<body>
	<h2>Servlet OAuth example</h2>
	<br>
	<div class="g-signin2" data-onsuccess="onSignIn"></div>
        
        <hr>

        <a href="#" onclick="signOut();">Sign out</a>
        <script>
          function signOut() {
            var auth2 = gapi.auth2.getAuthInstance();
            auth2.signOut().then(function () {
              console.log('User signed out.');
            });
          }
        </script>
  <script>
        
        function onSignIn(googleUser) {
            var profile = googleUser.getBasicProfile();
            console.log('ID: ' + profile.getId());
            console.log('Name: ' + profile.getName());
            console.log('Image URL: ' + profile.getImageUrl());
            console.log('Email: ' + profile.getEmail());
            console.log('id_token: ' + googleUser.getAuthResponse().id_token);

            var id_token = googleUser.getAuthResponse().id_token;

            $.post("Login",
              {
                token: id_token
              },
              function(data, status){
                alert("Data: " + data + "\nStatus: " + status);
              });
        }

   </script>
</body>
</html>