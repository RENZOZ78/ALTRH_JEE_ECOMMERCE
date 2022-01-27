<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<p>page d'erreur</p>
<% if(request.getAttribute("msg")!=null){ %>
<p> <%=request.getAttribute("msg") %></p>
<% }else{ %>
<p> <%=request.getAttribute("msgErreurTel") %></p>
<% } %>

</body>
</html>