<%-- 
    Document   : WelcomePage
    Created on : 24-apr-2019, 10.39.24
    Author     : Eugenio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Welcome</title>
</head>
<body>


<!--  forward to index page for login if user info is not in session  -->
<% if (session.getAttribute("userName") == null) {%>
<jsp:forward page="/index.jsp"/>
<% } %>

<h3>Welcome  ${userName}</h3>

</body>
</html>