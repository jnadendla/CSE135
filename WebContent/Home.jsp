<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
String userName = (String)session.getAttribute("userName");
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
</head>
<body>
    <p>
        <font size="6">
            Home
        </font>
    </p> 
    <p style="color: green;">
        <font size="3">
            Hello
        </font>
        <font size="3">
            <% out.print(userName); %>
        </font>
    </p>
    <a name="categories" href="JavaScript:window.location='Categories.jsp';">Categories</a>
    <a name="products" href="JavaScript:window.location='Products.jsp';">Products</a>
    <a name="browse" href="JavaScript:window.location='ProductBrowser.jsp';">Browse Products</a>
    <a name="cart" href="JavaScript:window.location='ShoppingCart.jsp';">Shopping Cart</a>
</body>
</html>
