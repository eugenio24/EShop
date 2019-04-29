<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Test GoogleLogin Server Side</title>
        
        <!-- BEGIN Pre-requisites -->
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js">
        </script>
        <script src="https://apis.google.com/js/client:platform.js?onload=start" async defer>
        </script>
        <!-- END Pre-requisites -->
        
        <script>
        function start() {
          gapi.load('auth2', function() {
            auth2 = gapi.auth2.init({
              client_id: '480786958905-9apnlcit3b9uuahh45t38b8h5gidt1rl.apps.googleusercontent.com'
            });
          });
        }
      </script>
    </head>
    <body>
        <button id="signinButton">Sign in with Google</button>
        <script>
          $('#signinButton').click(function() {            
            auth2.grantOfflineAccess().then(signInCallback);
          });
          
          
        function signInCallback(authResult) {
            if (authResult['code']) {
                console.log(authResult['code']);
                // Hide the sign-in button now that the user is authorized, for example:
                //$('#signinButton').attr('style', 'display: none');

                // Send the code to the server
                $.ajax({
                  type: 'POST',
                  url: 'GoogleLogin',
                  // Always include an `X-Requested-With` header in every AJAX request,
                  // to protect against CSRF attacks.
                  headers: {
                    'X-Requested-With': 'XMLHttpRequest'
                  },
                  data: { 
                      'code': authResult['code'] 
                  }
                });
            } else {
                console.log("error");
            }
        }
        </script>
        
        
    </body>
</html>
